package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnectionMySql() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/alura_hotel",
				"alura_hotel_app_user", "Ew@101199");
	}
	
}
