package ie.spring.jdbc.services;

import ie.spring.jdbc.entities.Cartoon;
import java.util.List;

public interface CartoonService {
  int count();
  List<Cartoon> findAll();
  Cartoon findById(int id);

  void delete(int id);

  void save(Cartoon cartoon) throws MalformedDataException, DataConflictException;
}
