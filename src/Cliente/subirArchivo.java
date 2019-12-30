package Cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class subirArchivo extends JFrame {

	private JTextField txtRuta;

	/**
	 * Launch the application.
	 */


	public subirArchivo(Socket s, DataInputStream dis, DataOutputStream dos) {
		// initComponents();
		setBounds(100, 100, 450, 200);
		this.setLocationRelativeTo(this);

		getContentPane().setLayout(null);
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));

		JLabel lblNewLabel = new JLabel("Archivo a subir");
		lblNewLabel.setBounds(10, 11, 109, 14);
		getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 36, 411, 104);
		getContentPane().add(panel);
		panel.setLayout(null);

		txtRuta = new JTextField();
		txtRuta.setBounds(10, 29, 288, 20);
		panel.add(txtRuta);
		txtRuta.setColumns(10);

		JButton btnAbrir = new JButton("Explorar");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					abrir();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnAbrir.setBounds(312, 28, 89, 23);
		panel.add(btnAbrir);

		JButton btnSubir = new JButton("Subir");
		btnSubir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				subir(s, dis, dos);
				dispose();
			}
		});
		btnSubir.setBounds(312, 61, 89, 23);
		panel.add(btnSubir);

	}

	public void abrir() {
		JFileChooser jf = new JFileChooser();
		jf.showOpenDialog(this);
		File f = jf.getSelectedFile();
		if (f != null) {

			txtRuta.setText(f.getAbsolutePath());
		}
	}

	public void subir(Socket s, DataInputStream dis, DataOutputStream dos) {
		String directorio = txtRuta.getText();
		System.out.println("Subiendo " + directorio + "...");
		try {
			System.out.println("PUT" + directorio);
			dos.writeUTF("PUT" + directorio);
			dos.flush();
			System.out.println("Enviado al servidor");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}