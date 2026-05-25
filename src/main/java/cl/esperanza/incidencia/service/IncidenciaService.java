package cl.esperanza.incidencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.esperanza.incidencia.repository.IncidenciaRepository;
import jakarta.transaction.Transactional;

import cl.esperanza.incidencia.model.Incidencia;

import java.util.List;

@Service
@Transactional
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository inciRepo;

    public List<Incidencia> obtenerPorPrioridad(int prioridad){
        return inciRepo.findByPrioridad(prioridad);
    }

    public Incidencia guardarIncidencia(Incidencia inci){
        return inciRepo.save(inci);
    }
}
