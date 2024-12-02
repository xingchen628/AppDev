package ie.spring.lab1.repositories.weddings;

import ie.spring.lab1.repositories.basic.Person;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


import java.util.*;

@Profile("empty") // Define the profile name as "empty"
@Repository
public class MockWeddingRepositoryImplEmpty implements WeddingRepository {

  private final Map<String, Wedding> weddings = new HashMap<>();

  public MockWeddingRepositoryImplEmpty() {
    List<Guest> guests = new ArrayList<>();
    Wedding wedding = new Wedding("RS342",
        new Person(89, "Minnie", "Mouse"),
        new Person(100, "Mickey", "Mouse"),
        56.00, guests);
    weddings.put(wedding.getWeddingId(), wedding);
  }

  @Override
  public int getNumberOfGuests(String id) {
    Wedding wedding = weddings.get(id);
    return wedding != null ? wedding.getGuests().size() : 0;
  }

  @Override
  public Optional<Wedding> findById(String id) {
    return Optional.ofNullable(weddings.get(id));
  }
}
