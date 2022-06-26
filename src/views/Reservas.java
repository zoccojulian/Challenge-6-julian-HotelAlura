package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import modelo.Precio;
import modelo.Reserva;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import com.jgoodies.forms.factories.DefaultComponentFactory;

public class Reservas extends JFrame {

	private JPanel contentPane;
	private JTextField txtValor;
	private Precio precioDia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservas frame = new Reservas();
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
	public Reservas() {
		this.precioDia = new Precio();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Reservas.class.getResource("/imagenes/calendario.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(0, 0, 900, 502);
		contentPane.add(panel);
		panel.setLayout(null);

		JDateChooser txtFechaE = new JDateChooser();
		txtFechaE.setBounds(88, 166, 235, 33);
		panel.add(txtFechaE);

		JLabel lblNewLabel_1 = new JLabel("Fecha de Check In");
		lblNewLabel_1.setBounds(88, 142, 133, 14);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Fecha de Check Out");
		lblNewLabel_1_1.setBounds(88, 210, 133, 14);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1);

		JDateChooser txtFechaS = new JDateChooser();
		txtFechaS.setBounds(88, 234, 235, 33);
		txtFechaS.getCalendarButton().setBackground(Color.WHITE);
		panel.add(txtFechaS);

		txtValor = new JTextField();
		txtValor.setBounds(88, 303, 235, 33);
		txtValor.setEnabled(false);
		panel.add(txtValor);
		txtValor.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Valor de la Reserva");
		lblNewLabel_1_1_1.setBounds(88, 278, 133, 14);
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1);

		JComboBox txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(88, 373, 235, 33);
		txtFormaPago.setFont(new Font("Arial", Font.PLAIN, 14));
		txtFormaPago.setModel(new DefaultComboBoxModel(
				new String[] { "Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo" }));
		panel.add(txtFormaPago);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_1_1_1.setBounds(88, 347, 133, 24);
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_4 = new JLabel("Sistema de Reservas");
		lblNewLabel_4.setBounds(108, 93, 199, 42);
		lblNewLabel_4.setForeground(new Color(65, 105, 225));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 20));
		panel.add(lblNewLabel_4);
		
		JButton btnSalir = new JButton("");
		btnSalir.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/cerrar-sesion 32-px.png")));
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setBounds(815, 436, 54, 41);
		panel.add(btnSalir);
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
		});

		JButton btnReservar = new JButton("Continuar");
		btnReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				System.out.println(txtFechaE.getDate());
//				LocalDate nuevaFecha = txtFechaE.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//				System.out.println(nuevaFecha);

				try {
					
					if(valorDeEstadia(txtFechaE, txtFechaS) <= 0) {
						throw new NullPointerException();
					}
					Reserva reserva = new Reserva(
							txtFechaE.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
							txtFechaS.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
							txtFormaPago.getSelectedItem().toString(),
							valorDeEstadia(txtFechaE, txtFechaS));
					
					RegistroHuesped huesped = new RegistroHuesped(reserva);
					huesped.setVisible(true);
					dispose();
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacío y el Check-in no puede ser posterior al Check-out");
				}

			}
		});

		btnReservar.setForeground(Color.WHITE);
		btnReservar.setBounds(183, 436, 140, 33);
		btnReservar.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/calendario.png")));
		btnReservar.setBackground(new Color(65, 105, 225));
		btnReservar.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(btnReservar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(399, 0, 491, 502);
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, -16, 500, 539);
		panel_1.add(lblNewLabel);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/reservas-img-2.png")));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Reservas.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(15, 6, 104, 107);
		panel.add(lblNewLabel_2);
		
		
		
		txtFechaE.addPropertyChangeListener(evt -> {
			if (txtFechaS.getDate() != null) {
				double precioEstadia = valorDeEstadia(txtFechaE, txtFechaS);
				cargarValorEstadia(txtValor, precioEstadia);

			}
		});

		txtFechaS.addPropertyChangeListener(evt -> {
			if (txtFechaE.getDate() != null) {
				double precioEstadia = valorDeEstadia(txtFechaE, txtFechaS);
				cargarValorEstadia(txtValor, precioEstadia);
			}
		});

	}

	private void cargarValorEstadia(JTextField textoValor, Double precio) {
		if (precio >= 0) {
			textoValor.setText(Double.toString(precio));
		} else {
			textoValor.setText("Fecha Check-in posterior a Check-out");
		}

	}

	private double valorDeEstadia(JDateChooser txtFechaE, JDateChooser txtFechaS) {
		LocalDate fechaEntrada = txtFechaE.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate fechasalida = txtFechaS.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return precioDia.valorEstadia(fechaEntrada.until(fechasalida, ChronoUnit.DAYS));

	}
}
