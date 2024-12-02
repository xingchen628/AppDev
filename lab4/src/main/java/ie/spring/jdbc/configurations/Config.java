package ie.spring.jdbc.configurations;

import ie.spring.jdbc.repository.CartoonRepository;
import ie.spring.jdbc.repository.CartoonRepositoryImpl;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackages = "ie.spring.jdbc.services")
@PropertySource("classpath:application.properties")
public class Config {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .addScript("classpath:schema.sql")
        .addScript("classpath:data.sql")
        .build();
  }
  @Bean
  public CartoonRepository cartoonRepository(JdbcTemplate jdbcTemplate) {
    return new CartoonRepositoryImpl(jdbcTemplate);
  }
  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  @Profile("dev")
  @Bean(initMethod = "start")
  public Server h2WebServer() throws SQLException {
    return Server.createWebServer("-web", "-webPort", "8082");
  }
}
