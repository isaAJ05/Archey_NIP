
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Natalia Carpintero, Paula Nuñez e Isabella Arrieta
 */
public class Principal extends javax.swing.JFrame {

    //SUBRUTINAS: EMPLEADOS
    //01 Subrutina para llenar el archivo Empleados
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

    //02 SUBRUTINA PARA MOSTRAR LOS CAMPOS ARCHIVO EMPLEADOS-VENTAS
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
                                    sonido("/Sonidos/papelera.wav");
                        JOptionPane.showMessageDialog(null, "Los datos se han eliminado satisfactoriamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                                    sonido("/Sonidos/error.wav");
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al renombrar el archivo temporal.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    LeerNormal(sc, file_name, tabla);
                } else {
                                sonido("/Sonidos/error.wav");
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el archivo original.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                            sonido("/Sonidos/error.wav");
                JOptionPane.showMessageDialog(null, "No se encontró el empleado con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (IOException ex) {
                        sonido("/Sonidos/error.wav");
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }
    //SUBRUTINAS VENTAS

//06 Subrutina para Agregar Datos de Ventas
    //Variables Globales
    boolean existeempleado;
    boolean existecod;

    public void AgregarVentas(String file_name) {
        /**
         * (Nombre, cédula, cargo, teléfono de contacto, fecha de ingreso,
         * salario fijo mensual y salario más comisiones *
         */
        String Nombre, Cedula, Tipo, Codigo, Monto;

        try {
            FileWriter outFile = new FileWriter(file_name + ".txt", true);  //Archivo.txt
            // if false the file will be deleted and created everytime
            // if true the registers will be appended to the end of the file
            PrintWriter registrar_ventas = new PrintWriter(outFile);
            Nombre = fvendedor.getSelectedItem().toString();
            Cedula = fcedulav.getText();
            Tipo = ComboBox.getSelectedItem().toString();
            Codigo = fcodigo.getText();
            Monto = fmonto.getText();

            //Llamar a funciones de validaciones
            //Validacion1: verifica que no haya campos vacios
            //Validacion2: verifica que cada campo no tenga algun error de formato
            if (validacionventa1(Codigo, Monto)) {
                if (validacionventa2(Codigo, Monto)) {
                    if (ValidarExistenciaEmpleado(Nombre, Cedula) && ValidarNoExistenciaCodigo(file_name, Codigo)) {//si no existe codigo y si existe empleado registrar la venta
                        registrar_ventas.println(Nombre + "\t" + Cedula + "\t" + Tipo + "\t" + Codigo + "\t" + Monto);
                        RelacionAutos(Tipo, Monto);
                        if (Double.parseDouble(Monto) > 30000000) {// actualizar comision empleado si este vendio vehiculo superio a 30 millones
                            actualizarSalarioConComisiones("Empleados", Nombre, Monto, TablaEMPLEADOS);
                        }
                        sonido("/Sonidos/correcto.wav");//implementacion de sonidos
                        JOptionPane.showMessageDialog(null, "Los datos se han agregado satisfactoriamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                   //los mensajes van incluidos en las funciones
                } else {
                    //los mensajes van incluidos en las funciones
                }
            }
            registrar_ventas.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al agregar los datos.", "Error", JOptionPane.ERROR_MESSAGE);

            System.out.println("Error creando el archivo");
            ex.printStackTrace();
        }

    }

    //07 Subrutina PARA CONFIRMAR QUE EXISTA EMPLEADO
    public void LeerEmpleados(Scanner sc, String file_name, String Nombre, String Cedula) {
        existeempleado = false;
        boolean hay = false;
        while (!hay) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;

                while ((line = br.readLine()) != null && !existeempleado) {
                    String temp[] = line.split("\t");
                    System.out.println("Estos son los temp[0]: " + temp[0] + "\t" + "Los temp1: " + temp[1]);
                    if (temp[0].equals(Nombre) && temp[1].equals(Cedula)) {
                        System.out.println("NOMBRE encontrado: " + temp[0]);
                        System.out.println("Cedula encontrada: " + temp[1]);
                        existeempleado = true;
                        System.out.println("Existe empleado es true");
                    }
                }
                br.close();
                hay = true;
            } catch (IOException ex) {
                System.out.println("No se encontró archivo");
                hay = false;
                file_name = sc.nextLine(); // Archivo
            }
        }
    }

    //08 Subrutina PARA CONFIRMAR QUE NO EXISTA CODIGO
    public void LeerVentasC(Scanner sc, String file_name, String Codigo) {
        existecod = false;
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                System.out.println("Este es el codigo a buscar: " + Codigo);
                while ((line = br.readLine()) != null && existecod == false) {
                    String temp[] = line.split("\t");
                    System.out.println("Estos son los temp2 codigo: " + temp[3]);
                    if (temp[3].equals(Codigo)) {
                        System.out.println("CODIGO ENCONTRADO: " + temp[3]);
                        existecod = true;
                        System.out.println("Existe codigo es true");
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
     //08 Subrutina PARA BUSCAR CEDULA
    public void LeerEmpleados2(Scanner sc, String file_name, String Nombre) {
        boolean existec = false;
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                System.out.println("Este es el nombre a buscar: " + Nombre);
                while ((line = br.readLine()) != null && existec == false) {
                    String temp[] = line.split("\t");
                    if (temp[0].equals(Nombre)) {
                        System.out.println("Nombre encontrado: " + temp[0]);
                        existec = true;
                        fcedulav.setText(temp[1]);
                        System.out.println("Existe cedula es true y es: "+ temp[1]);
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
    int cantT, cantF, cantH, cantB, cantM;//contador de autos
    double MontoT, MontoF, MontoH, MontoB, MontoM;//contador de monto
    //09 Subrutina para relacionar autos vendidos con el inventario

    public void RelacionAutos(String Tipo, String Monto) {
        System.out.println("Tipo es: " + Tipo);
        switch (Tipo) {
            case "Toyota":
                cantT++;
                CantVendidaToyota.setText(String.valueOf(cantT));
                MontoT += Double.parseDouble(Monto);
                TotalToyota.setText("$" + String.valueOf((int) MontoT));
                break;
            case "Ford":
                cantF++;
                CantVendidaFord.setText(String.valueOf(cantF));
                MontoF += Double.parseDouble(Monto);
                TotalFord.setText("$" + String.valueOf((int) MontoF));
                break;
            case "Honda":
                cantH++;
                CantVendidaHonda.setText(String.valueOf(cantH));
                MontoH += Double.parseDouble(Monto);
                TotalHonda.setText("$" + String.valueOf((int) MontoH));
                break;
            case "BMW":
                cantB++;
                CantVendidaBMW.setText(String.valueOf(cantB));
                MontoB += Double.parseDouble(Monto);
                TotalBMW.setText("$" + String.valueOf((int) MontoB));
                break;
            case "Mercedes":
                cantM++;
                CantVendidaMercedes.setText(String.valueOf(cantM));
                MontoM += Double.parseDouble(Monto);
                TotalMercedes.setText("$" + String.valueOf((int) MontoM));
                break;

            default:
                CantVendidaToyota.setText("0");
                TotalToyota.setText("0");
                CantVendidaFord.setText("0");
                TotalFord.setText("0");
                CantVendidaHonda.setText("0");
                TotalHonda.setText("0");
                CantVendidaBMW.setText("0");
                TotalBMW.setText("0");
                CantVendidaMercedes.setText("0");
                TotalMercedes.setText("0");
                break;
        }
    }

    public void RelacionAutos2(String Tipo, String Monto) {
        System.out.println("Tipo a eliminar es: " + Tipo);
        switch (Tipo) {
            case "Toyota":
                cantT--;
                CantVendidaToyota.setText(String.valueOf(cantT));
                MontoT -= Double.parseDouble(Monto);
                TotalToyota.setText("$" + String.valueOf((int) MontoT));
                break;
            case "Ford":
                cantF--;
                CantVendidaFord.setText(String.valueOf(cantF));
                MontoF -= Double.parseDouble(Monto);
                TotalFord.setText("$" + String.valueOf((int) MontoF));
                break;
            case "Honda":
                cantH--;
                CantVendidaHonda.setText(String.valueOf(cantH));
                MontoH -= Double.parseDouble(Monto);
                TotalHonda.setText("$" + String.valueOf((int) MontoH));
                break;
            case "BMW":
                cantB--;
                CantVendidaBMW.setText(String.valueOf(cantB));
                MontoB -= Double.parseDouble(Monto);
                TotalBMW.setText("$" + String.valueOf((int) MontoB));
                break;
            case "Mercedes":
                cantM--;
                CantVendidaMercedes.setText(String.valueOf(cantM));
                MontoM -= Double.parseDouble(Monto);
                TotalMercedes.setText("$" + String.valueOf((int) MontoM));
                break;

            default:
                CantVendidaToyota.setText("0");
                TotalToyota.setText("0");
                CantVendidaFord.setText("0");
                TotalFord.setText("0");
                CantVendidaHonda.setText("0");
                TotalHonda.setText("0");
                CantVendidaBMW.setText("0");
                TotalBMW.setText("0");
                CantVendidaMercedes.setText("0");
                TotalMercedes.setText("0");
                break;
        }
    }
//10 Subrutina para actualizar el salario+comisiones

    public void actualizarSalarioConComisiones(String file_name, String NombreVendedor, String Monto, JTable tabla) {
        try {
            File archivoOriginal = new File(file_name + ".txt");
            File archivoTemporal = new File("EmpleadosTemp.txt");

            FileReader fr = new FileReader(archivoOriginal);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(archivoTemporal);
            BufferedWriter bw = new BufferedWriter(fw);

            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0);

            String linea;
            boolean cambiosRealizados = false; // Variable para rastrear si se realizaron cambios

            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("\t");
                String nombre = campos[0];
                double SalarioF = Double.parseDouble(campos[5]); // Índice del salario fijo+comisiones
                double SalarioC = Double.parseDouble(campos[6]); // Índice del salario fijo+comisiones

                // Solo calcular comisiones y actualizar salario si el nombre coincide
                if (nombre.equalsIgnoreCase(NombreVendedor)) {
                    double comisiones = Double.parseDouble(Monto) * 0.02;
                    // Calcular el salario total
                    double salarioTotal;
                    if (SalarioC == 0) {
                        salarioTotal = SalarioF + comisiones;
                    } else {
                        salarioTotal = SalarioC + comisiones;
                    }

                    // Actualizar la línea con el nuevo salario más comisiones
                    campos[6] = String.valueOf(salarioTotal); // Índice del salario más comisiones
                    cambiosRealizados = true; // Se realizó un cambio
                }
                model.addRow(campos); // Agregar a la tabla
                // Reconstruir la línea (incluso si no se actualiza)
                String nuevaLinea = String.join("\t", campos);

                // Escribir la línea actualizada o no en el archivo temporal
                bw.write(nuevaLinea);
                bw.newLine();
            }

            br.close();
            bw.close();

            if (cambiosRealizados) {
                // Renombrar el archivo temporal al archivo original solo si se realizaron cambios
                if (archivoOriginal.delete() && archivoTemporal.renameTo(archivoOriginal)) {
                    System.out.println("Salario más comisiones actualizado con éxito.");
                } else {
                    System.out.println("Error al actualizar el salario más comisiones.");
                }
            } else {
                System.out.println("No se realizaron cambios en el salario más comisiones.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //11 Subrutina para eliminar registros de ventas

    public void EliminarRegistroVenta(Scanner sc, String file_name, JTable tabla) {
        File original = new File(file_name + ".txt");
        try {
            FileReader FR = new FileReader(file_name + ".txt");
            BufferedReader BR = new BufferedReader(FR);
            FileWriter FW = new FileWriter("VentasTemp.txt", true);
            BufferedWriter BW = new BufferedWriter(FW);
            String linea, borrar_codigo = CodigoE;
            System.out.println("Borrar codigo es: " + borrar_codigo);
            boolean encontrado = false;
            while ((linea = BR.readLine()) != null) {
                String[] campos = linea.split("\t");
                if (campos[3].equalsIgnoreCase(borrar_codigo)) {
                    RelacionAutos2(campos[2], campos[4]);//PARA ACTUALIZAR EL INVENTARIO
                }
                if (!campos[3].equalsIgnoreCase(borrar_codigo)) {
                    BW.write(linea); // si el codigo no es el que se busca, se escribe en el archivo temporal
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
                    File temporal = new File("VentasTemp.txt");
                    if (temporal.renameTo(original)) {
                                    sonido("/Sonidos/papelera.wav");
                        JOptionPane.showMessageDialog(null, "Los datos se han eliminado satisfactoriamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ha ocurrido un error al renombrar el archivo temporal.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    LeerNormal(sc, file_name, tabla);
                } else {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el archivo original.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el codigo ingresado.", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar el codigo.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }
    //SUBRUTINA PARA MOSTRAR LOS CAMPOS ARCHIVO VENTAS

    public void LeerVentasok(Scanner sc, String file_name, JTable tabla) {
        boolean hay = false;
        while (hay == false) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file_name + ".txt"));
                String line = null;
                DefaultTableModel model = (DefaultTableModel) tabla.getModel();
                model.setRowCount(0);

                while ((line = br.readLine()) != null) {
                    String temp[] = line.split("\t");
                    RelacionAutos(temp[2],temp[4]);
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

    //11 Subrutina para limpiar campos de Empleados
    public void Limpiar() {
        fnombre.setText("");
        fcedula.setText("");
        fcargo.setText("");
        ftelefono.setText("");
        ffechaingreso.setText("");
        fsalariofijo.setText("");
        fsalariocomisiones.setText("");
    }

    //12 Subrutina para limpiar campos de Ventas
    public void LimpiarCamposVentas() {
//        fvendedor.setText("");
        fcedulav.setText("");
        fcodigo.setText("");
        fmonto.setText("");
    }

    //13 SUBRUTINA PARA APLICAR SONIDO
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
    DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

// Método para cargar elementos iniciales desde el archivo
    private void cargarElementosDesdeArchivo() {
        model.removeAllElements(); // Limpia el modelo antes de cargar los elementos desde el archivo
        boolean hayElementos = false; // Bandera para verificar si se encontraron elementos
        try (BufferedReader BR = new BufferedReader(new FileReader("ventas.txt"))) {
            String line;
            while ((line = BR.readLine()) != null) {
                String[] campos = line.split("\t");
                if (campos.length > 3) { // Asegúrate de que haya suficientes campos
                    String codigo = campos[3];
                    if (!existeEnComboBox(codigo)) {
                        model.addElement(codigo);
                        hayElementos = true; // Se encontraron elementos
                    }
                }
            }
            if (!hayElementos) {
                combocodigos.setEnabled(false); // Deshabilita el JComboBox
                BotonParaEliminarVenta.setEnabled(false);
            } else {
                combocodigos.setEnabled(true); // Habilita el JComboBox si hay elementos
                BotonParaEliminarVenta.setEnabled(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        combocodigos.setModel(model); // Asigna el modelo al JComboBox
    }

// Método para verificar si un elemento ya existe en el JComboBox
    private boolean existeEnComboBox(String codigo) {
        for (int i = 0; i < model.getSize(); i++) {
            if (codigo.equalsIgnoreCase(model.getElementAt(i))) {
                return true;
            }
        }
        return false;
    }
    
    DefaultComboBoxModel<String> modelV = new DefaultComboBoxModel<>();

// Método para cargar elementos iniciales desde el archivo
private void cargarElementosDesdeArchivo2() {
    //modelV.removeAllElements(); // Limpia el modelo antes de cargar los elementos desde el archivo
    boolean hayElementos = false; // Bandera para verificar si se encontraron elementos
    try (BufferedReader BR = new BufferedReader(new FileReader("empleados.txt"))) {
        String line;
        while ((line = BR.readLine()) != null) {
            String[] nombres = line.split("\t");
            if (nombres.length > 2) { // Asegúrate de que haya suficientes campos
                String nombre = nombres[0];
                String cedula = nombres[1];
                String cargo = nombres[2];
                if (!existeEnComboBox2(nombre) && cargo.equalsIgnoreCase("Vendedor")) {
                    modelV.addElement(nombre);
                    hayElementos = true; // Se encontraron elementos
                }else {
                        fcedulav.setText(cedula);
                    }
            }
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }

    fvendedor.setModel(modelV); // Asigna el modelo al JComboBox
}

// Método para verificar si un elemento ya existe en el JComboBox
private boolean existeEnComboBox2(String nombre) {
    for (int i = 0; i < modelV.getSize(); i++) {
        if (nombre.equalsIgnoreCase(modelV.getElementAt(i))) {
            return true;
        }
    }
    return false;
}

//FUNCIONES 
    // 01 funcion para validar si estan vacios los campos EMPLEADOS
    public boolean validacion1(String c1, String c2, String c3, String c4, String c5, String c6, String c7) {
        if (!c1.isEmpty() && !c2.isEmpty() && !c3.isEmpty() && !c4.isEmpty() && !c5.isEmpty() && !c6.isEmpty() && !c7.isEmpty()) {
            return true;
        } else {
            sonido("/Sonidos/error.wav");
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // 01.1 funcion para validar si estan vacios los campos VENTAS
    public boolean validacionventa1(String c4, String c5) {
        if (!c4.isEmpty() && !c5.isEmpty()) { //Codigo auto, y monto
            return true;
        } else {
            sonido("/Sonidos/error.wav");
            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    //02 Funcion para validar Campos de EMPLEADOS
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
           sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
            return false;
        }

        if (!validosalario) {
            error6.setVisible(true); // Mostrar mensaje de error
            sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
            return false;
        } catch (DateTimeParseException e) {
            error5.setVisible(false);
        }
        return true;

    }

    //02.2 Funcion para validar campos de VENTAS
    // Nombre, Cedula, Codigo, Monto
    public boolean validacionventa2(String c4, String c5) {

        //Validacion Codigo
        if (c4.length() != 6) {
            error3v.setVisible(true); // Mostrar mensaje de error
            sonido("/Sonidos/error.wav");
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
            sonido("/Sonidos/error.wav");
            return false;
        } else {
            error4v.setVisible(false); // Ocultar mensaje de error  

        }

        return true;

    }

    //02.3 Funcion validar nombre en eliminar
    public boolean NombreCorrecto(JTextField nombre) {
        // Validacion Nombre
        boolean validon = true;
        for (char c : nombre.getText().toCharArray()) {
            if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
                validon = false;
                break;
            }
        }
        if (!validon) {
                        sonido("/Sonidos/error.wav");
            errornombre.setVisible(true);
            return false;
        } else {
            errornombre.setVisible(false);
        }
        return true;
    }

    //03 Funcion para validar Existencia Empleado
    public boolean ValidarExistenciaEmpleado(String Nombre, String Cedula) {
        Scanner sc = new Scanner(System.in);
        LeerEmpleados(sc, "Empleados", Nombre, Cedula);
        sc.close();
        if (existeempleado == false) {//si el empleado no existe mostrar mensaje de error
            sonido("/Sonidos/error.wav");
            JOptionPane.showMessageDialog(null, "Verifique los campos del vendedor", "Vendedor No Encontrado", JOptionPane.WARNING_MESSAGE);
            return false;

        }
        return true;
    }
    
    //04 Funcion para validar que el codigo no exista
    public boolean ValidarNoExistenciaCodigo(String file_name, String Codigo) {
        Scanner sc = new Scanner(System.in);
        LeerVentasC(sc, file_name, Codigo);//llamar subrutina que leea registro y compare los codigos
        sc.close();

        if (existecod == true) {//si el codigo existe mostrar mensaje de error
            System.out.println("Existe codigo es TRUE tiene que aparecer error");
            error3v.setText("(!) Codigo existente");
            error3v.setVisible(true);
            sonido("/Sonidos/error.wav");
            return false;
        }
        return true;
    }

//MAIN 
    public Principal() {
        initComponents();
        //INTERFAZ
        this.setTitle("ARCHEYS");
       ImageIcon icono = new ImageIcon(getClass().getResource("ICONS/iconooo.png"));
                setIconImage((icono).getImage());
                
                FrameEliminar.setFrameIcon(icono);
                FrameAgregar.setFrameIcon(icono);
                FrameEliminarVenta.setFrameIcon(icono);
                FrameAgregarVenta.setFrameIcon(icono);
        
        this.setLocationRelativeTo(null); //centrar ventana
        PanelEmpleados.setVisible(false);
        PanelInventario.setVisible(false);
        PanelVentas.setVisible(true);
        PanelEmpleados.setEnabled(false);
        PanelVentas.setEnabled(true);
        GrafiPanel.setVisible(false);
        GrafiPanel.setEnabled(false);
        PanelInfo.setVisible(false);
        PanelInfo.setEnabled(false);
        Nocturno = false;
        PanelInventario.setEnabled(false);
        Actual = PanelVentas;
        CambiarBotones("PanelVentas");
        TituloPanel.setText("|  Ventas");
        //ARCHIVO EMPLEADOS
        //Crear
        //agregarDatosEmpleados("Empleados"); //ya esta creado
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

        //Para la tabla EMPLEADOS
        // Obtener el encabezado de la tabla
        JTableHeader header = TablaEMPLEADOS.getTableHeader();

// Crear una fuente en negrita
        Font font = new Font("Tahoma", Font.BOLD, 14);

// Aplicar la fuente en negrita al encabezado
        header.setFont(font);

        //ARCHIVO VENTAS
       
        //No visible
        FrameAgregarVenta.setVisible(false);
        FrameEliminarVenta.setVisible(false);
        fondoborrosoventas.setVisible(false);
//        error1v.setVisible(false);
//        error2v.setVisible(false);
        error3v.setVisible(false);
        error4v.setVisible(false);
        errornombre.setVisible(false);
        //Para Mostrar Archivo de Ventas al iniciar
        File archivo = new File("Ventas.txt");
        // Verificar si el archivo existe
        if (archivo.exists()) {
            // Verificar si el archivo está vacío
            if (archivo.length() == 0) {
                System.out.println("El archivo de ventas está vacío.");
            } else {
                System.out.println("El archivo de ventas no está vacío.");
                LeerVentasok(sc, "Ventas", TablaVENTAS);
            }
        } else {
            System.out.println("El archivo de ventas no existe.");
        }
        //Para la tabla VENTAS
        // Obtener el encabezado de la tabla
        JTableHeader header2 = TablaVENTAS.getTableHeader();

// Crear una fuente en negrita
        Font font2 = new Font("Tahoma", Font.BOLD, 14);

// Aplicar la fuente en negrita al encabezado
        header2.setFont(font2);

    }

    public void CambiaEstadoPANEL(JPanel p) {
        p.setVisible(!p.isVisible());
        p.setEnabled(!p.isEnabled());

    }

    public void CambiaEstadoPANEL(JScrollPane p) {
        p.setVisible(!p.isVisible());
        p.setEnabled(!p.isEnabled());
    }

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
        if (Actual.equalsIgnoreCase("PanelInfo")) {
            Boton_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/inventarriosinfondox53.png")));
            Boton_Ventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/ventassinfondox53.png")));
            Boton_Empleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleadosinfondo x53.png")));
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel40 = new javax.swing.JLabel();
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
        ComboBox = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        fmonto = new javax.swing.JTextField();
        fcodigo = new javax.swing.JTextField();
        cerraragregarventa = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        error3v = new javax.swing.JLabel();
        error4v = new javax.swing.JLabel();
        fvendedor = new javax.swing.JComboBox<>();
        fcedulav = new javax.swing.JTextField();
        AgregarVenta = new javax.swing.JButton();
        FrameEliminarVenta = new javax.swing.JInternalFrame();
        jPanel1 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        BotonParaEliminarVenta = new javax.swing.JButton();
        cerrareliminar = new javax.swing.JButton();
        combocodigos = new javax.swing.JComboBox<>();
        fondoborrosoventas = new javax.swing.JLabel();
        BuscadorVentas = new javax.swing.JTextField();
        BTNBuscarVentas = new javax.swing.JButton();
        MostrarBusquedaVentas = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaVENTAS = new javax.swing.JTable();
        BotonEliminarVenta = new javax.swing.JButton();
        BotonRegistrarVenta = new javax.swing.JButton();
        PanelEmpleados = new javax.swing.JPanel();
        FrameEliminar = new javax.swing.JInternalFrame();
        PanelEliminarEmpleado = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        fnombreE = new javax.swing.JTextField();
        cerrar1 = new javax.swing.JButton();
        BotonEliminarEmpleados = new javax.swing.JButton();
        BotonLimpiar1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        errornombre = new javax.swing.JLabel();
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
        LabelFondoBorroso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEMPLEADOS = new javax.swing.JTable();
        EmpleBuscador = new javax.swing.JTextField();
        BTNBuscar = new javax.swing.JButton();
        MostrarBusquedaEmple = new javax.swing.JLabel();
        BotonparaAgregar = new javax.swing.JButton();
        BotonparaEliminar = new javax.swing.JButton();
        BotonOrdenarSalario = new javax.swing.JRadioButton();
        BotonOrdenar = new javax.swing.JRadioButton();
        BotonSinOrdenar = new javax.swing.JRadioButton();
        PanelInfo = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        InfoLabel = new javax.swing.JLabel();
        PanelInventario = new javax.swing.JPanel();
        BTNgrafi = new javax.swing.JButton();
        GrafiPanel = new javax.swing.JPanel();
        Gpa = new javax.swing.JPanel();
        Gpa1 = new javax.swing.JPanel();
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

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/lOGOx60.png"))); // NOI18N
        getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 60, 70));

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
        jLabel2.setText("ARCHEYS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, -1));

        jLabel14.setBackground(new java.awt.Color(51, 0, 0));
        jLabel14.setOpaque(true);
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 60));

        jLabel13.setBackground(new java.awt.Color(51, 0, 0));
        jLabel13.setOpaque(true);
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 80, 660));

        TituloPanel.setFont(new java.awt.Font("Footlight MT Light", 1, 50)); // NOI18N
        TituloPanel.setForeground(new java.awt.Color(51, 0, 0));
        TituloPanel.setText("|      Empleados");
        TituloPanel.setAlignmentY(0.0F);
        getContentPane().add(TituloPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 450, -1));

        PanelVentas.setBackground(new java.awt.Color(255, 255, 255));
        PanelVentas.setPreferredSize(new java.awt.Dimension(1240, 700));
        PanelVentas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FrameAgregarVenta.setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel34.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel34.setText("Monto de la venta:");

        jLabel35.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel35.setText("Nombre del vendedor:");

        jLabel36.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel36.setText("Cédula:");

        jLabel37.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel37.setText("Tipo de auto:");

        ComboBox.setBackground(new java.awt.Color(255, 102, 102));
        ComboBox.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N
        ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar tipo de auto", "Toyota", "Ford", "Honda", "BMW", "Mercedes" }));

        jLabel38.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel38.setText("Código del auto:");

        fmonto.setBackground(new java.awt.Color(255, 102, 102));
        fmonto.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N

        fcodigo.setBackground(new java.awt.Color(255, 102, 102));
        fcodigo.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N

        cerraragregarventa.setBackground(new java.awt.Color(255, 102, 102));
        cerraragregarventa.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        cerraragregarventa.setText("Cerrar");
        cerraragregarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerraragregarventaActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 102, 102));
        jButton3.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        error3v.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        error3v.setForeground(new java.awt.Color(255, 0, 0));
        error3v.setText("(!) El código debe contener 6 caracteres.");

        error4v.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        error4v.setForeground(new java.awt.Color(255, 0, 0));
        error4v.setText("(!)Monto debe contener solo numeros mayores a cero");

        fvendedor.setBackground(new java.awt.Color(255, 102, 102));
        fvendedor.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N
        fvendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar empleado" }));
        fvendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fvendedorActionPerformed(evt);
            }
        });

        fcedulav.setEditable(false);
        fcedulav.setBackground(new java.awt.Color(255, 102, 102));
        fcedulav.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N

        AgregarVenta.setBackground(new java.awt.Color(255, 102, 102));
        AgregarVenta.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        AgregarVenta.setText("Agregar");
        AgregarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37))
                        .addGap(52, 52, 52)
                        .addComponent(error3v, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(error4v, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(fmonto, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel35))
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fcedulav, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(56, 56, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(360, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(AgregarVenta)
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
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel36))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fvendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(fcedulav, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38))
                .addGap(1, 1, 1)
                .addComponent(error3v, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fmonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(3, 3, 3)
                .addComponent(error4v)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(AgregarVenta))
                .addContainerGap(19, Short.MAX_VALUE))
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

        FrameEliminarVenta.setVisible(true);

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel39.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel39.setText("Seleccione el código:");

        BotonParaEliminarVenta.setBackground(new java.awt.Color(255, 102, 102));
        BotonParaEliminarVenta.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        BotonParaEliminarVenta.setText("Eliminar");
        BotonParaEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonParaEliminarVentaActionPerformed(evt);
            }
        });

        cerrareliminar.setBackground(new java.awt.Color(255, 102, 102));
        cerrareliminar.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        cerrareliminar.setText("Cerrar");
        cerrareliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrareliminarActionPerformed(evt);
            }
        });

        combocodigos.setBackground(new java.awt.Color(255, 102, 102));
        combocodigos.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N
        combocodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combocodigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(combocodigos, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cerrareliminar)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BotonParaEliminarVenta)
                        .addGap(154, 154, 154))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(cerrareliminar)
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(combocodigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(BotonParaEliminarVenta)
                .addGap(77, 77, 77))
        );

        javax.swing.GroupLayout FrameEliminarVentaLayout = new javax.swing.GroupLayout(FrameEliminarVenta.getContentPane());
        FrameEliminarVenta.getContentPane().setLayout(FrameEliminarVentaLayout);
        FrameEliminarVentaLayout.setHorizontalGroup(
            FrameEliminarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        FrameEliminarVentaLayout.setVerticalGroup(
            FrameEliminarVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PanelVentas.add(FrameEliminarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 660, 320));

        fondoborrosoventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojofondo.png"))); // NOI18N
        PanelVentas.add(fondoborrosoventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -80, 1240, 780));

        BuscadorVentas.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        BuscadorVentas.setText("Buscar...");
        BuscadorVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuscadorVentasMouseClicked(evt);
            }
        });
        BuscadorVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscadorVentasActionPerformed(evt);
            }
        });
        PanelVentas.add(BuscadorVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 92, 440, 30));

        BTNBuscarVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupita.png"))); // NOI18N
        BTNBuscarVentas.setContentAreaFilled(false);
        BTNBuscarVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBuscarVentasActionPerformed(evt);
            }
        });
        PanelVentas.add(BTNBuscarVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 50, 50));

        MostrarBusquedaVentas.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelVentas.add(MostrarBusquedaVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 1090, 30));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        jScrollPane3.setForeground(new java.awt.Color(51, 0, 0));
        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setFont(new java.awt.Font("Adobe Gothic Std B", 1, 14)); // NOI18N
        jScrollPane3.setOpaque(false);

        TablaVENTAS.setBackground(new java.awt.Color(255, 204, 204));
        TablaVENTAS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        TablaVENTAS.setFont(new java.awt.Font("Palatino Linotype", 0, 18)); // NOI18N
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
        TablaVENTAS.setDoubleBuffered(true);
        TablaVENTAS.setRowHeight(40);
        TablaVENTAS.setSelectionBackground(new java.awt.Color(255, 153, 153));
        TablaVENTAS.setSelectionForeground(new java.awt.Color(153, 0, 0));
        TablaVENTAS.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        TablaVENTAS.setShowGrid(false);
        TablaVENTAS.setShowVerticalLines(true);
        jScrollPane3.setViewportView(TablaVENTAS);
        TablaVENTAS.getAccessibleContext().setAccessibleName("");

        PanelVentas.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 176, 1101, -1));

        BotonEliminarVenta.setBackground(new java.awt.Color(204, 0, 0));
        BotonEliminarVenta.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        BotonEliminarVenta.setForeground(new java.awt.Color(255, 255, 255));
        BotonEliminarVenta.setText("(-) Eliminar Venta");
        BotonEliminarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarVentaActionPerformed(evt);
            }
        });
        PanelVentas.add(BotonEliminarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 70, -1, 30));

        BotonRegistrarVenta.setBackground(new java.awt.Color(204, 0, 0));
        BotonRegistrarVenta.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        BotonRegistrarVenta.setForeground(new java.awt.Color(255, 255, 255));
        BotonRegistrarVenta.setText("(+) Registrar Venta");
        BotonRegistrarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarVentaActionPerformed(evt);
            }
        });
        PanelVentas.add(BotonRegistrarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, -1, 30));

        getContentPane().add(PanelVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        PanelEmpleados.setBackground(new java.awt.Color(255, 255, 255));
        PanelEmpleados.setPreferredSize(new java.awt.Dimension(1240, 700));
        PanelEmpleados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        FrameEliminar.setVisible(true);
        FrameEliminar.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelEliminarEmpleado.setBackground(new java.awt.Color(255, 204, 204));
        PanelEliminarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel32.setText("Nombre:");
        PanelEliminarEmpleado.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        fnombreE.setBackground(new java.awt.Color(255, 102, 102));
        fnombreE.setFont(new java.awt.Font("Perpetua", 0, 18)); // NOI18N
        PanelEliminarEmpleado.add(fnombreE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 280, -1));

        cerrar1.setBackground(new java.awt.Color(255, 102, 102));
        cerrar1.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        cerrar1.setText("Cerrar");
        cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrar1ActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        BotonEliminarEmpleados.setBackground(new java.awt.Color(255, 102, 102));
        BotonEliminarEmpleados.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        BotonEliminarEmpleados.setText("Eliminar");
        BotonEliminarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarEmpleadosActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(BotonEliminarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, -1, -1));

        BotonLimpiar1.setBackground(new java.awt.Color(255, 102, 102));
        BotonLimpiar1.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        BotonLimpiar1.setText("Limpiar");
        BotonLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiar1ActionPerformed(evt);
            }
        });
        PanelEliminarEmpleado.add(BotonLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(534, 404, -1, -1));

        jLabel33.setFont(new java.awt.Font("Perpetua", 1, 18)); // NOI18N
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondo.png"))); // NOI18N
        PanelEliminarEmpleado.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 170, 210));

        errornombre.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        errornombre.setForeground(new java.awt.Color(255, 0, 0));
        errornombre.setText("(!) El nombre no debe contener números ni caracteres especiales.");
        PanelEliminarEmpleado.add(errornombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 400, -1));

        FrameEliminar.getContentPane().add(PanelEliminarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 440));

        PanelEmpleados.add(FrameEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 670, 460));

        FrameAgregar.setVisible(true);

        PanelAgregarEmpleado.setBackground(new java.awt.Color(255, 204, 204));
        PanelAgregarEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel5.setText("Nombre:");
        PanelAgregarEmpleado.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel6.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel6.setText("Cédula:");
        PanelAgregarEmpleado.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel7.setText("Cargo:");
        PanelAgregarEmpleado.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel8.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel8.setText("Teléfono:");
        PanelAgregarEmpleado.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel9.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel9.setText("Fecha de Ingreso:");
        PanelAgregarEmpleado.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel10.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel10.setText("Salario Fijo Mensual:");
        PanelAgregarEmpleado.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        jLabel11.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        jLabel11.setText("Salario+Comisiones:");
        PanelAgregarEmpleado.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, -1, 22));

        fnombre.setBackground(new java.awt.Color(255, 102, 102));
        fnombre.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(fnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 280, -1));

        fcedula.setBackground(new java.awt.Color(255, 102, 102));
        fcedula.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(fcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 280, -1));

        fcargo.setBackground(new java.awt.Color(255, 102, 102));
        fcargo.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(fcargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 280, -1));

        ffechaingreso.setBackground(new java.awt.Color(255, 102, 102));
        ffechaingreso.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(ffechaingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 280, -1));

        ftelefono.setBackground(new java.awt.Color(255, 102, 102));
        ftelefono.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(ftelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 280, -1));

        fsalariofijo.setBackground(new java.awt.Color(255, 102, 102));
        fsalariofijo.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(fsalariofijo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 300, 280, -1));

        fsalariocomisiones.setBackground(new java.awt.Color(255, 102, 102));
        fsalariocomisiones.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelAgregarEmpleado.add(fsalariocomisiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 280, -1));

        cerrar.setBackground(new java.awt.Color(255, 102, 102));
        cerrar.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        cerrar.setText("Cerrar");
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        BotonAgregarEmpleados.setBackground(new java.awt.Color(255, 102, 102));
        BotonAgregarEmpleados.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        BotonAgregarEmpleados.setText("Agregar");
        BotonAgregarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarEmpleadosActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(BotonAgregarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 400, -1, -1));

        BotonLimpiar.setBackground(new java.awt.Color(255, 102, 102));
        BotonLimpiar.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        BotonLimpiar.setText("Limpiar");
        BotonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonLimpiarActionPerformed(evt);
            }
        });
        PanelAgregarEmpleado.add(BotonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/empleado sin fondo.png"))); // NOI18N
        PanelAgregarEmpleado.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 170, 210));

        error7.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error7.setForeground(new java.awt.Color(255, 0, 0));
        error7.setText("(!) Salario debe contener solo numeros mayores a cero");
        PanelAgregarEmpleado.add(error7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 290, -1));

        error1.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error1.setForeground(new java.awt.Color(255, 0, 0));
        error1.setText("(!) El nombre no debe contener números ni caracteres especiales.");
        PanelAgregarEmpleado.add(error1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 320, -1));

        error2.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error2.setForeground(new java.awt.Color(255, 0, 0));
        error2.setText("(!) La cédula debe contener solo números.");
        PanelAgregarEmpleado.add(error2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 290, 10));

        error3.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error3.setForeground(new java.awt.Color(255, 0, 0));
        error3.setText("(!) Este campo no debe contener números ni caracteres especiales.");
        PanelAgregarEmpleado.add(error3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 330, -1));

        error4.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error4.setForeground(new java.awt.Color(255, 0, 0));
        error4.setText("(!) El telefono solo debe contener 10 digitos numéricos.");
        PanelAgregarEmpleado.add(error4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 290, 20));

        error5.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error5.setForeground(new java.awt.Color(255, 0, 0));
        error5.setText("(!) Formato de fecha no válido. Recuerde (DD/MM/AA)");
        PanelAgregarEmpleado.add(error5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 290, 20));

        error6.setFont(new java.awt.Font("Perpetua", 0, 13)); // NOI18N
        error6.setForeground(new java.awt.Color(255, 0, 0));
        error6.setText("(!) Salario debe contener solo numeros mayores a cero");
        PanelAgregarEmpleado.add(error6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 290, 20));

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

        LabelFondoBorroso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/rojofondo1.png"))); // NOI18N
        PanelEmpleados.add(LabelFondoBorroso, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -120, 1480, 890));

        jScrollPane1.setBackground(new java.awt.Color(51, 0, 0));
        jScrollPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setToolTipText("");
        jScrollPane1.setFont(new java.awt.Font("Adobe Gothic Std B", 1, 12)); // NOI18N
        jScrollPane1.setOpaque(false);

        TablaEMPLEADOS.setAutoCreateRowSorter(true);
        TablaEMPLEADOS.setBackground(new java.awt.Color(255, 204, 204));
        TablaEMPLEADOS.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        TablaEMPLEADOS.setFont(new java.awt.Font("Palatino Linotype", 0, 18)); // NOI18N
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

        PanelEmpleados.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 180, 1101, -1));
        jScrollPane1.getAccessibleContext().setAccessibleName("");

        EmpleBuscador.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        EmpleBuscador.setText("Buscar...");
        EmpleBuscador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EmpleBuscadorMouseClicked(evt);
            }
        });
        EmpleBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpleBuscadorActionPerformed(evt);
            }
        });
        PanelEmpleados.add(EmpleBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 370, 30));

        BTNBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/lupita.png"))); // NOI18N
        BTNBuscar.setContentAreaFilled(false);
        BTNBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNBuscarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BTNBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 50, 50));

        MostrarBusquedaEmple.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        PanelEmpleados.add(MostrarBusquedaEmple, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 1060, 30));

        BotonparaAgregar.setBackground(new java.awt.Color(204, 0, 0));
        BotonparaAgregar.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
        BotonparaAgregar.setForeground(new java.awt.Color(255, 255, 255));
        BotonparaAgregar.setText("+ (Nuevo Registro)");
        BotonparaAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonparaAgregar.setBorderPainted(false);
        BotonparaAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonparaAgregarActionPerformed(evt);
            }
        });
        PanelEmpleados.add(BotonparaAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 140, 40));

        BotonparaEliminar.setBackground(new java.awt.Color(204, 0, 0));
        BotonparaEliminar.setFont(new java.awt.Font("Perpetua", 1, 15)); // NOI18N
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
        PanelEmpleados.add(BotonparaEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 140, 40));

        BotonOrdenarSalario.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        BotonOrdenarSalario.setText("Ordenar por Salario");
        PanelEmpleados.add(BotonOrdenarSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 60, -1, -1));

        BotonOrdenar.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        BotonOrdenar.setText("Ordenar por nombre");
        PanelEmpleados.add(BotonOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, -1, -1));

        BotonSinOrdenar.setFont(new java.awt.Font("Perpetua", 0, 15)); // NOI18N
        BotonSinOrdenar.setText("Sin ordenar");
        PanelEmpleados.add(BotonSinOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 100, -1, -1));

        getContentPane().add(PanelEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        PanelInfo.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(null);
        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setOpaque(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1137, 1500));

        InfoLabel.setBackground(new java.awt.Color(255, 255, 255));
        InfoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/InfoIMGclarox1125.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(InfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel3);

        javax.swing.GroupLayout PanelInfoLayout = new javax.swing.GroupLayout(PanelInfo);
        PanelInfo.setLayout(PanelInfoLayout);
        PanelInfoLayout.setHorizontalGroup(
            PanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInfoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelInfoLayout.setVerticalGroup(
            PanelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInfoLayout.createSequentialGroup()
                .addGap(0, 91, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE))
        );

        getContentPane().add(PanelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        PanelInventario.setBackground(new java.awt.Color(255, 255, 255));
        PanelInventario.setPreferredSize(new java.awt.Dimension(1240, 700));
        PanelInventario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTNgrafi.setBackground(new java.awt.Color(204, 0, 0));
        BTNgrafi.setFont(new java.awt.Font("Poor Richard", 3, 18)); // NOI18N
        BTNgrafi.setForeground(new java.awt.Color(255, 255, 255));
        BTNgrafi.setText("Gráficas");
        BTNgrafi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNgrafiActionPerformed(evt);
            }
        });
        PanelInventario.add(BTNgrafi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 130, 40));

        GrafiPanel.setBackground(new java.awt.Color(255, 255, 255));

        Gpa.setOpaque(false);

        javax.swing.GroupLayout GpaLayout = new javax.swing.GroupLayout(Gpa);
        Gpa.setLayout(GpaLayout);
        GpaLayout.setHorizontalGroup(
            GpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        GpaLayout.setVerticalGroup(
            GpaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Gpa1.setOpaque(false);

        javax.swing.GroupLayout Gpa1Layout = new javax.swing.GroupLayout(Gpa1);
        Gpa1.setLayout(Gpa1Layout);
        Gpa1Layout.setHorizontalGroup(
            Gpa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );
        Gpa1Layout.setVerticalGroup(
            Gpa1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout GrafiPanelLayout = new javax.swing.GroupLayout(GrafiPanel);
        GrafiPanel.setLayout(GrafiPanelLayout);
        GrafiPanelLayout.setHorizontalGroup(
            GrafiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GrafiPanelLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(Gpa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(Gpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        GrafiPanelLayout.setVerticalGroup(
            GrafiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GrafiPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(GrafiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Gpa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Gpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        PanelInventario.add(GrafiPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 1120, 520));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        InventarioSubPanel.setBackground(new java.awt.Color(255, 255, 255));
        InventarioSubPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 204, 204));
        jLabel27.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1190, -1, -1));

        jLabel26.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 204, 204));
        jLabel26.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 910, -1, -1));

        jLabel25.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 204, 204));
        jLabel25.setText("Vendidos :");
        InventarioSubPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1190, -1, -1));

        jLabel24.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 204, 204));
        jLabel24.setText("Vendidos :");
        InventarioSubPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 910, -1, -1));

        jLabel23.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 204, 204));
        jLabel23.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 640, -1, -1));

        jLabel22.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 204, 204));
        jLabel22.setText("Vendidos :");
        InventarioSubPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 640, -1, -1));

        jLabel21.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 204, 204));
        jLabel21.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, -1, -1));

        jLabel20.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 204, 204));
        jLabel20.setText("Vendidos :");
        InventarioSubPanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, -1, -1));

        jLabel19.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 204, 204));
        jLabel19.setText("Valor Total: ");
        InventarioSubPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 100, -1, -1));

        jLabel18.setFont(new java.awt.Font("Poor Richard", 2, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 204, 204));
        jLabel18.setText("Vendidos :");
        InventarioSubPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, -1, -1));

        CantVendidaMercedes.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        CantVendidaMercedes.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaMercedes.setText("0");
        InventarioSubPanel.add(CantVendidaMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 1230, 130, 60));

        CantVendidaBMW.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        CantVendidaBMW.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaBMW.setText("0");
        InventarioSubPanel.add(CantVendidaBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 950, 130, 60));

        CantVendidaHonda.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        CantVendidaHonda.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaHonda.setText("0");
        InventarioSubPanel.add(CantVendidaHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 680, 150, 60));

        CantVendidaFord.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        CantVendidaFord.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaFord.setText("0");
        InventarioSubPanel.add(CantVendidaFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 130, 70));

        CantVendidaToyota.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        CantVendidaToyota.setForeground(new java.awt.Color(255, 255, 255));
        CantVendidaToyota.setText("0");
        InventarioSubPanel.add(CantVendidaToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 130, 70));

        TotalMercedes.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TotalMercedes.setForeground(new java.awt.Color(255, 255, 255));
        TotalMercedes.setText("$0");
        InventarioSubPanel.add(TotalMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 1230, 440, 60));

        TotalBMW.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TotalBMW.setForeground(new java.awt.Color(255, 255, 255));
        TotalBMW.setText("$0");
        InventarioSubPanel.add(TotalBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 950, 430, 60));

        TotalHonda.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TotalHonda.setForeground(new java.awt.Color(255, 255, 255));
        TotalHonda.setText("$0");
        InventarioSubPanel.add(TotalHonda, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 680, 420, 60));

        TotalFord.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TotalFord.setForeground(new java.awt.Color(255, 255, 255));
        TotalFord.setText("$0");
        InventarioSubPanel.add(TotalFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, 440, 60));

        TotalToyota.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TotalToyota.setForeground(new java.awt.Color(255, 255, 255));
        TotalToyota.setText("$0");
        InventarioSubPanel.add(TotalToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 440, 70));

        TitMercedes.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TitMercedes.setForeground(new java.awt.Color(255, 255, 255));
        TitMercedes.setText("MERCEDES - BENZ");
        InventarioSubPanel.add(TitMercedes, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 1110, -1, -1));

        TitToyota.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TitToyota.setForeground(new java.awt.Color(255, 255, 255));
        TitToyota.setText("TOYOTA");
        InventarioSubPanel.add(TitToyota, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, -1, -1));

        TitBMW.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TitBMW.setForeground(new java.awt.Color(255, 255, 255));
        TitBMW.setText("BMW");
        InventarioSubPanel.add(TitBMW, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 850, -1, -1));

        TitFord.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
        TitFord.setForeground(new java.awt.Color(255, 255, 255));
        TitFord.setText("FORD");
        InventarioSubPanel.add(TitFord, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, -1, -1));

        TitHonda.setFont(new java.awt.Font("Footlight MT Light", 1, 45)); // NOI18N
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

        PanelInventario.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 83, 1160, 557));

        getContentPane().add(PanelInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 1160, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    JPanel Actual;

    private void Boton_EmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_EmpleadosActionPerformed
        sonido("/Sonidos/boop.wav");
        if (Actual != PanelEmpleados) {
            PanelEmpleados.setVisible(true);
            PanelEmpleados.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelEmpleados;
            EmpleBuscador.setText("Buscar...");
            MostrarBusquedaEmple.setText(null);
            //System.out.println(Actual); } *
            TituloPanel.setText("|  Empleados");
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
        sonido("/Sonidos/boop.wav");
        if (Actual != PanelVentas) {
            PanelVentas.setVisible(true);
            PanelVentas.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelVentas;
            BuscadorVentas.setText("Buscar ...");
            TituloPanel.setText("|  Ventas");
            CambiarBotones("PanelVentas");
            MostrarBusquedaVentas.setText(null);
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_VentasActionPerformed

    private void Boton_InventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_InventarioActionPerformed
        sonido("/Sonidos/boop.wav");
        if (Actual != PanelInventario) {
            PanelInventario.setVisible(true);
            PanelInventario.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelInventario;
            TituloPanel.setText("|  Inventario");
            CambiarBotones("PanelInventario");
//           
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_Boton_InventarioActionPerformed

    private void InfoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InfoBTNActionPerformed
        sonido("/Sonidos/boop.wav");
        if (Actual != PanelInfo) {
            PanelInfo.setVisible(true);
            PanelInfo.setEnabled(true);
            CambiaEstadoPANEL(Actual);
            Actual = PanelInfo;
            TituloPanel.setText("|  Guía Informativa");
            CambiarBotones("PanelInfo");
//           
            //System.out.println(Actual);
        }
    }//GEN-LAST:event_InfoBTNActionPerformed

    private void BotonOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarActionPerformed

    }//GEN-LAST:event_BotonOrdenarActionPerformed

    private void BotonOrdenarSalarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonOrdenarSalarioActionPerformed

    }//GEN-LAST:event_BotonOrdenarSalarioActionPerformed

    private void BotonparaAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonparaAgregarActionPerformed
        sonido("/Sonidos/ficha.wav");
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
        TituloPanel.repaint();
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
sonido("/Sonidos/ficha.wav");        Limpiar();        Limpiar();    }//GEN-LAST:event_BotonLimpiarActionPerformed

    private void BotonparaEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonparaEliminarActionPerformed
        sonido("/Sonidos/ficha.wav");
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
        //para limpiar
        fnombreE.setText("");
        errornombre.setVisible(false);
        TituloPanel.repaint();
    }//GEN-LAST:event_cerrar1ActionPerformed
//BOTON ELIMINAR EMPLEADOS
    private void BotonEliminarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarEmpleadosActionPerformed
        if (NombreCorrecto(fnombreE)) {
            Scanner sc = new Scanner(System.in);
            EliminarRegistro(sc, "Empleados", TablaEMPLEADOS);
            LeerNormal(sc, "Empleados", TablaEMPLEADOS);
            sc.close();

        }

    }//GEN-LAST:event_BotonEliminarEmpleadosActionPerformed

    private void BotonLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonLimpiar1ActionPerformed
        fnombreE.setText("");
        errornombre.setVisible(false);
        sonido("/Sonidos/ficha.wav");

    }//GEN-LAST:event_BotonLimpiar1ActionPerformed

    private void BotonRegistrarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarVentaActionPerformed
        sonido("/Sonidos/ficha.wav");
        FrameAgregarVenta.setVisible(true);
        fondoborrosoventas.setVisible(true);
        TablaVENTAS.setVisible(false);
        jScrollPane3.setVisible(false);
        BotonRegistrarVenta.setVisible(false);
        BotonEliminarVenta.setVisible(false);
        cargarElementosDesdeArchivo2();
        

    }//GEN-LAST:event_BotonRegistrarVentaActionPerformed

    boolean Nocturno;
    Color fondoclaro = Color.decode("#FFFFFF");//blanco
    Color fondooscuro = Color.decode("#2A2333");
    Color rojooscuro = Color.decode("#330000");
    Color negro = Color.decode("000000");
    private void ModoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModoActionPerformed
        sonido("/Sonidos/boop.wav");
        if (Nocturno == true) {//esta oscuro
            System.out.println("Pasar Modo claro");
            TituloPanel.setForeground(rojooscuro);
            PanelEmpleados.setBackground(fondoclaro);
            PanelVentas.setBackground(fondoclaro);
            PanelInventario.setBackground(fondoclaro);
            InventarioSubPanel.setBackground(fondoclaro);
            PanelInfo.setBackground(fondoclaro);
            BotonOrdenar.setForeground(negro);
            BotonOrdenarSalario.setForeground(negro);
            BotonSinOrdenar.setForeground(negro);
            MostrarBusquedaEmple.setForeground(negro);
            MostrarBusquedaVentas.setForeground(negro);
            GrafiPanel.setBackground(fondoclaro);
            jPanel3.setBackground(fondoclaro);
            
            InfoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/InfoIMGclarox1125.png")));
            Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53.png")));
            Modo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/Nightx53brillo.png")));
            URL imageURL = Principal.class.getResource("/Imagenes/borrosoclaroempleadp.png");
            ImageIcon imageIconE = new ImageIcon(imageURL);
            
            URL imageURLV = Principal.class.getResource("/Imagenes/ventasborrosoclaro.png");
            ImageIcon imageIconV = new ImageIcon(imageURLV);
            
            TituloPanel.repaint();
            Nocturno = false;
        } else {//estaclaro
            System.out.println("Pasar Modo oscuro");
            TituloPanel.setForeground(fondoclaro);
            PanelEmpleados.setBackground(fondooscuro);
            GrafiPanel.setBackground(fondooscuro);
            PanelVentas.setBackground(fondooscuro);
            PanelInfo.setBackground(fondooscuro);
            PanelInventario.setBackground(fondooscuro);

            InventarioSubPanel.setBackground(fondooscuro);
            BotonOrdenar.setForeground(fondoclaro);
            jPanel3.setBackground(fondooscuro);
            BotonOrdenarSalario.setForeground(fondoclaro);
            BotonSinOrdenar.setForeground(fondoclaro);
            MostrarBusquedaEmple.setForeground(fondoclaro);
            MostrarBusquedaVentas.setForeground(fondoclaro);
            URL imageURL1 = Principal.class.getResource("/Imagenes/borrosooscuroempleado.png");
            ImageIcon imageIcon = new ImageIcon(imageURL1);
            
            URL imageURL2 = Principal.class.getResource("/Imagenes/ventasoscuroborroso.png");
            ImageIcon imageIcon2 = new ImageIcon(imageURL2);
            InfoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/InfoIMGoscurox1125.png")));
           
            Modo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/solsinfondpx53.png")));
            Modo.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/ICONS/solsinfondpx53brillo.png")));
            TituloPanel.repaint();
            Nocturno = true;
        }
        TituloPanel.repaint();
//       
    }//GEN-LAST:event_ModoActionPerformed

    private void cerraragregarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerraragregarventaActionPerformed
        FrameAgregarVenta.setVisible(false);
        fondoborrosoventas.setVisible(false);
        LimpiarCamposVentas();
        TablaVENTAS.setVisible(true);
        jScrollPane3.setVisible(true);
        BotonRegistrarVenta.setVisible(true);
        BotonEliminarVenta.setVisible(true);
        TituloPanel.repaint();

    }//GEN-LAST:event_cerraragregarventaActionPerformed

    private void limpiarventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarventasActionPerformed
        LimpiarCamposVentas();
    }//GEN-LAST:event_limpiarventasActionPerformed

    private void BotonEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarVentaActionPerformed
        sonido("/Sonidos/ficha.wav");
        FrameEliminarVenta.setVisible(true);
        fondoborrosoventas.setVisible(true);
        TablaVENTAS.setVisible(false);
        jScrollPane3.setVisible(false);
        BotonRegistrarVenta.setVisible(false);
        BotonEliminarVenta.setVisible(false);
        cargarElementosDesdeArchivo();

    }//GEN-LAST:event_BotonEliminarVentaActionPerformed

    private void BotonParaEliminarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonParaEliminarVentaActionPerformed
        System.out.println("Codigo a Eliminar: " + CodigoE);
        if (CodigoE.equalsIgnoreCase("Seleccionar empleado")) {
                        sonido("/Sonidos/error.wav");
            JOptionPane.showMessageDialog(null, "Debe seleccionar un codigo.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Scanner sc = new Scanner(System.in);
            EliminarRegistroVenta(sc, "Ventas", TablaVENTAS);
            sc.close();
            Scanner sc2 = new Scanner(System.in);
            LeerNormal(sc2, "Ventas", TablaVENTAS);
            sc2.close();
        }
        cargarElementosDesdeArchivo();

    }//GEN-LAST:event_BotonParaEliminarVentaActionPerformed

    private void cerrareliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrareliminarActionPerformed
        FrameEliminarVenta.setVisible(false);
        fondoborrosoventas.setVisible(false);
        TablaVENTAS.setVisible(true);
        jScrollPane3.setVisible(true);
        BotonRegistrarVenta.setVisible(true);
        BotonEliminarVenta.setVisible(true);
        TituloPanel.repaint();
    }//GEN-LAST:event_cerrareliminarActionPerformed
    String NombreEmpleado, TipoAuto;
    private void fvendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fvendedorActionPerformed
    Object selectedItem2 = fvendedor.getSelectedItem().toString();
        if (selectedItem2 != null) {
            NombreEmpleado = selectedItem2.toString();
            Scanner sc = new Scanner(System.in);
            LeerEmpleados2(sc, "Empleados", NombreEmpleado);
            sc.close();
        } else {
            
        }
        
    }//GEN-LAST:event_fvendedorActionPerformed

    private void ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AgregarVentas("Ventas");
        Scanner sc = new Scanner(System.in);
        LeerNormal(sc, "Ventas", TablaVENTAS);
        sc.close();
        TipoAuto = ComboBox.getSelectedItem().toString();
        NombreEmpleado = fvendedor.getSelectedItem().toString();
        if (NombreEmpleado.equalsIgnoreCase("Seleccionar empleado")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (TipoAuto.equalsIgnoreCase("Seleccionar tipo de auto")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de auto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarElementosDesdeArchivo2();
        String seleccionarv = "Seleccionar empleado";
        String seleccionarc = "Seleccionar tipo de auto";
        fvendedor.setSelectedItem(seleccionarv);
        ComboBox.setSelectedItem(seleccionarc);
        fcedulav.setText("");
        fcodigo.setText("");
        fmonto.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String seleccionarv = "Seleccionar empleado";
        String seleccionarc = "Seleccionar tipo de auto";
        fvendedor.setSelectedItem(seleccionarv);
        ComboBox.setSelectedItem(seleccionarc);
        fcedulav.setText("");
        fcodigo.setText("");
        fmonto.setText("");
        sonido("/Sonidos/ficha.wav");
    }//GEN-LAST:event_jButton3ActionPerformed


    private void BTNgrafiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNgrafiActionPerformed
        sonido("/Sonidos/boop.wav");
        //cambiar nombre boton
        if (BTNgrafi.getText() == "Gráficas") {
            BTNgrafi.setText("Inventario");
        } else {
            BTNgrafi.setText("Gráficas");
        }
        CambiaEstadoPANEL(GrafiPanel);
        CambiaEstadoPANEL(jScrollPane2);
        TituloPanel.repaint();
        //llamar libreria
        DefaultPieDataset datos = new DefaultPieDataset();
        //Establecer valores
        datos.setValue("Toyota ", cantT);
        datos.setValue("Ford ", cantF);
        datos.setValue("Honda", cantH);
        datos.setValue("BMW", cantB);
        datos.setValue("Mercedes - Benz", cantM);
        //Crear objeto para grafico

        //Graficos 2D
//        JFreeChart grafico_circular=ChartFactory.createPieChart(
//              //Argumentos
//                "Cantidad Vendida",//Nombre Grafico 
//                datos,//datos
//                true,//categorias
//                true,//herramientas
//                false//url
//        );
        //Grafico 3D REDONDO
        JFreeChart grafico_circular = ChartFactory.createPieChart3D("Cantidad De Autos Vendida", datos, true, true, false);
        PiePlot piePlot = (PiePlot) grafico_circular.getPlot();
        piePlot.setBackgroundPaint(fondoclaro);

        ChartPanel pnl = new ChartPanel(grafico_circular);
        pnl.setMouseWheelEnabled(true);
        pnl.setPreferredSize(new Dimension(500, 400));

        Gpa1.setLayout(new BorderLayout());
        Gpa1.add(pnl, BorderLayout.WEST);

        //gRAFICO DE BARRAS
        DefaultCategoryDataset barchartData = new DefaultCategoryDataset(); //Llamar libreria
        barchartData.setValue((int) MontoT, "Monto Total", "Toyota");//Establecer datos
        barchartData.setValue((int) MontoF, "Monto Total", "Ford");
        barchartData.setValue((int) MontoH, "Monto Total", "Honda");
        barchartData.setValue((int) MontoB, "Monto Total", "BMW");
        barchartData.setValue((int) MontoM, "Monto Total", "Mercedes-Benz");
        //Crear tabla
        JFreeChart barChart = ChartFactory.createBarChart3D("Total Obtenido", "Tipos De Auto", "Monto", barchartData, PlotOrientation.HORIZONTAL, false, true, false);
        CategoryPlot barchrt = barChart.getCategoryPlot();
        //Modificar los numeros de los ejes
        NumberAxis yAxis = (NumberAxis) barchrt.getRangeAxis();
        NumberFormat format = new DecimalFormat("#,###.#"); // Personaliza el formato de los números
        yAxis.setNumberFormatOverride(format);

        //Usar la grafica en el panel
        ChartPanel pnl2 = new ChartPanel(barChart);
        pnl2.setMouseWheelEnabled(true);
        pnl2.setPreferredSize(new Dimension(550, 400));
        Gpa.setLayout(new BorderLayout());
        Gpa.add(pnl2, BorderLayout.EAST);
        //Verificar
        pnl.validate();
        pnl2.validate();
        pack();
        repaint();
    }//GEN-LAST:event_BTNgrafiActionPerformed

    private void BuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorActionPerformed
        String valorBuscado = EmpleBuscador.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaEMPLEADOS.getModel();
        boolean coincidenciaEncontrada = false;
        if (!valorBuscado.isEmpty()) { // Validar que el campo de búsqueda no esté vacío
            for (int row = 0; row < modelo.getRowCount(); row++) {
                Object valorCelda = modelo.getValueAt(row, 0); // Acceder solo a la primera columna
                if (valorCelda != null && valorCelda.toString().toLowerCase().contains(valorBuscado)) {
                    TablaEMPLEADOS.setRowSelectionInterval(row, row);
                    TablaEMPLEADOS.scrollRectToVisible(TablaEMPLEADOS.getCellRect(row, 0, true));
                    coincidenciaEncontrada = true;
                    break;
                }
            }
        }

        if (coincidenciaEncontrada == true) {
            MostrarBusquedaEmple.setVisible(true);
            MostrarBusquedaEmple.setText("Empleado encontrado");

        } else {
            MostrarBusquedaEmple.setVisible(true);
            MostrarBusquedaEmple.setText("Empleado no encontrado");
        }
    }//GEN-LAST:event_BuscadorActionPerformed

    private void BTNBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBuscarActionPerformed
        String valorBuscado = EmpleBuscador.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaEMPLEADOS.getModel();
        boolean coincidenciaEncontrada = false;
        if (!valorBuscado.isEmpty()) { // Validar que el campo de búsqueda no esté vacío
            for (int row = 0; row < modelo.getRowCount(); row++) {
                Object valorCelda = modelo.getValueAt(row, 0); // Acceder solo a la primera columna
                if (valorCelda != null && valorCelda.toString().toLowerCase().contains(valorBuscado)) {
                    TablaEMPLEADOS.setRowSelectionInterval(row, row);
                    TablaEMPLEADOS.scrollRectToVisible(TablaEMPLEADOS.getCellRect(row, 0, true));
                    coincidenciaEncontrada = true;
                    break;
                }
            }
        }

        if (coincidenciaEncontrada == true) {
            MostrarBusquedaEmple.setVisible(true);
            MostrarBusquedaEmple.setText("Empleado encontrado");
            sonido("/Sonidos/boop.wav");

        } else {
            MostrarBusquedaEmple.setVisible(true);
            MostrarBusquedaEmple.setText("Empleado no encontrado");
            sonido("/Sonidos/error.wav");
        }
    }//GEN-LAST:event_BTNBuscarActionPerformed

    private void BuscadorVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BuscadorVentasMouseClicked
        BuscadorVentas.setText(null);
        MostrarBusquedaVentas.setText(null);
        
    }//GEN-LAST:event_BuscadorVentasMouseClicked

    private void EmpleBuscadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EmpleBuscadorMouseClicked
        EmpleBuscador.setText(null);
        MostrarBusquedaEmple.setText(null);
    }//GEN-LAST:event_EmpleBuscadorMouseClicked

    private void BTNBuscarVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNBuscarVentasActionPerformed
        String valorBuscado = BuscadorVentas.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaVENTAS.getModel();
        boolean coincidenciaEncontrada = false;
        if (!valorBuscado.isEmpty()) { // Validar que el campo de búsqueda no esté vacío
            for (int row = 0; row < modelo.getRowCount(); row++) {
                Object valorCelda = modelo.getValueAt(row, 3); // Acceder solo a la cuarta columna
                if (valorCelda != null && valorCelda.toString().toLowerCase().contains(valorBuscado)) {
                    TablaVENTAS.setRowSelectionInterval(row, row);
                    TablaVENTAS.scrollRectToVisible(TablaVENTAS.getCellRect(row, 0, true));
                    coincidenciaEncontrada = true;
                    break;
                }
            }
        }

        if (coincidenciaEncontrada == true) {
            MostrarBusquedaVentas.setVisible(true);
            MostrarBusquedaVentas.setText("Venta encontrada");
            sonido("/Sonidos/boop.wav");

        } else {
            MostrarBusquedaVentas.setVisible(true);
            MostrarBusquedaVentas.setText("Venta no encontrada");
            sonido("/Sonidos/error.wav");
        }
    }//GEN-LAST:event_BTNBuscarVentasActionPerformed

    private void BuscadorVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscadorVentasActionPerformed
        String valorBuscado = BuscadorVentas.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaVENTAS.getModel();
        boolean coincidenciaEncontrada = false;
        if (!valorBuscado.isEmpty()) { // Validar que el campo de búsqueda no esté vacío
            for (int row = 0; row < modelo.getRowCount(); row++) {
                Object valorCelda = modelo.getValueAt(row, 3); // Acceder solo a la cuarta columna
                if (valorCelda != null && valorCelda.toString().toLowerCase().contains(valorBuscado)) {
                    TablaVENTAS.setRowSelectionInterval(row, row);
                    TablaVENTAS.scrollRectToVisible(TablaVENTAS.getCellRect(row, 0, true));
                    coincidenciaEncontrada = true;
                    break;
                }
            }
        }

        if (coincidenciaEncontrada == true) {
            MostrarBusquedaVentas.setVisible(true);
            MostrarBusquedaVentas.setText("Venta encontrada");

        } else {
            MostrarBusquedaVentas.setVisible(true);
            MostrarBusquedaVentas.setText("Venta no encontrada");
        }

    }//GEN-LAST:event_BuscadorVentasActionPerformed

    String CodigoE;

    private void combocodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combocodigosActionPerformed
        Object selectedItem = combocodigos.getSelectedItem();
        if (selectedItem != null) {
            CodigoE = selectedItem.toString();
        } else {
            
        }
    }//GEN-LAST:event_combocodigosActionPerformed

    private void EmpleBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpleBuscadorActionPerformed
        String valorBuscado = EmpleBuscador.getText().toLowerCase();
        DefaultTableModel modelo = (DefaultTableModel) TablaEMPLEADOS.getModel();
        boolean coincidenciaEncontrada = false;
        if (!valorBuscado.isEmpty()) { // Validar que el campo de búsqueda no esté vacío
            for (int row = 0; row < modelo.getRowCount(); row++) {
                Object valorCelda = modelo.getValueAt(row, 0); // Acceder solo a la primera columna
                if (valorCelda != null && valorCelda.toString().toLowerCase().contains(valorBuscado)) {
                    TablaEMPLEADOS.setRowSelectionInterval(row, row);
                    TablaEMPLEADOS.scrollRectToVisible(TablaEMPLEADOS.getCellRect(row, 0, true));
                    coincidenciaEncontrada = true;
                    break;
                }
            }
        }

        if (coincidenciaEncontrada == true) {
            MostrarBusquedaEmple.setVisible(true);
            MostrarBusquedaEmple.setText("Empleado encontrado");

        } else {
            MostrarBusquedaEmple.setVisible(true);
            MostrarBusquedaEmple.setText("Empleado no encontrado");
        }
    }//GEN-LAST:event_EmpleBuscadorActionPerformed

    private void AgregarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarVentaActionPerformed
        AgregarVentas("Ventas");
        Scanner sc = new Scanner(System.in);
        LeerNormal(sc, "Ventas", TablaVENTAS);
        sc.close();
        TipoAuto = ComboBox.getSelectedItem().toString();
        NombreEmpleado = fvendedor.getSelectedItem().toString();
        if (NombreEmpleado.equalsIgnoreCase("Seleccionar empleado")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (TipoAuto.equalsIgnoreCase("Seleccionar tipo de auto")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de auto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        cargarElementosDesdeArchivo2();
        String seleccionarv = "Seleccionar empleado";
        String seleccionarc = "Seleccionar tipo de auto";
        fvendedor.setSelectedItem(seleccionarv);
        ComboBox.setSelectedItem(seleccionarc);
        fcedulav.setText("");
        fcodigo.setText("");
        fmonto.setText("");
    }//GEN-LAST:event_AgregarVentaActionPerformed

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
    private javax.swing.JButton AgregarVenta;
    private javax.swing.JButton BTNBuscar;
    private javax.swing.JButton BTNBuscarVentas;
    private javax.swing.JButton BTNgrafi;
    private javax.swing.JButton BotonAgregarEmpleados;
    private javax.swing.JButton BotonEliminarEmpleados;
    private javax.swing.JButton BotonEliminarVenta;
    private javax.swing.JButton BotonLimpiar;
    private javax.swing.JButton BotonLimpiar1;
    private javax.swing.JRadioButton BotonOrdenar;
    private javax.swing.JRadioButton BotonOrdenarSalario;
    private javax.swing.JButton BotonParaEliminarVenta;
    private javax.swing.JButton BotonRegistrarVenta;
    private javax.swing.JRadioButton BotonSinOrdenar;
    private javax.swing.JButton Boton_Empleados;
    private javax.swing.JButton Boton_Inventario;
    private javax.swing.JButton Boton_Ventas;
    private javax.swing.JButton BotonparaAgregar;
    private javax.swing.JButton BotonparaEliminar;
    private javax.swing.JTextField BuscadorVentas;
    private javax.swing.JLabel CantVendidaBMW;
    private javax.swing.JLabel CantVendidaFord;
    private javax.swing.JLabel CantVendidaHonda;
    private javax.swing.JLabel CantVendidaMercedes;
    private javax.swing.JLabel CantVendidaToyota;
    private javax.swing.JComboBox<String> ComboBox;
    private javax.swing.JTextField EmpleBuscador;
    private javax.swing.JInternalFrame FrameAgregar;
    private javax.swing.JInternalFrame FrameAgregarVenta;
    private javax.swing.JInternalFrame FrameEliminar;
    private javax.swing.JInternalFrame FrameEliminarVenta;
    private javax.swing.JPanel Gpa;
    private javax.swing.JPanel Gpa1;
    private javax.swing.JPanel GrafiPanel;
    private javax.swing.JButton InfoBTN;
    private javax.swing.JLabel InfoLabel;
    private javax.swing.JPanel InventarioSubPanel;
    private javax.swing.JLabel LabelFondoBorroso;
    private javax.swing.JButton Modo;
    private javax.swing.JLabel MostrarBusquedaEmple;
    private javax.swing.JLabel MostrarBusquedaVentas;
    private javax.swing.JPanel PanelAgregarEmpleado;
    private javax.swing.JPanel PanelEliminarEmpleado;
    private javax.swing.JPanel PanelEmpleados;
    private javax.swing.JPanel PanelInfo;
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
    private javax.swing.JButton cerrareliminar;
    private javax.swing.JComboBox<String> combocodigos;
    private javax.swing.JLabel error1;
    private javax.swing.JLabel error2;
    private javax.swing.JLabel error3;
    private javax.swing.JLabel error3v;
    private javax.swing.JLabel error4;
    private javax.swing.JLabel error4v;
    private javax.swing.JLabel error5;
    private javax.swing.JLabel error6;
    private javax.swing.JLabel error7;
    private javax.swing.JLabel errornombre;
    private javax.swing.JTextField fcargo;
    private javax.swing.JTextField fcedula;
    private javax.swing.JTextField fcedulav;
    private javax.swing.JTextField fcodigo;
    private javax.swing.JTextField ffechaingreso;
    private javax.swing.JTextField fmonto;
    private javax.swing.JTextField fnombre;
    private javax.swing.JTextField fnombreE;
    private javax.swing.JLabel fondoborrosoventas;
    private javax.swing.JTextField fsalariocomisiones;
    private javax.swing.JTextField fsalariofijo;
    private javax.swing.JTextField ftelefono;
    private javax.swing.JComboBox<String> fvendedor;
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
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}