package nz.ac.auckland.se281;

import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

/** This class is the main entry point. */
public class MapEngine {

  WorldMap worldMap;

  public MapEngine() {
    worldMap = new WorldMap();
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
        worldMap.addNeighbour(worldMap.getCountry(listOfNeighbours[0]), worldMap.getCountry(listOfNeighbours[i]));
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    Country country = getCountryInput(MessageCli.INSERT_COUNTRY.getMessage());
    MessageCli.COUNTRY_INFO.printMessage(country.getName(), country.getContinent(), String.valueOf(country.getTax()));
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    Country source;
    Country destination;

    // Get valid starting country
    source = getCountryInput(MessageCli.INSERT_SOURCE.getMessage());
    
    // Get valid destination country
    destination = getCountryInput(MessageCli.INSERT_DESTINATION.getMessage());

    if (source == destination) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
    } else {
      List<Country> shortestRoute = worldMap.findRoute(source, destination);
      Set<String> visitedContinents = new LinkedHashSet<>();

      for (Country c : shortestRoute) {
        visitedContinents.add(c.getContinent());
      }

      MessageCli.ROUTE_INFO.printMessage(shortestRoute.toString());
      MessageCli.CONTINENT_INFO.printMessage(visitedContinents.toString());
      MessageCli.TAX_INFO.printMessage(String.valueOf(calculateTax(shortestRoute)));
    }
  }

  public Country getCountryInput(String initialMessage) {
    System.out.println(initialMessage);

    while (true) {
      String input = Utils.scanner.nextLine();
      String capInput = Utils.capitalizeFirstLetterOfEachWord(input);

      try {
        // Return the country that user is asking for
        return checkCountryInput(capInput);
      }
      catch (InvalidCountryException e) {
        // When user inputs a country not in worldMap
        MessageCli.INVALID_COUNTRY.printMessage(capInput);
      }
    }
  }

    /**
   * A method to check that the user has inputted a valid country.
   *
   * @param inputCountry the input that was received from user
   * @return the country that user is referring to
   */
  public Country checkCountryInput(String inputCountry) {
    Country c = worldMap.getCountry(inputCountry);
    if (c == null) {
      throw new InvalidCountryException();
    } else {
      return c;
    }
  }

  public int calculateTax(List<Country> route) {
    int total = 0;
    // Skip first one since it doesn't count
    for (int i = 1; i < route.size(); i++) {
      total = total + route.get(i).getTax();
    }
    return total;
  }

  
}
