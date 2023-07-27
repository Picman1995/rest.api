package py.edu.ucsa.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {
		"py.edu.ucsa.rest.api.web.controllers", 
		"py.edu.ucsa.rest.api.core.services", 
		"py.edu.ucsa.rest.api.core.repositories"})
//@Component
//@EnableAutoConfiguration
//@ComponentScan(basePackages = {
//		"py.edu.ucsa.rest.api.controllers", 
//		"py.edu.ucsa.rest.api.services", 
//		"py.edu.ucsa.rest.api.repositories"})
@Import(JpaConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
