package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.joda.time.DateTime;
import org.joda.time.Days;

import controllers.HospedeController;

public class Reserva {
	
	private int id;
	private Date dataEntrada;
	private Date dataSaida;
	private double valor;
	private Object pagamento;
	private Hospede hospede;
	
	public Reserva(Date entrada, Date saida, double valor, Object pagamento) {
		this.dataEntrada = entrada;
		this.dataSaida = saida;
		this.valor = valor;
		this.pagamento = pagamento;
	}
	
	public Reserva(int id, Date entrada, Date saida, double valor, Object pagamento, String cpf) {
		this.id = id;
		this.dataEntrada = entrada;
		this.dataSaida = saida;
		this.valor = valor;
		this.pagamento = pagamento;
		this.hospede = new HospedeController().buscar(cpf);
	}
	
	public int getId() {
		return this.id;
	}
	
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Date getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}

	public Object getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	@Override
	public String toString() {
		return "Reserva [dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", valor=" + valor + ", pagamento="
				+ pagamento + ", hospede=" + hospede + "]";
	}
	
	public double calculaValor(Date entrada, Date saida) {
    	DateTime inicio = new DateTime(entrada);
    	DateTime fim = new DateTime(saida);
    	
    	return Days.daysBetween(inicio, fim).getDays() * 60;
    }

}
