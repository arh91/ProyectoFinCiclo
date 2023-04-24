package vista;

import controlador.Controlador;
import modeloVo.Coche;
import modeloVo.Involucra;
import modeloVo.Reserva;
import validaciones.ConvertirFechas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class prueba extends JFrame {

    private final JPanel contentPanel = new JPanel();

    LocalDate todaysDate = LocalDate.now();  //Fecha actual del sistema
    String today = String.valueOf(todaysDate);

    Controlador controlador;

    private JTextField textFecInicial;
    private JTextField textFecFinal;
    private JTextField textLitros;
    private JTextField textCodReserva;
    private ModeloComboClientes comboBox_Clientes;
    private ModeloComboCoches comboBox_Coches;
    private JComboBox comboBoxDiaInicio1;
    private JComboBox comboBoxMesInicio1;
    private JComboBox comboBoxAnhoInicio1;
    private JComboBox comboBoxDiaFin1;
    private JComboBox comboBoxMesFin1;
    private JComboBox comboBoxAnhoFin1;
    private JComboBox comboBoxDiaInicio2;
    private JComboBox comboBoxMesInicio2;
    private JComboBox comboBoxAnhoInicio2;
    private JComboBox comboBoxDiaFin2;
    private JComboBox comboBoxMesFin2;
    private JComboBox comboBoxAnhoFin2;


    private int codigoReserva;
    private Date fechaInicio;
    private Date fechaFinal;
    private int litros;

    private String dniCliente;
    private String matricula;
    private Date fecInicio;
    private Date fecFinal;
    private String nifInvolucra;

    ConvertirFechas convertirFechas = new ConvertirFechas();

    private JTextField textField_nif_cliente;
    private JTextField textField_Fecha_Inicio;
    private JTextField textField_Matricula_Coche;
    private JTextField textField_fecha_final;

    //combos fecha inicio reserva
    private ArrayList<Integer> treintayUnoDias = new ArrayList<Integer>();
    private ArrayList<Integer> treintaDias = new ArrayList<Integer>();
    private ArrayList<Integer> veintiochoDias = new ArrayList<Integer>();
    private ArrayList<Integer> veintinueveDias = new ArrayList<Integer>();
    private String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private ArrayList<Integer> anho = new ArrayList<Integer>();


    private ArrayList<String> mes = new ArrayList<String>();
    private ArrayList<String> dias = new ArrayList<String>();

    private String[] fecActual = today.split("-");
    private String a = fecActual[0];
    private String m = fecActual[1];
    private String d = fecActual[2];
    int anhoActual = Integer.parseInt(a);
    int mesActual = Integer.parseInt(m);
    int diaActual = Integer.parseInt(d);

    private int contadorDia=0;
    private int contadorMes=0;

    private boolean existeProxAnho = false;


    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public static void main(String[] args) {
        try {
            prueba frame = new prueba();
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public prueba() {
        System.out.println(todaysDate);
        setTitle("Reserva Coches");
        setBounds(100, 100, 869, 455);
        getContentPane().setLayout(new BorderLayout());
        setExtendedState(JFrame.NORMAL);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(35, 24, 56, 16);
        contentPanel.add(lblCliente);

        JLabel lblCoche = new JLabel("Coche:");
        lblCoche.setBounds(35, 67, 56, 16);
        contentPanel.add(lblCoche);



        JPanel panel_Reserva = new JPanel();
        panel_Reserva.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_Reserva.setBounds(12, 105, 827, 139);
        contentPanel.add(panel_Reserva);
        panel_Reserva.setLayout(null);

        JLabel lblFechaInicial = new JLabel("Fecha Inicial:");
        lblFechaInicial.setBounds(45, 35, 61, 16);
        panel_Reserva.add(lblFechaInicial);

        JLabel lblFechafinal = new JLabel("FechaFinal :");
        lblFechafinal.setBounds(438, 35, 61, 16);
        panel_Reserva.add(lblFechafinal);

        JLabel lblLitros = new JLabel("Litros consumidos:");
        lblLitros.setBounds(438, 81, 152, 16);
        panel_Reserva.add(lblLitros);

        textLitros = new JTextField();
        textLitros.setHorizontalAlignment(SwingConstants.CENTER);
        textLitros.setBounds(629, 78, 116, 22);
        panel_Reserva.add(textLitros);
        textLitros.setColumns(10);

        JLabel lblCdigoReserva = new JLabel("C\u00F3digo Reserva:");
        lblCdigoReserva.setBounds(45, 81, 167, 16);
        panel_Reserva.add(lblCdigoReserva);

        textCodReserva = new JTextField();
        textCodReserva.setHorizontalAlignment(SwingConstants.CENTER);
        textCodReserva.setColumns(10);
        textCodReserva.setBounds(249, 78, 116, 22);
        panel_Reserva.add(textCodReserva);

        comboBoxDiaInicio1 = new JComboBox();
        comboBoxDiaInicio1.setBounds(133, 33, 60, 21);
        panel_Reserva.add(comboBoxDiaInicio1);

        comboBoxMesInicio1 = new JComboBox();
        comboBoxMesInicio1.setBounds(207, 33, 80, 21);
        panel_Reserva.add(comboBoxMesInicio1);



        comboBoxAnhoInicio1 = new JComboBox();
        comboBoxAnhoInicio1.setBounds(317, 33, 80, 21);
        panel_Reserva.add(comboBoxAnhoInicio1);


        comboBoxDiaFin1 = new JComboBox();
        comboBoxDiaFin1.setBounds(521, 33, 60, 21);
        panel_Reserva.add(comboBoxDiaFin1);

        comboBoxMesFin1 = new JComboBox();
        comboBoxMesFin1.setBounds(592, 33, 80, 21);
        panel_Reserva.add(comboBoxMesFin1);

        comboBoxAnhoFin1 = new JComboBox();
        comboBoxAnhoFin1.setBounds(692, 33, 80, 21);
        panel_Reserva.add(comboBoxAnhoFin1);
        comboBoxAnhoFin1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int next = anhoActual+1;
                String proxAnho = String.valueOf(next);
                System.out.println(proxAnho);
                if(comboBoxAnhoFin1.getSelectedItem().toString().equals(anhoActual)) {
                    anhadirMesesAnhoActual(comboBoxMesFin1);
                }
                if(comboBoxAnhoFin1.getSelectedItem().toString().equals(proxAnho)) {
                    anhadirMesesProximoAnho(comboBoxMesFin1);
                }
            }
        });

        comboBox_Coches = new ModeloComboCoches();
        comboBox_Coches.setBounds(101, 65, 686, 20);
        contentPanel.add(comboBox_Coches);

        comboBox_Clientes = new ModeloComboClientes();
        comboBox_Clientes.setBounds(101, 22, 196, 20);
        contentPanel.add(comboBox_Clientes);

        JPanel panel_Cancelar_Reserva = new JPanel();
        panel_Cancelar_Reserva.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cancelar Reserva", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_Cancelar_Reserva.setBounds(12, 266, 827, 111);
        contentPanel.add(panel_Cancelar_Reserva);
        panel_Cancelar_Reserva.setLayout(null);

        JLabel labelNifCliente = new JLabel("NIF Cliente");
        labelNifCliente.setBounds(51, 29, 66, 13);
        panel_Cancelar_Reserva.add(labelNifCliente);

        JLabel labelFechaInicio = new JLabel("Fecha Inicio");
        labelFechaInicio.setBounds(50, 70, 67, 13);
        panel_Cancelar_Reserva.add(labelFechaInicio);

        textField_nif_cliente = new JTextField();
        textField_nif_cliente.setHorizontalAlignment(SwingConstants.CENTER);
        textField_nif_cliente.setColumns(10);
        textField_nif_cliente.setBounds(248, 20, 116, 22);
        panel_Cancelar_Reserva.add(textField_nif_cliente);

        textField_Matricula_Coche = new JTextField();
        textField_Matricula_Coche.setHorizontalAlignment(SwingConstants.CENTER);
        textField_Matricula_Coche.setColumns(10);
        textField_Matricula_Coche.setBounds(636, 25, 116, 22);
        panel_Cancelar_Reserva.add(textField_Matricula_Coche);

        JLabel lblNewLabel_2 = new JLabel("Matrívula Vehículo");
        lblNewLabel_2.setBounds(441, 29, 101, 13);
        panel_Cancelar_Reserva.add(lblNewLabel_2);

        JLabel labelFechaFin = new JLabel("Fecha Fin");
        labelFechaFin.setBounds(441, 71, 60, 13);
        panel_Cancelar_Reserva.add(labelFechaFin);

        comboBoxDiaInicio2 = new JComboBox();
        comboBoxDiaInicio2.setBounds(142, 66, 60, 21);
        panel_Cancelar_Reserva.add(comboBoxDiaInicio2);

        comboBoxMesInicio2 = new JComboBox();
        comboBoxMesInicio2.setBounds(217, 66, 80, 21);
        panel_Cancelar_Reserva.add(comboBoxMesInicio2);

        comboBoxAnhoInicio2 = new JComboBox();
        comboBoxAnhoInicio2.setBounds(312, 66, 80, 21);
        panel_Cancelar_Reserva.add(comboBoxAnhoInicio2);

        comboBoxDiaFin2 = new JComboBox();
        comboBoxDiaFin2.setBounds(529, 66, 60, 21);
        panel_Cancelar_Reserva.add(comboBoxDiaFin2);

        comboBoxMesFin2 = new JComboBox();
        comboBoxMesFin2.setBounds(604, 66, 80, 21);
        panel_Cancelar_Reserva.add(comboBoxMesFin2);

        comboBoxAnhoFin2 = new JComboBox();
        comboBoxAnhoFin2.setBounds(699, 66, 80, 21);
        panel_Cancelar_Reserva.add(comboBoxAnhoFin2);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Efectuar Reserva");
                okButton.addActionListener(new OkButtonActionListener());
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);

                JButton cancelButton = new JButton("Cancelar Reserva");
                cancelButton.addActionListener(new CancelButtonActionListener());
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton btnAtras = new JButton("Atras");
                btnAtras.addActionListener(new AtrasButtonActionListener());
                btnAtras.setActionCommand("Cancel");
                buttonPane.add(btnAtras);
            }
        }


        for(int i=1; i<32; i++) {
            treintayUnoDias.add(i);
        }
        for(int i=1; i<31; i++){
            treintaDias.add(i);
        }
        for(int i=1; i<30; i++) {
            veintinueveDias.add(i);
        }
        for(int i=1; i<29; i++) {
            veintiochoDias.add(i);
        }


        for (int i=1; i<32; i++) {
            if(i<10) {
                dias.add("0"+String.valueOf(i));
            }else {
                dias.add(String.valueOf(i));
            }
        }

        for (int i=1; i<13; i++) {
            if(i<10) {
                mes.add("0"+String.valueOf(i));
            }else {
                mes.add(String.valueOf(i));
            }
        }



        for(int i=0; i<dias.size(); i++) {
            if(dias.get(i).equals(d)) {
                break;
            }
            contadorDia++;
        }

        for(int i=0; i<mes.size(); i++) {
            if(mes.get(i).equals(m)) {
                break;
            }
            contadorMes++;
        }

        for(int i=contadorMes; i<meses.length; i++) {
            comboBoxMesInicio1.addItem(meses[i]);
            comboBoxMesFin1.addItem(meses[i]);
            comboBoxMesInicio2.addItem(meses[i]);
            comboBoxMesFin2.addItem(meses[i]);
        }


        anho.add(anhoActual);
        if(12-mesActual<06){
            anho.add(anhoActual+1);
            existeProxAnho = true;
        }



        for(int i=0; i<anho.size(); i++) {
            comboBoxAnhoInicio1.addItem(anho.get(i));
            comboBoxAnhoFin1.addItem(anho.get(i));
            comboBoxAnhoInicio2.addItem(anho.get(i));
            comboBoxAnhoFin2.addItem(anho.get(i));
        }


        comboBoxMesInicio1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int b=Integer.parseInt(a)+1;
                String bS = String.valueOf(b);
                if(comboBoxMesInicio1.getSelectedItem()=="Febrero" && anhoActual%4!=0 && comboBoxAnhoInicio1.getSelectedItem().equals(a) || comboBoxAnhoInicio1.getSelectedItem().equals(a+1)) {
                    rellenar28Dias(comboBoxMesInicio1, comboBoxDiaInicio1, m);
                }
                if(comboBoxMesInicio1.getSelectedItem()=="Febrero" && anhoActual%4==0) {
                    rellenar29Dias(comboBoxMesInicio1, comboBoxDiaInicio1, m);
                }
                if(comboBoxMesInicio1.getSelectedItem()=="Abril" || comboBoxMesInicio1.getSelectedItem()=="Junio" || comboBoxMesInicio1.getSelectedItem()=="Septiembre" || comboBoxMesInicio1.getSelectedItem()=="Noviembre" && comboBoxAnhoInicio1.getSelectedItem().equals(a) || comboBoxAnhoInicio1.getSelectedItem().equals(a+1)) {
                    rellenar30Dias(comboBoxMesInicio1, comboBoxDiaInicio1, m);
                }
                if(comboBoxMesInicio1.getSelectedItem()=="Enero" || comboBoxMesInicio1.getSelectedItem()=="Marzo" || comboBoxMesInicio1.getSelectedItem()=="Mayo" || comboBoxMesInicio1.getSelectedItem()=="Julio" || comboBoxMesInicio1.getSelectedItem()=="Agosto" || comboBoxMesInicio1.getSelectedItem()=="Octubre" || comboBoxMesInicio1.getSelectedItem()=="Diciembre" && comboBoxAnhoInicio1.getSelectedItem().equals(a) || comboBoxAnhoInicio1.getSelectedItem().equals(a+1)) {
                    rellenar31Dias(comboBoxMesInicio1, comboBoxDiaInicio1, m);
                }
            }
        });

        comboBoxMesFin1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                contadorMes = 0;
                for(int i=0; i<mes.size(); i++) {
                    if(mes.get(i).equals(m)) {
                        break;
                    }
                    contadorMes++;
                }
                String mes_Actual = meses[contadorMes];
                System.out.println(mes_Actual);
                if(comboBoxMesFin1.getSelectedItem()=="Febrero" && anhoActual%4!=0 && comboBoxAnhoFin1.getSelectedItem().equals(a) || comboBoxAnhoFin1.getSelectedItem().equals(a+1)) {
                    rellenar28Dias(comboBoxMesFin1, comboBoxDiaFin1, mes_Actual);
                }
                if(comboBoxMesFin1.getSelectedItem()=="Febrero" && anhoActual%4==0) {
                    rellenar29Dias(comboBoxMesFin1, comboBoxDiaFin1, mes_Actual);
                }

                if(comboBoxMesFin1.getSelectedItem()=="Abril" || comboBoxMesFin1.getSelectedItem()=="Junio" || comboBoxMesFin1.getSelectedItem()=="Septiembre" || comboBoxMesFin1.getSelectedItem()=="Noviembre" && comboBoxAnhoFin1.getSelectedItem().equals(a) || comboBoxAnhoFin1.getSelectedItem().equals(a+1)) {
                    rellenar30Dias(comboBoxMesFin1, comboBoxDiaFin1, mes_Actual);
                }
                if(comboBoxMesFin1.getSelectedItem()=="Enero" || comboBoxMesFin1.getSelectedItem()=="Marzo" || comboBoxMesFin1.getSelectedItem()=="Mayo" || comboBoxMesFin1.getSelectedItem()=="Julio" || comboBoxMesFin1.getSelectedItem()=="Agosto" || comboBoxMesFin1.getSelectedItem()=="Octubre" || comboBoxMesFin1.getSelectedItem()=="Diciembre" && comboBoxAnhoFin1.getSelectedItem().equals(a)/* || comboBoxAnhoFin1.getSelectedItem().equals(a+1)*/) {
                    rellenar31Dias(comboBoxMesFin1, comboBoxDiaFin1, mes_Actual);
                }
            }
        });

        comboBoxMesInicio2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if(comboBoxMesInicio2.getSelectedItem()=="Febrero" && anhoActual%4!=0 && comboBoxAnhoInicio2.getSelectedItem().equals(a) || comboBoxAnhoInicio2.getSelectedItem().equals(a+1)) {
                    rellenar28Dias(comboBoxMesInicio2, comboBoxDiaInicio2, m);
                }
                if(comboBoxMesInicio2.getSelectedItem()=="Febrero" && anhoActual%4==0) {
                    rellenar29Dias(comboBoxMesInicio2, comboBoxDiaInicio2, m);
                }
                if(comboBoxMesInicio2.getSelectedItem()=="Abril" || comboBoxMesInicio2.getSelectedItem()=="Junio" || comboBoxMesInicio2.getSelectedItem()=="Septiembre" || comboBoxMesInicio2.getSelectedItem()=="Noviembre" && comboBoxAnhoInicio2.getSelectedItem().equals(a) || comboBoxAnhoInicio2.getSelectedItem().equals(a+1)) {
                    rellenar30Dias(comboBoxMesInicio2, comboBoxDiaInicio2, m);
                }
                if(comboBoxMesInicio2.getSelectedItem()=="Enero" || comboBoxMesInicio2.getSelectedItem()=="Marzo" || comboBoxMesInicio2.getSelectedItem()=="Mayo" || comboBoxMesInicio2.getSelectedItem()=="Julio" || comboBoxMesInicio2.getSelectedItem()=="Agosto" || comboBoxMesInicio2.getSelectedItem()=="Octubre" || comboBoxMesInicio2.getSelectedItem()=="Diciembre" && comboBoxAnhoInicio2.getSelectedItem().equals(a) || comboBoxAnhoInicio2.getSelectedItem().equals(a+1)) {
                    rellenar31Dias(comboBoxMesInicio2, comboBoxDiaInicio2, m);
                }
            }
        });


        comboBoxMesFin2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if(comboBoxMesFin2.getSelectedItem()=="Febrero" && anhoActual%4!=0 && comboBoxAnhoFin2.getSelectedItem().equals(a) || comboBoxAnhoFin2.getSelectedItem().equals(a+1)) {
                    rellenar28Dias(comboBoxMesFin2, comboBoxDiaFin2, m);
                }
                if(comboBoxMesFin2.getSelectedItem()=="Febrero" && anhoActual%4==0) {
                    rellenar29Dias(comboBoxMesFin2, comboBoxDiaFin2, m);
                }
                if(comboBoxMesFin2.getSelectedItem()=="Abril" || comboBoxMesFin2.getSelectedItem()=="Junio" || comboBoxMesFin2.getSelectedItem()=="Septiembre" || comboBoxMesFin2.getSelectedItem()=="Noviembre" && comboBoxAnhoFin2.getSelectedItem().equals(a) || comboBoxAnhoFin2.getSelectedItem().equals(a+1)) {
                    rellenar30Dias(comboBoxMesFin2, comboBoxDiaFin2, m);
                }
                if(comboBoxMesFin2.getSelectedItem()=="Enero" || comboBoxMesFin2.getSelectedItem()=="Marzo" || comboBoxMesFin2.getSelectedItem()=="Mayo" || comboBoxMesFin2.getSelectedItem()=="Julio" || comboBoxMesFin2.getSelectedItem()=="Agosto" || comboBoxMesFin2.getSelectedItem()=="Octubre" || comboBoxMesFin2.getSelectedItem()=="Diciembre" && comboBoxAnhoFin2.getSelectedItem().equals(a) || comboBoxAnhoFin2.getSelectedItem().equals(a+1)) {
                    rellenar31Dias(comboBoxMesFin2, comboBoxDiaFin2, m);
                }
            }
        });

        comboBoxAnhoInicio1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                int next = anhoActual+1;
                String proxAnho = String.valueOf(next);
                //System.out.println(proxAnho);
                if(comboBoxAnhoInicio1.getSelectedItem().toString().equals(anhoActual)) {
                    anhadirMesesAnhoActual(comboBoxMesInicio1);
                }
                if(comboBoxAnhoInicio1.getSelectedItem().toString().equals(proxAnho)) {
                    anhadirMesesProximoAnho(comboBoxMesInicio1);
                }
            }
        });

		/*if(comboBoxMesInicio1.getSelectedItem()=="Febrero" && anhoActual%4!=0) {
			rellenar28Dias();
		}
		if(comboBoxMesInicio1.getSelectedItem()=="Febrero" && anhoActual%4==0) {
			rellenar29Dias();
		}
		if(comboBoxMesInicio1.getSelectedItem()=="Abril" || comboBoxMesInicio1.getSelectedItem()=="Junio" || comboBoxMesInicio1.getSelectedItem()=="Septiembre" || comboBoxMesInicio1.getSelectedItem()=="Noviembre") {
			rellenar30Dias();
		}else {
			rellenar31Dias();
		}*/

        System.out.println(contadorDia);
        System.out.println(contadorMes);

        //comboBoxAnhoFin1.setSelectedIndex(0);
		/*comboBoxAnhoFin1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent itemEvent) {
				int next = anhoActual+1;
				String proxAnho = String.valueOf(next);
				System.out.println(proxAnho);
				if(comboBoxAnhoFin1.getSelectedItem().toString().equals(proxAnho)) {
					anhadirMesesProximoAnho(comboBoxMesFin1);
				}

			}
		});*/
    }
    private class OkButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
			/*String cadena = String.valueOf(comboBox_Coches.getSelectedItem());
			String[] arr = cadena.split("  ");
			String matricula = arr[0];*/
            Coche coche = new Coche();
            Reserva reserva = new Reserva();
            Involucra involucra = new Involucra();


            controlador.preguntarDisponibilidadCoche(matricula, coche);

            if(coche.isDisponible()==false) {
                JOptionPane.showMessageDialog(null, "Lo sentimos, el coche seleccionado no se encuentra disponible para las fechas que usted ha seleccionado.");
            }else {
                controlador.reservarCoche(fechaInicio, fechaFinal, matricula);
				/*Reserva(reserva);
				Involucra(involucra);*/
                controlador.insertarReserva(reserva);
                controlador.insertarInvolucra(involucra);
            }


        }
    }

    private class CancelButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

        }
    }

    private class AtrasButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            dispose();
            controlador.mostrarF01Inicial();
        }
    }

	/*public void Reserva(Reserva reserva) {
		codigoReserva = Integer.parseInt(textCodReserva.getText());
		fechaInicio = convertirFechas.convertirStringDate(textFecInicial.getText());
		fechaFinal = convertirFechas.convertirStringDate(textFecFinal.getText());

		reserva.setCodigo(codigoReserva);
		reserva.setFecInicio(fechaInicio);
		reserva.setFecFinal(fechaFinal);
	}*/

    public void Involucra(Involucra involucra) {
        String infoCoche = String.valueOf(comboBox_Coches.getSelectedItem());
        String[] arrCoche = infoCoche.split("  ");
        matricula = arrCoche[0];

        String infoCliente = String.valueOf(comboBox_Clientes.getSelectedItem());
        String[] arrCliente = infoCliente.split("  ");
        nifInvolucra = arrCliente[0];

        codigoReserva = Integer.parseInt(textCodReserva.getText());
        litros = Integer.parseInt(textLitros.getText());

        involucra.setMatricula(matricula);
        involucra.setCliente(nifInvolucra);
        involucra.setReserva(codigoReserva);
        involucra.setLitros(litros);
    }

	/*public void datosCancelacion() {
		dniCliente = textField_nif_cliente.getText();
		matricula = textField_Matricula_Coche.getText();
		fecInicio = convertirFechas.convertirStringDate(textField_Fecha_Inicio.getText());
		fecFinal = convertirFechas.convertirStringDate(textField_fecha_final.getText());
	}*/

    private void rellenar28Dias(JComboBox comboBoxMes, JComboBox comboBoxDia, String cad) {
        comboBoxDia.removeAllItems();

        if(comboBoxMes.getSelectedItem().equals(cad)) {
            //llenarArraylist28(veintiochoDias);

            for(int i=contadorDia; i<veintiochoDias.size(); i++) {
                comboBoxDia.addItem(veintiochoDias.get(i));
            }
        }
        else {
            //llenarArraylist28(veintiochoDias);

            for(int i=0; i<veintiochoDias.size(); i++) {
                comboBoxDia.addItem(veintiochoDias.get(i));
            }
        }
    }

    private void rellenar29Dias(JComboBox comboBoxMes, JComboBox comboBoxDia, String cad) {
        comboBoxDia.removeAllItems();

        if(comboBoxMes.getSelectedItem().equals(cad)) {
            //llenarArraylist29(veintinueveDias);

            for(int i=contadorDia; i<veintinueveDias.size(); i++) {
                comboBoxDia.addItem(veintinueveDias.get(i));
            }
        }
        else {
            //llenarArraylist29(veintinueveDias);

            for(int i=0; i<veintinueveDias.size(); i++) {
                comboBoxDia.addItem(veintinueveDias.get(i));
            }
        }
    }

    private void rellenar30Dias(JComboBox comboBoxMes, JComboBox comboBoxDia, String cad) {
        comboBoxDia.removeAllItems();

        if(comboBoxMes.getSelectedItem().equals(cad)) {
            //llenarArraylist30(treintaDias);

            for(int i=contadorDia; i<treintaDias.size(); i++) {
                comboBoxDia.addItem(treintaDias.get(i));
            }
        }
        else {
            //llenarArraylist30(treintaDias);

            for(int i=0; i<treintaDias.size(); i++) {
                comboBoxDia.addItem(treintaDias.get(i));
            }
        }
    }

    private void rellenar31Dias(JComboBox comboBoxMes, JComboBox comboBoxDia, String cad) {
        comboBoxDia.removeAllItems();

        if(comboBoxMes.getSelectedItem().equals(cad)) {
            //llenarArraylist31(treintayUnoDias);

            for(int i=contadorDia; i<treintayUnoDias.size(); i++) {
                comboBoxDia.addItem(treintayUnoDias.get(i));
            }
        }
        else {
            //llenarArraylist31(treintayUnoDias);


            for(int i=0; i<treintayUnoDias.size(); i++) {
                comboBoxDia.addItem(treintayUnoDias.get(i));
            }
        }
    }

    private void anhadirMesesProximoAnho(JComboBox combo) {
        //if(12 - mesActual<06){
        int n = 06-(12-mesActual);
        String n1 = String.valueOf(n);
        removefirstChar(n1);
        int limiteMax = Integer.parseInt(n1);

        combo.removeAllItems();

        for(int j=0; j<limiteMax; j++) {
            combo.addItem(meses[j]);
            //System.out.println(meses[j]);
        }
		/*}else {
			combo.removeAllItems();
		}
		/*else {
			int n = mesActual+06;
			String n1 = String.valueOf(n);
			if(Integer.parseInt(n1)<10) {
				removefirstChar(n1);
			}
			int limiteMax = Integer.parseInt(n1);

			combo.removeAllItems();

			for(int j=0; j<limiteMax; j++) {
				combo.addItem(meses[j]);
				System.out.println(meses[j]);
			}
		}*/
    }

    private void anhadirMesesAnhoActual(JComboBox combo) {
        if (mesActual<=06) {
            int n = mesActual+06;
            String n1 = String.valueOf(n);
            if(Integer.parseInt(n1)<10) {
                removefirstChar(n1);
            }
            int limiteMax = Integer.parseInt(n1);

            combo.removeAllItems();

            int limiteMin=0;
            removefirstChar(m);
            limiteMin = Integer.parseInt(m) - 1;

            for(int j=limiteMin; j<limiteMax; j++) {
                combo.addItem(meses[j]);
                System.out.println(meses[j]);
            }
        }
        else {
            int n = mesActual;
            String n1 = String.valueOf(n);
            if(Integer.parseInt(n1)<10) {
                removefirstChar(n1);
            }
            int limiteMin = Integer.parseInt(n1) - 1;

            combo.removeAllItems();

            for(int j=limiteMin; j<meses.length; j++) {
                combo.addItem(meses[j]);
                System.out.println(meses[j]);
            }
        }

    }


    public boolean esBisiesto(String cad) {
        int num = Integer.parseInt(cad);
        if(num % 4 == 0) {
            return true;
        }else {
            return false;
        }
    }

    public static String removefirstChar(String str)
    {
        if (str == null || str.length() == 0) {
            return str;
        }
        return str.substring(1);
    }

}
