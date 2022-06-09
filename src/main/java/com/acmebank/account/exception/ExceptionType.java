package com.acmebank.account.exception;

public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    ENTITY_BALANCE_INVALID("balance.invalid"),
    ENTITY_EXCEPTION("exception");

    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}