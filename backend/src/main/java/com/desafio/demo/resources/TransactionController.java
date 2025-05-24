package com.desafio.demo.resources;

import com.desafio.demo.DTO.TransactionDTO;
import com.desafio.demo.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/transacao")
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> insert(@RequestBody TransactionDTO dto){

        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getValue()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/transacao")
    public ResponseEntity<Void> deleteAll() {

        service.delete();
        return ResponseEntity.noContent().build();
    }
}
