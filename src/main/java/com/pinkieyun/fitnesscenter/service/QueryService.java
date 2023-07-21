package com.pinkieyun.fitnesscenter.service;

import com.pinkieyun.fitnesscenter.Constants.OPERATOR;
import com.pinkieyun.fitnesscenter.filter.DateFilter;
import com.pinkieyun.fitnesscenter.filter.IntegerFilter;
import com.pinkieyun.fitnesscenter.filter.LocalDateFilter;
import com.pinkieyun.fitnesscenter.filter.StringFilter;
import com.pinkieyun.fitnesscenter.service.specification.Expression;
import com.pinkieyun.fitnesscenter.service.specification.CustomSpecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class QueryService<T> {

    public Specification<T> buildIntegerFilter(String field, IntegerFilter integerFilter) {
        if (integerFilter.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, integerFilter.getEquals()));
        }
        if (integerFilter.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, integerFilter.getNotEquals()));
        }
        if (integerFilter.getLessThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN, integerFilter.getLessThan()));
        }
        if (integerFilter.getLessThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, integerFilter.getLessThanOrEquals()));
        }
        if (integerFilter.getGreaterThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN, integerFilter.getGreaterThan()));
        }
        if (integerFilter.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, integerFilter.getGreaterThanOrEquals()));
        }
        return null;
    }

    public Specification<T> buildStringFilter(String field, StringFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }
        if (value.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }
        if (value.getContains() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.CONTAINS, value.getContains()));
        }
        if (value.getNotContains() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_CONTAINS, value.getNotContains()));
        }
        return null;
    }

    public Specification<T> buildDateFilter(String field, DateFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }
        if (value.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }
        if (value.getLessThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }
        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        if (value.getGreaterThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }
        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }
        return null;
    }

    public Specification<T> buildLocalDateFilter(String field, LocalDateFilter value) {
        if (value.getEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.EQUALS, value.getEquals()));
        }
        if (value.getNotEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.NOT_EQUALS, value.getNotEquals()));
        }
        if (value.getLessThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN, value.getLessThan()));
        }
        if (value.getLessThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.LESS_THAN_OR_EQUALS, value.getLessThanOrEquals()));
        }
        if (value.getGreaterThan() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN, value.getGreaterThan()));
        }
        if (value.getGreaterThanOrEquals() != null) {
            return new CustomSpecification<T>(new Expression(field, OPERATOR.GREATER_THAN_OR_EQUALS, value.getGreaterThanOrEquals()));
        }
        return null;
    }

}