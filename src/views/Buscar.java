package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllers.HospedeController;
import controllers.ReservaController;
import modelos.ColunaNaoEditavel;
import modelos.Hospede;
import modelos.Reserva;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHospedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloHospedes;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Buscar frame = new Buscar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Buscar() {
		this.setTitle("Busca - Alura Hotel");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
		lblTitulo.setForeground(new Color(12, 138, 199));
		lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblTitulo.setBounds(331, 62, 280, 42);
		contentPane.add(lblTitulo);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
				
		tbReservas = new JTable();
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbReservas.setName("Reservas");
		modelo = new ColunaNaoEditavel(tbReservas);
		tbReservas.setModel(modelo);
		modelo.addColumn("Número da reserva");
		modelo.addColumn("Data Check In");
		modelo.addColumn("Data Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		modelo.addColumn("Hóspede");
		atualizaReservas(modelo, new ReservaController().listarTodos());
		JScrollPane scroll_table = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
		scroll_table.setVisible(true);
		
		
		tbHospedes = new JTable();
		tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		tbHospedes.setName("Hóspedes");
		modeloHospedes = new ColunaNaoEditavel(tbHospedes);
		tbHospedes.setModel(modeloHospedes);
		modeloHospedes.addColumn("Numero de Hóspede");
		modeloHospedes.addColumn("Nome");
		modeloHospedes.addColumn("Data de Nascimento");
		modeloHospedes.addColumn("Nacionalidade");
		modeloHospedes.addColumn("Telefone");
		modeloHospedes.addColumn("Última Reserva");
		
		atualizaHospedes(modeloHospedes, new HospedeController().listarTodos());
		JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
		panel.addTab("Hóspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
		scroll_tableHuespedes.setVisible(true);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.isShowing()) {
					if(txtBuscar.getText().isBlank()) {
						List<Reserva> todasReservas = new ReservaController().listarTodos();
						atualizaReservas(modelo, todasReservas);
					} else {
						List<Reserva> resultadoBusca = new ReservaController().buscarPorId(Integer.parseInt(txtBuscar.getText()));
						if(!resultadoBusca.isEmpty()) {
							atualizaReservas(modelo, resultadoBusca);
						} else {
							new JOptionPane().showMessageDialog(null, "Nenhuma reserva encontrada");
						}
					}
				}else {
					if(txtBuscar.getText().isBlank()) {
						List<Hospede> hospedes = new HospedeController().listarTodos();
						atualizaHospedes(modeloHospedes, hospedes);
					} else {
						List<Hospede> resultadoBusca = new ArrayList();
						Hospede hospede = new HospedeController().buscar(txtBuscar.getText());
						if(hospede != null) {
							resultadoBusca.add(hospede);
							atualizaHospedes(modeloHospedes ,resultadoBusca);
						} else {
							new JOptionPane().showMessageDialog(null, "Nenhum hóspede encontrado");
						}
					}
				}
			}
		});
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.isShowing()) {
					if(tbReservas.getSelectedRow() < 0)
						new JOptionPane().showMessageDialog(null, "Selecione uma reserva para editar");
					else {
						int row = tbReservas.getSelectedRow();
						int id = (int) tbReservas.getValueAt(row, 0);
						ReservaController reservaController = new ReservaController();
						Reserva reserva = reservaController.buscarPorId(id).get(0);
						if(reserva != null) {
							reserva.setDataEntrada(util.Date.parseToDateSql(tbReservas.getValueAt(row, 1).toString()));
							reserva.setDataSaida(util.Date.parseToDateSql(tbReservas.getValueAt(row, 2).toString()));
							double valor = reserva.calculaValor(reserva.getDataEntrada(), reserva.getDataSaida());
							reserva.setValor(valor);
							reserva.setPagamento((String) tbReservas.getValueAt(row, 4));
							reserva.setHospede(new HospedeController().buscar(tbReservas.getValueAt(row, 5).toString()));
							
							if(reservaController.editar(reserva) != 0) {
								new JOptionPane().showMessageDialog(null, "Reserva " + reserva.getId()
									+ " atualizada com sucesso");
								atualizaReservas(modelo, reservaController.listarTodos());
							} else
								new JOptionPane().showMessageDialog(null, "Não foi possível realizar a edição. Tente novalente.");
						} else {
							new JOptionPane().showMessageDialog(null, "Não foi possível realizar a edição. Tente novalente.");
						}
					}
				} else if(tbHospedes.isShowing()) {
					if(tbHospedes.getSelectedRow() < 0)
						new JOptionPane().showMessageDialog(null, "Selecione uma reserva para editar");
					else {
						int row = tbHospedes.getSelectedRow();
						String idHospede = (String) tbHospedes.getValueAt(row, 0);
						HospedeController hospedeController = new HospedeController();
						Hospede hospede = hospedeController.buscar(idHospede);
						if(hospede != null) {
							hospede.setNome((String) tbHospedes.getValueAt(row, 1));
							hospede.setDataNascimento(util.Date.parseToDateSql(tbHospedes.getValueAt(row, 2).toString()));
							hospede.setNascionalidade((String) tbHospedes.getValueAt(row, 3));
							hospede.setTelefone((String) tbHospedes.getValueAt(row, 4));
							
							if(hospedeController.editar(hospede) > 0) {
								new JOptionPane().showMessageDialog(null, "Hospede " + hospede.getCpf()
								+ " atualizado com sucesso");
								atualizaHospedes(modeloHospedes, hospedeController.listarTodos());
							} else
								new JOptionPane().showMessageDialog(null, "Não foi possível realizar a edição. Tente novalente.");
						} else
							new JOptionPane().showMessageDialog(null, "Não foi possível realizar a edição. Tente novalente.");
					}
				}
			}
		});
		
		JPanel btnDeletar = new JPanel();
		btnDeletar.setLayout(null);
		btnDeletar.setBackground(new Color(12, 138, 199));
		btnDeletar.setBounds(767, 508, 122, 35);
		btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnDeletar);
		btnDeletar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbReservas.isShowing()) {
					int row = tbReservas.getSelectedRow();
					int col = tbReservas.getSelectedColumn();
					int id = (int) tbReservas.getValueAt(row, 0);
					int confirmacao = JOptionPane.showConfirmDialog(null, "DESEJA EXCLUIR A RESERVA Nº " + id + "?");
					if(confirmacao == JOptionPane.OK_OPTION) {
						ReservaController reservaController = new ReservaController();
						reservaController.deletar(id);
						atualizaReservas(modelo, reservaController.listarTodos());
						
					}
				}
				
				if(tbHospedes.isShowing()) {
					int row = tbHospedes.getSelectedRow();
					int col = tbHospedes.getSelectedColumn();
					String cpf = (String) tbHospedes.getValueAt(row, 0);
					int confirmacao = JOptionPane.showConfirmDialog(null, "PARA EXCLUIR O HÓSPEDE, SERÁ NECESSÁRIO"
							+ "EXCLUIR SUAS RESERVAS. DESEJA CONTINUAR?");
					if(confirmacao == JOptionPane.OK_OPTION) {
						HospedeController hospedeController = new HospedeController();
						hospedeController.deletar(cpf);
						ReservaController reservaController = new ReservaController();
						reservaController.deletarReservasHospede(cpf);
						atualizaHospedes(modeloHospedes, hospedeController.listarTodos());
						atualizaReservas(modelo, reservaController.listarTodos());
					}
				}
			}
		});
		
		JLabel lblExcluir = new JLabel("DELETAR");
		lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcluir.setForeground(Color.WHITE);
		lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblExcluir.setBounds(0, 0, 122, 35);
		btnDeletar.add(lblExcluir);
		setResizable(false);
	}
	
	//Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"	
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
	    }
	    
	    public void atualizaReservas(DefaultTableModel model, List<Reserva> reservas) {
	    	model.setRowCount(0);
	    	reservas.forEach(reserva -> {
				Vector<Object> vector = new Vector();
				vector.add(reserva.getId());
				vector.add(reserva.getDataEntrada());
				vector.add(reserva.getDataSaida());
				vector.add("R$" + reserva.getValor());
				vector.add(reserva.getPagamento());
				vector.add(reserva.getHospede().getCpf());
				model.addRow(vector);
			});
	    }
	    
	    public void atualizaHospedes(DefaultTableModel model, List<Hospede> hospedes) {
	    	model.setRowCount(0);
	    	hospedes.forEach(hospede -> {
				Vector<Object> vector = new Vector();
				vector.add(hospede.getCpf());
				vector.add(hospede.getNome());
				vector.add(hospede.getDataNascimento());
				vector.add(hospede.getNascionalidade());
				vector.add(hospede.getTelefone());
				
				int id = hospede.getIdUltimaReserva();
				if(id != 0)
				vector.add(id);
				
				model.addRow(vector);
			});
	    }
	    
}
