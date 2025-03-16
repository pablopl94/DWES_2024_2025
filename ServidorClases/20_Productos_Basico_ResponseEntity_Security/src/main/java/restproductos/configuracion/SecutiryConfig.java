package restproductos.configuracion;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecutiryConfig {
/*	
	@Bean

	UserDetailsManager usersCustom(DataSource dataSource) {

	JdbcUserDetailsManager users = 
			new JdbcUserDetailsManager(dataSource); 
			users.setUsersByUsernameQuery("select username,password,enabled from Usuarios u where username=?"); 
			users.setAuthoritiesByUsernameQuery("select u.username,p.nombre from Usuario_Perfiles up " +
			 "inner join usuarios u on u.username = up.username " +
					"inner join perfiles p on p.id_perfil = up.id_perfil " +
					"where u.username = ?");

	return users;
*/
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf(csrf -> csrf.disable());
		http.authorizeHttpRequests(authorize -> authorize
			.requestMatchers("/productos/all","/productos/detail/**")
			.permitAll()
	 		.requestMatchers("/productos/cud/**").hasAnyAuthority("ROLE_ADMON")
			.anyRequest().authenticated()
				);
		
		http.sessionManagement((session) -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		        );
		http.httpBasic(Customizer.withDefaults());
		
		
		return http.build();
	}
	
	/*
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
	/**
	 * @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
	 */

}
