package ie.spring.jdbc.repository;

import ie.spring.jdbc.entities.Cartoon;
import java.util.List;
import java.util.Optional;


public interface CartoonRepository {
  int count(); // Returns the total number of cartoons
  List<Cartoon> findAll(); // Returns all cartoons
  Optional<Cartoon> findByCartoonId(int cartoonId);

  void deleteById(int id);

  void save(Cartoon cartoon);
}
