package com.desafio.demo.services;

import com.desafio.demo.DTO.TransactionDTO;
import com.desafio.demo.entities.Transaction;
import com.desafio.demo.exceptions.UnprocessableEntityException;
import com.desafio.demo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class TransactionService {

    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public TransactionDTO insert(TransactionDTO dto){
        Transaction entity = new Transaction();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        long offsetDateTimeInMili = offsetDateTime.toInstant().toEpochMilli();
        long dtoDateTimeInMili = dto.getDateTime().toInstant().toEpochMilli();

        if (dtoDateTimeInMili > offsetDateTimeInMili) {
            throw new UnprocessableEntityException("Transação não pode acontecer no futuro.");
        }

        if (dto.getValue() < 0) {
            throw new UnprocessableEntityException("Valor não pode ser negativo.");
        }

        entity.setValue(dto.getValue());
        entity.setDateTime(dto.getDateTime());

        entity = repository.save(entity);

        return new TransactionDTO(entity);
    }
}
