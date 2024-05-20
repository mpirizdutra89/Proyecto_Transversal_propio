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
            Conexion.msjError.add("IncripcionData: guardarInscripcion ->" + ex.getMessage());
        }

        // ver donde cerrrar la coneccion puede ser en el main o vista o cuando ce
        // cierra un jFrame interno
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

                    lista.add(new Inscripcion(idInscripto, alumData.buscarAlumnoID(idAlumno),
                            mateData.buscarMateria(idMateria), nota));
                }
            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Conexion.msjError.add("IncripcionData: obtenerInscripcion()  -> " + ex.getMessage());
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

                lista.add(new Inscripcion(idInscripto, alumData.buscarAlumnoID(idAlumno),
                        mateData.buscarMateria(idMateria), nota));

            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Conexion.msjError.add("IncripcionData: obtenerInscripcionPorAlumno(int id) ->" + ex.getMessage());
        }

        return lista;
    }

    public ArrayList<Materia> obtenerMateriasCursadas(int idAlumno) {
        ArrayList<Materia> lista = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement ps = null;

        inscripcion = null;
        String consulta = "SELECT M.idMateria, nombre, año, estado FROM inscripcion I,materia M WHERE M.idMateria=I.idMateria and  idAlumno=?";

        try {
            ps = conec.prepareStatement(consulta);
            ps.setInt(1, idAlumno);
            res = ps.executeQuery();
            if (res.next()) {
                int idMateria = res.getInt("idMateria");
                String nombre = res.getString("nombre");
                int year = res.getInt("año");
                boolean estado = res.getBoolean("estado");

                lista.add(new Materia(idMateria, nombre, year, estado));

            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Conexion.msjError.add("IncripcionData:  obtenerMateriasCursadas(int idAlumno) ->" + ex.getMessage());
        }

        return lista;
    }

    public ArrayList<Materia> obtenerMateriasNoCursadas() {
        ArrayList<Materia> lista = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement ps = null;

        inscripcion = null;
        String consulta = "select * from materia where idMateria NOT IN (SELECT M.idMateria FROM materia M,inscripcion I WHERE M.idMateria=I.idMateria and estado=1) and estado=1;";
        try {
            ps = conec.prepareStatement(consulta);

            res = ps.executeQuery();
            if (res.next()) {
                int idMateria = res.getInt("idMateria");
                String nombre = res.getString("nombre");
                int year = res.getInt("año");
                boolean estado = res.getBoolean("estado");

                lista.add(new Materia(idMateria, nombre, year, estado));

            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Conexion.msjError.add("IncripcionData:  obtenerMateriasNoCursadas() ->" + ex.getMessage());
        }

        return lista;
    }

    public boolean borrarInscripcionMateriaALumno(int idAlumno, int idMateria) {
        String query = "DELETE FROM inscripcion WHERE idAlumno=? and idMateria=?";

        boolean res = false;

        try {
            PreparedStatement ps = conec.prepareStatement(query);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int borrado = ps.executeUpdate();

            if (borrado == 1) {
                res = true;
            }
            ps.close();

        } catch (SQLException ex) {

            Conexion.msjError.add("IncripcionData: borrarInscripcionMateriaALumno() ->" + ex.getMessage());
        }

        return res;
    }

    public boolean actualizarNota(int idAlumno, int idMateria, double nota) {
        String query = "UPDATE inscripcion SET nota=? WHERE idAlumno=? and idMateria=?";

        boolean res = false;

        try {
            PreparedStatement ps = conec.prepareStatement(query);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            int actualizar = ps.executeUpdate();

            if (actualizar == 1) {
                res = true;
            }
            ps.close();

        } catch (SQLException ex) {

            Conexion.msjError.add("MateriaData: actualizarNota() ->" + ex.getMessage());
        }

        return res;
    }

    public ArrayList<Alumno> obtenerAlumnoXMAteria(int idMateria) {
        ArrayList<Alumno> lista = new ArrayList<>();
        ResultSet res = null;
        PreparedStatement ps = null;

        inscripcion = null;
        String consulta = "SELECT A.idAlumno,dni,apellido, nombre, fechaNacimiento, estado FROM inscripcion I,alumno A WHERE A.idAlumno=I.idAlumno and  idMateria=?";

        try {
            ps = conec.prepareStatement(consulta);
            ps.setInt(1, idMateria);
            res = ps.executeQuery();
            if (res.next()) {
                int idAlumno = res.getInt("idMateria");
                int dni = res.getInt("dni");
                String apellido = res.getString("apellido");
                String nombre = res.getString("nombre");
                LocalDate fechaNacimiento = res.getDate("fechaNacimiento").toLocalDate();
                boolean estado = res.getBoolean("estado");

                lista.add(new Alumno(idAlumno, dni, apellido, nombre, fechaNacimiento, estado));

            }
            ps.close();
            res.close();

        } catch (SQLException ex) {
            Conexion.msjError.add("IncripcionData:  obtenerAlumnoXMAteria(int idMateria) ->" + ex.getMessage());
        }

        return lista;
    }

    // // fin
}
