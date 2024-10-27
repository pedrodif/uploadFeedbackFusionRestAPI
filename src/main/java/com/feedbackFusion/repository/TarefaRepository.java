package com.feedbackFusion.repository;

import com.feedbackFusion.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByColaboradorId(Long colaboradorId);
    List<Tarefa> findByGestorIdAndColaboradorId(Long gestorId, Long colaboradorId);
}