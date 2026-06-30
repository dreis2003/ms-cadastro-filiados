package br.com.ikonbrasil.cadastrofiliados.seguranca.infraestrutura.autenticacao;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public final class AutenticacaoAtual {

    private AutenticacaoAtual() {
    }

    public static UUID filialId(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            return null;
        }
        String filialId = jwt.getClaimAsString("filialId");
        if (filialId == null || filialId.isBlank()) {
            return null;
        }
        return UUID.fromString(filialId);
    }
}
