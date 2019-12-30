package Cliente;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Abrir extends JFrame{
	
	
	public Abrir(String ruta) {
		setTitle("Abrir");
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));

		
		getContentPane().setLayout(null);
		setMinimumSize(new Dimension(500,300));
		JLabel lblNewLabel = new JLabel("Deseas abrir el archivo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblNewLabel.setBounds(10, 11, 364, 42);
		getContentPane().add(lblNewLabel);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));
		setLocationRelativeTo(null);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				abrirArchivo(ruta);
			}
		});
		btnAceptar.setBounds(24, 81, 89, 23);
		getContentPane().add(btnAceptar);
		
		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		btnRechazar.setBounds(147, 81, 89, 23);
		getContentPane().add(btnRechazar);
		
	}

	public void abrirExplorador(String path) {  //abre el explorador en el path indicado
		
		File file = new File ("path");
		Desktop desktop = Desktop.getDesktop();
		try {
			if(file.exists()) {
			desktop.open(file);   
			}else
				System.err.println("No se puede abrir el fichero o no existe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void abrirArchivo(String path) {
		
		try {
		    Process builder = Runtime.getRuntime().exec("cmd /c start " + path);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}