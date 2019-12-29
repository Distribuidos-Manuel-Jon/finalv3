package Cliente;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

public class Pool {

	public DataSource dataSource;
	
	private static final String URL=getPropiedad("url");
	private static final String USR=getPropiedad("user");
	private static final String PWD=getPropiedad("password");
	
	public static String getPropiedad(String clave) {
		Properties p=new Properties();
		try {
			p.load(new java.io.FileInputStream("src/conexion.properties"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return (String) p.get(clave);
	}
	
	public Pool() {
		inici();
	}
	
	public void inici() {
	
		Connection con = null;
	    try {
	      con =  DriverManager.getConnection(URL,USR,PWD);
	      
	    } catch (SQLException e) {
		      e.printStackTrace();
		    } finally {
		    	try {
		      if (con != null) {
		          con.close();
		      }
		        } catch (SQLException ex) {
		          ex.printStackTrace();
		        }
		      
		    }
	}
	
	
	
}