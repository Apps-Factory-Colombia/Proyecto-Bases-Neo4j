package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.Medico;
import com.example.demo.Repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    // Obtener todos los médicos
    @GetMapping
    public List<Medico> getAllMedicos() {
        return medicoRepository.findAll();
    }

    // Obtener un médico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Medico> getMedicoById(@PathVariable Long id) { // Cambié el tipo de ID a Long para Neo4j
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo médico
    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody Medico medico) {
        Medico savedMedico = medicoRepository.save(medico);
        return ResponseEntity.status(201).body(savedMedico);
    }

    // Actualizar un médico por ID
    @PutMapping("/{id}")
    public ResponseEntity<Medico> updateMedico(@PathVariable Long id, @RequestBody Medico medicoDetails) {
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isPresent()) {
            Medico existingMedico = medico.get();
            existingMedico.setNombre(medicoDetails.getNombre());
            existingMedico.setCedulaProfesional(medicoDetails.getCedulaProfesional());
            existingMedico.setCorreo(medicoDetails.getCorreo());
            existingMedico.setEspecialidad(medicoDetails.getEspecialidad());
            existingMedico.setTelefono(medicoDetails.getTelefono());
            return ResponseEntity.ok(medicoRepository.save(existingMedico));
        }
        return ResponseEntity.notFound().build();
    }

    // Eliminar un médico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        if (medicoRepository.existsById(id)) {
            medicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
