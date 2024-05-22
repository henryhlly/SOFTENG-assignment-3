package nz.ac.auckland.se281;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class WorldMap {
  private Map<Country, List<Country>> adjCountries;

  public WorldMap() {
    this.adjCountries = new HashMap<>();
  }

  public void addCountry(Country country) {
    adjCountries.putIfAbsent(country, new ArrayList<>());
  }

  public void addNeighbour(String c1, String c2) {
    Country country1 = null;
    Country country2 = null;

    for (Country c : adjCountries.keySet()) {
      if (c.getName().equals(c1)) {
        country1 = c;
      } else if (c.getName().equals(c2)) {
        country2 = c;
      }
    }
    if ((country1 != null) & (country2!=null)) {
      adjCountries.get(country1).add(country2);
      adjCountries.get(country2).add(country1);
    }
  }
}
