package accesoADatos;



import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Martin Piriz
 */
public class Conexion {

    public static Connection conec = null;
    private static final String host = "jdbc:mariadb://localhost/";
    private static final String user = "lab";
    private static final String pass = "1234";
    private static final String bd = "universidadulp";
    private static final String driver = "org.mariadb.jdbc.Driver";
    public static ArrayList<String> msjError = new ArrayList<String>();
    
    private Conexion(){
        
    }
    
    public static Connection getConexion() {
        
        if (servicioMysql()) {
            if (servicioMysql()) {

                try {
                    Class.forName(driver);
                    
                    conec = DriverManager.getConnection(host + bd, user, pass);
                   
                } catch (NullPointerException | SQLException | ClassNotFoundException ex) {
                   
                    msjError.add("Conexion: getConexion() -> " + ex.getMessage());
                }
            }
        } else {
            msjError.add("Servicio mysql esta caido.- llamada en getConeccion()");
        }
        return conec;

    }

    public static boolean servicioMysql() {
        boolean res;
        String host = "localhost";
        int puerto = 3306;

        try {

            Socket socket = new Socket(host, puerto);
            res = true;
            socket.close();
        } catch (Exception e) {
            res = false;

        }
        return res;
    }

    public static boolean getDesconexion() {
        boolean result;
        try {
            conec.close();
            // System.out.println("desconectado");
            conec = null;
            result = true;
        } catch (NullPointerException | SQLException ex) {

            msjError.add("fallo la desconexion: " + ex.getMessage());
            result = false;
        }
        return result;
    }

    // retorna un ResultSet
    public static ResultSet consulta(String sql) {
        ResultSet res = null;
        

            Statement stmt;

            try {
                stmt = conec.createStatement();
                res = stmt.executeQuery(sql);

            } catch (SQLException ex) {

                msjError.add("fallo la consulta: " + ex.getMessage());
            }

        
        return res;
    }

    // devulve 1 el usuario se borro o se actualizo depende de la consulta


    public static void mostrarErrores() {
        if (msjError.size() > 0) {
            for (String arg : msjError) {
                System.out.println(arg);
            }
        }
    }

}
