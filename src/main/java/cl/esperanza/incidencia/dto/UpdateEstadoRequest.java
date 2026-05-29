package cl.esperanza.incidencia.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateEstadoRequest(
    @NotNull(message = "El estado de reparación es obligatorio")
    Boolean estadoReparacion
) {}
