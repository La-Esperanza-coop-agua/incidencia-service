package cl.esperanza.incidencia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.esperanza.incidencia.dto.CreateIncidenciaRequest;
import cl.esperanza.incidencia.exception.ResourceNotFoundException;
import cl.esperanza.incidencia.mapper.IncidenciaMapper;
import cl.esperanza.incidencia.model.Incidencia;
import cl.esperanza.incidencia.repository.IncidenciaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository inciRepo;
    private final WebClient sociosWebClient;

    @Autowired
    public IncidenciaService(IncidenciaRepository inciRepo, 
                             @org.springframework.beans.factory.annotation.Qualifier("sociosWebClient") WebClient sociosWebClient) {
        this.inciRepo = inciRepo;
        this.sociosWebClient = sociosWebClient;
    }
    

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

    public Incidencia registrarIncidenciaValidada(CreateIncidenciaRequest request) {
        // 1. Validar con el Guardián
        Boolean existeSocio = sociosWebClient.get()
                .uri("/api/v1/socios/existe/{run}", request.runSocio())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        System.out.println("DEBUG: ¿El socio " + request.runSocio() + " existe? -> " + existeSocio);

        if (Boolean.FALSE.equals(existeSocio) || existeSocio == null) {
            throw new ResourceNotFoundException("El socio con RUN " + request.runSocio() + " no existe.");
        }
        
        return inciRepo.save(IncidenciaMapper.toModel(request));
    }

}