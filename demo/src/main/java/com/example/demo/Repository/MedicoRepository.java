package com.example.demo.Repository;

import com.example.demo.Models.Medico;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends Neo4jRepository<Medico, Long> {
    // Puedes agregar métodos de búsqueda personalizada si es necesario
}
