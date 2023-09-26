package com.gariel.crud.controllers;

import com.gariel.crud.dto.TarefaResponseDTO;
import com.gariel.crud.models.Prioridade;
import com.gariel.crud.models.Tarefa;
import com.gariel.crud.repository.PrioridadeRepository;
import com.gariel.crud.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {


    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> getAllTask(){
        List<TarefaResponseDTO> tarefas = tarefaRepository.findAll().stream().map(TarefaResponseDTO::new).toList();

        return ResponseEntity.ok().body(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> getByIdk(@PathVariable Integer id){
        Optional<Tarefa>  tarefa = tarefaRepository.findById(id);
        if(tarefa.isPresent()){
            TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefa.get());
            return ResponseEntity.ok().body(tarefaResponseDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody Tarefa request){
        if(request != null){
            Integer existe =  request.getPrioridade().getId();
            Optional<Prioridade> prioridade = prioridadeRepository.findById(existe);


           if(prioridade.isPresent()){
                Tarefa tarefa = tarefaRepository.save(request);
               TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO(tarefa,prioridade.get());
               return  ResponseEntity.ok().body(tarefaResponseDTO);
           }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> createTask(@PathVariable Integer id, @RequestBody Tarefa request){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        Optional<Prioridade> prioridade = prioridadeRepository.findById(request.getPrioridade().getId());
        if(tarefa.isPresent() && prioridade.isPresent()){
            Tarefa tarefaNova = tarefa.get();
            tarefaNova.setId(tarefa.get().getId());
            tarefaNova.setNome(request.getNome());
            tarefaNova.setDescricao(request.getDescricao());
            tarefaNova.setPrioridade(request.getPrioridade());
            tarefaRepository.save(tarefaNova);

            TarefaResponseDTO responseDTO =  new TarefaResponseDTO(tarefaNova,prioridade.get());

            return ResponseEntity.ok().body(responseDTO);

        }

        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> createTask(@PathVariable Integer id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if(tarefa.isPresent()){
            tarefaRepository.deleteById(id);
            return ResponseEntity.ok().body("Deletado com sucesso");
        }
        return ResponseEntity.badRequest().build();
    }

}
