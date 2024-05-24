package nz.ac.auckland.se281;

public class Country {
  private int id;
  private String name;
  private String continent;
  private int tax;
  
  // Constructor to initialise all the instance fields of Country object
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
    }
    catch (Exception e) {
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        if (id == -1) {
          if (other.id != -1)
            return false;
        } else if (!(id == other.id)){
          return false;
        }
        return true;
    }
}
