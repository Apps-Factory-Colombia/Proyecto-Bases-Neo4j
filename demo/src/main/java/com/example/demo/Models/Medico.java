package com.example.demo.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Medico") // Indica que esta clase representa un nodo en Neo4j
public class Medico {

    @Id
    @GeneratedValue // Neo4j generará automáticamente un identificador único
    private Long id; // Cambia el tipo de ID a Long o String según tus necesidades

    private String nombre;
    private String cedulaProfesional;
    private String correo;
    private String especialidad;
    private String telefono;

    // Constructor vacío requerido por Spring
    public Medico() {
    }

    // Constructor con parámetros
    public Medico(String nombre, String cedulaProfesional, String correo, String especialidad, String telefono) {
        this.nombre = nombre;
        this.cedulaProfesional = cedulaProfesional;
        this.correo = correo;
        this.especialidad = especialidad;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
