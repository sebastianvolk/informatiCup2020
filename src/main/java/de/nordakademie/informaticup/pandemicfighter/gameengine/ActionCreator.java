package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.*;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.City;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Game;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.Pathogen;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.ConnectionClosedEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.Event;
import de.nordakademie.informaticup.pandemicfighter.gameengine.elements.events.PathogenEncounteredEvent;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.CityProvider;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.MedicationCabinet;
import de.nordakademie.informaticup.pandemicfighter.gameengine.provider.cabinets.VaccineCabinet;

import java.util.ArrayList;

public class ActionCreator {
    private int availablePoints;
    public ArrayList<Action> getAllPossibleActions(Game game) {
        availablePoints = game.getPoints();
        ArrayList<City> cities = CityProvider.getCities();
        ArrayList<Pathogen> pathogens = getPathogensFromPathogenEncounteredEvents(game.getEventsByType("pathogenEncountered"));
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(new EndRoundAction());
        actions.addAll(getAllPossiblePutUnderQuarantineActions(cities));
        actions.addAll(getAllPossibleCloseAirportActions(cities));
        actions.addAll(getAllPossibleCloseConnectionActions(cities));
        actions.addAll(getAllPossibleDevelopVaccineActions(pathogens));
        actions.addAll(getAllPossibleDeployVaccineActions(pathogens, cities));
        actions.addAll(getAllPossibleDevelopMedicationActions(pathogens));
        actions.addAll(getAllPossibleDeployMedicationActions(pathogens, cities));
        actions.addAll(getAllPossibleExertInfluenceActions(cities));
        actions.addAll(getAllPossibleCallElectionsActions(cities));
        actions.addAll(getAllPossibleApplyHygienicMeasuresActions(cities));
        actions.addAll(getAllPossibleLaunchCampaignActions(cities));
        return actions;
    }

    private ArrayList<Action> getAllPossiblePutUnderQuarantineActions(ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (City city : cities) {
            ArrayList<Event> quarantineEvents = city.getEventsByType("quarantine");
            if (quarantineEvents.size() == 0) {
                int rounds = 1;
                boolean inserted = true;
                while (inserted) {
                    PutUnderQuarantineAction action = new PutUnderQuarantineAction(city, rounds);
                    inserted = isActionPossible(action);
                    if (inserted) {
                        actions.add(action);
                    }
                    rounds++;
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleCloseAirportActions(ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (City city : cities) {
            ArrayList<Event> airportClosedEvents = city.getEventsByType("airportClosed");
            if (airportClosedEvents.size() == 0) {
                int rounds = 1;
                boolean inserted = true;
                while (inserted) {
                    CloseAirportAction action = new CloseAirportAction(city, rounds);
                    inserted = isActionPossible(action);
                    if (inserted) {
                        actions.add(action);
                    }
                    rounds++;
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleCloseConnectionActions(ArrayList<City> cities) {
        ArrayList<Action> actions = new ArrayList<>();
        for (City fromCity : cities) {
            ArrayList<City> toCities = new ArrayList<>(cities);
            toCities.remove(fromCity);
            ArrayList<Event> connectionClosedEvents = fromCity.getEventsByType("connectionClosed");
            for (City toCity : toCities) {
                boolean connectionClosed = false;
                for (Event event : connectionClosedEvents) {
                    ConnectionClosedEvent connectionClosedEvent = (ConnectionClosedEvent) event;
                    if (toCity.getName().equals(connectionClosedEvent.getCity())) {
                        connectionClosed = true;
                    }
                }
                if (!connectionClosed) {
                    int rounds = 1;
                    boolean inserted = true;
                    while (inserted) {
                        CloseConnectionAction action = new CloseConnectionAction(fromCity, toCity, rounds);
                        inserted = isActionPossible(action);
                        if (inserted) {
                            actions.add(action);
                        }
                        rounds++;
                    }
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleDevelopVaccineActions(ArrayList<Pathogen> pathogens){
        ArrayList<Action> actions = new ArrayList<>();
        for (Pathogen pathogen : pathogens) {
            if (-1 == VaccineCabinet.roundsUntilVaccineIsAvailable(pathogen.getName())) {
                DevelopVaccineAction action = new DevelopVaccineAction(pathogen);
                if (isActionPossible(action)) {
                    actions.add(action);
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleDeployVaccineActions(ArrayList<Pathogen> pathogens, ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (Pathogen pathogen : pathogens) {
            if (0 == VaccineCabinet.roundsUntilVaccineIsAvailable(pathogen.getName())) {
                for (City city : cities) {
                    DeployVaccineAction action = new DeployVaccineAction(pathogen, city);
                    if (isActionPossible(action)) {
                        actions.add(action);
                    }
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleDevelopMedicationActions(ArrayList<Pathogen> pathogens){
        ArrayList<Action> actions = new ArrayList<>();
        for (Pathogen pathogen : pathogens) {
            if (-1 == MedicationCabinet.roundsUntilMedicationIsAvailable(pathogen.getName())) {
                DevelopMedicationAction action = new DevelopMedicationAction(pathogen);
                if (isActionPossible(action)) {
                    actions.add(action);
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleDeployMedicationActions(ArrayList<Pathogen> pathogens, ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (Pathogen pathogen : pathogens) {
            if (0 == MedicationCabinet.roundsUntilMedicationIsAvailable(pathogen.getName())) {
                for (City city : cities) {
                    DeployMedicationAction action = new DeployMedicationAction(pathogen, city);
                    if (isActionPossible(action)) {
                        actions.add(action);
                    }
                }
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleExertInfluenceActions(ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (City city : cities) {
            ExertInfluenceAction action = new ExertInfluenceAction(city);
            if (isActionPossible(action)) {
                actions.add(action);
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleCallElectionsActions(ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (City city : cities) {
            CallElectionsAction action = new CallElectionsAction(city);
            if (isActionPossible(action)) {
                actions.add(action);
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleApplyHygienicMeasuresActions(ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (City city : cities) {
            ApplyHygienicMeasuresAction action = new ApplyHygienicMeasuresAction(city);
            if (isActionPossible(action)) {
                actions.add(action);
            }
        }
        return actions;
    }

    private ArrayList<Action> getAllPossibleLaunchCampaignActions(ArrayList<City> cities){
        ArrayList<Action> actions = new ArrayList<>();
        for (City city : cities) {
            LaunchCampaignAction action = new LaunchCampaignAction(city);
            if (isActionPossible(action)) {
                actions.add(action);
            }
        }
        return actions;
    }

    private boolean isActionPossible(Action action) {
        return availablePoints >= action.getPoints();
    }

    private ArrayList<Pathogen> getPathogensFromPathogenEncounteredEvents(ArrayList<Event> events) {
        ArrayList<Pathogen> pathogens = new ArrayList<>();
        for (Event event : events) {
            PathogenEncounteredEvent pathogenEncounteredEvent = (PathogenEncounteredEvent) event;
            pathogens.add(pathogenEncounteredEvent.getPathogen());
        }
        return pathogens;
    }
}
