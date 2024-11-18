package com.example.demo.Repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.Models.Cita;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends Neo4jRepository<Cita, Long> {

        // Buscar citas en un rango de fechas
        @Query("MATCH (c:Cita) WHERE c.fecha >= $startDate AND c.fecha <= $endDate RETURN c")
        List<Cita> findByFechaBetween(LocalDate startDate, LocalDate endDate);

        // Buscar citas con una condición específica, prioridad y paciente por ID
        @Query("MATCH (c:Cita) " +
                        "WHERE $condicion IN c.condicionesEspeciales AND c.prioridad = $prioridad AND c.idPaciente = $idPaciente "
                        +
                        "RETURN c")
        List<Cita> findByCondicionesEspecialesInAndPrioridadAndIdPaciente(String condicion, String prioridad,
                        Long idPaciente);

        // Obtener todas las citas con una prioridad específica
        @Query("MATCH (c:Cita) WHERE c.prioridad = $prioridad RETURN c")
        List<Cita> findByPrioridad(String prioridad);

        // Obtener citas de un médico por su ID
        @Query("MATCH (c:Cita)-[:ASIGNADA_A]->(m:Medico) WHERE m.id = $idMedico RETURN c")
        List<Cita> findCitasByIdMedico(Long idMedico);

        // Obtener citas de un paciente por su ID
        @Query("MATCH (c:Cita)-[:PARA]->(p:Paciente) WHERE p.id = $idPaciente RETURN c")
        List<Cita> findCitasByIdPaciente(Long idPaciente);
}
