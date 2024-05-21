package librerias;

import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Nicolas
 */
public class FuncionesComunes {

    public static void textPrompt(JTextField textField, String titulo) {
        librerias.TextPrompt placeholder = new librerias.TextPrompt(titulo, textField);

        placeholder.changeAlpha(0.50f);
        placeholder.changeStyle(Font.ITALIC);

    }

    //reseteal loscomponentes dentro del panel
    public static void resetFormContentPanel(JPanel jpnl) {
        Component[] components = jpnl.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("");
            }
            if (component instanceof JCheckBox) {
                JCheckBox chb = (JCheckBox) component;
                chb.setSelected(false);
            }
        }
    }

    public static boolean EventoValidarNumericos(String cadena) {
        return cadena.matches("^[0-9]+$");
    }

    public static boolean EventoValidarDoubles(String cadena) {
        return cadena.matches("^[0-9]+\\.[0-9]{2}$");
    }
    
    public static boolean validarVacio(String text){
       
         return !text.isEmpty();
    }
    
    
    //tipo es el logito para error o informacion 0 o 1 creo 
     public static void vistaDialogo(String msj, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, msj, titulo, tipo);
    }

    public static void vistaDialogo(String msj, int tipo) {
        JOptionPane.showMessageDialog(null, msj, "", tipo);
    }
//devuelve true si acepta
    public static boolean vistaDialogoSiNo() {
        return JOptionPane.showConfirmDialog(null, "¿Deseas continuar?", "Confirmación",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

    }
    
    public static String voltearFecha( LocalDate fechaNacimiento,boolean tipo){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if(tipo){
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
            
         
        return fechaNacimiento.format(formatter);
    }
    
    public static LocalDate convertirFechaBd(String date){
        LocalDate fecha = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        return fecha;
    }
    
    public static boolean validaFecha(String date) {
        // Expresión regular para el formato dd-MM-yyyy
        String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

}
