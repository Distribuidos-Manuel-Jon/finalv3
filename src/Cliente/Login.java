package Cliente;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Login extends JFrame {

	private File[] listado;
	//a
	private List list;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try(Socket cliente = new Socket("localhost",6666);
//						DataInputStream dis = new DataInputStream(cliente.getInputStream());
//						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
//								ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream())) {
//					a frame = new a(cliente,obin,dis,dos);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public Login(Socket c,ObjectInputStream obin, DataInputStream dis, DataOutputStream dos) {
		setTitle("Archivos");
		//setIconImage(new ImageIcon(getClass().getResource("/Cliente/icono.png")).getImage());
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));
		setBounds(100, 100, 400, 300);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Archivos");
		lblNewLabel.setBounds(10, 11, 76, 14);
		getContentPane().add(lblNewLabel);
		setMinimumSize(new Dimension(500,300));

		list = new List();
		list.setBounds(10, 31, 350, 161);
		getContentPane().add(list);

		JButton btnNewButton = new JButton("Descargar");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int aux = list.getSelectedIndex();
				System.out.println(aux);
				descargar(aux,c, dis, dos);
				String directorio = list.getItem(aux);
				System.out.println(directorio);
				Abrir a = new Abrir(directorio);
				a.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(250, 217, 109, 23);
		getContentPane().add(btnNewButton);

		JButton btnSubir = new JButton("Subir archivos");
		btnSubir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subirArchivo md = new subirArchivo(dis,dos);
				md.setVisible(true);
			}
		});
		btnSubir.setBounds(12, 217, 125, 23);
		getContentPane().add(btnSubir);
		
		JButton btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				refrescar(dos, obin);
			}
		});
		btnRefresh.setBounds(371, 31, 103, 23);
		getContentPane().add(btnRefresh);

		listar(obin);
	}

	public void descargar(int a,Socket c, DataInputStream dis, DataOutputStream dos) {

		String directorio = list.getItem(a);
		System.out.println("descargando " + directorio + "...");
		try {
			
			File f = new File(directorio);
			FileOutputStream fos = new FileOutputStream(f);
			
			System.out.println("GET" + directorio);
			dos.writeUTF("GET" + directorio);
			dos.flush();

//			long tam = dis.readLong();
//			System.out.println(tam);
			byte[] buf = new byte[1024 * 32];
			int leidos;
			System.out.println(f.getAbsolutePath() + "  " + f.length());
			while ((leidos = dis.read(buf)) != -1) {
				fos.write(buf, 0, leidos);
			}
			if (f.exists()) {
				System.out.println(f.getName() + " descargado");
				System.out.println(f.getAbsolutePath() + "  " + f.length());
			}

			fos.close();
//			long cantidad = leidos;
//			while (cantidad <= tam) {
//				
//
//				fos.write(buf, 0, leidos);
//
//				leidos = dis.read(buf);
//				cantidad=cantidad+leidos;
//				
//			}
//			long cantidadR =0L;
//			while(cantidadR < tam) {
//				int leido = dis.read();
//				fos.write(leido);
//				cantidadR++;
//			}
			
//			dos.flush();
//			if (f.exists()) {
//				System.out.println(f.getName() + " descargado");
//				System.out.println(f.getAbsolutePath() + "  " + f.length());
//			}
//			dos.writeUTF("List");
//			dos.flush();

//			fos.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	
	public void listar(ObjectInputStream obin) {
		try {
			list.clear();
			listado = (File[]) obin.readObject();
			if (listado.length == 0) {
				list.add("El directorio esta vacio ");
			} else {

				for (File f : listado) {
					list.add(f.getName());
					//System.out.println(f.getName());
				}
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void refrescar(DataOutputStream dos,ObjectInputStream obin) {
		try {
			dos.writeUTF("List");
			dos.flush();

			listar(obin);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
}