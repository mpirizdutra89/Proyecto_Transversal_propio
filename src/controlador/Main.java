package controlador;

import accesoADatos.Conexion;
import accesoADatos.AlumnoData;
import entidades.Alumno;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Nicolas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Preubas para la etapa 1
        Alumno alumnos1 = new Alumno(565, "piriz", "martin", LocalDate.of(1990, 12, 20), false);
        AlumnoData pruebasAlumnos = new AlumnoData();
        
        pruebasAlumnos.guardarAlumno(alumnos1);
        ArrayList<Alumno> listaAlumno = pruebasAlumnos.listarAlumnos();
        System.out.println(listaAlumno.size());
        for (Alumno alumno : listaAlumno) {
            System.out.println(alumno);
        }
        if(Conexion.getConexion()){
            System.out.println("conectado?");
        }
        
        Conexion.mostrarErrores();
    }
    
}
