package entidades;

/**
 *
 * @author Nicolas
 */
public class Materia {
    private int idMateria;
    private String nombre;
    private int year;
    private boolean estado;

    public Materia() {
    }

    public Materia(String nombre, int year, boolean estado) {
        this.nombre = nombre;
        this.year = year;
        this.estado = estado;
    }

    public Materia(int idMateria, String nombre, int year, boolean estado) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.year = year;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombre=" + nombre + ", year=" + year + ", estado=" + estado + '}';
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
