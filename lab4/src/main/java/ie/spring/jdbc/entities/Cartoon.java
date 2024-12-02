package ie.spring.jdbc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cartoon {
  private int cartoonId;
  private String cartoonName;
  private int firstAppearanceYear;
}
