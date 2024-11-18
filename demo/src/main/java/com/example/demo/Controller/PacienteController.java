package com.example.demo.Controller;

import com.example.demo.Models.Paciente;
import com.example.demo.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    // Obtener todos los pacientes
    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    // Obtener un paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo paciente
    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente savedPaciente = pacienteRepository.save(paciente);
        return ResponseEntity.status(201).body(savedPaciente);
    }

    // Actualizar un paciente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails) {
        return pacienteRepository.findById(id)
                .map(existingPaciente -> {
                    existingPaciente.setNombre(pacienteDetails.getNombre());
                    existingPaciente.setEdad(pacienteDetails.getEdad());
                    existingPaciente.setTelefono(pacienteDetails.getTelefono());
                    existingPaciente.setCorreo(pacienteDetails.getCorreo());
                    existingPaciente.setEps(pacienteDetails.getEps());
                    return ResponseEntity.ok(pacienteRepository.save(existingPaciente));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un paciente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        if (pacienteRepository.existsById(id)) {
            pacienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Obtener pacientes por rango de edad y EPS
    @GetMapping("/porEdadYEps")
    public List<Paciente> getPacientesPorEdadYEps(
            @RequestParam int startAge,
            @RequestParam int endAge,
            @RequestParam String eps) {
        return pacienteRepository.findByEdadBetweenAndEps(startAge, endAge, eps);
    }

    // Obtener pacientes mayores de 65 años por EPS
    @GetMapping("/mayoresDe65PorEps")
    public List<Paciente> getPacientesMayoresDe65PorEps(@RequestParam String eps) {
        return pacienteRepository.findByEpsAndEdadGreaterThan(eps, 65);
    }
}
