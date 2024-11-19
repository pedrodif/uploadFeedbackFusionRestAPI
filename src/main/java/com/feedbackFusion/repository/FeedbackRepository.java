package com.feedbackFusion.repository;


import com.feedbackFusion.model.Feedback;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f JOIN f.gestor g WHERE f.colaboradorId = :colaboradorId")
    List<Feedback> findByColaboradorId(@Param("colaboradorId") Long colaboradorId);

    @Query("SELECT f FROM Feedback f JOIN f.gestor g WHERE g.id = :gestorId AND f.colaboradorId = :colaboradorId")
    List<Feedback> findByGestorIdAndColaboradorId(
            @Param("gestorId") Long gestorId,
            @Param("colaboradorId") Long colaboradorId
    );
}