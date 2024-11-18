package com.example.demo.Models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Paciente") // Indica que esta clase representa un nodo en Neo4j
public class Paciente {

    @Id
    @GeneratedValue // Neo4j generará automáticamente un identificador único
    private Long id; // Cambiado a Long para usar IDs autogenerados por Neo4j

    private String nombre;
    private int edad;
    private String telefono;
    private String correo;
    private String eps;

    // Constructor vacío requerido por Spring
    public Paciente() {
    }

    // Constructor con parámetros
    public Paciente(String nombre, int edad, String telefono, String correo, String eps) {
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.eps = eps;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }
}
