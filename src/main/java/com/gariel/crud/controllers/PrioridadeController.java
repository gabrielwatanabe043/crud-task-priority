package com.gariel.crud.controllers;

import com.gariel.crud.dto.PrioridadeRequestDTO;
import com.gariel.crud.dto.PrioridadeResponseDTO;
import com.gariel.crud.models.Prioridade;

import com.gariel.crud.repository.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prioridade")
public class PrioridadeController {

    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @GetMapping
    public ResponseEntity<List<PrioridadeResponseDTO>> getAll(){

        List<PrioridadeResponseDTO> prioridades = prioridadeRepository.findAll().stream().map(PrioridadeResponseDTO::new).toList();
        return ResponseEntity.ok().body(prioridades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrioridadeResponseDTO> getById(@PathVariable Integer id){
        Optional<Prioridade> prioridade = prioridadeRepository.findById(id);

       if (prioridade.isPresent()){
           Prioridade response = prioridade.get();

           PrioridadeResponseDTO prioridadeResponseDTO = new PrioridadeResponseDTO(response);
            return (ResponseEntity.ok(prioridadeResponseDTO));
        }
       return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<PrioridadeResponseDTO> createPriority(@RequestBody @Validated PrioridadeRequestDTO prioridadeDTO){
        if(prioridadeDTO != null){
            Prioridade prioridade = new Prioridade(prioridadeDTO);
            prioridadeRepository.save(prioridade);
            PrioridadeResponseDTO response = new PrioridadeResponseDTO(prioridade);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<PrioridadeResponseDTO> updatePriority(@PathVariable Integer id, @RequestBody PrioridadeRequestDTO request){
        Optional<Prioridade> prioridade = prioridadeRepository.findById(id);
        if(prioridade.isPresent()){
            prioridade.get().setNome(request.nome());
            prioridadeRepository.save(prioridade.get());
            PrioridadeResponseDTO prioridadeResponseDTO = new PrioridadeResponseDTO(prioridade.get());
            return ResponseEntity.ok(prioridadeResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePriority(@PathVariable Integer id){
        Optional<Prioridade> prioridade = prioridadeRepository.findById(id);
        if(prioridade.isPresent()){

            prioridadeRepository.deleteById(id);

            return ResponseEntity.ok().body("Deletedo com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
