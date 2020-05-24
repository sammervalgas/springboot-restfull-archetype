package it.pkg.model.login;

import lombok.Data;
import lombok.NoArgsConstructor;

public @NoArgsConstructor @Data class TokenDTO {

    private String token;
    private String type = AuthMethod.BEARER.getName();

    public TokenDTO(String token, AuthMethod method) {
        this.token = token;
        this.type = method.name();
    }

    public enum AuthMethod {
        BEARER("Bearer"), DIGEST("Digest");

        private String name;

        AuthMethod(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
