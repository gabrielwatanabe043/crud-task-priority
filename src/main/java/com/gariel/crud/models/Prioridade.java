package com.gariel.crud.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gariel.crud.dto.PrioridadeRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_prioridade")
public class Prioridade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String nome;
    @JsonIgnore
    @OneToMany(mappedBy = "prioridade")
    @Column(nullable = true)
    private List<Tarefa> tarefas = new ArrayList<>();

    public Prioridade(PrioridadeRequestDTO prioridadeDTO) {
        this.nome = prioridadeDTO.nome();

    }

    public void adicionaTarefa(Tarefa tarefa){
        tarefas.add(tarefa);
    }
}
