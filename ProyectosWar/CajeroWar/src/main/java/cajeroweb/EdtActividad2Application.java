package cajeroweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class EdtActividad2Application  extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) 
	{ return builder.sources(EdtActividad2Application.class);
	}
	

	public static void main(String[] args) {
		SpringApplication.run(EdtActividad2Application.class, args);
	}

}
