package com.gariel.crud.dto;


import com.gariel.crud.models.Prioridade;


public record PrioridadeRequestDTO(String nome) {

    public PrioridadeRequestDTO(Prioridade prioridade){
        this(prioridade.getNome());
    }
}
