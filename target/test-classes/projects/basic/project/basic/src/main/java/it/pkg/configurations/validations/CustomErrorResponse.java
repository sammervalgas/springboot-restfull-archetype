package it.pkg.configurations.validations;

import lombok.Getter;

@Getter
public class CustomErrorResponse {

    private String field;

    private String message;

    public CustomErrorResponse(String message) {
        this.message = message;
    }

    public CustomErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
