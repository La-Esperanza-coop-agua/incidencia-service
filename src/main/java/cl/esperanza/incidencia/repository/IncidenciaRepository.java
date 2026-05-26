package cl.esperanza.incidencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.esperanza.incidencia.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer>{
    Incidencia findByPrioridad(int prioridad);
}
