package accesoADatos;

import entidades.Materia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas
 */
public class MateriaData {

    private static Materia materia;

    public static boolean guardarAlumno(Materia materia) {
        String query = "INSERT INTO materia (nombre, año, estado) VALUES (?,?,?)";

        boolean res = false;
        if (Conexion.getConexion()) {
            try {
                PreparedStatement ps = Conexion.conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getYear());
                ps.setBoolean(3, materia.getEstado());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    materia.setIdMateria(rs.getInt(1));
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
    
    
       public static Materia buscarAlumno(int id){
        materia=null;
        PreparedStatement ps = null;
        String consulta="SELECT nombre,año,estado FROM materia WHERE  idMateria= ? and estado=1";
        if(Conexion.getConexion()){
        try{
             ps = Conexion.conec.prepareStatement(consulta);
             ps.setInt(1,id );
             ResultSet res = ps.executeQuery();
             if(res.next()){
                 materia=new Materia();
                 materia.setIdMateria(id);
                 materia.setNombre(res.getString("nombre"));
                 materia.setYear(res.getInt("año"));
                 materia.setEstado(res.getBoolean("estado"));
             }
             else{
                 JOptionPane.showMessageDialog(null, "No existe el materia");   
             }
                
        
         } catch (SQLException ex) {
                Conexion.msjError.add("Alumnos: BuscarAlumno ->" + ex.getMessage());
            }
        }
            
        
        
        return materia;
    }
    
    
    public static ArrayList<Materia> listarMateria(){
        ArrayList<Materia> lista=new ArrayList<>();
        materia=null;
        ResultSet res=null;
        try {
            res=Conexion.consulta("SELECT * from materia;");
            if(res!=null){
                while(res.next()){
                    int idMateria=res.getInt("idMateria");
                    String nombre=res.getString("nombre");
                    int year=res.getInt("año");
                    boolean estado=res.getBoolean("estado");
                    lista.add(new Materia(idMateria,nombre,year,estado));
                }
            }
            
        } catch (SQLException ex) {
            Conexion.msjError.add("Materia: listarAlumno ->" + ex.getMessage());
        }
        return lista;
    } 

}
