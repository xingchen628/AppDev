package ie.spring.lab1;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan({"ie.spring.lab1.repositories", "ie.spring.lab1.services"})
public class AppConfig {
  // Empty, as Spring handles component scanning now
}
