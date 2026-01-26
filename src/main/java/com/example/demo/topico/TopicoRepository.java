package com.example.demo.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("SELECT t FROM Topico t WHERE t.estado != 'ELIMINADO' ORDER BY t.fechaDeCreacion LIMIT 10")
    List<Topico> findTop10OrderByFechaDeCreacion();

    @Query("SELECT t FROM Topico t WHERE t.estado != 'ELIMINADO'")
    Page<Topico> findByEstadoNoEliminado(Pageable paginacion);
}
