package com.feedbackFusion.repository;

import com.feedbackFusion.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    @Query("SELECT e FROM Equipe e LEFT JOIN FETCH e.gestor g LEFT JOIN FETCH e.colaboradores c WHERE e.gestor.id = :gestorId")
    List<Equipe> findByGestorId(@Param("gestorId") Long gestorId);
}