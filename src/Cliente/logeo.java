package Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class logeo extends JFrame {

	private JPanel contentPane;
	public JTextField txtUsuario;
	public JPasswordField jpassClave;
	private String clave;
	private String usuario;
	private boolean correcto1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					logeo frame = new logeo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	
	public logeo() {
		setTitle("Inicio de Sesion");
		//setIconImage(new ImageIcon(getClass().getResource("/Cliente/icono.png")).getImage());
		setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Cliente/icono.png")));
		Base b = new Base();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(40, 35, 54, 16);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(40, 64, 116, 22);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Clave");
		lblNewLabel_1.setBounds(40, 99, 56, 16);
		contentPane.add(lblNewLabel_1);
		
		jpassClave = new JPasswordField();
		jpassClave.setBounds(40, 128, 116, 22);
		contentPane.add(jpassClave);
		
		
		JButton btnIngresar = new JButton("Iniciar Sesion");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char claveChar[] = jpassClave.getPassword();
				clave = new String(claveChar);
				usuario = txtUsuario.getText();
				boolean correcto = false;
				try {
				
				Socket cliente = new Socket("localhost",6666);
				DataInputStream dis = new DataInputStream(cliente.getInputStream());
				DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
						ObjectInputStream obin = new ObjectInputStream(cliente.getInputStream());
				
					dos.writeUTF(usuario);
					dos.flush();
					System.out.println(usuario);				
					dos.writeUTF(clave);
					dos.flush();
					correcto = dis.readBoolean();
					
					
					if(correcto) {
						Login md = new Login(cliente,obin,dis,dos);
						md.setVisible(true);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta");
					}
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnIngresar.setBounds(40, 167, 116, 39);
		contentPane.add(btnIngresar);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(170, 243, 0, -194);
		contentPane.add(separator);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registrar r = new Registrar();
				r.setVisible(true);
			}
		});
		btnRegistrar.setBounds(40, 219, 116, 22);
		contentPane.add(btnRegistrar);
	}
	
}