package cl.esperanza.incidencia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.esperanza.incidencia.service.IncidenciaService;
import cl.esperanza.incidencia.exception.ResourceNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import cl.esperanza.incidencia.model.Incidencia;


@RestController
@RequestMapping("/api/v1/incidencias")
public class IncidenciaController {
    private final IncidenciaService incidenciaService;

    // Constructor de Inyeccion 
    public IncidenciaController(IncidenciaService incidenciaService){
        this.incidenciaSevice = incidenciaService;
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

    
    
}
