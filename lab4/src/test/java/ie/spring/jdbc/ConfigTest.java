package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(Config.class)
@ActiveProfiles("dev")  // Activate dev profile
public class ConfigTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Test
    public void testBeansInitialization() {
        assertNotNull(dataSource, "DataSource bean should not be null");
        assertNotNull(jdbcTemplate, "JdbcTemplate bean should not be null");
        assertNotNull(namedParameterJdbcTemplate, "NamedParameterJdbcTemplate bean should not be null");
    }
}
