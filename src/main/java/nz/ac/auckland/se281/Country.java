package nz.ac.auckland.se281;

public class Country {
  private int id;
  private String name;
  private String continent;
  private int tax;

  public Country(int id, String name, String continent, String tax) {
    this.id = id;
    this.name = name;
    this.continent = continent;
    this.tax = toInteger(tax);
  }

  public int toInteger(String str) {
    try {
       return Integer.parseInt(str);
    }
    catch (Exception e) {
      return -1;
    }
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
        return true;
    }
}
