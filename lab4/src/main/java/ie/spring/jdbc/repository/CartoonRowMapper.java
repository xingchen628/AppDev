package ie.spring.jdbc.repository;

import ie.spring.jdbc.entities.Cartoon;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartoonRowMapper implements RowMapper<Cartoon> {
  @Override
  public Cartoon mapRow(ResultSet rs, int rowNum) throws SQLException {
    Cartoon cartoon = new Cartoon();
    cartoon.setCartoonId(rs.getInt("cartoon_id"));
    cartoon.setCartoonName(rs.getString("cartoon_name"));
    cartoon.setFirstAppearanceYear(rs.getInt("first_appearance_year"));
    return cartoon;
  }
}

