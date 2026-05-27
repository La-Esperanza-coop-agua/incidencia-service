package cl.esperanza.incidencia.mapper;

import cl.esperanza.incidencia.dto.CreateIncidenciaRequest;
import cl.esperanza.incidencia.model.Incidencia;

public class IncidenciaMapper {
    public static Incidencia toModel(CreateIncidenciaRequest request){
        return new Incidencia(null,
            request.prioridad(), request.coordenadas(), request.estadoReparacion(),
            request.observaciones()
        );
    }
}
