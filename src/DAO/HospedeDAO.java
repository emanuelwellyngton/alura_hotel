package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos.Hospede;

public class HospedeDAO {

	private Connection connection;

	public HospedeDAO(Connection cn) {
		this.connection = cn;
	}

	public void cadastrar(Hospede hospede) {
		String sql = "INSERT INTO hospedes(cpf, nome_commpleto, data_nascimento, nacionalidade, telefone)"
				+ " VALUES(?, ?, ?, ?, ?);";
		try (PreparedStatement st = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			st.setString(1, hospede.getCpf());
			st.setString(2, hospede.getNome());
			st.setDate(3, hospede.getDataNascimento());
			st.setString(4, hospede.getNascionalidade());
			st.setString(5, hospede.getTelefone());

			st.execute();
			ResultSet keys = st.getGeneratedKeys();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Hospede buscar(String cpf) {
		try (PreparedStatement st = this.connection.prepareStatement("SELECT * FROM hospedes WHERE cpf = ?")) {
			st.setString(1, cpf);
			st.execute();

			try (ResultSet rs = st.getResultSet()) {
				if (rs.next()) {
					return new Hospede(rs.getString("cpf"), rs.getString("nome_commpleto"),
							rs.getDate("data_nascimento"), rs.getString("nacionalidade"), rs.getString("telefone"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<Hospede> listarTodos() {

		ArrayList<Hospede> hospedes = new ArrayList<>();

		try (var st = connection.prepareStatement("SELECT * FROM hospedes;")) {
			st.execute();
			ResultSet rs = st.getResultSet();

			while (rs.next()) {
				Hospede hospede = new Hospede(rs.getString("cpf"), rs.getString("nome_commpleto"),
						rs.getDate("data_nascimento"), rs.getString("nacionalidade"), rs.getString("telefone"));
				hospedes.add(hospede);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hospedes;
	}
	
	public void deletar(String cpf) {
		try(var st = connection.prepareStatement("DELETE FROM hospedes WHERE cpf = ?;")) {
			st.setString(1, cpf);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int buscarIdUltimaReserva(Hospede hospede) {
		try(var st = connection.prepareStatement("SELECT id FROM reservas WHERE id_hospede=? ORDER BY data_entrada DESC "
				+ "LIMIT 1;")) {
			st.setString(1, hospede.getCpf());
			if (st.execute()) {
				try(ResultSet rs = st.getResultSet()) {
					if(rs.next()) {
						return rs.getInt("id");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int editar(Hospede hospede) {
		try(var st = connection.prepareStatement("UPDATE hospedes SET nome_commpleto=?, data_nascimento=?, "
				+ "nacionalidade=?, telefone=? WHERE cpf=?;")) {
			st.setString(1, hospede.getNome());
			st.setDate(2, hospede.getDataNascimento());
			st.setString(3, hospede.getNascionalidade());
			st.setString(4, hospede.getTelefone());
			st.setString(5, hospede.getCpf());
			
			return st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}
