package com.feedbackFusion.service;

import com.feedbackFusion.dto.SolicitacaoAjudaDTO;
import com.feedbackFusion.model.SolicitacaoAjuda;
import com.feedbackFusion.model.Tarefa;
import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.repository.SolicitacaoAjudaRepository;
import com.feedbackFusion.repository.TarefaRepository;
import com.feedbackFusion.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitacaoAjudaService {

    @Autowired
    private SolicitacaoAjudaRepository repository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private void configurarSolicitacaoAjuda(SolicitacaoAjuda solicitacaoAjuda, SolicitacaoAjudaDTO solicitacaoAjudaDTO) {
        solicitacaoAjuda.setMonitorId(solicitacaoAjudaDTO.getMonitorId());
        solicitacaoAjuda.setDataSolicitacao(solicitacaoAjudaDTO.getDataSolicitacao());
    }

    public SolicitacaoAjudaDTO create(SolicitacaoAjudaDTO solicitacaoAjudaDTO) {
        SolicitacaoAjuda solicitacaoAjuda = new SolicitacaoAjuda();
        configurarSolicitacaoAjuda(solicitacaoAjuda, solicitacaoAjudaDTO);

        Tarefa tarefa = tarefaRepository.findById(solicitacaoAjudaDTO.getTarefaId())
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada com ID: " + solicitacaoAjudaDTO.getTarefaId()));

        solicitacaoAjuda.setTarefa(tarefa);

        Usuario usuario = usuarioRepository.findById(solicitacaoAjudaDTO.getColaboradorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + solicitacaoAjudaDTO.getColaboradorId()));

        solicitacaoAjuda.setColaborador(usuario);

        repository.save(solicitacaoAjuda);
        solicitacaoAjudaDTO.setId(solicitacaoAjuda.getId());
        solicitacaoAjudaDTO.setColaborador(solicitacaoAjuda.getColaborador());
        solicitacaoAjudaDTO.setTarefa(solicitacaoAjuda.getTarefa());
        return solicitacaoAjudaDTO;
    }

    private SolicitacaoAjudaDTO converterSolicitacaoAjuda(SolicitacaoAjuda solicitacaoAjuda) {
        SolicitacaoAjudaDTO solicitacaoAjudaDTO = new SolicitacaoAjudaDTO();
        solicitacaoAjudaDTO.setId(solicitacaoAjuda.getId());
        solicitacaoAjudaDTO.setMonitorId(solicitacaoAjuda.getMonitorId());
        solicitacaoAjudaDTO.setDataSolicitacao(solicitacaoAjuda.getDataSolicitacao());
        solicitacaoAjudaDTO.setColaborador(solicitacaoAjuda.getColaborador());
        solicitacaoAjudaDTO.setColaboradorId(solicitacaoAjuda.getColaborador().getId());
        solicitacaoAjudaDTO.setTarefaId(solicitacaoAjuda.getTarefa().getId());
        solicitacaoAjudaDTO.setTarefa(solicitacaoAjuda.getTarefa());
        return solicitacaoAjudaDTO;
    }

    public List<SolicitacaoAjudaDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::converterSolicitacaoAjuda)
                .collect(Collectors.toList());
    }

    public List<SolicitacaoAjudaDTO> getByMonitorId(Long monitorId) {
        List<SolicitacaoAjuda> solicitacoes = repository.findByMonitorId(monitorId);

        if (solicitacoes.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma solicitação de ajuda encontrada para o monitor ID: " + monitorId);
        }

        return solicitacoes.stream()
                .map(this::converterSolicitacaoAjuda)
                .collect(Collectors.toList());
    }

    public SolicitacaoAjudaDTO getById(Long solicitacaoAjudaId){
        SolicitacaoAjuda solicitacaoRecuperada = repository.findById(solicitacaoAjudaId)
                .orElseThrow(()-> new EntityNotFoundException("Nenhuma solicitação encontrada com o ID"+ solicitacaoAjudaId));
        return converterSolicitacaoAjuda(solicitacaoRecuperada);
    }

    public String delete(Long solicitacaoAjudaId) {
        repository.deleteById(solicitacaoAjudaId);
        return "Solicitação de ajuda deletada com sucesso!";
    }
}
