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

    public AlumnoData() {
    }
     
     
     
     
     
    public  boolean guardarAlumno(Alumno alumno) {
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
                if (ex.getErrorCode() == 1062) {//es para que capturar campos unicos
                    //igual va a seguir sliendo ya que es la libreria lo que lo imprime
                    //[ WARN] (main) Error: 1062-23000: Duplicate entry '565' for key 'alumno.dni'
                    //igual el usuario no va averla consola
                  
                     Conexion.msjError.add("AlumnosDAta: guardarAlumno 'Clave repetida para dni' ->" + ex.getMessage());
                }else{
                     Conexion.msjError.add("AlumnosDAta: guardarAlumno ->" + ex.getMessage());
                }
               
            }
        }
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno
        
        return res;
    }

    public  Alumno buscarAlumnoID(int id){
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
                Conexion.msjError.add("AlumnosDAta: BuscarAlumnoID ->" + ex.getMessage());
            }
        }
            
        
        
        return alumno;
    }
    
        public  Alumno buscarAlumnoDni(int dni){
        alumno=null;
        PreparedStatement ps = null;
        String consulta="SELECT dni,apellido,nombre,fechaNacimiento FROM alumno WHERE  dni= ? and estado=1";
        if(Conexion.getConexion()){
        try{
             ps = Conexion.conec.prepareStatement(consulta);
             ps.setInt(1,dni );
             ResultSet res = ps.executeQuery();
             if(res.next()){
                 alumno=new Alumno();
                 alumno.setIdAlumno(res.getInt("idAlumno"));
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
                Conexion.msjError.add("AlumnosData: BuscarAlumnoDNI ->" + ex.getMessage());
            }
        }
            
        
        
        return alumno;
    }
    
     public  boolean modificarMateria(Alumno alumno) {
        String query = "UPDATE alumno SET dni=?,apellido=?,nombre=?,fechaNacimiento=?,estado=? where idAlumno=?";

        boolean res = false;
        if (Conexion.getConexion()) {
            try {
                PreparedStatement ps = Conexion.conec.prepareStatement(query);

                ps.setInt(1, alumno.getDni());
                ps.setString(2, alumno.getApellido());
                ps.setString(3, alumno.getNombre());
                ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
                ps.setBoolean(5,alumno.getEstado());
                ps.setInt(6, alumno.getIdAlumno());
                ps.executeUpdate();//devuelve 1 0 mas si varias filas se afectaron y si hay erroe sale por la excepcion
                
                res=true;
                ps.close();
            } catch (SQLException ex) {

                Conexion.msjError.add("AlumnoDAta: modiicarAlumno() ->" + ex.getMessage());
            }
        }
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno

        return res;
    }
    
    public  boolean eliminarAlumno(int idAlumno) {
        String query = "DELETE FROM alumno WHERE idAlumno=?"; //la otra opcion seria darle de baja

        boolean res = false;
        if (Conexion.getConexion()) {
            try {
                PreparedStatement ps = Conexion.conec.prepareStatement(query);
                ps.setInt(1,idAlumno);
               int borrado= ps.executeUpdate();//devuelve 1 0 mas si varias filas se afectaron y si hay erroe sale por la excepcion
                if(borrado==1){
                    res=true;
                }
                ps.close();
            } catch (SQLException ex) {

                Conexion.msjError.add("AlumnosData: borrado de la alumno ->" + ex.getMessage());
            }
        }
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno

        return res;
    }
        
    public  ArrayList<Alumno> listarAlumnos() {
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
