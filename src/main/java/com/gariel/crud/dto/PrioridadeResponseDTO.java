package com.gariel.crud.dto;

import com.gariel.crud.models.Prioridade;
import com.gariel.crud.models.Tarefa;

import java.util.List;
import java.util.Optional;

public record PrioridadeResponseDTO(Integer id, String nome, List<Tarefa> tarefas) {

    public PrioridadeResponseDTO(Prioridade prioridade){
        this(prioridade.getId(), prioridade.getNome(), prioridade.getTarefas());

    }


}
