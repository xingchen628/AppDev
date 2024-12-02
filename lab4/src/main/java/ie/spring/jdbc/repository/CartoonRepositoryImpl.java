package ie.spring.jdbc.repository;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.services.DataConflictException;
import ie.spring.jdbc.services.NotFoundException;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class CartoonRepositoryImpl implements CartoonRepository {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public int count() {
    String sql = "SELECT COUNT(*) FROM cartoons";
    return jdbcTemplate.queryForObject(sql, Integer.class); // Executes count query
  }

  @Override
  public List<Cartoon> findAll() {
    String sql = "SELECT * FROM cartoons";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cartoon.class));
  }
  @Override
  public Optional<Cartoon> findByCartoonId(int cartoonId) {
    String sql = "SELECT * FROM cartoons WHERE cartoon_id = ?";
    List<Cartoon> results = jdbcTemplate.query(sql, new CartoonRowMapper(), cartoonId);
    return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
  }
  @Override
  public void deleteById(int id) {
    String sql = "DELETE FROM cartoons WHERE cartoon_id = :id";
    int rowsAffected = jdbcTemplate.update(sql, Map.of("id", id));
    if (rowsAffected == 0) {
      throw new NotFoundException("Cartoon with ID " + id + " not found.");
    }
  }

  @Override
  public void save(Cartoon cartoon) {
    String sql = """
        INSERT INTO cartoons (cartoon_id, cartoon_name, first_appearance_year)
        VALUES (:id, :name, :year)
        """;
    int rowsAffected = jdbcTemplate.update(
        sql,
        Map.of(
            "id", cartoon.getCartoonId(),
            "name", cartoon.getCartoonName(),
            "year", cartoon.getFirstAppearanceYear()
        )
    );
    if (rowsAffected == 0) {
      throw new DataConflictException("Failed to insert cartoon with ID " + cartoon.getCartoonId());
    }
  }
}
