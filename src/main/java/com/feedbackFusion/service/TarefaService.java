package com.feedbackFusion.service;

import com.feedbackFusion.dto.TarefaDTO;
import com.feedbackFusion.model.Tarefa;
import com.feedbackFusion.repository.TarefaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;

    private void configurarTarefa(Tarefa tarefa, TarefaDTO tarefaDTO) {
        tarefa.setTitulo(tarefaDTO.getTitulo());
        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefa.setDataPrazo(tarefaDTO.getDataPrazo());
        tarefa.setDataConclusao(tarefaDTO.getDataConclusao());
        tarefa.setDataEdicao(tarefaDTO.getDataEdicao());
        tarefa.setPontuacao(tarefaDTO.getPontuacao());
        tarefa.setDocumentos(tarefaDTO.getDocumentos());
        tarefa.setComentariosGestor(tarefaDTO.getComentariosGestor());
        tarefa.setGestorId(tarefaDTO.getGestorId());
        tarefa.setColaboradorId(tarefaDTO.getColaboradorId());
        tarefa.setStatusConclusao(tarefaDTO.isStatusConclusao());
    }

    public TarefaDTO create(TarefaDTO tarefaDTO) {
        Tarefa tarefa = new Tarefa();
        configurarTarefa(tarefa, tarefaDTO);
        tarefa.setDataCriacao(tarefaDTO.getDataCriacao());
        repository.save(tarefa);

        tarefaDTO.setId(tarefa.getId());
        return tarefaDTO;
    }

    public TarefaDTO update(Long tarefaId, TarefaDTO tarefaDTO) {
        Tarefa tarefa = repository.findById(tarefaId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma tarefa encontrada com o ID: " + tarefaId));

        configurarTarefa(tarefa, tarefaDTO);
        tarefa.setDataCriacao(tarefa.getDataCriacao());
        repository.save(tarefa);

        tarefaDTO.setId(tarefa.getId());
        tarefaDTO.setDataCriacao(tarefa.getDataCriacao());

        return tarefaDTO;
    }

    private TarefaDTO converterTarefa(Tarefa tarefa) {
        TarefaDTO tarefaConvertida = new TarefaDTO();
        tarefaConvertida.setId(tarefa.getId());
        tarefaConvertida.setTitulo(tarefa.getTitulo());
        tarefaConvertida.setDescricao(tarefa.getDescricao());
        tarefaConvertida.setDataCriacao(tarefa.getDataCriacao());
        tarefaConvertida.setDataPrazo(tarefa.getDataPrazo());
        tarefaConvertida.setDataConclusao(tarefa.getDataConclusao());
        tarefaConvertida.setDataEdicao(tarefa.getDataEdicao());
        tarefaConvertida.setPontuacao(tarefa.getPontuacao());
        tarefaConvertida.setDocumentos(tarefa.getDocumentos());
        tarefaConvertida.setStatusConclusao(tarefa.isStatusConclusao());
        tarefaConvertida.setComentariosGestor(tarefa.getComentariosGestor());
        tarefaConvertida.setGestorId(tarefa.getGestorId());
        tarefaConvertida.setColaboradorId(tarefa.getColaboradorId());
        return tarefaConvertida;
    }

    public List<TarefaDTO> getAll(){
        return repository.findAll().stream().map(this::converterTarefa).collect(Collectors.toList());
    }

    public List<TarefaDTO> getByColaboradorId(Long colaboradorId) {
        List<Tarefa> tarefas = repository.findByColaboradorId(colaboradorId);

        if (tarefas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma tarefa encontrada para o colaborador ID: " + colaboradorId);
        }
        return tarefas.stream().map(this::converterTarefa).collect(Collectors.toList());
    }

    public List<TarefaDTO> getByGestorIdAndColaboradorId(Long gestorId, Long colaboradorId) {
        List<Tarefa> tarefas = repository.findByGestorIdAndColaboradorId(gestorId, colaboradorId);

        if (tarefas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma tarefa encontrada para o gestor ID: " + gestorId + " e colaborador ID: " + colaboradorId);
        }
        return tarefas.stream().map(this::converterTarefa).collect(Collectors.toList());
    }

    public TarefaDTO getById(Long tarefaId){
        Tarefa tarefaRecuperada = repository.findById(tarefaId)
                .orElseThrow(()-> new EntityNotFoundException("Nenhuma tarefa encontrada com o ID"+ tarefaId));
        return converterTarefa(tarefaRecuperada);
    }

    public String delete(Long tarefaId){
        repository.deleteById(tarefaId);
        return "Tarefa deletada com sucesso!";
    }
}
