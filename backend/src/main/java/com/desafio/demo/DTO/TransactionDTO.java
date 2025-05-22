package com.desafio.demo.DTO;


import com.desafio.demo.entities.Transaction;

import java.time.OffsetDateTime;

public class TransactionDTO {

    private Float value;
    private OffsetDateTime dateTime;

    public TransactionDTO(Transaction entity) {
        this.value = entity.getValue();
        this.dateTime = entity.getDateTime();
    }

    public TransactionDTO(Float value, OffsetDateTime dateTime) {
        this.value = value;
        this.dateTime = dateTime;
    }

    public TransactionDTO() {};

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
