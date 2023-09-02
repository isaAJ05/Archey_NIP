
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

//SUBRUTINAS: EMPLEADOS
    //01 Subrutina para llenar el archivo Empleados
    public static void agregarDatosEmpleados(String file_name) {
        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", false); ///Se crea un archivo donde se le escribiran los registros
            PrintWriter registro = new PrintWriter(outFile);

            //Matriz para crear Archivo Existente (EMPLEADOS]
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
            for (String[] fila : empleados) { //Llenar cada campo con cada columna
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
                model.setRowCount(0); //Vacia todas las filas de la tabla 

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
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));//Leer archivo
                String line = null; 
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);//Limpliar la tabla

                while ((line = br.readLine()) != null) {//Hasta que no llegue a EOF
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

    //04 Subrutina para Agregar Datos de Empleados
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

    //05 Subrutina para eliminar registros de empleados
    public void EliminarRegistro(Scanner sc, String file_name, JTable tabla) {
        File original = new File(file_name + ".txt"); //lamar archivo
        //Se hace una copiar del original, haciendo una excepcion de registro que se dese eliminar
        try {
            FileReader FR = new FileReader(file_name + ".txt"); 
            BufferedReader BR = new BufferedReader(FR); //LEER
            FileWriter FW = new FileWriter("EmpleadosTemp.txt", true);//Copia temporal
            BufferedWriter BW = new BufferedWriter(FW); //Leer copiar temporal
            String linea, borrar_nombre = fnombreE.getText(); //Se le pide por el nombre del empleado
            boolean encontrado = false;//Indicador
            
            //Se escriben los que no sean ese nombre en le temporal
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
            //Cerrar 
            BR.close();
            BW.close();
            //Si se encontro renombrar el temporal como si fuera el ooriginal
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
    

//SUBRUTINAS VENTAS

    //06 Subrutina para Agregar Datos de Ventas (Segun codigo de carro)
    //Variables Globales
    String CodigoAux;
    boolean existecod= false;
    
    public void AgregarVentas(String file_name) {
        /**
         * (Nombre del empleado, cédula empleado, tipo o marca del auto, Codigo del auto
         *  monto o precio por el auto)
         */
        String Nombre, Cedula, Tipo, Codigo, Monto;

        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", true);  //Archivo.txt
            // if false the file will be deleted and created everytime
            // if true the registers will be appended to the end of the file
            PrintWriter registrar_ventas = new PrintWriter(outFile); //Se escribira en el archivo de ventas
            Nombre = fvendedor.getText();
            Cedula = fcedulav.getText();
            Tipo = ComboBox.getSelectedItem().toString();
            Codigo = fcodigo.getText();
            Monto = fmonto.getText();

            //Llamar a funciones de validaciones
            //Validacion1: verifica que no haya campos vacios: devuelven un bool
            if (validacionventa1(Nombre, Cedula, Tipo, Codigo, Monto)) {//if true
                //Validacion2: verifica que cada campo no tenga algun error de formato: devuelve un bool
                if (validacionventa2(Nombre, Cedula, Codigo, Monto)) {//If true
                    CodigoAux=Codigo;//Asignar codigo ingresado a variable global
                    Scanner sc = new Scanner (System.in);
                    LeerVentasC(sc,file_name);//llamar subrutina que leea registro y compare los codigos
                    sc.close();
                    if(existecod==true){//si el codigo existe mostrar mensaje de error
                        error3v.setText("(!) Codigo existente");
                    }else{//si no existe registrar la venta
                       registrar_ventas.println(Nombre + "\t" + Cedula + "\t" + Tipo + "\t" + Codigo + "\t" + Monto);
                       RelacionAutos(Tipo,Monto);
                    sonido("/Sonidos/correcto.wav");//implementacion de sonidos
                    JOptionPane.showMessageDialog(null, "Los datos se han agregado satisfactoriamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE); 
                    }
                    
                } else {
                    sonido("/Sonidos/error.wav");
                }
            }
            //Cerrar
            registrar_ventas.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar los datos.", "Error", JOptionPane.ERROR_MESSAGE);

            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }

    }
   
    //07 Subrutina PARA CONFIRMAR QUE NO EXISTA CODIGO DEL AUTO
    public void LeerVentasC(Scanner sc, String file_name) {
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt")); //Leer archivo
                String line = null;
                //Verifica que el codigo este en el archivo
                while ((line = br.readLine()) != null && existecod==false) {
                    String temp[] = line.split("\t");
                    if (temp[2]==CodigoAux){
                        existecod=true;
                    }
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
    
    //Variables Globales
    int cantT , cantF, cantH, cantB, cantM;
    double MontoT , MontoF, MontoH, MontoB, MontoM;
    JPanel Actual;
    
    //08 Subrutina para relacionar autos vendidos con el inventario
    public void RelacionAutos(String Tipo, String Monto){
                switch (Tipo) {
                    case "Toyota":
                        cantT++; //cONTADOR
                        CantVendidaToyota.setText(String.valueOf(cantT)); //IMPRIMIR
                        MontoT+=Double.parseDouble(Monto); //ACUMULADOR
                        TotalToyota.setText(String.valueOf(MontoT)); //IMPRIMIR
                        break;
                    case "Ford":
                        cantF++;
                        CantVendidaFord.setText(String.valueOf(cantF));
                        MontoF+=Double.parseDouble(Monto);
                        TotalFord.setText(String.valueOf(MontoF));
                        break;
                    case "Honda":
                        cantH++;
                        CantVendidaHonda.setText(String.valueOf(cantH));
                        MontoH+=Double.parseDouble(Monto);
                        TotalHonda.setText(String.valueOf(MontoH));
                        break;
                        case "BMW":
                        cantB++;
                        CantVendidaBMW.setText(String.valueOf(cantB));
                        MontoB+=Double.parseDouble(Monto);
                        TotalBMW.setText(String.valueOf(MontoB));
                        break;
                        case "Mercedes":
                        cantM++;
                        CantVendidaMercedes.setText(String.valueOf(cantM));
                        MontoM+=Double.parseDouble(Monto);
                        TotalMercedes.setText(String.valueOf(MontoM));
                        break;
                    default:
                        //La empresa no maneja este tipo de auto
                        break;
                }
            }

    //09 Subrutina para agregar lo del porcentaje
    //Actualizar el campo salario más comisiones en el archivo de Empleados, 
    //teniendo en cuenta que si realizó una venta superior a $30 millones en el mes, recibirá un 2% de comisiones sobre la venta total. 

    //10 Subrutina para limpiar campos
    public void Limpiar() {
        fnombre.setText("");
        fcedula.setText("");
        fcargo.setText("");
        ftelefono.setText("");
        ffechaingreso.setText("");
        fsalariofijo.setText("");
        fsalariocomisiones.setText("");
    }

    //11 SUBRUTINA PARA APLICAR SONIDO
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
    // 01 funcion para validar si estan vacios los campos EMPLEADOS
    public boolean validacion1(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty() && !c4.isEmpty() && !c5.isEmpty() && !c6.isEmpty() && !c7.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // 01.1 funcion para validar si estan vacios los campos VENTAS
    public boolean validacionventa1(String c1, String c2, String c3, String c4, String c5) {
        if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty() && !c4.isEmpty() && !c5.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    //02 Funcion para validar 2 EMPLEADOS
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

    //02 Funcion para validar 2 VENTAS
    // Nombre, Cedula, Codigo, Monto
    public boolean validacionventa2(String c1, String c2, String c4, String c5) {

        // Validacion Nombre
        boolean validon = true;
        for (char c : c1.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                validon = false;
                break;
            }
        }
        if (!validon) {
            error1v.setVisible(true);
            return false;
        } else {
            error1v.setVisible(false);
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
            error2v.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            error2v.setVisible(false); // Ocultar mensaje de error
        }
        //Validacion Codigo
        boolean validocod = true;
        for (char c : c4.toCharArray()) {
            if (!Character.isDigit(c)) {
                validoc = false;
                break;
            }
        }
        if (!validocod) {
            error3v.setText("El código debe tener solo numeros");
            error3v.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            error3v.setVisible(false); // Ocultar mensaje de error
        }

        // Validación salario
        boolean validomonto = true;
        for (char c : c5.toCharArray()) {
            if (!Character.isDigit(c)) {
                validomonto = false;
                break;
            }
        }

        try {
            double monto = Double.parseDouble(c5);
            if (monto < 0) {
                error4v.setVisible(false);
            }
        } catch (NumberFormatException e) {
            error4v.setVisible(true); // Mostrar mensaje de error
            return false;
        }

        if (!validomonto) {
            error4v.setVisible(true); // Mostrar mensaje de error
            return false;
        } else {
            error4v.setVisible(false); // Ocultar mensaje de error  

        }

        return true;

    }

    //INICIO PRINCIPAL
    public Principal() {
        initComponents();
        //INTERFAZ
        //setIconImage(new ImageIcon(getClass().getResource("Imagenes/icon.png")).getImage());
        //this.setTitle(" ");
        this.setLocationRelativeTo(null); //centrar ventana
        PanelEmpleados.setVisible(false); //Visibilidad de paneles
        PanelInventario.setVisible(false);
        PanelVentas.setVisible(true);
        PanelEmpleados.setEnabled(false);
        PanelVentas.setEnabled(true);
        Nocturno = false; //Modo nocturno off
        PanelInventario.setEnabled(false);
        Actual = PanelVentas; //Conteo de panel visible
        CambiarBotones("PanelVentas"); //Subrutina para los botones
        TituloPanel.setText("|  Ventas"); //Titulo del panel principal 
        
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
        //Si se da click al boton se llama a la funcion de ordenar
        BotonOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerOrdenado(sc, "Empleados", TablaEMPLEADOS, "Nombre"); //Se ordena por Nombre
                sc.close();
            }
        });
        BotonOrdenarSalario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerOrdenado(sc, "Empleados", TablaEMPLEADOS, "Salario"); //Se ordena por salario
                sc.close();
            }
        });
        BotonSinOrdenar.addActionListener(new ActionListener() { //SIn orden en especifico
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerNormal(sc, "Empleados", TablaEMPLEADOS);
                sc.close();
            }
        });
       
        //No visible
        FrameAgregar.setVisible(false);
        FrameEliminar.setVisible(false);
        FrameAgregarVenta.setVisible(false);
        LabelFondoBorroso.setVisible(false);
        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);
        error4.setVisible(false);
        error5.setVisible(false);
        error6.setVisible(false);
        error7.setVisible(false);

    }
    
    //DECORACION 
    
    //Subrutina para el cambio de paneles, que cambien al lo contrario de que estan
    public static void CambiaEstadoPANEL(JPanel p) {
        p.setVisible(!p.isVisible());
        p.setEnabled(!p.isEnabled());
    }

    //Subrutina para que los botones tengan en cuenta el panel que se este mostrando
    public void CambiarBotones(String Actual) {
        if (Actual.equalsIgnoreCase("PanelInventario")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox70.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
        }
        if (Actual.equalsIgnoreCase("PanelVentas")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox70.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
        }
        if (Actual.equalsIgnoreCase("PanelEmpleados")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondox70.png")));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Boton_Empleados = new javax.swing.JButton();
        Boton_Ventas = new javax.swing.JButton();
        Boton_Inventario = new javax.swing.JButton();
        Modo = new javax.swing.JButton();
        InfoBTN = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TituloPanel = new javax.swing.JLabel();
        PanelVentas = new javax.swing.JPanel();
        FrameAgregarVenta = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        fmonto = new javax.swing.JTextField();
        fvendedor = new javax.swing.JTextField();
        fcedulav = new javax.swing.JTextField();
        fcodigo = new javax.swing.JTextField();
        ComboBox = new javax.swing.JComboBox<>();
        cerraragregarventa = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        error1v = new javax.swing.JLabel();
        error2v = new javax.swing.JLabel();
        error3v = new javax.swing.JLabel();
        error4v = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaVENTAS = new javax.swing.JTable();
        BotonRegistrarVenta = new javax.swing.JButton();
        BotonEliminarVenta = new javax.swing.JButton();
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
        PanelInventario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        InventarioSubPanel = new javax.swing.JPanel();
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

        Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png"))); // NOI18N
        Boton_Empleados.setBorderPainted(false);
        Boton_Empleados.setContentAreaFilled(false);
        Boton_Empleados.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondox45.png"))); // NOI18N
        Boton_Empleados.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53brillo.png"))); // NOI18N
        Boton_Empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_EmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Empleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 80, 70));

        Boton_Ventas.setBackground(new java.awt.Color(51, 0, 0));
        Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png"))); // NOI18N
        Boton_Ventas.setBorderPainted(false);
        Boton_Ventas.setContentAreaFilled(false);
        Boton_Ventas.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox45.png"))); // NOI18N
        Boton_Ventas.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53brillo.png"))); // NOI18N
        Boton_Ventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_VentasActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Ventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 80, 70));

        Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png"))); // NOI18N
        Boton_Inventario.setBorderPainted(false);
        Boton_Inventario.setContentAreaFilled(false);
        Boton_Inventario.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox45.png"))); // NOI18N
        Boton_Inventario.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53brillo.png"))); // NOI18N
        Boton_Inventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_InventarioActionPerformed(evt);
            }
        });
        getContentPane().add(Boton_Inventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 80, 70));

        Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53.png"))); // NOI18N
        Modo.setContentAreaFilled(false);
        Modo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53brillo.png"))); // NOI18N
        Modo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModoActionPerformed(evt);
            }
        });
        getContentPane().add(Modo, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 540, 70, 53));

        InfoBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/infosinfondox53.png"))); // NOI18N
        InfoBTN.setContentAreaFilled(false);
        InfoBTN.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/infosinfondox53brillo.png"))); // NOI18N
        InfoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InfoBTNActionPerformed(evt);
            }
        });
        getContentPane().add(InfoBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 80, 53));

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

        TituloPanel.setFont(new java.awt.Font("Adobe Gothic Std B", 0, 48)); // NOI18N
        TituloPanel.setForeground(new java.awt.Color(51, 0, 0));
        TituloPanel.setText("|      Empleados");
        TituloPanel.setAlignmentY(0.0F);
        getContentPane().add(TituloPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        PanelVentas.setBackground(new java.awt.Color(255, 255, 255));
        PanelVentas.setPreferredSize(new java.awt.Dimension(1240, 700));
        PanelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FrameAgregarVenta.setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setText("Monto de la venta:");

        jLabel35.setText("Nombre del vendedor:");

        jLabel36.setText("Cédula:");

        jLabel37.setText("Tipo de auto:");

        jLabel38.setText("Código del auto:");

        fvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fvendedorActionPerformed(evt);
            }
        });

        ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toyota", "Ford", "Honda", "Mercedes" }));
        ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxActionPerformed(evt);
            }
        });

        cerraragregarventa.setText("Cerrar");
        cerraragregarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerraragregarventaActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar");

        jButton3.setText("Limpiar");

        error1v.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error1v.setForeground(new java.awt.Color(255, 0, 0));
        error1v.setText("(!) El nombre no debe contener números ni caracteres especiales.");

        error2v.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error2v.setForeground(new java.awt.Color(255, 0, 0));
        error2v.setText("(!) La cédula debe contener solo números.");

        error3v.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error3v.setForeground(new java.awt.Color(255, 0, 0));
        error3v.setText("(!) Depende");

        error4v.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error4v.setForeground(new java.awt.Color(255, 0, 0));
        error4v.setText("(!)Monto debe contener solo numeros mayores a cero");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel35)
                        .addGap(18, 18, 18)
                        .addComponent(fvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(error3v, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel36)
                        .addGap(100, 100, 100)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(error1v, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fcedulav, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(error2v, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(error4v, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(cerraragregarventa))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(cerraragregarventa)
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(fvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(error1v)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fcedulav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(1, 1, 1)
                .addComponent(error2v, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(1, 1, 1)
                .addComponent(error3v, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(3, 3, 3)
                .addComponent(error4v)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout FrameAgregarVentaLayout = new javax.swing.GroupLayout(FrameAgregarVenta.getContentPane());
        FrameAgregarVenta.getContentPane().setLayout(FrameAgregarVentaLayout);
        FrameAgregarVentaLayout.setHorizontalGroup(
            FrameAgregarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrameAgregarVentaLayout.setVerticalGroup(
            FrameAgregarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelVentas.add(FrameAgregarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 130, 550, 430));

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
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        TablaVENTAS.setColumnSelectionAllowed(true);
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

        PanelVentas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 176, 1101, -1));

        BotonRegistrarVenta.setText("(+) Registrar Venta");
        BotonRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarVentaActionPerformed(evt);
            }
        });
        PanelVentas.add(BotonRegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 140, -1, -1));

        BotonEliminarVenta.setText("(-) Eliminar Registro");
        PanelVentas.add(BotonEliminarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 140, -1, -1));

        getContentPane().add(PanelVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

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

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondo.png"))); // NOI18N
        PanelEliminarEmpleado.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 170, 210));

        error9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        error9.setForeground(new java.awt.Color(255, 0, 0));
        error9.setText("(!) El nombre no debe contener números ni caracteres especiales.");
        PanelEliminarEmpleado.add(error9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 290, -1));

        FrameEliminar.getContentPane().add(PanelEliminarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 440));

        PanelEmpleados.add(FrameEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 670, 460));

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

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondo.png"))); // NOI18N
        PanelAgregarEmpleado.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 170, 210));

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

        BotonOrdenar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonOrdenar.setText("Ordenar por nombre");
        BotonOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, -1, -1));

        BotonOrdenarSalario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonOrdenarSalario.setText("Ordenar por Salario");
        BotonOrdenarSalario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonOrdenarSalarioActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonOrdenarSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 200, -1, -1));

        BotonSinOrdenar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonSinOrdenar.setText("Sin ordenar");
        PanelEmpleados.add(BotonSinOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 220, -1, -1));

        BotonparaAgregar.setBackground(new java.awt.Color(204, 0, 0));
        BotonparaAgregar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonparaAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BotonparaAgregar.setText("+ (Nuevo Registro)");
        BotonparaAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonparaAgregar.setBorderPainted(false);
        BotonparaAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonparaAgregarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonparaAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 191, 130, 40));

        BotonparaEliminar.setBackground(new java.awt.Color(204, 0, 0));
        BotonparaEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BotonparaEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BotonparaEliminar.setText("- (Eliminar Registro)");
        BotonparaEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonparaEliminar.setBorderPainted(false);
        BotonparaEliminar.setFocusPainted(false);
        BotonparaEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonparaEliminarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonparaEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 191, 140, 40));

        LabelFondoBorroso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/desenfocadobeta.png"))); // NOI18N
        PanelEmpleados.add(LabelFondoBorroso, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1340, 590));

        getContentPane().add(PanelEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        PanelInventario.setBackground(new java.awt.Color(255, 255, 255));
        PanelInventario.setPreferredSize(new java.awt.Dimension(1240, 700));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        InventarioSubPanel.setBackground(new java.awt.Color(255, 255, 255));
        InventarioSubPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 204, 204));
        jLabel27.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 1190, -1, -1));

        jLabel26.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 204, 204));
        jLabel26.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 920, -1, -1));

        jLabel25.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 204, 204));
        jLabel25.setText("Cantidad Vendida :");
        InventarioSubPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1190, -1, -1));

        jLabel24.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 204, 204));
        jLabel24.setText("Cantidad Vendida :");
        InventarioSubPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 920, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 204, 204));
        jLabel23.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 650, -1, -1));

        jLabel22.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 204, 204));
        jLabel22.setText("Cantidad Vendida :");
        InventarioSubPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 650, -1, -1));

        jLabel21.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 204, 204));
        jLabel21.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 370, -1, -1));

        jLabel20.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 204, 204));
        jLabel20.setText("Cantidad Vendida :");
        InventarioSubPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 370, -1, -1));

        jLabel19.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 204));
        jLabel19.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tekton Pro Ext", 2, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 204, 204));
        jLabel18.setText("Cantidad Vendida :");
        InventarioSubPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, -1, -1));

        CantVendidaMercedes.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaMercedes.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaMercedes.setText("xx");
        InventarioSubPanel.add(CantVendidaMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 1230, 160, 60));

        CantVendidaBMW.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaBMW.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaBMW.setText("xx");
        InventarioSubPanel.add(CantVendidaBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 960, 160, 60));

        CantVendidaHonda.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaHonda.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaHonda.setText("xx");
        InventarioSubPanel.add(CantVendidaHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 700, 160, 60));

        CantVendidaFord.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaFord.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaFord.setText("xx");
        InventarioSubPanel.add(CantVendidaFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 410, 160, 60));

        CantVendidaToyota.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        CantVendidaToyota.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaToyota.setText("xx");
        InventarioSubPanel.add(CantVendidaToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 160, 60));

        TotalMercedes.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalMercedes.setForeground(new java.awt.Color(255, 255, 255));
        TotalMercedes.setText("$XXXXX");
        InventarioSubPanel.add(TotalMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 1230, 350, 60));

        TotalBMW.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalBMW.setForeground(new java.awt.Color(255, 255, 255));
        TotalBMW.setText("$XXXXX");
        InventarioSubPanel.add(TotalBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 960, 350, 60));

        TotalHonda.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalHonda.setForeground(new java.awt.Color(255, 255, 255));
        TotalHonda.setText("$XXXXX");
        InventarioSubPanel.add(TotalHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 700, 350, 60));

        TotalFord.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalFord.setForeground(new java.awt.Color(255, 255, 255));
        TotalFord.setText("$XXXXX");
        InventarioSubPanel.add(TotalFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, 350, 60));

        TotalToyota.setFont(new java.awt.Font("Orator Std", 1, 48)); // NOI18N
        TotalToyota.setForeground(new java.awt.Color(255, 255, 255));
        TotalToyota.setText("$XXXXX");
        InventarioSubPanel.add(TotalToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, 350, 60));

        TitMercedes.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitMercedes.setForeground(new java.awt.Color(255, 255, 255));
        TitMercedes.setText("MERCEDES - BENS");
        InventarioSubPanel.add(TitMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1110, -1, -1));

        TitToyota.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitToyota.setForeground(new java.awt.Color(255, 255, 255));
        TitToyota.setText("TOYOTA");
        InventarioSubPanel.add(TitToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        TitBMW.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitBMW.setForeground(new java.awt.Color(255, 255, 255));
        TitBMW.setText("BMW");
        InventarioSubPanel.add(TitBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 850, -1, -1));

        TitFord.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitFord.setForeground(new java.awt.Color(255, 255, 255));
        TitFord.setText("FORD");
        InventarioSubPanel.add(TitFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, -1));

        TitHonda.setFont(new java.awt.Font("Orator Std", 1, 36)); // NOI18N
        TitHonda.setForeground(new java.awt.Color(255, 255, 255));
        TitHonda.setText("HONDA");
        InventarioSubPanel.add(TitHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 570, -1, -1));

        jLabel31.setBackground(new java.awt.Color(255, 255, 255));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bmwx430.png"))); // NOI18N
        jLabel31.setOpaque(true);
        InventarioSubPanel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 827, 430, 220));

        jLabel30.setBackground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mercedesx430.png"))); // NOI18N
        jLabel30.setOpaque(true);
        InventarioSubPanel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 1094, 430, 220));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/hondax430.png"))); // NOI18N
        jLabel29.setOpaque(true);
        InventarioSubPanel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 557, 430, 220));

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fordx430.png"))); // NOI18N
        jLabel28.setOpaque(true);
        InventarioSubPanel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 430, 220));

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/toyotax430.png"))); // NOI18N
        jLabel17.setOpaque(true);
        InventarioSubPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 430, 210));

        jLabel4.setBackground(new java.awt.Color(102, 0, 0));
        jLabel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel4.setOpaque(true);
        InventarioSubPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 542, 1077, 250));

        jLabel1.setBackground(new java.awt.Color(102, 0, 0));
        jLabel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setOpaque(true);
        InventarioSubPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 274, 1077, 250));

        jLabel3.setBackground(new java.awt.Color(102, 0, 0));
        jLabel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setOpaque(true);
        InventarioSubPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 6, 1077, 250));

        jLabel15.setBackground(new java.awt.Color(102, 0, 0));
        jLabel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel15.setOpaque(true);
        InventarioSubPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 810, 1077, 250));

        jLabel16.setBackground(new java.awt.Color(102, 0, 0));
        jLabel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel16.setOpaque(true);
        InventarioSubPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 1078, 1077, 250));

        jScrollPane2.setViewportView(InventarioSubPanel);

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
    
    //BOTONES MENU
    private void Boton_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_EmpleadosActionPerformed
        //si el que se muestra actualemte no es el de empleados que cambie a empleados
        if (Actual != PanelEmpleados) {
            PanelEmpleados.setVisible(true);
            PanelEmpleados.setEnabled(true);
            CambiaEstadoPANEL(Actual); //Llama subrutina
            Actual = PanelEmpleados;//Actualiza el actual
            //System.out.println(Actual); } *
            TituloPanel.setText("|  Empleados"); //Actualizar titulo del panel           
            CambiarBotones("PanelEmpleados");//Actualizar botones
        } //SI si es, que no haga nada, pues ya se muestra
    }//GEN-LAST:event_Boton_EmpleadosActionPerformed

    private void Boton_VentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_VentasActionPerformed
        if (Actual != PanelVentas) {//si el que se muestra actualemte no es el de ventas que cambie a ventas
            PanelVentas.setVisible(true);
            PanelVentas.setEnabled(true);
            CambiaEstadoPANEL(Actual);//llama subrutina
            Actual = PanelVentas;//Actualizar
            TituloPanel.setText("|  Ventas");
            CambiarBotones("PanelVentas");
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_VentasActionPerformed

    private void Boton_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_InventarioActionPerformed
        if (Actual != PanelInventario) {//si el que se muestra actualemte no es el de inventario que cambie a inventario
            PanelInventario.setVisible(true);
            PanelInventario.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelInventario;
            TituloPanel.setText("|  Inventario");
            CambiarBotones("PanelInventario");
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_InventarioActionPerformed

    private void InfoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoBTNActionPerformed
       
    }//GEN-LAST:event_InfoBTNActionPerformed

    private void BotonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarActionPerformed

    }//GEN-LAST:event_BotonOrdenarActionPerformed

    private void BotonOrdenarSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarSalarioActionPerformed

    }//GEN-LAST:event_BotonOrdenarSalarioActionPerformed

    //AGREGAR EMPLEADOS NUEVA VENTANA
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
    //Cerra nueva ventana
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
    // Activacion para agregar empleados 
    private void BotonAgregarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarEmpleadosActionPerformed
        AgregarEmpleados("Empleados");//Lamma subrutina
        Scanner sc = new Scanner(System.in);
        LeerNormal(sc, "Empleados", TablaEMPLEADOS);//Llama subrutina
        sc.close();
    }//GEN-LAST:event_BotonAgregarEmpleadosActionPerformed

    private void BotonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiarActionPerformed
        Limpiar();        Limpiar();    }//GEN-LAST:event_BotonLimpiarActionPerformed

    //Ventasn Emergente para eliminar
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
    //Cerrar ventana emergente
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
        EliminarRegistro(sc, "Empleados", TablaEMPLEADOS);//Llama a la funcion 
        LeerNormal(sc, "Empleados", TablaEMPLEADOS); //Llama nuevamenta par actualizar
        sc.close();
    }//GEN-LAST:event_BotonEliminarEmpleadosActionPerformed

    private void BotonLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiar1ActionPerformed
        Limpiar();
        Limpiar();
    }//GEN-LAST:event_BotonLimpiar1ActionPerformed
    //abre ventan emergente para REGISTRAR
    private void BotonRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarVentaActionPerformed
        FrameAgregarVenta.setVisible(true);
    }//GEN-LAST:event_BotonRegistrarVentaActionPerformed

    private void fvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fvendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fvendedorActionPerformed

    private void ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxActionPerformed

    //MODO NOCTURNO
    boolean Nocturno;
    //Colores
    Color fondoclaro = Color.decode("#FFFFFF");//blanco
    Color fondooscuro = Color.decode("#2A2333"); //Azul oscuro
    Color rojooscuro = Color.decode("#330000");//Rojo Oscuro Fuerte
    Color negro=Color.decode("000000");
    Color ooo=Color.decode("#604A4A");
    Color rojoclaro=Color.decode("#FFCCCC");
    private void ModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModoActionPerformed
        if (Nocturno == true) {//esta oscuro ON
            System.out.println("Pasar Modo claro");//Se debe pasar a modo claro
            //Cambiar colores e imagenes
            TituloPanel.setForeground(rojooscuro);
            PanelEmpleados.setBackground(fondoclaro);
            PanelVentas.setBackground(fondoclaro);
            PanelInventario.setBackground(fondoclaro);
            InventarioSubPanel.setBackground(fondoclaro);
            BotonOrdenar.setForeground(negro);
            BotonOrdenarSalario.setForeground(negro);
            BotonSinOrdenar.setForeground(negro);
            TablaEMPLEADOS.setBackground(rojoclaro);
            TablaEMPLEADOS.setBorder(new javax.swing.border.LineBorder(fondoclaro, 3, true));
            jScrollPane1.setBorder(new javax.swing.border.LineBorder(fondoclaro, 3, true));
            TablaEMPLEADOS.setForeground(negro);
            Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53.png")));
            Modo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53brillo.png")));
            Nocturno = false; //OFF Actualizar Modo Actual
        } else {//estaclaro  OFF
            System.out.println("Pasar Modo oscuro");//Se debe pasar a modo oscuro
            //Cambiar colores
            TituloPanel.setForeground(fondoclaro);
            PanelEmpleados.setBackground(fondooscuro);
            PanelVentas.setBackground(fondooscuro);
            PanelInventario.setBackground(fondooscuro);
            InventarioSubPanel.setBackground(fondooscuro);
            BotonOrdenar.setForeground(fondoclaro);
            BotonOrdenarSalario.setForeground(fondoclaro);
            BotonSinOrdenar.setForeground(fondoclaro);
            TablaEMPLEADOS.setBackground(ooo);//Esto se debe cambiar que feo
            TablaEMPLEADOS.setBorder(new javax.swing.border.LineBorder(fondooscuro, 3, true));
            jScrollPane1.setBorder(new javax.swing.border.LineBorder(fondooscuro, 3, true));
            TablaEMPLEADOS.setForeground(fondoclaro);
            Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/solsinfondpx53.png")));
            Modo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/solsinfondpx53brillo.png"))); 
            Nocturno = true;//ON Actulizar Modo Actual 
        } 
        TituloPanel.setVisible(true);
    }//GEN-LAST:event_ModoActionPerformed
    
//Cerrar Ventada emergente DE VENTAS
    private void cerraragregarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerraragregarventaActionPerformed
        FrameAgregarVenta.setVisible(false);
    }//GEN-LAST:event_cerraragregarventaActionPerformed

    //Main
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
    private javax.swing.JButton BotonEliminarVenta;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JButton BotonLimpiar1;
    private javax.swing.JRadioButton BotonOrdenar;
    private javax.swing.JRadioButton BotonOrdenarSalario;
    private javax.swing.JButton BotonRegistrarVenta;
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
    private javax.swing.JComboBox<String> ComboBox;
    private javax.swing.JInternalFrame FrameAgregar;
    private javax.swing.JInternalFrame FrameAgregarVenta;
    private javax.swing.JInternalFrame FrameEliminar;
    private javax.swing.JButton InfoBTN;
    private javax.swing.JPanel InventarioSubPanel;
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
    public javax.swing.JLabel TituloPanel;
    private javax.swing.JLabel TotalBMW;
    private javax.swing.JLabel TotalFord;
    private javax.swing.JLabel TotalHonda;
    private javax.swing.JLabel TotalMercedes;
    private javax.swing.JLabel TotalToyota;
    private javax.swing.JButton cerrar;
    private javax.swing.JButton cerrar1;
    private javax.swing.JButton cerraragregarventa;
    private javax.swing.JLabel error1;
    private javax.swing.JLabel error1v;
    private javax.swing.JLabel error2;
    private javax.swing.JLabel error2v;
    private javax.swing.JLabel error3;
    private javax.swing.JLabel error3v;
    private javax.swing.JLabel error4;
    private javax.swing.JLabel error4v;
    private javax.swing.JLabel error5;
    private javax.swing.JLabel error6;
    private javax.swing.JLabel error7;
    private javax.swing.JLabel error9;
    private javax.swing.JTextField fcargo;
    private javax.swing.JTextField fcedula;
    private javax.swing.JTextField fcedulav;
    private javax.swing.JTextField fcodigo;
    private javax.swing.JTextField ffechaingreso;
    private javax.swing.JTextField fmonto;
    private javax.swing.JTextField fnombre;
    private javax.swing.JTextField fnombreE;
    private javax.swing.JTextField fsalariocomisiones;
    private javax.swing.JTextField fsalariofijo;
    private javax.swing.JTextField ftelefono;
    private javax.swing.JTextField fvendedor;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
