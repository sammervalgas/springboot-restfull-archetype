#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.configurations.validations;

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
