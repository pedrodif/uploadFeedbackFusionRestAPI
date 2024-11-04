package com.feedbackFusion.repository;

import com.feedbackFusion.model.Conquista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConquistaRepository extends JpaRepository<Conquista, Long> {
    List<Conquista> findByColaboradorId(Long colaboradorId);
    List<Conquista> findByGestorIdAndColaboradorId(Long gestorId, Long colaboradorId);
}