package com.jaruiz.examples.observability.api.rest.dto;

import java.io.Serial;
import java.io.Serializable;

public class ResultDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer initialValue;
    private Integer finalValue;

    public Integer getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }

    public Integer getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Integer finalValue) {
        this.finalValue = finalValue;
    }
}
