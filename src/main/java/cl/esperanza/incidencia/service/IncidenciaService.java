package cl.esperanza.incidencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Incidencia actualizarIncidencia(Integer id, Incidencia inciActualizada){
        inciActualizada.setIdIncidencia(id);
        return inciRepo.save(inciActualizada);
    }
}
