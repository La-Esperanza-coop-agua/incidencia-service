package cl.esperanza.incidencia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import cl.esperanza.incidencia.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Integer>{
    List<Incidencia> findByPrioridad(int prioridad);
}
