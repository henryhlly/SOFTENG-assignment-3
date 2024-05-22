package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {

  WorldMap worldMap = new WorldMap();

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    int id = 0;
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();

    System.out.println(countries.get(0));
    System.out.println(adjacencies.get(0));

    // Add the new countries into Map
    for (String c : countries) {
      String[] countryData = c.split(",");
      Country newCountry = new Country(id, countryData[0], countryData[1], countryData[2]);
      worldMap.addCountry(newCountry);

      int index = countries.indexOf(countryData[0]);
      String[] listOfNeighbours = adjacencies.get(index).split(",");
      for (int i = 1; i < listOfNeighbours.length; i++) {
        //worldMap.addNeighbour(worldMap.get(index) , null);
      }

      id++;
    }

    // Add the corresponding neighbours to the Map
    for (String adjString : adjacencies) {
      int index = adjacencies.indexOf(adjString);
      

    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // add code here
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
