package nz.ac.auckland.se281;

/**
 * A custom unchecked exception that is thrown when a country that does
 * not exist is being referenced.
 *
 * @author Henry Ly
 */
public class InvalidCountryException extends Exception {
  // Constructor for the custom exception
  public InvalidCountryException() {
    super("Country not found");
  }
}
