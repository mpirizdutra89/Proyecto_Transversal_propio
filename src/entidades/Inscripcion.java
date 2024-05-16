package entidades;

/**
 *
 * @author Nicolas
 */
public class Inscripcion {
    int idInscripto;
    Alumno alumno;
    Materia materia;
    double nota;
    //`idInscripto`, `idAlumno`, `idMateria`, `nota` FROM `inscripcion`

    public Inscripcion() {
    }

    public Inscripcion(Alumno alumno, Materia materia, double nota) {
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(int idInscripto, Alumno alumno, Materia materia, double nota) {
        this.idInscripto = idInscripto;
        this.alumno = alumno;
        this.materia = materia;
        this.nota = nota;
    }

    public Inscripcion(double nota) {
        this.nota = nota;
    }
    
    

    public int getIdInscripto() {
        return idInscripto;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public int getIdAlumno(){
        return alumno.getIdAlumno();
    }
    public void setIdAlumnno(int id){
        alumno.setIdAlumno(id);
    }
    
    public int getIdMateria(){
        return materia.getIdMateria();
    }
    public void setIdMateria(int id){
        materia.setIdMateria(id);
    }
    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    
}
