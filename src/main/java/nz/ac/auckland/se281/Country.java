package nz.ac.auckland.se281;

/**
 * A class that represents the Country object. Stores all the info
 * that a country needs to have. With custom hashcode generation and
 * equals method.
 *
 * @author Henry Ly
 */
public class Country {
  private int id;
  private String name;
  private String continent;
  private int tax;
  
  /**
   * A constructor method to initialise all the instance fields in Country.
   *
   * @param id the unique ID of the country
   * @param name the name of the country
   * @param continent the continent that the country is in
   * @param tax the tax fees of the country
   */
  public Country(int id, String name, String continent, String tax) {
    this.id = id;
    this.name = name;
    this.continent = continent;
    // Convert tax as string to int using custom method
    this.tax = toInteger(tax);
  }

  /**
   * A simple method that converts a string into an integer but also handles exceptions.
   *
   * @param str the string that we wish to turn into an integer
   * @return the integer value of the string value given
   */
  public int toInteger(String str) {
    try {
      return Integer.parseInt(str);
    } catch (Exception e) {
      return -1;
    }
  }

  // Getter method for the continent instance field
  public String getContinent() {
    return continent;
  }

  // Getter method for the tax instance field
  public int getTax() {
    return tax;
  }
  
  @Override 
  public String toString() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Country other = (Country) obj;
    // Only need to compare ids
    if (id != other.id) {
      return false;
    }
    return true;
  }

  
}
