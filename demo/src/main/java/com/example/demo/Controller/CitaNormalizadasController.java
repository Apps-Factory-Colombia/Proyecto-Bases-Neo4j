package com.example.demo.Controller;

import com.example.demo.Models.Cita;
import com.example.demo.Repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citas")
public class CitaNormalizadasController {

    @Autowired
    private CitaRepository citaRepository;

    // Obtener todas las citas
    @GetMapping
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    // Obtener una cita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        Optional<Cita> cita = citaRepository.findById(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva cita
    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        Cita savedCita = citaRepository.save(cita);
        return ResponseEntity.status(201).body(savedCita);
    }

    // Actualizar una cita por ID
    @PutMapping("/{id}")
    public ResponseEntity<Cita> updateCita(@PathVariable Long id, @RequestBody Cita citaDetails) {
        return citaRepository.findById(id)
                .map(existingCita -> {
                    existingCita.setFecha(citaDetails.getFecha());
                    existingCita.setPrioridad(citaDetails.getPrioridad());
                    existingCita.setCondicionesEspeciales(citaDetails.getCondicionesEspeciales());
                    return ResponseEntity.ok(citaRepository.save(existingCita));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar una cita por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        if (citaRepository.existsById(id)) {
            citaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar citas por rango de fechas
    @GetMapping("/porRangoDeFechas")
    public List<Cita> getCitasPorRangoDeFechas(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return citaRepository.findByFechaBetween(startDate, endDate);
    }

    // Buscar citas por prioridad
    @GetMapping("/porPrioridad")
    public List<Cita> getCitasPorPrioridad(@RequestParam String prioridad) {
        return citaRepository.findByPrioridad(prioridad);
    }

    // Buscar citas por médico
    @GetMapping("/porMedico/{idMedico}")
    public List<Cita> getCitasPorMedico(@PathVariable Long idMedico) {
        return citaRepository.findCitasByIdMedico(idMedico);
    }

    // Buscar citas por paciente
    @GetMapping("/porPaciente/{idPaciente}")
    public List<Cita> getCitasPorPaciente(@PathVariable Long idPaciente) {
        return citaRepository.findCitasByIdPaciente(idPaciente);
    }
}
