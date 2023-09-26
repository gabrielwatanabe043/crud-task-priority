package com.gariel.crud.dto;

import com.gariel.crud.models.Prioridade;
import com.gariel.crud.models.Tarefa;

import java.util.Optional;

public record TarefaResponseDTO(Integer id, String name, String descricao, Integer id_prioridade, String nome_prioridade) {


    public TarefaResponseDTO(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(), tarefa.getPrioridade().getId(), tarefa.getPrioridade().getNome());
    }

    public TarefaResponseDTO(Tarefa tarefa, Prioridade prioridade) {
        this(tarefa.getId(), tarefa.getNome(), tarefa.getDescricao(),prioridade.getId(), prioridade.getNome());
    }


}
