package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class Server {

	public static void main(String[] args) {

		ExecutorService pool = Executors.newCachedThreadPool();
		
		//JOptionPane.showMessageDialog(null,"Servidor Arrancado");

		try {ServerSocket server = new ServerSocket(6666);

			while (true) {
				
					Socket cliente = server.accept();
					pool.execute(new AtenderPeticion(cliente));
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}

	}

}