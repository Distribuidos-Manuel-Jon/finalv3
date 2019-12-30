package Cliente;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class Registrar extends JFrame{
	
	
	private JTextField txtUsuario;
	private JPasswordField jpassClave;
	private String clave;
	private String usuario;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try(Socket cliente = new Socket("localhost",6666);
//						DataInputStream dis = new DataInputStream(cliente.getInputStream());
//						DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
//								ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream())) {
//					p frame = new p();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public Registrar() {
		setTitle("Crear nuevo usuario");
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));
		setMinimumSize(new Dimension(300, 280));
		MetodoRegistrar r = new MetodoRegistrar();
		getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setBounds(23, 35, 62, 14);
		getContentPane().add(lblUsuario);
		
		JLabel lblclave = new JLabel("Clave");
		lblclave.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblclave.setBounds(23, 91, 62, 14);
		getContentPane().add(lblclave);
		
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(99, 32, 127, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(99, 88, 127, 20);
		getContentPane().add(jpassClave);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char claveChar[] = jpassClave.getPassword();
				clave = new String(claveChar);
				usuario = txtUsuario.getText();
				
					try {
						if(r.registrar(usuario, clave) == 1) {
							File f = new File(usuario);
							f.mkdir();
							File fich = new File(f.getAbsolutePath(),"Bienvenido.txt");
							fich.createNewFile();
							JOptionPane.showMessageDialog(null, "Usuario Registrado");
							setVisible(false);
						}  else {
							JOptionPane.showMessageDialog(null, "Usuario o contrase�a ya cogidos");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
				}
				
				
		});
		btnRegistrar.setBounds(117, 153, 89, 23);
		getContentPane().add(btnRegistrar);
		
		
	}
	//a
}