package pruebawarbbdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PruebawarbbddApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PruebawarbbddApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PruebawarbbddApplication.class);
    }
}
