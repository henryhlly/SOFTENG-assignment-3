package nz.ac.auckland.se281;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/** This class is the main entry point. */
public class MapEngine {

  private WorldMap worldMap;

  /**
   * A constructor method that initialises the map as a worldMap object.
   *
   * @author Henry Ly
   */
  public MapEngine() {
    // Generate the worldMap
    worldMap = new WorldMap();
    loadMap(); // keep this method invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    // Extract data from csv files
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    
    // Add the new countries into Map
    for (String c : countries) {
      String[] countryData = c.split(",");
      // Parameters: id = index of country, name = [0], continent = [1], tax = [2]
      worldMap.addCountry(
        new Country(
          countries.indexOf(c), 
          countryData[0], 
          countryData[1], 
          countryData[2]
        )
      );
    }

    // Add the corresponding neighbours to the Map
    for (String adjString : adjacencies) {
      String[] listOfNeighbours = adjString.split(",");

      for (int i = 1; i < listOfNeighbours.length; i++) {
        worldMap.addNeighbour(
            worldMap.getCountry(listOfNeighbours[0]), 
            worldMap.getCountry(listOfNeighbours[i])
        );
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    // Get country and then show its information
    Country country = getCountryInput(MessageCli.INSERT_COUNTRY.getMessage());
    MessageCli.COUNTRY_INFO.printMessage(
        country.toString(), 
        country.getContinent(), 
        String.valueOf(country.getTax())
    );
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {
    Country source;
    Country destination;

    // Get valid starting country
    source = getCountryInput(MessageCli.INSERT_SOURCE.getMessage());
    
    // Get valid destination country
    destination = getCountryInput(MessageCli.INSERT_DESTINATION.getMessage());

    // Deal with equal start and end
    if (source == destination) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
    } else {
      // Deal with route output
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

  /**
   * A method to simplify the process of getting input. It will repeated ask the user
   * for a country until it gets a valid country that is registered in worldMap.
   *
   * @param initialMessage the initial message to be displayed before getting feedback
   * @return the country instance that user was inputting.
   */
  private Country getCountryInput(String initialMessage) {
    System.out.println(initialMessage);

    // Loop until valid input is provided
    while (true) {
      String input = Utils.scanner.nextLine();
      String capInput = Utils.capitalizeFirstLetterOfEachWord(input);

      try {
        // Return the country that user is asking for
        return checkCountryInput(capInput);
      } catch (InvalidCountryException e) {
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
  private Country checkCountryInput(String inputCountry) {
    Country c = worldMap.getCountry(inputCountry);
    // Throw custom error if user input is invalid
    if (c == null) {
      throw new InvalidCountryException();
    } else {
      return c;
    }
  }

  /**
   * A method to calculate the total tax of a route.
   *
   * @param route the list of countries that the method is calculating the tax for
   * @return the total amount calculated as an integer
   */
  public int calculateTax(List<Country> route) {
    int total = 0;
    // Skip first one since it doesn't count
    for (int i = 1; i < route.size(); i++) {
      total = total + route.get(i).getTax();
    }
    return total;
  }

  
}
