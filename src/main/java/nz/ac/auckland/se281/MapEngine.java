package nz.ac.auckland.se281;

import java.util.List;
import java.util.ArrayList;

/** This class is the main entry point. */
public class MapEngine {

  WorldMap worldMap = new WorldMap();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    
    // Add the new countries into Map
    for (String c : countries) {
      String[] countryData = c.split(",");
      // Parameters: id = index of country, name = [0], continent = [1], tax = [2]
      worldMap.addCountry(new Country(countries.indexOf(c), countryData[0], countryData[1], countryData[2]));
    }

    // Add the corresponding neighbours to the Map
    for (String adjString : adjacencies) {
      String[] listOfNeighbours = adjString.split(",");
      for (int i = 1; i < listOfNeighbours.length; i++) {
        worldMap.addNeighbour(listOfNeighbours[0], listOfNeighbours[i]);
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
