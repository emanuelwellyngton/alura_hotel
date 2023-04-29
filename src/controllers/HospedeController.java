package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import DAO.HospedeDAO;
import logica.ConnectionFactory;
import logica.Hospede;

public class HospedeController {

	private HospedeDAO hospedeDAO;

	public HospedeController() {
		try {
			Connection connection = new ConnectionFactory().getConnectionMySql();
			this.hospedeDAO = new HospedeDAO(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrar(Hospede hospede) {
		this.hospedeDAO.cadastrar(hospede);
	}

	public Hospede buscar(String cpf) {
		return this.hospedeDAO.buscar(cpf);
	}
	
	public List<Hospede> listarTodos() {
		return this.hospedeDAO.listarTodos();
	}
	
	public void deletar(String cpf) {
		this.hospedeDAO.deletar(cpf);
	}
	
	public int buscarIdUltimaReserva(Hospede hospede) {
		return this.hospedeDAO.buscarIdUltimaReserva(hospede);
	}

}
