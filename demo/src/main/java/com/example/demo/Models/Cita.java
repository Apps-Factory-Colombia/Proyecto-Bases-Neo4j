package com.example.demo.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Date;
import java.util.List;

@Node("Cita") // Indica que esta clase representa un nodo en Neo4j
public class Cita {

    @Id
    @GeneratedValue // Generación automática de ID por Neo4j
    private Long id;

    private Date fecha;
    private Long idMedico; // Referencia al nodo Medico (ID)
    private Long idPaciente; // Referencia al nodo Paciente (ID)
    private String prioridad;
    private List<String> condicionesEspeciales;

    // Constructor vacío requerido por Spring
    public Cita() {
    }

    // Constructor con parámetros
    public Cita(Date fecha, Long idMedico, Long idPaciente, String prioridad, List<String> condicionesEspeciales) {
        this.fecha = fecha;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.prioridad = prioridad;
        this.condicionesEspeciales = condicionesEspeciales;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Long idMedico) {
        this.idMedico = idMedico;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public List<String> getCondicionesEspeciales() {
        return condicionesEspeciales;
    }

    public void setCondicionesEspeciales(List<String> condicionesEspeciales) {
        this.condicionesEspeciales = condicionesEspeciales;
    }
}
