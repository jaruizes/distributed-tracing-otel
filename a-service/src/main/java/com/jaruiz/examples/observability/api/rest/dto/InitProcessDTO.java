package com.jaruiz.examples.observability.api.rest.dto;

import java.io.Serial;
import java.io.Serializable;

public class InitProcessDTO implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private Integer initialValue;

    public Integer getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Integer initialValue) {
        this.initialValue = initialValue;
    }
}
