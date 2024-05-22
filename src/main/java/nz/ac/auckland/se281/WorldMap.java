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

  public void addNeighbour() {
    
  }
}
