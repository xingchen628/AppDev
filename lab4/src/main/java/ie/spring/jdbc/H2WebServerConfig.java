package ie.spring.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.h2.tools.Server;

import java.sql.SQLException;

@Configuration
public class H2WebServerConfig {

  @Profile("dev")
  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server h2WebServer() throws SQLException {
    return Server.createWebServer("-web", "-webPort", "8082");
  }
}
