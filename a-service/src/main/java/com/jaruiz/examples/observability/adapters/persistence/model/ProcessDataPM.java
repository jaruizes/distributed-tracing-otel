package com.jaruiz.examples.observability.adapters.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "PROCESS_DATA")
public class ProcessDataPM {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "initValue")
    private Integer initValue;
    @Column(name = "finalValue")
    private Integer finalValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getInitValue() {
        return initValue;
    }

    public void setInitValue(Integer initValue) {
        this.initValue = initValue;
    }

    public Integer getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Integer finalValue) {
        this.finalValue = finalValue;
    }
}
