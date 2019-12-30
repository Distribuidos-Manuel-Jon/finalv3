package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

import Cliente.Base;

public class AtenderPeticion implements Runnable {

	Socket cliente;

	public AtenderPeticion(Socket c) {
		super();
		this.cliente = c;
		System.out.println("En la dirección: " + c.getRemoteSocketAddress());
        System.out.println("En el puerto: " + c.getPort());
	}

	public void run() {
		String usR = null;
		String paR = null;
		String comando = null;
		String dir;
		
		boolean correcto = false;
		File fich;
		File[] listado;
		Base b = new Base();

		try{DataInputStream dis = new DataInputStream(cliente.getInputStream());
				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
				ObjectOutputStream obout = new ObjectOutputStream(cliente.getOutputStream());

			System.out.println("Entrando al servidor");
			usR = dis.readUTF();
			System.out.println("Usuario recivido " + usR);
			paR = dis.readUTF();
			System.out.println(paR);

			try {
				if (b.login(usR, paR) == 1) {
					correcto = true;
					dos.writeBoolean(correcto);
					dos.flush();
				} else {
					dos.writeBoolean(correcto);
					dos.flush();
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}

			fich = new File(usR);

			listado = fich.listFiles();
			obout.writeObject(listado);
			obout.flush();
			System.out.println("Objeto enviado, espearando orden");
			
			
			comando = dis.readUTF();
		while (!comando.equals("END")) {
			
			System.out.println("antes del read comando");
			
			System.out.println(comando);
			
				if (comando.startsWith("GET")) {
					
					byte[] buff = new byte[1024 * 32];

					dir = comando.substring(3);
					System.out.println("Enviando " + dir);
					File f = new File(usR + "\\" + dir);
					System.out.println(f.getAbsolutePath() + " " + f.length() + f.getName());
					try (FileInputStream fis = new FileInputStream(f)) {
						
						int leidos;
						System.out.println(cliente.getLocalPort() + "  " +cliente.getLocalSocketAddress());
						if(f.exists()) {
							System.out.println("Existe el fich");
						}
						dos.writeLong(f.length());
						
						while ((leidos = fis.read(buff)) != -1) {
							System.out.println("a1");
							dos.write(buff, 0, leidos);
							System.out.println("a2");
						}
						dos.flush();
						System.out.println("flush");
//						long cantEnviada =0L;
//						int leido;
//						
//						while(cantEnviada < f.length()) {
//							leido=fis.read();
//							dos.write(leido);
//							cantEnviada++;
//						}
						
					}
					//comando = dis.readUTF();
					
				}
				
				if (comando.startsWith("PUT")) {
					
					byte[] buff = new byte[1024 * 32];
					dir = comando.substring(3);
					System.out.println("Subiendo " + dir);
					int aux2 = comando.lastIndexOf("\\");
					String aux = comando.substring(aux2);
					//System.out.println(aux);
					File f = new File(dir);
					//System.out.println(f.getAbsolutePath());

					// System.out.println(usR+"\\"+dir);
					try (FileInputStream fis = new FileInputStream(f);
							FileOutputStream fos = new FileOutputStream(usR + "\\" + aux)) {
						int leidos;
						while ((leidos = fis.read(buff)) != -1) {
							fos.write(buff, 0, leidos);
							//System.out.println("aa");
						}
					}
					//comando = dis.readUTF();
				}
				
				if (comando.startsWith("List")) {

					listado = fich.listFiles();
					obout.writeObject(listado);
					obout.flush();
					System.out.println("Objeto enviado, espearando orden");
					//comando = dis.readUTF();
				}
				
				if (comando.startsWith("END")) {
					
					dis.close();
					dos.close();
					obout.close();
				
				}
				//comando = dis.readUTF();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}