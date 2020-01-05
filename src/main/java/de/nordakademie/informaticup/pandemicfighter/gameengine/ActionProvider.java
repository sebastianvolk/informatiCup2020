package de.nordakademie.informaticup.pandemicfighter.gameengine;

import com.google.gson.JsonObject;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;

public class ActionProvider {

    public static JsonObject endRound(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "endRound");
        return jsonObject;
    }

    public static JsonObject putUnderQuarantine(City city, int rounds){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "putUnderQuarantine");
        jsonObject.addProperty("city", city.getName());
        jsonObject.addProperty("rounds", rounds);
        return jsonObject;
    }

    public static JsonObject closeAirport(City city, int rounds){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "closeAirport");
        jsonObject.addProperty("city", city.getName());
        jsonObject.addProperty("rounds", rounds);
        return jsonObject;
    }

    public static JsonObject closeConnection(City fromCity, City toCity, int rounds){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "closeConnection");
        jsonObject.addProperty("fromCity", fromCity.getName());
        jsonObject.addProperty("toCity", toCity.getName());
        jsonObject.addProperty("rounds", rounds);
        return jsonObject;
    }

    public static JsonObject developVaccine(Pathogen pathogen){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "developVaccine");
        jsonObject.addProperty("pathogen", pathogen.getName());
        return jsonObject;
    }

    public static JsonObject deployVaccine(Pathogen pathogen, City city){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "deployVaccine");
        jsonObject.addProperty("pathogen", pathogen.getName());
        jsonObject.addProperty("city", city.getName());
        return jsonObject;
    }

    public static JsonObject developMedication(Pathogen pathogen){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "developMedication");
        jsonObject.addProperty("pathogen", pathogen.getName());
        return jsonObject;
    }

    public static JsonObject deployMedication(Pathogen pathogen, City city){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "deployMedication");
        jsonObject.addProperty("pathogen", pathogen.getName());
        jsonObject.addProperty("city", city.getName());
        return jsonObject;
    }

    public static JsonObject exertInfluence(City city){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "exertInfluence");
        jsonObject.addProperty("city", city.getName());
        return jsonObject;
    }

    public static JsonObject callElections(City city){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "callElections");
        jsonObject.addProperty("city", city.getName());
        return jsonObject;
    }

    public static JsonObject applyHygienicMeasures(City city){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "applyHygienicMeasures");
        jsonObject.addProperty("city", city.getName());
        return jsonObject;
    }

    public static JsonObject launchCampaign(City city){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "launchCampaign");
        jsonObject.addProperty("city", city.getName());
        return jsonObject;
    }
}
