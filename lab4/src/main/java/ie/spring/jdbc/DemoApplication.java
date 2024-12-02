package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import ie.spring.jdbc.repository.CartoonRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication(scanBasePackages = "ie.spring.jdbc")
public class DemoApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CartoonRepository repository = context.getBean(CartoonRepository.class);

        System.out.println("Number of cartoons: " + repository.count());
        System.out.println("Cartoons: " + repository.findAll());
    }
}
