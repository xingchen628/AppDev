package ie.spring.jdbc;

import ie.spring.jdbc.configurations.Config;
import ie.spring.jdbc.repository.CartoonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitConfig(classes = {Config.class})
public class CartoonRepositoryTests {

  @Autowired
  private CartoonRepository cartoonRepository;

  @Test
  public void testCount() {
    int count = cartoonRepository.count();
    assertEquals(10, count, "The count of cartoons should be 10");
  }
  @Test
  public void testFindAll() {
    var cartoons = cartoonRepository.findAll();
    assertEquals(10, cartoons.size(), "The findAll() method should return 10 cartoons");

    cartoons.forEach(cartoon -> System.out.println(cartoon.toString()));
  }
  @Test
  public void testFindByCartoonId() {
    var cartoon = cartoonRepository.findByCartoonId(1);
    assertTrue(cartoon.isPresent(), "Cartoon with ID 1 should be present");
    assertEquals("SpongeBob SquarePants", cartoon.get().getCartoonName(),
        "The cartoon name for ID 1 should be 'SpongeBob SquarePants'");

    var nonExistentCartoon = cartoonRepository.findByCartoonId(100);
    assertTrue(nonExistentCartoon.isEmpty(), "Cartoon with ID 100 should not exist");
  }
}
