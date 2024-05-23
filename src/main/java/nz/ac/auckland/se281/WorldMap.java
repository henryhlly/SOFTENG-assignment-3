package nz.ac.auckland.se281;

import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

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

  public List<Country> findRoute(Country start, Country end) {
    Set<Country> visited = new HashSet<>();
    Map<Country, Country> neighbourMap = new HashMap<>();
    Queue<Country> queue = new LinkedList<>();  

    queue.add(start);
    visited.add(start);
    neighbourMap.put(start, null);

    while (!queue.isEmpty()) {
        Country currentCountry = queue.poll();
        for (Country neighbour : adjCountries.get(currentCountry)) {
            if (neighbour.equals(end)) {

                // Destination detected
                // Create and fill the shortest route
                List<Country> shortestRoute = new LinkedList<>();
                shortestRoute.add(neighbour);
                Country nextCountry = currentCountry;
                while (nextCountry != null) {
                  shortestRoute.add(nextCountry);
                  nextCountry = neighbourMap.get(nextCountry);
                }
                // Reverse the route because it is backwards
                Collections.reverse(shortestRoute);
                return shortestRoute;

            } else if (!visited.contains(neighbour)) {
              visited.add(neighbour);
              queue.add(neighbour);
              neighbourMap.put(neighbour, currentCountry);
            }
        }
    }
    // Destination was not reached.
    return null;
 }
}
