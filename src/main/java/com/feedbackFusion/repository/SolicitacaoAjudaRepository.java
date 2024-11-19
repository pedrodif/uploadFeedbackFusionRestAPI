package com.feedbackFusion.repository;

import com.feedbackFusion.model.SolicitacaoAjuda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolicitacaoAjudaRepository extends JpaRepository<SolicitacaoAjuda, Long> {
    @Query("SELECT sa FROM SolicitacaoAjuda sa " +
            "JOIN FETCH sa.tarefa " +
            "JOIN FETCH sa.colaborador " +
            "WHERE sa.monitorId = :monitorId")
    List<SolicitacaoAjuda> findByMonitorId(@Param("monitorId") Long monitorId);
}