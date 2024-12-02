package ie.spring.lab1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Locale;

@SpringJUnitConfig(LanguageConfig.class)
public class LanguageConfigTests {

  @Autowired
  private MessageSource messageSource;

  @Test
  void testMessageSourceBeanCreation() {
    Assertions.assertNotNull(messageSource, "MessageSource bean should be created.");
  }

  @Test
  void testDefaultLanguage() {
    String message = messageSource.getMessage("greeting", null, Locale.ENGLISH);
    Assertions.assertEquals("Hello", message, "Default language greeting should be 'Hello'.");
  }

  @Test
  void testFrenchLanguage() {
    String message = messageSource.getMessage("greeting", null, Locale.FRENCH);
    Assertions.assertEquals("Bonjour", message, "French language greeting should be 'Bonjour'.");
  }
}
