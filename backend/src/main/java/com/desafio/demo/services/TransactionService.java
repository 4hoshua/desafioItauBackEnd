package com.desafio.demo.services;

import com.desafio.demo.DTO.TransactionDTO;
import com.desafio.demo.entities.Transaction;
import com.desafio.demo.exceptions.UnprocessableEntityException;
import com.desafio.demo.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TransactionService {

    private final TransactionRepository repository;

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

    @Transactional
    public void delete() {
        repository.deleteAll();
    }

    @Transactional
    public String getAllBySeconds(int time) {

        DoubleSummaryStatistics doubleSummaryStatistics = new DoubleSummaryStatistics();

        List<Double> statistics = repository.getLatestRecords(time);

        long count;

        if (statistics.isEmpty()) {

            doubleSummaryStatistics.accept(0);
            count = doubleSummaryStatistics.getCount() - 1;
        }

        else {

            for (Double statistic : statistics) {
                doubleSummaryStatistics.accept(statistic);
            }

            count = doubleSummaryStatistics.getCount();
        }


        return "{\"count\":" + count + ","
                + "\"sum\":" + doubleSummaryStatistics.getSum() + ","
                + "\"avg\":" + doubleSummaryStatistics.getAverage() + ","
                + "\"min\":" + doubleSummaryStatistics.getMin() + ","
                + "\"max\":" + doubleSummaryStatistics.getMax() + "}";
    }
}
