package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;

public class VerificarLog {

	String usR = null;
	String paR = null;
	Base b = new Base();
	boolean correcto = false;

	public VerificarLog(DataInputStream dis, DataOutputStream dos) {
		System.out.println("Entrando al servidor");
		try {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public String getUsr() {
		return this.usR;
	}

}
