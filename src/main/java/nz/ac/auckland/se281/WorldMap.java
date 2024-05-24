package nz.ac.auckland.se281;

import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Collections;

public class WorldMap {
  private Map<Country, List<Country>> adjCountries;

  // Constructor that intialises the adjacency map
  public WorldMap() {
    this.adjCountries = new HashMap<>();
  }

  /**
   * A method that adds a new unique country as a key to the adjacency map
   *
   * @param country the country that we want to add to the adjacency map
   */
  public void addCountry(Country country) {
    adjCountries.putIfAbsent(country, new ArrayList<>());
  }

  /**
   * A method that adds a country as a value to a chosen key for the adjacency map
   *
   * @param country1 the key for which country2 needs to be stored
   * @param country2 the value that needs to be stored in the arraylist for country1
   */
  public void addNeighbour(Country country1, Country country2) {
    adjCountries.get(country1).add(country2);
  }

  /**
   * A method that takes in a string of a country name and returns the corresponding
   * Country instance with the same name.
   *
   * @param countryName the string with the name of the country
   * @return the corresponding Country instance, returns null if country is not in 
   * the adjacency map
   */
  public Country getCountry(String countryName) {
    // Check through all keys in adjacency map to see if country exists
    for (Country c : adjCountries.keySet()) {
      if (c.toString().equals(countryName)) {
        return c;
      }
    }
    
    return null;
  }

  /**
   * A method that calculates the shortest route from one country to another.
   *
   * @param start the country that the route starts at
   * @param end the country that the route need to end at
   * @return the route as an ordered list of countries starting at 'start' and 
   * ending at 'end', returns null is there is no possible route from start to end
   */
  public List<Country> findRoute(Country start, Country end) {
    Set<Country> visited = new LinkedHashSet<>();
    Map<Country, Country> neighbourMap = new HashMap<>();
    Queue<Country> queue = new LinkedList<>();  

    // Add the root country to the collections
    queue.add(start);
    visited.add(start);
    neighbourMap.put(start, null);

    // Run as long as queue is not empty
    while (!queue.isEmpty()) {
      Country currentCountry = queue.poll();
      for (Country neighbour : adjCountries.get(currentCountry)) {
        if (neighbour.equals(end)) {
          // Destination detected, create and fill the shortest route
          List<Country> shortestRoute = new LinkedList<>();
          shortestRoute.add(neighbour);

          // Backtrack using the neighbourMap to find shortest route
          Country country = currentCountry;
          while (country != null) {
            shortestRoute.add(country);
            country = neighbourMap.get(country);
          }

          // Reverse the route because it is backwards
          Collections.reverse(shortestRoute);
          return shortestRoute;

        } else if (!visited.contains(neighbour)) {
          // When neighbour has not been visited queue it up to be processed
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
