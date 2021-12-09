package lab16;


public class usuarios {
    String codigo, apellidos, nombres, direccion, distrito;

    public usuarios(String codigo, String apellidos, String nombres, String direccion, String distrito) {
        this.codigo = codigo;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.direccion = direccion;
        this.distrito = distrito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
}
