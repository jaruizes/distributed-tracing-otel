package com.jaruiz.examples.observability.business.model;

public class ProcessData {
    private Long id;
    private Integer initValue;
    private Integer finalValue;

    public ProcessData(Long id, Integer initValue, Integer finalValue) {
        this.id = id;
        this.initValue = initValue;
        this.finalValue = finalValue;
    }

    public Long getId() {
        return id;
    }

    public Integer getInitValue() {
        return initValue;
    }

    public Integer getFinalValue() {
        return finalValue;
    }
}
