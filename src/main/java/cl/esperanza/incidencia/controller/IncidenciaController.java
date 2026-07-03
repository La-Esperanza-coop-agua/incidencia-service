package cl.esperanza.incidencia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.esperanza.incidencia.dto.CreateIncidenciaRequest;
import cl.esperanza.incidencia.dto.UpdateEstadoRequest;
import cl.esperanza.incidencia.exception.ResourceNotFoundException;
import cl.esperanza.incidencia.model.Incidencia;
import cl.esperanza.incidencia.service.IncidenciaService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/incidencias")
@Tag(name = "Incidencias", description = "Gestión de reportes de fallas y averías críticas en la red de agua.")
public class IncidenciaController {
    
    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService){
        this.incidenciaService = incidenciaService;
    }

    @Operation(summary = "Obtener incidencia por prioridad", description = "Busca una falla registrada filtrando directamente por su nivel de prioridad.")
    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<Incidencia> getIncidenciaPorPrioridad(@PathVariable int prioridad) {
        Incidencia incidencia = incidenciaService.obtenerPorPrioridad(prioridad);
        if (incidencia == null){
            throw new ResourceNotFoundException("No se encontró ninguna incidencia con la prioridad: " + prioridad);
        }
        return ResponseEntity.ok(incidencia);
    }

    @Operation(summary = "Registrar nueva incidencia", description = "Crea un reporte de falla en terreno y valida mediante WebClient si el socio que reporta realmente existe.")
    @PostMapping
    public ResponseEntity<Incidencia> saveIncidencia(@Valid @RequestBody CreateIncidenciaRequest request) {
        Incidencia nuevaIncidencia = incidenciaService.registrarIncidenciaValidada(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
    }
    
    @Operation(summary = "Actualizar estado de reparación", description = "Modifica el estado de la incidencia (Pasa a true cuando el microservicio de Reparaciones lo notifica).")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Incidencia> cambiarEstadoIncidencia(@PathVariable Integer id, @Valid @RequestBody UpdateEstadoRequest request) {
        Incidencia incidenciaActualizada = incidenciaService.actualizarEstado(id, request.estadoReparacion());
        return ResponseEntity.ok(incidenciaActualizada);
    }
}
