package daw.libreria.web.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class InitSecurity extends AbstractSecurityWebApplicationInitializer {

    public InitSecurity() {
        super(WebMVCSecurity.class);

    }
}
