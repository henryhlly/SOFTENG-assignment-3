package nz.ac.auckland.se281;

import java.util.List;

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
    MessageCli.INSERT_COUNTRY.printMessage();
    while (true) {
      String input = Utils.scanner.nextLine();
      String capInput = Utils.capitalizeFirstLetterOfEachWord(input);
      
      try {
        Country country = checkCountryInput(capInput);
        MessageCli.COUNTRY_INFO.printMessage(country.getName(), country.getContinent(), country.getTax());
        break;
      }
      catch (InvalidCountryException e) {
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

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}

  
}
