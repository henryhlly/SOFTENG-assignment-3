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

  public void addNeighbour(Country country1, Country country2) {
    addCountry(country1);
    addCountry(country2);
    adjCountries.get(country1).add(country2);
    adjCountries.get(country2).add(country1);
  }

  public Country getCountry(String countryName) {
    for (Country c : adjCountries.keySet()) {
      if (c.getName().equals(countryName)) {
        return c;
      }
    }
    
    return null;
  }
}
