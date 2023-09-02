
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natalia Carpintero, Paula Nuñez e Isabella Arrieta
 */
public class Principal extends javax.swing.JFrame {

    //SUBRUTINAS
    //01 Subrutina para llenar el archivo Empleados
    /**
     * * INSTRUCCION 1: PARA EMPLEADOS (DESPUES BORRAR) Los datos de sus
     * empleados (Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso,
     * salario fijo mensual y salario más comisiones) se encuentran en un
     * archivo de texto llamado Empleados, sin ningún orden específico.
     */
    public static void agregarDatosEmpleados(String file_name) {
        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", false);
            PrintWriter registro = new PrintWriter(outFile);

            //Matriz para crear Archivo Existente 
            String[][] empleados = {
                {"Ana López", "123456789", "Vendedor", "3101234567", "05-01-2023", "2500000", "3000000"},
                {"Carlos Pérez", "234567890", "Gerente", "3112345678", "15-11-2022", "4000000", "5500000"},
                {"María Rodríguez", "345678901", "Asistente", "3203456789", "10-02-2023", "2000000", "2300000"},
                {"Luis González", "456789012", "Vendedor", "3104567890", "20-03-2023", "2200000", "2800000"},
                {"Laura Martínez", "567890123", "Contador", "3215678901", "08-09-2022", "3500000", "4200000"},
                {"Juan Sánchez", "678901234", "Vendedor", "3106789012", "03-12-2022", "2300000", "3000000"},
                {"Diana Herrera", "789012345", "Recepcionista", "3227890123", "25-04-2023", "1800000", "2000000"},
                {"Andrés Castro", "890123456", "Técnico", "3118901234", "10-01-2023", "2100000", "2500000"},
                {"Sofía Ramírez", "901234567", "Vendedor", "3109012345", "01-03-2023", "2400000", "3200000"},
                {"David Méndez", "012345678", "Asistente", "3210123456", "18-10-2022", "2100000", "2500000"},
                {"Camila Vargas", "123456780", "Vendedor", "3101234567", "15-02-2023", "2600000", "3500000"},
                {"Oscar Ríos", "234567891", "Gerente", "3122345678", "01-09-2022", "4200000", "5800000"},
                {"Elena Mendoza", "345678902", "Asistente", "3223456789", "08-01-2023", "2100000", "2500000"},
                {"Javier Herrera", "456789013", "Vendedor", "3114567890", "10-04-2023", "2300000", "3000000"},
                {"Paula Gómez", "567890124", "Contador", "3205678901", "22-11-2022", "3600000", "4300000"},
                {"Mateo López", "678901235", "Vendedor", "3106789012", "18-12-2022", "2200000", "2800000"},
                {"Isabella Castro", "789012346", "Recepcionista", "3217890123", "10-03-2023", "1900000", "2100000"},
                {"Santiago Ramírez", "890123457", "Técnico", "3128901234", "15-01-2023", "2000000", "2300000"},
                {"Valentina Méndez", "901234568", "Vendedor", "3109012345", "05-03-2023", "2500000", "3300000"},
                {"Andrea Vargas", "012345679", "Asistente", "3210123456", "28-09-2022", "2000000", "2300000"}

            };

            //Agregar datos de la matriz al registro
            for (String[] fila : empleados) {
                String Nombre = fila[0];
                String Cedula = fila[1];
                String Cargo = fila[2];
                String Telefono = fila[3];
                String FechaIngreso = fila[4];
                String SalarioFijo = fila[5];
                String SalarioComisiones = fila[6];
                // Agregar los datos al archivo
                registro.println(Nombre + "\t" + Cedula + "\t" + Cargo + "\t" + Telefono + "\t" + FechaIngreso + "\t" + SalarioFijo + "\t" + SalarioComisiones);

            }

            registro.close();
            System.out.println("Datos agregados exitosamente al archivo.");

        } catch (IOException ex) {
            System.out.println("Error al agregar datos al archivo");
            ex.printStackTrace();
        }
    }

    //02 SUBRUTINA PARA MOSTRAR LOS CAMPOS ARCHIVO EMPLEADOS
    public static void LeerNormal(Scanner sc, String file_name, JTable tabla) {
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");

                    model.addRow(temp); //Agregar datos del archivo a la tabla
                }

                br.close();
                hay = true;

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }
    }

    //03 SUBRUTINA PARA MOSTRAR LOS CAMPOS ORDENADOS DEL ARCHIVO EMPLEADOS 
    public static void LeerOrdenado(Scanner sc, String file_name, JTable tabla, String NoS) {
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");
                    model.addRow(temp); //Agregar datos del archivo a la tabla
                }

                br.close();
                hay = true;
                if (NoS == "Nombre") {
                    // Utilizo la tabla como si fuera una matriz
                    // Ordenamiento burbuja por columna 0 (nombre)
                    int Contador = model.getRowCount();
                    for (int i = 0; i < Contador - 1; i++) {
                        for (int j = 0; j < Contador - i - 1; j++) {
                            if (model.getValueAt(j, 0).toString().compareTo(model.getValueAt(j + 1, 0).toString()) > 0) {
                                // Intercambiar filas en la tabla
                                for (int col = 0; col < model.getColumnCount(); col++) {
                                    Object temp = model.getValueAt(j, col);
                                    model.setValueAt(model.getValueAt(j + 1, col), j, col);
                                    model.setValueAt(temp, j + 1, col);
                                }
                            }
                        }
                    }
                } else {
                    /**
                     * ESTO ES INTERESANTE ES ALGO PROPIO DE LA TABLA
                     * TableRowSorter<DefaultTableModel> sorter = new
                     * TableRowSorter<>(model); tabla.setRowSorter(sorter);
                     *
                     * // Ordenar por la columna de salario de mayor a menor
                     * int salarioColumna = 6;
                     * sorter.setComparator(salarioColumna, new
                     * Comparator<String>() {
                     *
                     * @Override public int compare(String o1, String o2) {
                     * double salario1 = Double.parseDouble(o1); double salario2
                     * = Double.parseDouble(o2); return Double.compare(salario2,
                     * salario1); } }); *
                     */
                    //Ordenamiento Burbuja
                    int salarioColumna = 6;
                    int contador = model.getRowCount();

                    for (int i = 0; i < contador - 1; i++) {
                        for (int j = 0; j < contador - i - 1; j++) {
                            String salario1Str = model.getValueAt(j, salarioColumna).toString();
                            String salario2Str = model.getValueAt(j + 1, salarioColumna).toString();

                            double salario1 = Double.parseDouble(salario1Str);
                            double salario2 = Double.parseDouble(salario2Str);

                            if (salario1 < salario2) {
                                // Intercambiar filas en el modelo de la tabla
                                for (int col = 0; col < model.getColumnCount(); col++) {
                                    Object temp = model.getValueAt(j, col);
                                    model.setValueAt(model.getValueAt(j + 1, col), j, col);
                                    model.setValueAt(temp, j + 1, col);
                                }
                            }
                        }
                    }

                }

            } catch (IOException ex) {
                System.out.println("No se encontro archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }
    }

    //04 Subrutina para Agregar Datos de Empleados(si quieren se puede hacer así)
    public void AgregarEmpleados(String file_name) {
        /**
         * (Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso,
         * salario fijo mensual y salario más comisiones *
         */
        String Nombre, Cedula, Cargo, Telefono, FechaIngreso, SalarioFijo, SalarioComisiones;

        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", true);  //Archivo.txt
            // if false the file will be deleted and created everytime
            // if true the registers will be appended to the end of the file
            PrintWriter registrar_empleados = new PrintWriter(outFile);
            // LOGICA
            Nombre = fnombre.getText();
            Cedula = fcedula.getText();
            Cargo = fcargo.getText();
            Telefono = ftelefono.getText();
            FechaIngreso = ffechaingreso.getText();
            SalarioFijo = fsalariofijo.getText();
            SalarioComisiones = fsalariocomisiones.getText();

            //Llamar a funciones de validaciones
            //Validacion1: verifica que no haya campos vacios
            //Validacion2: verifica que cada campo no tenga algun error de formato
            if (validacion1(Nombre, Cedula, Cargo, Telefono, FechaIngreso, SalarioFijo, SalarioComisiones)) {
                if (validacion2(Nombre, Cedula, Cargo, Telefono, FechaIngreso, SalarioFijo, SalarioComisiones)) {
                    registrar_empleados.println(Nombre + "\t" + Cedula + "\t" + Cargo + "\t" + Telefono + "\t" + FechaIngreso + "\t" + SalarioFijo + "\t" + SalarioComisiones);
                    sonido("/Sonidos/correcto.wav");//implementacion de sonidos
                    JOptionPane.showMessageDialog(null, "Los datos se han agregado satisfactoriamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    sonido("/Sonidos/error.wav");
                }
            }
            registrar_empleados.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar los datos.", "Error", JOptionPane.ERROR_MESSAGE);

            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }

    }

    //05 Subrutina para eliminar registros
    public void EliminarRegistro(Scanner sc, String file_name, JTable tabla) {
        File original = new File(file_name + ".txt");
        try {
            FileReader FR = new FileReader(file_name + ".txt");
            BufferedReader BR = new BufferedReader(FR);
            FileWriter FW = new FileWriter("EmpleadosTemp.txt", true);
            BufferedWriter BW = new BufferedWriter(FW);
            String linea, borrar_nombre = fnombreE.getText();
            boolean encontrado = false;
            while ((linea = BR.readLine()) != null) {
                String[] campos = linea.split("\t");
                if (!campos[0].equalsIgnoreCase(borrar_nombre)) {
                    BW.write(linea); // si el nombre no es el que se busca, se escribe en el archivo temporal
                    BW.newLine();
                } else {
                    encontrado = true;
                    System.out.println("ENCONTRADO");
                }

            }
            BR.close();
            BW.close();
            if (encontrado) {
                if (original.delete()) {
                    File temporal = new File("EmpleadosTemp.txt");
                    if (temporal.renameTo(original)) {
                        JOptionPane.showMessageDialog(null, "Los datos se han eliminado satisfactoriamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al renombrar el archivo temporal.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    LeerNormal(sc, file_name, tabla);
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el archivo original.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el empleado con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }

    //06 Subrutina para limpiar campos
    public void Limpiar() {
        fnombre.setText("");
        fcedula.setText("");
        fcargo.setText("");
        ftelefono.setText("");
        ffechaingreso.setText("");
        fsalariofijo.setText("");
        fsalariocomisiones.setText("");
    }

    //06 SUBRUTINA PARA APLICAR SONIDO
    private void sonido(String cadena) {
        try {
            Clip clip = AudioSystem.getClip();
            URL url = getClass().getResource(cadena);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip.open(audioIn);
            clip.start();

        } catch (Exception e) {
            //System.err.println(e.getMessage());
        }
    }

//FUNCIONES - Total 2
    // 01 funcion para validar si estan vacios los campos
    public boolean validacion1(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty() && !c4.isEmpty() && !c5.isEmpty() && !c6.isEmpty() && !c7.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    //02 Funcion para validar 2
    // Nombre, Cedula, Cargo, Telefono, FechaIngreso, SalarioFijo, SalarioComisiones
    public boolean validacion2(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {

        // Validacion Nombre
        boolean validon = true;
        for (char c : c1.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                validon = false;
                break;
            }
        }
        if (!validon) {
            error1.setVisible(true);
            return false;
        } else {
            error1.setVisible(false);
        }

        //Validacion Cedula
        boolean validoc = true;
        for (char c : c2.toCharArray()) {
            if (!Character.isDigit(c)) {
                validoc = false;
                break;
            }
        }
        if (!validoc) {
            error2.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            error2.setVisible(false); // Ocultar mensaje de error
        }

        // Validacion Cargo
        boolean validocargo = true;
        for (char c : c3.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                validocargo = false;
                break;
            }
        }
        if (!validocargo) {
            error3.setVisible(true);
            return false;
        } else {
            error3.setVisible(false);
        }
        //Validacion telefono de contacto
        // Verificar si la entrada consiste en números y tiene 10 dígitos
        boolean validonumero = true;
        for (char c : c4.toCharArray()) {
            if (!Character.isDigit(c)) {
                validonumero = false;
                break;
            }
        }

        boolean tiene10Digitos = c4.length() == 10;

        if (validonumero && tiene10Digitos) {
            error4.setVisible(false);
        } else {
            error4.setVisible(true);
            return false;
        }

        // Validación salario
        boolean validosalario = true;
        for (char c : c6.toCharArray()) {
            if (!Character.isDigit(c)) {
                validosalario = false;
                break;
            }
        }

        try {
            double salariofijo = Double.parseDouble(c6);
            if (salariofijo < 0) {
                error6.setVisible(false);
            }
        } catch (NumberFormatException e) {
            error6.setVisible(true); // Mostrar mensaje de error
            return false;
        }

        if (!validosalario) {
            error6.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            error6.setVisible(false); // Ocultar mensaje de error  

        }

        // Validacion salario+Comisiones
        boolean validosalario2 = true;
        for (char c : c7.toCharArray()) {
            if (!Character.isDigit(c)) {
                validosalario2 = false;
                break;
            }
        }

        try {
            double salariofijo = Double.parseDouble(c7);
            if (salariofijo < 0) {
                error7.setVisible(false);
            }
        } catch (NumberFormatException e) {
            error7.setVisible(true); // Mostrar mensaje de error
            return false;
        }

        if (!validosalario2) {
            error7.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            error7.setVisible(false); // Ocultar mensaje de error  

        }
        //Validacion fecha de ingreso
        try {
            LocalDate fecha = LocalDate.parse(c5); // Intenta analizar la cadena como una fecha
            error5.setVisible(true);
            return false;
        } catch (DateTimeParseException e) {
            error5.setVisible(false);
        }
        return true;

    }

    public Principal() {
        initComponents();
        //INTERFAZ
        //setIconImage(new ImageIcon(getClass().getResource("Imagenes/icon.png")).getImage());
        //this.setTitle(" ");
        this.setLocationRelativeTo(null); //centrar ventana
        PanelEmpleados.setVisible(false);
        PanelInventario.setVisible(false);
        PanelVentas.setVisible(true);
        PanelEmpleados.setEnabled(false);
        PanelVentas.setEnabled(true);
        PanelInventario.setEnabled(false);
        Actual = PanelVentas;
        Nocturno = false;
        CambiarBotones("PanelVentas");
        TituloPanel.setText("|  Ventas");
        //ARCHIVO EMPLEADOS
        //Crear
        agregarDatosEmpleados("Empleados");
        //Mostrar Archivo Empleados
        Scanner sc = new Scanner(System.in);
        LeerNormal(sc, "Empleados", TablaEMPLEADOS);
        sc.close();
        //Opcion de Ordenar por Salario o Nombre
        ButtonGroup buttonGroup = new ButtonGroup();
        // Agregar los radio buttons al grupo
        buttonGroup.add(BotonOrdenar);
        buttonGroup.add(BotonOrdenarSalario);
        buttonGroup.add(BotonSinOrdenar);
        BotonOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerOrdenado(sc, "Empleados", TablaEMPLEADOS, "Nombre");
                sc.close();
            }
        });
        BotonOrdenarSalario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerOrdenado(sc, "Empleados", TablaEMPLEADOS, "Salario");
                sc.close();
            }
        });
        BotonSinOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerNormal(sc, "Empleados", TablaEMPLEADOS);
                sc.close();
            }
        });
        //No visible
        FrameAgregar.setVisible(false);
        FrameEliminar.setVisible(false);
        LabelFondoBorroso.setVisible(false);
        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);
        error4.setVisible(false);
        error5.setVisible(false);
        error6.setVisible(false);
        error7.setVisible(false);

    }

    public static void CambiaEstadoPANEL(JPanel p) {
        p.setVisible(!p.isVisible());
        p.setEnabled(!p.isEnabled());
    }

    public void CambiarBotones(String Actual) {
        if (Actual.equalsIgnoreCase("PanelInventario")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox63.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
        }
        if (Actual.equalsIgnoreCase("PanelVentas")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox63.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
        }
        if (Actual.equalsIgnoreCase("PanelEmpleados")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondox63.png")));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TituloPanel = new javax.swing.JLabel();
        Boton_Empleados = new javax.swing.JButton();
        Boton_Ventas = new javax.swing.JButton();
        Boton_Inventario = new javax.swing.JButton();
        Modo = new javax.swing.JButton();
        InfoBTN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        PanelEmpleados = new javax.swing.JPanel();
        FrameEliminar = new javax.swing.JInternalFrame();
        PanelEliminarEmpleado = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        fnombreE = new javax.swing.JTextField();
        cerrar1 = new javax.swing.JButton();
        BotonEliminarEmpleados = new javax.swing.JButton();
        BotonLimpiar1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        error9 = new javax.swing.JLabel();
        FrameAgregar = new javax.swing.JInternalFrame();
        PanelAgregarEmpleado = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        fnombre = new javax.swing.JTextField();
        fcedula = new javax.swing.JTextField();
        fcargo = new javax.swing.JTextField();
        ffechaingreso = new javax.swing.JTextField();
        ftelefono = new javax.swing.JTextField();
        fsalariofijo = new javax.swing.JTextField();
        fsalariocomisiones = new javax.swing.JTextField();
        cerrar = new javax.swing.JButton();
        BotonAgregarEmpleados = new javax.swing.JButton();
        BotonLimpiar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        error7 = new javax.swing.JLabel();
        error1 = new javax.swing.JLabel();
        error2 = new javax.swing.JLabel();
        error3 = new javax.swing.JLabel();
        error4 = new javax.swing.JLabel();
        error5 = new javax.swing.JLabel();
        error6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEMPLEADOS = new javax.swing.JTable();
        BotonOrdenar = new javax.swing.JRadioButton();
        BotonOrdenarSalario = new javax.swing.JRadioButton();
        BotonSinOrdenar = new javax.swing.JRadioButton();
        BotonparaAgregar = new javax.swing.JButton();
        BotonparaEliminar = new javax.swing.JButton();
        LabelFondoBorroso = new javax.swing.JLabel();
        PanelVentas = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaVENTAS = new javax.swing.JTable();
        PanelInventario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        CantVendidaMercedes = new javax.swing.JLabel();
        CantVendidaBMW = new javax.swing.JLabel();
        CantVendidaHonda = new javax.swing.JLabel();
        CantVendidaFord = new javax.swing.JLabel();
        CantVendidaToyota = new javax.swing.JLabel();
        TotalMercedes = new javax.swing.JLabel();
        TotalBMW = new javax.swing.JLabel();
        TotalHonda = new javax.swing.JLabel();
        TotalFord = new javax.swing.JLabel();
        TotalToyota = new javax.swing.JLabel();
        TitMercedes = new javax.swing.JLabel();
        TitToyota = new javax.swing.JLabel();
        TitBMW = new javax.swing.JLabel();
        TitFord = new javax.swing.JLabel();
        TitHonda = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TituloPanel.setFont(new java.awt.Font("Adobe Gothic Std B", 0, 48)); // NOI18N
        TituloPanel.setForeground(new java.awt.Color(51, 0, 0));
        TituloPanel.setText("|      Empleados");
        TituloPanel.setAlignmentY(0.0F);
        getContentPane().add(TituloPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png"))); // NOI18N
        Boton_Empleados.setBorderPainted(false);
        Boton_Empleados.setContentAreaFilled(false);
        Boton_Empleados.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53brillo.png"))); // NOI18N
        Boton_Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_EmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 80, 70));

        Boton_Ventas.setBackground(new java.awt.Color(51, 0, 0));
        Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png"))); // NOI18N
        Boton_Ventas.setBorderPainted(false);
        Boton_Ventas.setContentAreaFilled(false);
        Boton_Ventas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53brillo.png"))); // NOI18N
        Boton_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_VentasActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 80, 70));

        Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png"))); // NOI18N
        Boton_Inventario.setBorderPainted(false);
        Boton_Inventario.setContentAreaFilled(false);
        Boton_Inventario.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53brillo.png"))); // NOI18N
        Boton_Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_InventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 80, 70));

        Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53.png"))); // NOI18N
        Modo.setContentAreaFilled(false);
        Modo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53brillo.png"))); // NOI18N
        Modo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModoActionPerformed(evt);
            }
        });
        getContentPane().add(Modo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 60, 60));

        InfoBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/infosinfondox53.png"))); // NOI18N
        InfoBTN.setContentAreaFilled(false);
        InfoBTN.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/infosinfondox53brillo.png"))); // NOI18N
        InfoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfoBTNActionPerformed(evt);
            }
        });
        getContentPane().add(InfoBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 623, 60, 60));

        jLabel2.setBackground(new java.awt.Color(255, 204, 204));
        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 2, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 204));
        jLabel2.setText("ARCHEY");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 140, -1));

        jLabel14.setBackground(new java.awt.Color(51, 0, 0));
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 60));

        jLabel13.setBackground(new java.awt.Color(51, 0, 0));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, 660));

        PanelEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        PanelEmpleados.setPreferredSize(new java.awt.Dimension(1240, 700));
        PanelEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FrameEliminar.setVisible(true);
        FrameEliminar.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelEliminarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        PanelEliminarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setText("Nombre:");
        PanelEliminarEmpleado.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        fnombreE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnombreEActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(fnombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 280, -1));

        cerrar1.setText("Cerrar");
        cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar1ActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        BotonEliminarEmpleados.setText("Eliminar");
        BotonEliminarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarEmpleadosActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(BotonEliminarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 404, -1, -1));

        BotonLimpiar1.setText("Limpiar");
        BotonLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiar1ActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(BotonLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 404, -1, -1));

        jLabel33.setText("Poner Imagen de Empleado");
        PanelEliminarEmpleado.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 170, 210));

        error9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error9.setForeground(new java.awt.Color(255, 0, 0));
        error9.setText("(!) El nombre no debe contener números ni caracteres especiales.");
        PanelEliminarEmpleado.add(error9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 290, -1));

        FrameEliminar.getContentPane().add(PanelEliminarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        PanelEmpleados.add(FrameEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 700, -1));

        FrameAgregar.setVisible(true);

        PanelAgregarEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        PanelAgregarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Nombre:");
        PanelAgregarEmpleado.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 89, -1, -1));

        jLabel6.setText("Cédula:");
        PanelAgregarEmpleado.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 135, -1, -1));

        jLabel7.setText("Cargo:");
        PanelAgregarEmpleado.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 175, -1, -1));

        jLabel8.setText("Teléfono:");
        PanelAgregarEmpleado.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 215, -1, -1));

        jLabel9.setText("Fecha de Ingreso:");
        PanelAgregarEmpleado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 258, -1, -1));

        jLabel10.setText("Salario Fijo Mensual:");
        PanelAgregarEmpleado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 298, -1, -1));

        jLabel11.setText("Salario+Comisiones:");
        PanelAgregarEmpleado.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 335, -1, 22));

        fnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnombreActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(fnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 86, 280, -1));
        PanelAgregarEmpleado.add(fcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 132, 280, -1));
        PanelAgregarEmpleado.add(fcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 172, 280, -1));
        PanelAgregarEmpleado.add(ffechaingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 255, 280, -1));
        PanelAgregarEmpleado.add(ftelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 212, 280, -1));
        PanelAgregarEmpleado.add(fsalariofijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 295, 280, -1));
        PanelAgregarEmpleado.add(fsalariocomisiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(166, 335, 280, -1));

        cerrar.setText("Cerrar");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        BotonAgregarEmpleados.setText("Agregar");
        BotonAgregarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarEmpleadosActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(BotonAgregarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(456, 404, -1, -1));

        BotonLimpiar.setText("Limpiar");
        BotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiarActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(BotonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 404, -1, -1));

        jLabel12.setText("Poner Imagen de Empleado");
        PanelAgregarEmpleado.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 170, 210));

        error7.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error7.setForeground(new java.awt.Color(255, 0, 0));
        error7.setText("(!) Salario debe contener solo numeros mayores a cero");
        PanelAgregarEmpleado.add(error7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 290, -1));

        error1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error1.setForeground(new java.awt.Color(255, 0, 0));
        error1.setText("(!) El nombre no debe contener números ni caracteres especiales.");
        PanelAgregarEmpleado.add(error1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 290, -1));

        error2.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error2.setForeground(new java.awt.Color(255, 0, 0));
        error2.setText("(!) La cédula debe contener solo números.");
        PanelAgregarEmpleado.add(error2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 158, 290, 10));

        error3.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error3.setForeground(new java.awt.Color(255, 0, 0));
        error3.setText("(!) Este campo no debe contener números ni caracteres especiales.");
        PanelAgregarEmpleado.add(error3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 194, 300, -1));

        error4.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error4.setForeground(new java.awt.Color(255, 0, 0));
        error4.setText("(!) El telefono solo debe contener 10 digitos numéricos.");
        PanelAgregarEmpleado.add(error4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 290, 20));

        error5.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error5.setForeground(new java.awt.Color(255, 0, 0));
        error5.setText("(!) Formato de fecha no válido. Recuerde (DD/MM/AA)");
        PanelAgregarEmpleado.add(error5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 290, 10));

        error6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error6.setForeground(new java.awt.Color(255, 0, 0));
        error6.setText("(!) Salario debe contener solo numeros mayores a cero");
        PanelAgregarEmpleado.add(error6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 290, 10));

        javax.swing.GroupLayout FrameAgregarLayout = new javax.swing.GroupLayout(FrameAgregar.getContentPane());
        FrameAgregar.getContentPane().setLayout(FrameAgregarLayout);
        FrameAgregarLayout.setHorizontalGroup(
            FrameAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelAgregarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        FrameAgregarLayout.setVerticalGroup(
            FrameAgregarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FrameAgregarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelAgregarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelEmpleados.add(FrameAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 680, -1));

        jScrollPane1.setBackground(new java.awt.Color(51, 0, 0));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setFont(new java.awt.Font("Adobe Gothic Std B", 1, 12)); // NOI18N
        jScrollPane1.setOpaque(false);

        TablaEMPLEADOS.setAutoCreateRowSorter(true);
        TablaEMPLEADOS.setBackground(new java.awt.Color(255, 204, 204));
        TablaEMPLEADOS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        TablaEMPLEADOS.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TablaEMPLEADOS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "C.C.", "Cargo", "Telf. Contacto", "Fecha Ingreso", "Salario Mensual", "Salario+Comisiones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaEMPLEADOS.setColumnSelectionAllowed(true);
        TablaEMPLEADOS.setGridColor(new java.awt.Color(255, 255, 255));
        TablaEMPLEADOS.setIntercellSpacing(new java.awt.Dimension(5, 5));
        TablaEMPLEADOS.setRowHeight(40);
        TablaEMPLEADOS.setSelectionBackground(new java.awt.Color(255, 153, 153));
        TablaEMPLEADOS.setSelectionForeground(new java.awt.Color(102, 0, 0));
        TablaEMPLEADOS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaEMPLEADOS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaEMPLEADOS.setShowGrid(false);
        TablaEMPLEADOS.setShowVerticalLines(true);
        TablaEMPLEADOS.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(TablaEMPLEADOS);
        TablaEMPLEADOS.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        PanelEmpleados.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 244, 1101, -1));
        jScrollPane1.getAccessibleContext().setAccessibleName("");

        BotonOrdenar.setText("Ordenar por nombre");
        BotonOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, -1, -1));

        BotonOrdenarSalario.setText("Ordenar por Salario");
        BotonOrdenarSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarSalarioActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonOrdenarSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 200, -1, -1));

        BotonSinOrdenar.setText("Sin ordenar");
        PanelEmpleados.add(BotonSinOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 220, -1, -1));

        BotonparaAgregar.setText("+ (Agregar nuevo registro)");
        BotonparaAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonparaAgregarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonparaAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 210, -1, -1));

        BotonparaEliminar.setText("- (Eliminar registro)");
        BotonparaEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonparaEliminarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonparaEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 140, -1));

        LabelFondoBorroso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/desenfocadobeta.png"))); // NOI18N
        PanelEmpleados.add(LabelFondoBorroso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1340, 590));

        getContentPane().add(PanelEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        PanelVentas.setBackground(new java.awt.Color(255, 255, 255));
        PanelVentas.setPreferredSize(new java.awt.Dimension(1240, 700));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jScrollPane3.setForeground(new java.awt.Color(51, 0, 0));
        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setFont(new java.awt.Font("Adobe Gothic Std B", 1, 14)); // NOI18N

        TablaVENTAS.setBackground(new java.awt.Color(255, 204, 204));
        TablaVENTAS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        TablaVENTAS.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TablaVENTAS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Vendedor", "C.C.", "Tipo Auto", "Codigo", "Monto de Venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVENTAS.setDoubleBuffered(true);
        TablaVENTAS.setGridColor(new java.awt.Color(255, 255, 255));
        TablaVENTAS.setOpaque(false);
        TablaVENTAS.setRequestFocusEnabled(false);
        TablaVENTAS.setRowHeight(40);
        TablaVENTAS.setSelectionBackground(new java.awt.Color(255, 153, 153));
        TablaVENTAS.setSelectionForeground(new java.awt.Color(153, 0, 0));
        TablaVENTAS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaVENTAS.setShowGrid(false);
        TablaVENTAS.setShowVerticalLines(true);
        jScrollPane3.setViewportView(TablaVENTAS);
        TablaVENTAS.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaVENTAS.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout PanelVentasLayout = new javax.swing.GroupLayout(PanelVentas);
        PanelVentas.setLayout(PanelVentasLayout);
        PanelVentasLayout.setHorizontalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        PanelVentasLayout.setVerticalGroup(
            PanelVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelVentasLayout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        getContentPane().add(PanelVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        PanelInventario.setBackground(new java.awt.Color(255, 255, 255));
        PanelInventario.setPreferredSize(new java.awt.Dimension(1240, 700));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 204, 204));
        jLabel27.setText("Valor Total: ");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1190, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 204, 204));
        jLabel26.setText("Valor Total: ");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 920, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 204, 204));
        jLabel25.setText("Cantidad Vendida :");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1190, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 204, 204));
        jLabel24.setText("Cantidad Vendida :");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 920, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 204, 204));
        jLabel23.setText("Valor Total: ");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 650, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 204, 204));
        jLabel22.setText("Cantidad Vendida :");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 650, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 204, 204));
        jLabel21.setText("Valor Total: ");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 204, 204));
        jLabel20.setText("Cantidad Vendida :");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 204));
        jLabel19.setText("Valor Total: ");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 204, 204));
        jLabel18.setText("Cantidad Vendida :");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, -1, -1));

        CantVendidaMercedes.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaMercedes.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaMercedes.setText("xx");
        jPanel1.add(CantVendidaMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1230, 160, 60));

        CantVendidaBMW.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaBMW.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaBMW.setText("xx");
        jPanel1.add(CantVendidaBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 960, 160, 60));

        CantVendidaHonda.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaHonda.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaHonda.setText("xx");
        jPanel1.add(CantVendidaHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 700, 160, 60));

        CantVendidaFord.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaFord.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaFord.setText("xx");
        jPanel1.add(CantVendidaFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 160, 60));

        CantVendidaToyota.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaToyota.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaToyota.setText("xx");
        jPanel1.add(CantVendidaToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 160, 60));

        TotalMercedes.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalMercedes.setForeground(new java.awt.Color(255, 255, 255));
        TotalMercedes.setText("$XXXXX");
        jPanel1.add(TotalMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1230, 350, 60));

        TotalBMW.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalBMW.setForeground(new java.awt.Color(255, 255, 255));
        TotalBMW.setText("$XXXXX");
        jPanel1.add(TotalBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 960, 350, 60));

        TotalHonda.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalHonda.setForeground(new java.awt.Color(255, 255, 255));
        TotalHonda.setText("$XXXXX");
        jPanel1.add(TotalHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 700, 350, 60));

        TotalFord.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalFord.setForeground(new java.awt.Color(255, 255, 255));
        TotalFord.setText("$XXXXX");
        jPanel1.add(TotalFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, 350, 60));

        TotalToyota.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalToyota.setForeground(new java.awt.Color(255, 255, 255));
        TotalToyota.setText("$XXXXX");
        jPanel1.add(TotalToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, 350, 60));

        TitMercedes.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitMercedes.setForeground(new java.awt.Color(255, 255, 255));
        TitMercedes.setText("MERCEDES - BENS");
        jPanel1.add(TitMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1110, -1, -1));

        TitToyota.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitToyota.setForeground(new java.awt.Color(255, 255, 255));
        TitToyota.setText("TOYOTA");
        jPanel1.add(TitToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        TitBMW.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitBMW.setForeground(new java.awt.Color(255, 255, 255));
        TitBMW.setText("BMW");
        jPanel1.add(TitBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 850, -1, -1));

        TitFord.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitFord.setForeground(new java.awt.Color(255, 255, 255));
        TitFord.setText("FORD");
        jPanel1.add(TitFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, -1));

        TitHonda.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitHonda.setForeground(new java.awt.Color(255, 255, 255));
        TitHonda.setText("HONDA");
        jPanel1.add(TitHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 570, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bmwx430.png"))); // NOI18N
        jLabel31.setOpaque(true);
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 827, 430, 220));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mercedesx430.png"))); // NOI18N
        jLabel30.setOpaque(true);
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1094, 430, 220));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hondax430.png"))); // NOI18N
        jLabel29.setOpaque(true);
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 557, 430, 220));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fordx430.png"))); // NOI18N
        jLabel28.setOpaque(true);
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 430, 220));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/toyotax430.png"))); // NOI18N
        jLabel17.setOpaque(true);
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 430, 210));

        jLabel4.setBackground(new java.awt.Color(102, 0, 0));
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 542, 1077, 250));

        jLabel1.setBackground(new java.awt.Color(102, 0, 0));
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 274, 1077, 250));

        jLabel3.setBackground(new java.awt.Color(102, 0, 0));
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 6, 1077, 250));

        jLabel15.setBackground(new java.awt.Color(102, 0, 0));
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 810, 1077, 250));

        jLabel16.setBackground(new java.awt.Color(102, 0, 0));
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 1078, 1077, 250));

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout PanelInventarioLayout = new javax.swing.GroupLayout(PanelInventario);
        PanelInventario.setLayout(PanelInventarioLayout);
        PanelInventarioLayout.setHorizontalGroup(
            PanelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        PanelInventarioLayout.setVerticalGroup(
            PanelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInventarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(PanelInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    JPanel Actual;

    private void Boton_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_EmpleadosActionPerformed

        if (Actual != PanelEmpleados) {
            PanelEmpleados.setVisible(true);
            PanelEmpleados.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelEmpleados;

            //System.out.println(Actual); } *
            TituloPanel.setText("|  Empleados");
//             Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
//            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
//            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondox63.png")));
//     
            CambiarBotones("PanelEmpleados");
        }
        /**
         * *
         * PanelVentas.setVisible(false); PanelInventario.setVisible(false);
         * PanelEmpleados.setVisible(true); *
         */


    }//GEN-LAST:event_Boton_EmpleadosActionPerformed

    private void Boton_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_VentasActionPerformed
        if (Actual != PanelVentas) {
            PanelVentas.setVisible(true);
            PanelVentas.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelVentas;

            TituloPanel.setText("|  Ventas");
            CambiarBotones("PanelVentas");
//             Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
//            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox63.png")));
//            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
//      
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_VentasActionPerformed

    private void Boton_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_InventarioActionPerformed
        if (Actual != PanelInventario) {
            PanelInventario.setVisible(true);
            PanelInventario.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelInventario;
            TituloPanel.setText("|  Inventario");
            CambiarBotones("PanelInventario");
//            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox63.png")));
//            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
//            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
//       
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_InventarioActionPerformed

    private void InfoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoBTNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InfoBTNActionPerformed

    private void BotonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarActionPerformed

    }//GEN-LAST:event_BotonOrdenarActionPerformed

    private void BotonOrdenarSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarSalarioActionPerformed

    }//GEN-LAST:event_BotonOrdenarSalarioActionPerformed

    private void BotonparaAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonparaAgregarActionPerformed
        FrameAgregar.setVisible(true);
        TablaEMPLEADOS.setVisible(false);
        jScrollPane1.setVisible(false);
        LabelFondoBorroso.setVisible(true);
        BotonparaAgregar.setVisible(false);
        BotonparaEliminar.setVisible(false);
        BotonOrdenar.setVisible(false);
        BotonOrdenarSalario.setVisible(false);
        BotonSinOrdenar.setVisible(false);


    }//GEN-LAST:event_BotonparaAgregarActionPerformed

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed
        LabelFondoBorroso.setVisible(false);
        jScrollPane1.setVisible(true);
        TablaEMPLEADOS.setVisible(true);
        FrameAgregar.setVisible(false);
        LabelFondoBorroso.setVisible(false);
        BotonparaAgregar.setVisible(true);
        BotonparaEliminar.setVisible(true);
        BotonOrdenar.setVisible(true);
        BotonOrdenarSalario.setVisible(true);
        BotonSinOrdenar.setVisible(true);
        Limpiar();
    }//GEN-LAST:event_cerrarActionPerformed

    private void fnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnombreActionPerformed

    private void BotonAgregarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarEmpleadosActionPerformed
        AgregarEmpleados("Empleados");
        Scanner sc = new Scanner(System.in);
        LeerNormal(sc, "Empleados", TablaEMPLEADOS);
        sc.close();
    }//GEN-LAST:event_BotonAgregarEmpleadosActionPerformed

    private void BotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiarActionPerformed
        Limpiar();        Limpiar();    }//GEN-LAST:event_BotonLimpiarActionPerformed

    private void BotonparaEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonparaEliminarActionPerformed
        FrameEliminar.setVisible(true);
        LabelFondoBorroso.setVisible(true);
        TablaEMPLEADOS.setVisible(false);
        jScrollPane1.setVisible(false);
        BotonparaAgregar.setVisible(false);
        BotonparaEliminar.setVisible(false);
        BotonOrdenar.setVisible(false);
        BotonOrdenarSalario.setVisible(false);
        BotonSinOrdenar.setVisible(false);

    }//GEN-LAST:event_BotonparaEliminarActionPerformed

    private void fnombreEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnombreEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnombreEActionPerformed

    private void cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrar1ActionPerformed
        LabelFondoBorroso.setVisible(false);
        jScrollPane1.setVisible(true);
        TablaEMPLEADOS.setVisible(true);
        FrameAgregar.setVisible(false);
        FrameEliminar.setVisible(false);
        LabelFondoBorroso.setVisible(false);
        BotonparaAgregar.setVisible(true);
        BotonparaEliminar.setVisible(true);
        BotonOrdenar.setVisible(true);
        BotonOrdenarSalario.setVisible(true);
        BotonSinOrdenar.setVisible(true);
        Limpiar();
    }//GEN-LAST:event_cerrar1ActionPerformed
//BOTON ELIMINAR EMPLEADOS
    private void BotonEliminarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarEmpleadosActionPerformed
        Scanner sc = new Scanner(System.in);
        EliminarRegistro(sc, "Empleados", TablaEMPLEADOS);
        LeerNormal(sc, "Empleados", TablaEMPLEADOS);
        sc.close();
    }//GEN-LAST:event_BotonEliminarEmpleadosActionPerformed

    private void BotonLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiar1ActionPerformed
        Limpiar();
        Limpiar();
    }//GEN-LAST:event_BotonLimpiar1ActionPerformed

    boolean Nocturno;
    Color fondoclaro = Color.decode("#FFFFFF");//blanco
    Color fondooscuro = Color.decode("#2A2333");
    Color rojooscuro = Color.decode("#330000");

    private void ModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModoActionPerformed
        if (Nocturno == true) {//esta oscuro
            System.out.println("Pasar Modo claro");
            TituloPanel.setForeground(rojooscuro);
            PanelEmpleados.setBackground(fondoclaro);
            PanelVentas.setBackground(fondoclaro);
            PanelInventario.setBackground(fondoclaro);

            Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53.png")));
            
          
            Nocturno = false;
        } else {//estaclaro
            System.out.println("Pasar Modo oscuro");
            TituloPanel.setForeground(fondoclaro);
            
            PanelEmpleados.setBackground(fondooscuro);
            PanelVentas.setBackground(fondooscuro);
            PanelInventario.setBackground(fondooscuro);

            Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/solsinfondpx53.png")));
            
            Nocturno = true;
        }
        TituloPanel.repaint();
    }//GEN-LAST:event_ModoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAgregarEmpleados;
    private javax.swing.JButton BotonEliminarEmpleados;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JButton BotonLimpiar1;
    private javax.swing.JRadioButton BotonOrdenar;
    private javax.swing.JRadioButton BotonOrdenarSalario;
    private javax.swing.JRadioButton BotonSinOrdenar;
    private javax.swing.JButton Boton_Empleados;
    private javax.swing.JButton Boton_Inventario;
    private javax.swing.JButton Boton_Ventas;
    private javax.swing.JButton BotonparaAgregar;
    private javax.swing.JButton BotonparaEliminar;
    private javax.swing.JLabel CantVendidaBMW;
    private javax.swing.JLabel CantVendidaFord;
    private javax.swing.JLabel CantVendidaHonda;
    private javax.swing.JLabel CantVendidaMercedes;
    private javax.swing.JLabel CantVendidaToyota;
    private javax.swing.JInternalFrame FrameAgregar;
    private javax.swing.JInternalFrame FrameEliminar;
    private javax.swing.JButton InfoBTN;
    private javax.swing.JLabel LabelFondoBorroso;
    private javax.swing.JButton Modo;
    private javax.swing.JPanel PanelAgregarEmpleado;
    private javax.swing.JPanel PanelEliminarEmpleado;
    private javax.swing.JPanel PanelEmpleados;
    private javax.swing.JPanel PanelInventario;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JTable TablaEMPLEADOS;
    private javax.swing.JTable TablaVENTAS;
    private javax.swing.JLabel TitBMW;
    private javax.swing.JLabel TitFord;
    private javax.swing.JLabel TitHonda;
    private javax.swing.JLabel TitMercedes;
    private javax.swing.JLabel TitToyota;
    private javax.swing.JLabel TituloPanel;
    private javax.swing.JLabel TotalBMW;
    private javax.swing.JLabel TotalFord;
    private javax.swing.JLabel TotalHonda;
    private javax.swing.JLabel TotalMercedes;
    private javax.swing.JLabel TotalToyota;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton cerrar1;
    private javax.swing.JLabel error1;
    private javax.swing.JLabel error2;
    private javax.swing.JLabel error3;
    private javax.swing.JLabel error4;
    private javax.swing.JLabel error5;
    private javax.swing.JLabel error6;
    private javax.swing.JLabel error7;
    private javax.swing.JLabel error9;
    private javax.swing.JTextField fcargo;
    private javax.swing.JTextField fcedula;
    private javax.swing.JTextField ffechaingreso;
    private javax.swing.JTextField fnombre;
    private javax.swing.JTextField fnombreE;
    private javax.swing.JTextField fsalariocomisiones;
    private javax.swing.JTextField fsalariofijo;
    private javax.swing.JTextField ftelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
