package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.Reserva;

public class ReservaDAO {
	private Connection connection;

	public ReservaDAO(Connection cn) {
		this.connection = cn;
	}

	public void registra(Reserva reserva) {
		try (var st = connection
				.prepareStatement("INSERT INTO reservas(data_entrada, data_saida, valor, forma_pagamento, id_hospede) "
						+ "VALUES(?, ?, ?, ?, ?);");) {
			st.setDate(1, reserva.getDataEntrada());
			st.setDate(2, reserva.getDataSaida());
			st.setDouble(3, reserva.getValor());
			st.setString(4, reserva.getPagamento().toString());
			st.setString(5, reserva.getHospede().getCpf());
			st.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Reserva> listarTodos() {
		
		ArrayList<Reserva> reservas = new ArrayList<>();
		
		try (var st = connection.prepareStatement("SELECT * FROM reservas;")) {
			st.execute();
			ResultSet rs = st.getResultSet();
			
			while(rs.next()) {
				Reserva reserva = new Reserva(rs.getInt("id"), rs.getDate("data_entrada"), rs.getDate("data_saida"),
						rs.getDouble("valor"), rs.getObject("forma_pagamento"), rs.getString("id_hospede"));
				reservas.add(reserva);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservas;
	}
	
	public void deletar(int id) {
		try(var st = connection.prepareStatement("DELETE FROM reservas WHERE id = ?;")) {
			st.setInt(1, id);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deletarReservasHospede(String cpf) {
		try(var st = connection.prepareStatement("DELETE FROM reservas WHERE id_hospede = ?;")) {
			st.setString(1, cpf);
			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Reserva> buscarPorId(int id) {
		ArrayList<Reserva> reservas = new ArrayList<>();
		try(var st = this.connection.prepareStatement("SELECT * FROM reservas WHERE id = ?")) {
			st.setInt(1, id);
			
			if(st.execute()) {
				try(ResultSet rs = st.getResultSet()) {
					while(rs.next()) {
						Reserva reserva = new Reserva(rs.getInt("id") ,rs.getDate("data_entrada"), rs.getDate("data_saida"),
								rs.getDouble("valor"), rs.getObject("forma_pagamento"), rs.getString("id_hospede"));
						reservas.add(reserva);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservas;
	}
	
	public int editar(Reserva reserva) {
		try(var st = this.connection.prepareStatement("UPDATE reservas SET data_entrada=?, data_saida=?, valor=?, forma_pagamento=? "
				+ "WHERE id=?;")) {
			st.setDate(1, reserva.getDataEntrada());
			st.setDate(2, reserva.getDataSaida());
			st.setDouble(3, reserva.getValor());
			st.setString(4, (String) reserva.getPagamento());
			st.setInt(5, reserva.getId());
			
			return st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
