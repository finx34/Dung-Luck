package ventanas;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class Ventana {

	static final String nombreLabel1 = "Texto1";
	static final String nombreLabel2 = "Texto2";
	static final String nombreVentana = "Ventana";
	static final String nombreBoton1 = "Boton1";
	static final String nombreBoton2 = "Boton2";
	static final int anchoVentana = 300;
	static final int altoVentana = 400;
	static final boolean isResizable = false;
	static final boolean isText1Editable = false;
	static final boolean isText2Editable = true;
	static Ventana instance;

	private JFrame ventana;
	private JLabel label1;
	private JTextField text1;
	private JLabel label2;
	private JTextField text2;
	private JButton button1;
	private JButton button2;
	private Container contentPane;
	private JTextArea estado;
	private Runnable accion1 = null;
	private Runnable accion2 = null;

	public static void setAccion1(Runnable accion, String textoBoton) {
		instance.accion1 = accion;
		instance.button1.setText(textoBoton);
	}

	public static void setAccion2(Runnable accion, String textoBoton) {
		instance.accion2 = accion;
		instance.button2.setText(textoBoton);
	}

	public static void setEstado(String txt) {

		Calendar calendar = Calendar.getInstance();

		int intH = calendar.get(Calendar.HOUR_OF_DAY);
		int intM = calendar.get(Calendar.MINUTE);
		int intS = calendar.get(Calendar.SECOND);

		String h = String.format("%02d", intH);
		String m = String.format("%02d", intM);
		String s = String.format("%02d", intS);

		instance.estado.append(h + ":" + m + ":" + s + " " + txt + "\n");
	}

	public static String getText1() {
		return instance.text1.getText();
	}

	public static String getText2() {
		return instance.text2.getText();
	}

	public static void delete() {
		instance.ventana.dispose();

	}

	public static void setTextLabel1(String textLabel1) {
		instance.label1.setText(textLabel1);
	}

	public static void setTextLabel2(String textLabel2) {
		instance.label2.setText(textLabel2);
	}

	public static void setTitulo(String nombreVentana) {
		instance.ventana.setTitle(nombreVentana);
	}

	public static void launch(String nombreVentana, String textLabel1,
			String textLabel2) {
		launch();
		setTitulo(nombreVentana);
		setTextLabel1(textLabel1);
		setTextLabel2(textLabel2);

	}

	public static void setText1(String text1, boolean isEditable) {
		instance.text1.setEditable(isEditable);
		instance.text1.setText(text1);
	}

	public static void setText2(String text2, boolean isEditable) {
		instance.text2.setEditable(isEditable);
		instance.text2.setText(text2);
	}

	public static void launch() {
		instance = new Ventana();
		instance.ventana = new JFrame(nombreVentana);
		instance.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instance.contentPane = instance.ventana.getContentPane();

		SpringLayout layout = new SpringLayout();
		instance.contentPane.setLayout(layout);

		instance.label1 = new JLabel(nombreLabel1);
		instance.text1 = new JTextField(15);

		instance.label2 = new JLabel(nombreLabel2);
		instance.text2 = new JTextField(15);

		instance.contentPane.add(instance.label1);
		instance.contentPane.add(instance.text1);
		layout.putConstraint(SpringLayout.WEST, instance.label1, 10,
				SpringLayout.WEST, instance.contentPane);
		layout.putConstraint(SpringLayout.NORTH, instance.label1, 25,
				SpringLayout.NORTH, instance.contentPane);
		layout.putConstraint(SpringLayout.NORTH, instance.text1, 25,
				SpringLayout.NORTH, instance.contentPane);
		layout.putConstraint(SpringLayout.WEST, instance.text1, 20,
				SpringLayout.EAST, instance.label1);
		instance.text2.setText("");

		instance.contentPane.add(instance.label2);
		instance.contentPane.add(instance.text2);
		layout.putConstraint(SpringLayout.WEST, instance.label2, 10,
				SpringLayout.WEST, instance.contentPane);
		layout.putConstraint(SpringLayout.NORTH, instance.label2, 70,
				SpringLayout.NORTH, instance.contentPane);
		layout.putConstraint(SpringLayout.NORTH, instance.text2, 70,
				SpringLayout.NORTH, instance.contentPane);
		layout.putConstraint(SpringLayout.WEST, instance.text2, 20,
				SpringLayout.EAST, instance.label2);

		instance.text1.setEditable(isText1Editable);
		instance.text2.setEditable(isText2Editable);
		instance.text1.setText("");
		instance.text2.setText("");

		instance.button1 = new JButton(nombreBoton1);
		instance.contentPane.add(instance.button1);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, instance.button1,
				0, SpringLayout.HORIZONTAL_CENTER, instance.contentPane);
		layout.putConstraint(SpringLayout.NORTH, instance.button1, 120,
				SpringLayout.NORTH, instance.contentPane);
		
		instance.button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				System.out.println("Accion 1 activada");
				if (instance.accion1 != null) {
					instance.accion1.run();
				}

			}
		});

		instance.button2 = new JButton(nombreBoton2);
		instance.contentPane.add(instance.button2);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, instance.button2,
				0, SpringLayout.HORIZONTAL_CENTER, instance.contentPane);
		layout.putConstraint(SpringLayout.NORTH, instance.button2, 150,
				SpringLayout.NORTH, instance.contentPane);

		instance.button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e2) {
				System.out.println("Accion 2 activada");
				if (instance.accion2 != null) {
					instance.accion2.run();
				}

			}
		});
		JPanel label = new JPanel();
		instance.estado = new JTextArea(5, 20);
		instance.estado.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(instance.estado,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		GridBagLayout gridBag = new GridBagLayout();
		Container contentPane = label;
		contentPane.setLayout(gridBag);
		GridBagConstraints gridCons1 = new GridBagConstraints();
		gridCons1.gridwidth = GridBagConstraints.REMAINDER;
		gridCons1.fill = GridBagConstraints.HORIZONTAL;
		GridBagConstraints gridCons2 = new GridBagConstraints();
		gridCons2.weightx = 1.0;
		gridCons2.weighty = 1.0;
		contentPane.add(scrollPane, gridCons2);

		layout.putConstraint(SpringLayout.NORTH, label, 200,
				SpringLayout.NORTH, instance.contentPane);
		layout.putConstraint(SpringLayout.WEST, label, -30, SpringLayout.EAST,
				instance.label1);
		instance.contentPane.add(label);

		instance.ventana.setSize(anchoVentana, altoVentana);
		instance.ventana.setVisible(true);
		instance.ventana.setResizable(isResizable);

	}

}
