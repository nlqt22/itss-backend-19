package com.pinkieyun.fitnesscenter;

public class Constants {
    
    public interface AUTHENTICATION {
        String AUTHORIZATION_TOKEN = "Authorization";
    }

    public interface PERSON {
        String ID = "id";
        String FULLNAME = "fullName";
        String DOB = "dob";
        String PHONE = "phone";
        String IDENTITYCARD = "identityCard";
    }

    public interface OPERATOR {
        String EQUALS = "equals";
        String NOT_EQUALS = "notEquals";
        String CONTAINS = "contains";
        String NOT_CONTAINS = "notContains";
        String GREATER_THAN = "greaterThan";
        String LESS_THAN = "lessThan";
        String GREATER_THAN_OR_EQUALS = "greaterThanOrEquals";
        String LESS_THAN_OR_EQUALS = "lessThanOrEquals";
    }

    public interface ROLE {
        String ADMIN = "ADMIN";
        String PT = "PT_STAFF";
        String SALE = "SALE_STAFF";
        String MEMBER = "MEMBER";
    }

    public interface API {
        String BASE_API = "/api/v1";
        String ACCOUNTS = "/accounts";
        String MEMBERS = "/members";
        String STAFFS = "/staffs";
        String TRACKS = "/tracks";
        String EQUIPMENTS = "/equipments";
        String FEEDBACK = "/feedbacks";
        String PAYMENTS = "/payments";
        String PACKS = "/packs";
        String ORGANIZATIONS = "/organizations";
    }
}