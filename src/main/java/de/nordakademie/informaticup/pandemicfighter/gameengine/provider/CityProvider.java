package de.nordakademie.informaticup.pandemicfighter.gameengine.provider;

import de.nordakademie.informaticup.pandemicfighter.gameengine.ThreatIndicator;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;

import java.util.ArrayList;

public class CityProvider {
    private static ArrayList<City> cities = new ArrayList<>();
    private static final double MAX_DISTANCE = 500;

    public static ArrayList<City> getCities() {
        return cities;
    }

    public static ArrayList<City> getConnectedCities(City city) {
        ArrayList<City> connectedCities = new ArrayList<>();
        for (String connectedCityName : city.getConnections()) {
            connectedCities.add(getCity(connectedCityName));
        }
        return connectedCities;
    }

    public static ArrayList<City> getCitiesWhichHaveConnectionToGivenCity(City city){
        ArrayList<City> citiesWithConnectionToGivenCity = new ArrayList<>();
        for (City gameCity : cities) {
            ArrayList<City> citiesConnectedToGameCity = getConnectedCities(gameCity);
            if (citiesConnectedToGameCity.contains(city)){
                citiesWithConnectionToGivenCity.add(gameCity);
            }
        }
        return citiesWithConnectionToGivenCity;
    }

    public static ArrayList<City> getNearCities(City city) {
        ArrayList<City> nearCities = new ArrayList<>();
        for (City city2 : cities) {
            double distance = calculateDistance(city, city2);
            if (distance <= MAX_DISTANCE && distance != 0) {
                nearCities.add(city2);
            }
        }
        return nearCities;
    }

    public static void setCities(ArrayList<City> cities) {
        CityProvider.cities = cities;
    }

    private static City getCity(String name) {
        City city = null;
        boolean found = false;
        for (int i = 0; i < cities.size() && !found; i++) {
            City citiesElement = cities.get(i);
            if (citiesElement.getName().equals(name)) {
                city = citiesElement;
                found = true;
            }
        }
        return city;
    }

    private static double calculateDistance(City city1, City city2) {
        return calculateDistance(
                city1.getLatitude(),
                city1.getLongitude(),
                city2.getLatitude(),
                city2.getLongitude()
        );
    }

    private static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        if ((latitude1 == latitude2) && (longitude1 == longitude2)) {
            return 0;
        }
        else {
            double theta = longitude1 - longitude2;
            double dist = Math.sin(Math.toRadians(latitude1))
                    * Math.sin(Math.toRadians(latitude2))
                    + Math.cos(Math.toRadians(latitude1))
                    * Math.cos(Math.toRadians(latitude2))
                    * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515 * 1.609344;
            return (dist);
        }
    }
}
