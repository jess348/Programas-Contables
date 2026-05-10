package FormularioDGI;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Contribuyente {
    private String nombre;
    private String apellido;
    private String cedula;
    private String NumInss;
    private DateTimeFormatter fechaNacimiento = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String sexo;
    
    private String nomComercial;
    private String identReguladora;
    private Domicilio domicilio;
    private List<Familiar> familiares;
    private InfoFinanciera infoFinanciera;
    private RepresentanteLegal representanteLegal;

    // Constructor
    public Contribuyente() {
        this.familiares = new ArrayList<>();
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNumInss() { return NumInss; }
    public void setNumInss(String numInss) { this.NumInss = numInss; }

    public DateTimeFormatter getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(DateTimeFormatter fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNomComercial() { return nomComercial; }
    public void setNomComercial(String nomComercial) { this.nomComercial = nomComercial; }

    public String getIdentReguladora() { return identReguladora; }
    public void setIdentReguladora(String identReguladora) { this.identReguladora = identReguladora; }

    public Domicilio getDomicilio() { return domicilio; }
    public void setDomicilio(Domicilio domicilio) { this.domicilio = domicilio; }

    public List<Familiar> getFamiliares() { return familiares; }
    public void setFamiliares(List<Familiar> familiares) { this.familiares = familiares; }

    public InfoFinanciera getInfoFinanciera() { return infoFinanciera; }
    public void setInfoFinanciera(InfoFinanciera infoFinanciera) { this.infoFinanciera = infoFinanciera; }

    public RepresentanteLegal getRepresentanteLegal() { return representanteLegal; }
    public void setRepresentanteLegal(RepresentanteLegal representanteLegal) { this.representanteLegal = representanteLegal; }
}
