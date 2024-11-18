package com.example.demo.Repository;

import com.example.demo.Models.Paciente;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends Neo4jRepository<Paciente, Long> {

    // Buscar pacientes por rango de edad y EPS
    List<Paciente> findByEdadBetweenAndEps(int startAge, int endAge, String eps);

    // Buscar pacientes mayores de 65 años por EPS
    List<Paciente> findByEpsAndEdadGreaterThan(String eps, int edad);
}
