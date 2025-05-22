package com.desafio.demo.resources;

import com.desafio.demo.DTO.TransactionDTO;
import com.desafio.demo.entities.Transaction;
import com.desafio.demo.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/transacao")
public class TransactionController {

    private TransactionService service;

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
}
