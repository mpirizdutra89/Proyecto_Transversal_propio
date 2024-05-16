package accesoADatos;

import accesoADatos.Conexion;
import entidades.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JOptionPane;
/**
 *
 * @author Nicolas
 */
public class AlumnoData {
     private static Alumno alumno;

    public static boolean guardarAlumno(Alumno alumno) {
        String query = "INSERT INTO alumno (dni, apellido,nombre,fechaNacimiento,estado) VALUES (?,?,?,?,?)";
        
        boolean res = false; 
        if (Conexion.getConexion()) {
            try {
                PreparedStatement ps = Conexion.conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, alumno.getDni());
                ps.setString(2, alumno.getApellido());
                ps.setString(3, alumno.getNombre());
                ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
                ps.setBoolean(5, alumno.getEstado());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    alumno.setIdAlumno(rs.getInt(1));
                    res = true;
                }
                ps.close();
            } catch (SQLException ex) {
                Conexion.msjError.add("Alumnos: guardarAlumno ->" + ex.getMessage());
            }
        }
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno
        
        return res;
    }

    public static Alumno buscarAlumno(int id){
        alumno=null;
        PreparedStatement ps = null;
        String consulta="SELECT dni,apellido,nombre,fechaNacimiento FROM alumno WHERE  idAlumno= ? and estado=1";
        if(Conexion.getConexion()){
        try{
             ps = Conexion.conec.prepareStatement(consulta);
             ps.setInt(1,id );
             ResultSet res = ps.executeQuery();
             if(res.next()){
                 alumno=new Alumno();
                 alumno.setIdAlumno(id);
                 alumno.setDni(res.getInt("dni"));
                 alumno.setApellido(res.getString("apellido"));
                 alumno.setNombre(res.getString("nombre"));
                 alumno.setFechaNacimiento(res.getDate("fechaNacimiento").toLocalDate());
                 alumno.setEstado(res.getBoolean("estado"));
             }
             else{
                 JOptionPane.showMessageDialog(null, "No existe el alumno");   
             }
                
        
         } catch (SQLException ex) {
                Conexion.msjError.add("Alumnos: BuscarAlumno ->" + ex.getMessage());
            }
        }
            
        
        
        return alumno;
    }
        
    public static ArrayList<Alumno> listarAlumnos() {
        ArrayList<Alumno> lista = new ArrayList<>();
        ResultSet res = null;
        alumno = null;
        try {

            res = Conexion.consulta("Select * from alumno");
            if (res != null) {
                while (res.next()) {
                    //`idAlumno`, `dni`, `apellido`, `nombre`, `fechaNacimiento`, `estado`
                    int idAlumno = res.getInt("idAlumno");
                    int dni = res.getInt("dni");
                    String apellido = res.getString("apellido");
                    String nombre = res.getString("nombre");
                    LocalDate fechaNacimiento = res.getDate("fechaNacimiento").toLocalDate();
                    boolean estado = res.getBoolean("estado");

                    lista.add(new Alumno(idAlumno, dni, apellido, nombre, fechaNacimiento, estado));
                }
            }

        } catch (SQLException ex) {
            Conexion.msjError.add("fallo la consulta: " + ex.getMessage());
        }

        return lista;
    }

}
