package cl.esperanza.incidencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.esperanza.incidencia.exception.ResourceNotFoundException;
import cl.esperanza.incidencia.model.Incidencia;
import cl.esperanza.incidencia.repository.IncidenciaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository inciRepo;

    public Incidencia obtenerPorPrioridad(int prioridad){
        return inciRepo.findByPrioridad(prioridad);
    }

    public Incidencia guardarIncidencia(Incidencia inci){
        return inciRepo.save(inci);
    }

    public Incidencia actualizarEstado(Integer id, boolean nuevoEstado){
        Incidencia incidencia = inciRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No se encontró la incidencia con ID: "+ id));

        incidencia.setEstadoReparacion(nuevoEstado);
        return inciRepo.save(incidencia);
    }
}
