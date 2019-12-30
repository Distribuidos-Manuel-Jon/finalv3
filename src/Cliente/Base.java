package Cliente;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Base {


	public int login(String usuario, String contrasena) throws SQLException {
		int esta = 0;
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?serverTimezone=UTC", "root", "0321");

			Statement stm = con.createStatement();

			String sql = "SELECT l.* FROM login l WHERE l.usuario=  '" + usuario + "'  and l.contrasena= '" + contrasena+ "'";

			ResultSet res = stm.executeQuery(sql);
			if (res.next()) {
				esta = 1;
			}
			res.close();
			stm.close();

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

		return esta;

	}

}