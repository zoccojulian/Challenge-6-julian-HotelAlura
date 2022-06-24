package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.HuespedController;
import controller.ReservaController;
import modelo.Huesped;
import modelo.Reserva;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modeloHuesped;
	private DefaultTableModel modeloReserva;
	private HuespedController huespedController;
	private ReservaController reservaController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
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
	public Busqueda() {

		this.huespedController = new HuespedController();
		this.reservaController = new ReservaController();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		txtBuscar = new JTextField();
		txtBuscar.setBounds(647, 85, 158, 31);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/lupa2.png")));
		btnBuscar.setBounds(815, 75, 54, 41);
		contentPane.add(btnBuscar);

		JButton btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/editar-texto.png")));
		btnEditar.setBackground(SystemColor.menu);
		btnEditar.setBounds(587, 416, 54, 41);
		contentPane.add(btnEditar);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Búsqueda");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_4.setBounds(155, 42, 258, 42);
		contentPane.add(lblNewLabel_4);

		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 416, 54, 41);
		contentPane.add(btnSalir);

		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBounds(10, 127, 874, 265);
		contentPane.add(panel);

		tbHuespedes = new Tabla();
		tbHuespedes.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/persona.png")), tbHuespedes,
				null);

		tbReservas = new Tabla();
		tbReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/calendario.png")), tbReservas,
				null);

		JButton btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/deletar.png")));
		btnEliminar.setBackground(SystemColor.menu);
		btnEliminar.setBounds(651, 416, 54, 41);
		contentPane.add(btnEliminar);

		JButton btnCancelar = new JButton("");
		btnCancelar.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cancelar.png")));
		btnCancelar.setBackground(SystemColor.menu);
		btnCancelar.setBounds(713, 416, 54, 41);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(25, 10, 104, 107);
		contentPane.add(lblNewLabel_2);
		setResizable(false);

		// creo el modelo de la tabla huesped, y cargo los datos.
		modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
		String[] listaColumnasHuesped = { "ID", "APELLIDO", "NOMBRE", "FECHA DE NAC.", "NACIONALIDAD", "TELEFONO",
				"ID_RESERVA" };
		configurarTabla(modeloHuesped, listaColumnasHuesped);
		cargarTablaHuesped();

		// creo el modelo de la tabla reservas, y cargo los datos.
		modeloReserva = (DefaultTableModel) tbReservas.getModel();
		String[] listaColumnasReservas = { "ID", "INGRESO", "EGRESO", "PAGO", "PRECIO", "HUESPED", "HUESPED_ID" };
		configurarTabla(modeloReserva, listaColumnasReservas);
		cargarTablaReserva();

		// acciones del formulario

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				limpiarTablas();
				cargarTablaHuesped();
				cargarTablaReserva();

			}

		});

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();

			}

		});

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTablas();
				cargarTablaHuesped();
				cargarTablaReserva();
			}
		});

	}

	private boolean tieneFilaElegida(JTable tabla) {
		return tabla.getSelectedRowCount() == 0 || tabla.getSelectedColumnCount() == 0;
	}

	private void eliminar() {

		if (tbHuespedes.isVisible()) {

			if (tieneFilaElegida(tbHuespedes) || tbHuespedes.getSelectedRow() == 0) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			} else {

				int filaSeleccionada = tbHuespedes.getSelectedRow();
				int idHuesped = Integer.parseInt(tbHuespedes.getValueAt(filaSeleccionada, 0).toString());
				int idReserva = Integer.parseInt(
						tbHuespedes.getValueAt(filaSeleccionada, tbHuespedes.getColumnCount() - 1).toString());

				System.out.println(idReserva);
				System.out.println(idHuesped);

				if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar?") == 0) {
					reservaController.eliminar(idReserva);
					huespedController.eliminar(idHuesped);
					modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
					limpiarTablas();
					cargarTablaReserva();
					cargarTablaHuesped();

				}
			}
		}

		if (tbReservas.isVisible()) {

			if (tieneFilaElegida(tbReservas) || tbReservas.getSelectedRow() == 0) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			} else {

				int filaSeleccionada = tbReservas.getSelectedRow();
				int idHuesped = Integer
						.parseInt(tbReservas.getValueAt(filaSeleccionada, tbReservas.getColumnCount() - 1).toString());
				int idReserva = Integer.parseInt(tbReservas.getValueAt(filaSeleccionada, 0).toString());

				if (JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar?") == 0) {
					reservaController.eliminar(idReserva);
					huespedController.eliminar(idHuesped);
					modeloReserva.removeRow(tbReservas.getSelectedRow());
					limpiarTablas();
					cargarTablaReserva();
					cargarTablaHuesped();

				}
			}
		}

	}

	private void modificar() {
		if (tbHuespedes.isVisible()) {

			if (tieneFilaElegida(tbHuespedes) || tbHuespedes.getSelectedRow() == 0) {
				JOptionPane.showMessageDialog(this, "Por favor, elije un item");
				return;
			} else {
				int filaSeleccionada = tbHuespedes.getSelectedRow();
				
				int fila = tbHuespedes.getSelectedRow();
				
				Integer id = Integer.valueOf(tbHuespedes.getValueAt(fila, 0).toString());
				String apellido = tbHuespedes.getValueAt(fila, 1).toString();
				String nombre = tbHuespedes.getValueAt(fila, 2).toString();
				LocalDate nacimiento;
				try {
					nacimiento = LocalDate.parse(tbHuespedes.getValueAt(fila, 3).toString());
				}catch(DateTimeParseException e) {
					JOptionPane.showMessageDialog(this, "La fecha modificada no es una fecha válida");
					limpiarTablas();
					cargarTablaHuesped();
					return;
				}
				String nacionalidad = tbHuespedes.getValueAt(fila, 4).toString();
				String telefono = tbHuespedes.getValueAt(fila, 5).toString();
				Integer id_reserva = Integer.valueOf(tbHuespedes.getValueAt(fila, 6).toString());
				Huesped huespedModificacion = new Huesped(id, nombre, apellido, nacimiento, nacionalidad, telefono,  id_reserva);
				huespedController.modificar(huespedModificacion);
				JOptionPane.showMessageDialog(this, "Se modificó con éxito");
				
			}
		}

	}

	private void configurarTabla(DefaultTableModel modelo, String[] lista) {
		for (String columna : lista) {
			modelo.addColumn(columna);

		}

	}

	private void cargarTablaHuesped() {
		// CREA LOS NOMBRES DE LAS COLUMNAS
		String[] columnas = new String[modeloHuesped.getColumnCount()];
		for (int i = 0; i < modeloHuesped.getColumnCount(); i++) {
			columnas[i] = modeloHuesped.getColumnName(i);
		}
		modeloHuesped.addRow(columnas);
		List<Huesped> huespedes = this.huespedController.listar();

		huespedes.forEach(huesped -> modeloHuesped.addRow(new Object[] { huesped.getId(), huesped.getApellido(),
				huesped.getNombre(), huesped.getNacimiento().toString(), huesped.getNacionalidad(),
				huesped.getTelefono(), huesped.getId_reserva() }));
	}

	private void cargarTablaReserva() {

		// CREA LOS NOMBRES DE LAS COLUMNAS
		String[] columnas = new String[modeloReserva.getColumnCount()];
		for (int i = 0; i < modeloReserva.getColumnCount(); i++) {
			columnas[i] = modeloReserva.getColumnName(i);
		}
		modeloReserva.addRow(columnas);

		List<Reserva> reservas = this.reservaController.listar();

		reservas.forEach(reserva -> modeloReserva.addRow(new Object[] { reserva.getId(),
				reserva.getIngreso().toString(), reserva.getEgreso().toString(), reserva.getPago(), reserva.getPrecio(),
				reserva.getHuesped().getApellido() + " " + reserva.getHuesped().getNombre(),
				reserva.getHuesped().getId() }));
	}

	private void limpiarTablas() {
		modeloHuesped.getDataVector().clear();
		modeloReserva.getDataVector().clear();
	}

	private class Tabla extends JTable {

		@Override
		public boolean isCellEditable(int row, int column) {

			if (row == 0) {
				return false;
			}

			if (column == 0) {
				return false;
			}

			if (column == (this.getColumnCount() - 1)) {
				return false;
			}

			return true;
		}

	}
}