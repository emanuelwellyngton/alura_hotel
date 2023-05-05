package modelos;

import java.util.Date;

import controllers.HospedeController;

public class Hospede {

	private String cpf;
	private String nome;
	private java.sql.Date dataNascimento;
	private String nascionalidade;
	private String telefone;

	public Hospede(String cpf, String nome, java.sql.Date dataNascimento, String nascionalidade, String teleforne) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.nascionalidade = nascionalidade;
		this.telefone = teleforne;

	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return this.cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public java.sql.Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(java.sql.Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNascionalidade() {
		return nascionalidade;
	}

	public void setNascionalidade(String nascionalidade) {
		this.nascionalidade = nascionalidade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getIdUltimaReserva() {
		return new HospedeController().buscarIdUltimaReserva(this);
	}

}
