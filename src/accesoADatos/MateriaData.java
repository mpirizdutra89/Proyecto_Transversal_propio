package accesoADatos;

import entidades.Materia;
import java.sql.Connection;

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

    private  Materia materia;
    private static Connection conec = null;

    public MateriaData() {
        conec = Conexion.getConexion();
    }

    public  boolean guardarMateria(Materia materia) {
        String query = "INSERT INTO materia (nombre, año, estado) VALUES (?,?,?)";

        boolean res = false;
       
            try {
                PreparedStatement ps = conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

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

                Conexion.msjError.add("MAteriaData: guardarMAteria ->" + ex.getMessage());
            }
        
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno

        return res;
    }

    public  Materia buscarMateria(int id) {
        materia = null;
        PreparedStatement ps = null;
        String consulta = "SELECT nombre,año,estado FROM materia WHERE  idMateria= ? and estado=1";
       
            try {
                ps = conec.prepareStatement(consulta);
                ps.setInt(1, id);
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    materia = new Materia();
                    materia.setIdMateria(id);
                    materia.setNombre(res.getString("nombre"));
                    materia.setYear(res.getInt("año"));
                    materia.setEstado(res.getBoolean("estado"));
                } else {
                    JOptionPane.showMessageDialog(null, "No existe el materia");
                }

            } catch (SQLException ex) {
                Conexion.msjError.add("MateriaData: BuscarMteria ->" + ex.getMessage());
            }
        

        return materia;
    }

    public  boolean modificarMateria(Materia materia) {
        String query = "UPDATE materia SET nombre=?,año=?,estado=? WHERE idMateria=?";

        boolean res = false;
       
            try {
                PreparedStatement ps = conec.prepareStatement(query);

                ps.setString(1, materia.getNombre());
                ps.setInt(2, materia.getYear());
                ps.setBoolean(3, materia.getEstado());
                ps.setInt(4, materia.getIdMateria());
                ps.executeUpdate();//devuelve 1 0 mas si varias filas se afectaron y si hay erroe sale por la excepcion
                
                res=true;
                ps.close();
            } catch (SQLException ex) {

                Conexion.msjError.add("MateriaData: modiicarAlumno() ->" + ex.getMessage());
            }
        
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno

        return res;
    }
    //baja logica
    public  boolean eliminarMateria(int idMateria) {
        String query = "UPDATE  materia SET estado=0 WHERE idMateria=?"; //la otra opcion seria darle de baja

        boolean res = false;
        
            try {
                PreparedStatement ps = conec.prepareStatement(query);
                ps.setInt(1,idMateria);
               int borrado= ps.executeUpdate();//devuelve 1 0 mas si varias filas se afectaron y si hay erroe sale por la excepcion
                if(borrado==1){
                    res=true;
                }
                ps.close();
            } catch (SQLException ex) {

                Conexion.msjError.add("MateriaData: borrado de la materia ->" + ex.getMessage());
            }
        
        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno

        return res;
    }
    
    
    

    public ArrayList<Materia> listarMateria() {
        ArrayList<Materia> lista = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement ps = null;

        String consulta = "Select * from materia where estado=1 ";

        try {
            ps = conec.prepareStatement(consulta);

            res = ps.executeQuery();
            if (res != null) {
                while (res.next()) {
                    int idMateria = res.getInt("idMateria");
                    String nombre = res.getString("nombre");
                    int year = res.getInt("año");
                    boolean estado = res.getBoolean("estado");
                    lista.add(new Materia(idMateria, nombre, year, estado));
                }
            }

        } catch (SQLException ex) {
            Conexion.msjError.add("MateriaData: listarMateria ->" + ex.getMessage());
        }
        return lista;
    }

}
