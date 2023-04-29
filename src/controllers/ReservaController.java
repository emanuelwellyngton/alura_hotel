package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.ReservaDAO;
import logica.ConnectionFactory;
import logica.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		Connection connection;
		try {
			connection = new ConnectionFactory().getConnectionMySql();
			this.reservaDAO = new ReservaDAO(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrar(Reserva reserva) {
		this.reservaDAO.registra(reserva);
	}
	
	public List<Reserva> listarTodos() {
		return this.reservaDAO.listarTodos();
	}
	
	public void deletar(int id) {
		this.reservaDAO.deletar(id);
	}
	
	public void deletarReservasHospede(String cpf) {
		this.reservaDAO.deletarReservasHospede(cpf);
	}
	
	public List<Reserva> buscarPorId(int id) {
		return this.reservaDAO.buscarPorId(id);
	}

}
