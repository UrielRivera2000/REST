package mx.utez.edu.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement(name ="pendiente")
@XmlAccessorType(XmlAccessType.FIELD)

public class Pendiente {
    @XmlElement
    private int idPendiente;
    @XmlElement
    private String descripcion;
    @XmlElement
    private Date fecha;
    @XmlElement
    private String hora;
    @XmlElement
    private int estado;
    @XmlElement
    private int idUsuario;

public Pendiente(){ }
    public Pendiente(int idPendiente, String descripcion, Date fecha, String hora, int estado, int idUsuario) {
        this.idPendiente = idPendiente;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.idUsuario = idUsuario;
    }

    public int getIdPendiente() {
        return idPendiente;
    }

    public void setIdPendiente(int idPendiente) {
        this.idPendiente = idPendiente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
