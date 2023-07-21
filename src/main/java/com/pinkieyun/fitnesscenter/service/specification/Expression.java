package com.pinkieyun.fitnesscenter.service.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expression {

    private String field;

    private String operator;

    private Object value;

}