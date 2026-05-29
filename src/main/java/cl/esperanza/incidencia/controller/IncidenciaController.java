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
import cl.esperanza.incidencia.mapper.IncidenciaMapper;
import cl.esperanza.incidencia.model.Incidencia;
import cl.esperanza.incidencia.service.IncidenciaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/incidencias")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;

    // Constructor de Inyeccion 
    public IncidenciaController(IncidenciaService inciServ){
        this.incidenciaService = inciServ;
    }

    // EndPoint 1 findByPrioridad
    @GetMapping("/prioridad/{prioridad}")
    public ResponseEntity<Incidencia> getIncidenciaPorPrioridad(@PathVariable int prioridad) {
        Incidencia incidencia = incidenciaService.obtenerPorPrioridad(prioridad);
        if (incidencia == null){
            throw new ResourceNotFoundException("No se encontró ninguna incidencia con la prioridad: "+ prioridad);
        }
        return ResponseEntity.ok(incidencia);
    }

    // EndPoint 2 guardarIncidencia
    @PostMapping
    public ResponseEntity<Incidencia> saveIncidencia(@Valid @RequestBody CreateIncidenciaRequest request) {
        Incidencia nuevaIncidencia = incidenciaService.guardarIncidencia(IncidenciaMapper.toModel(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
    }
    
    // EndPoint 3 actualizar el estado de la incidencia
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Incidencia> cambiarEstadoIncidencia(@PathVariable Integer id, @Valid @RequestBody UpdateEstadoRequest request) {
        Incidencia incidenciaActualizada = incidenciaService.actualizarEstado(id, request.estadoReparacion());
        return ResponseEntity.ok(incidenciaActualizada);
    }
}
