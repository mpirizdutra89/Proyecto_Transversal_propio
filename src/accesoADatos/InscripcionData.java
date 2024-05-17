package accesoADatos;

import entidades.Inscripcion;
import accesoADatos.Conexion;
import entidades.Alumno;
import entidades.Materia;
import java.sql.Connection;

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
public class InscripcionData {

    private static Inscripcion inscripcion;

    private MateriaData mateData;
    private AlumnoData alumData;
    private static Connection conec = null;

    public InscripcionData() {
        conec = Conexion.getConexion();
    }

    public static boolean guardarInscripcion(Inscripcion inscripcion) {
        String query = "INSERT INTO inscripcion(idAlumno, idMateria, nota) VALUES (?,?,?)";

        boolean res = false;

        try {
            PreparedStatement ps = conec.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, inscripcion.getIdAlumno());
            ps.setInt(2, inscripcion.getIdMateria());
            ps.setDouble(3, inscripcion.getNota());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setIdInscripto(rs.getInt(1));
                res = true;
            }
            ps.close();
        } catch (SQLException ex) {
            Conexion.msjError.add("IncripcionesData: guardarInscripcion ->" + ex.getMessage());
        }

        //ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce cierra un jFrame interno
        return res;
    }

    public ArrayList<Inscripcion> obtenerInscripcion() {
        ArrayList<Inscripcion> lista = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement ps = null;
        inscripcion = null;
        
        String consulta = "Select * from inscripcion ";
       
            try {
                ps = conec.prepareStatement(consulta);
                
                res = ps.executeQuery();
            if (res != null) {
                while (res.next()) {
                    // `idInscripto`, `idAlumno`, `idMateria`, `nota` FROM `inscripcion`
                    int idInscripto = res.getInt("idInscripto");
                    int idAlumno = res.getInt("idAlumno");
                    int idMateria = res.getInt("idMateria");
                    double nota = res.getDouble("nota");
                    alumData = new AlumnoData();
                    mateData = new MateriaData();

                    lista.add(new Inscripcion(idInscripto, alumData.buscarAlumnoID(idAlumno), mateData.buscarMateria(idMateria), nota));
                }
            }

        } catch (SQLException ex) {
            Conexion.msjError.add("fallo la consulta: " + ex.getMessage());
        }

        return lista;
    }

    public ArrayList<Inscripcion> obtenerInscripcionPorAlumno(int id) {
        ArrayList<Inscripcion> lista = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement ps = null;

        inscripcion = null;
        String consulta = "Select * from inscripcion where idAlumno=?";
       
            try {
                ps = conec.prepareStatement(consulta);
                ps.setInt(1, id);
                res = ps.executeQuery();
                if (res.next()) {
                    int idInscripto = res.getInt("idInscripto");
                    int idAlumno = res.getInt("idAlumno");
                    int idMateria = res.getInt("idMateria");
                    double nota = res.getDouble("nota");
                    alumData = new AlumnoData();
                    mateData = new MateriaData();

                    lista.add(new Inscripcion(idInscripto, alumData.buscarAlumnoID(idAlumno), mateData.buscarMateria(idMateria), nota));

                }

            } catch (SQLException ex) {
                Conexion.msjError.add("fallo la consulta: " + ex.getMessage());
            }
        
        return lista;
    }

    //fin
}
