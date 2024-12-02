package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.services.DataConflictException;
import ie.spring.jdbc.services.CartoonService;
import ie.spring.jdbc.services.MalformedDataException;
import ie.spring.jdbc.services.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(Config.class)
public class CartoonServiceTests {

  @Autowired
  private CartoonService cartoonService;

  @Test
  public void testFindById_shouldReturnCartoon() {
    Cartoon cartoon = cartoonService.findById(2);
    assertEquals("Pikachu", cartoon.getCartoonName(), "Cartoon name should match");
  }

  @Test
  public void testFindById_notFound_shouldThrowException() {
    assertThrows(NotFoundException.class, () -> cartoonService.findById(111));
  }

  @Test
  public void testFindAll_shouldReturnAllCartoons() {
    List<Cartoon> cartoons = cartoonService.findAll();
    assertEquals(10, cartoons.size(), "Should return 10 cartoons");
  }

  @Test
  public void testCount_shouldReturnCountOfCartoons() {
    int count = cartoonService.count();
    assertEquals(10, count, "Cartoon count should be 10");
  }
  @Test
  public void delete_shouldDeleteCartoon() throws NotFoundException {
    cartoonService.delete(2);
    Assertions.assertThrows(NotFoundException.class, () -> cartoonService.findById(2));
  }

  @Test
  public void save_badYear_shouldThrowMalformed() {
    Cartoon cartoon = new Cartoon(11, "Muggles Unite", 3000);
    Assertions.assertThrows(MalformedDataException.class, () -> cartoonService.save(cartoon));
  }

  @Test
  public void save_noName_shouldThrowMalformed() {
    Cartoon cartoon = new Cartoon(11, "", 2024);
    Assertions.assertThrows(MalformedDataException.class, () -> cartoonService.save(cartoon));
  }

  @Test
  public void save_idAlreadyExists_shouldThrowDataConflict() {
    Cartoon cartoon = new Cartoon(1, "Muggles Unite", 2024);
    Assertions.assertThrows(DataConflictException.class, () -> cartoonService.save(cartoon));
  }
}
