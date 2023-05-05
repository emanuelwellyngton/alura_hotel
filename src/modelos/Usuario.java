package modelos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {
	
	private String login;
	private String senha;
	private boolean autenticado;
	
	public Usuario(String login, String senha) throws SQLException, AutenticationExeption {
		Connection cn = new ConnectionFactory().getConnectionMySql();
		var st = cn.prepareStatement(("SELECT * FROM usuarios WHERE login = ?;"));
		st.setString(1, login);
		st.execute();
		var rs = st.getResultSet();
		
		System.out.println(rs.getMetaData().getColumnCount());
		
		if (rs.getMetaData().getColumnCount() > 0 && rs.first()) {
			if (rs.getString("senha").equals(senha)) {
				this.autenticado = true;
				this.login = login;
				this.senha = senha;
			} else {
				throw new AutenticationExeption();
			}
		} else {
			throw new AutenticationExeption();
		}
		
	}
	
	public boolean getStatus() {
		return this.autenticado;
	}

}
