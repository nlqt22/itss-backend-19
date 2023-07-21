package com.pinkieyun.fitnesscenter.filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntegerFilter extends Filter<Integer> {

    private Integer greaterThan;

    private Integer greaterThanOrEquals;

    private Integer lessThan;

    private Integer lessThanOrEquals;
    
}