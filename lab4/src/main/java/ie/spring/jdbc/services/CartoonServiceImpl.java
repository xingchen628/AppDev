package ie.spring.jdbc.services;

import ie.spring.jdbc.entities.Cartoon;
import ie.spring.jdbc.repository.CartoonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartoonServiceImpl implements CartoonService {
  private final CartoonRepository cartoonRepository;

  public CartoonServiceImpl(CartoonRepository cartoonRepository) {
    this.cartoonRepository = cartoonRepository;
  }

  @Override
  public int count() {
    return cartoonRepository.count();
  }

  @Override
  public List<Cartoon> findAll() {
    return cartoonRepository.findAll();
  }

  @Override
  public Cartoon findById(int id) {
    return cartoonRepository.findByCartoonId(id)
        .orElseThrow(() -> new NotFoundException("Cartoon with ID " + id + " not found"));
  }
  @Override
  public void delete(int id) {
    try {
      cartoonRepository.deleteById(id);
    } catch (NotFoundException e) {
      throw new NotFoundException("Cartoon with ID " + id + " not found.");
    }
  }

  @Override
  public void save(Cartoon cartoon) throws MalformedDataException, DataConflictException {
    if (cartoon.getCartoonName() == null || cartoon.getCartoonName().isBlank()) {
      throw new MalformedDataException("Cartoon name cannot be null or blank.");
    }
    if (cartoon.getFirstAppearanceYear() < 1900 || cartoon.getFirstAppearanceYear() > 2100) {
      throw new MalformedDataException("First appearance year must be between 1900 and 2100.");
    }
    cartoonRepository.save(cartoon);
  }
}
