package cl.esperanza.incidencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateIncidenciaRequest(
    @NotBlank(message = "El RUN del socio es obligatorio") String runSocio,
    @PositiveOrZero(message = "El nivel de prioridad no puede ser negativo") int prioridad,
    @NotNull(message = "Las coordenadas no deben estar vacías") double coordenadas,
    @NotNull(message = "El estado de la reparación no debe estar vacío") boolean estadoReparacion,
    @NotBlank(message = "Las observaciones no deben estar vacías") String observaciones) {
}
