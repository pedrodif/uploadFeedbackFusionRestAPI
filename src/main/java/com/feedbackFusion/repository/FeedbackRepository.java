package com.feedbackFusion.repository;


import com.feedbackFusion.model.Feedback;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByColaboradorId(Long colaboradorId);
    List<Feedback> findByGestorIdAndColaboradorId(Long gestorId, Long colaboradorId);
}