package de.nordakademie.informaticup.pandemicfighter.gameengine;

public class ValueUtility {
    private static final double VERY_HIGH_VALUE_PATHOGEN = 1.1;
    private static final double HIGH_VALUE_PATHOGEN = 1.05;
    private static final double LOW_VALUE_PATHOGEN = 0.95;
    private static final double VERY_LOW_VALUE_PATHOGEN = 0.9;

    private static final double MID_VALUE = 1;

    private static final double VERY_HIGH_VALUE_CITY = 0.9;
    private static final double HIGH_VALUE_CITY = 0.95;
    private static final double LOW_VALUE_CITY = 1.05;
    private static final double VERY_LOW_VALUE_CITY = 1.1;

    public static double getValueEqualToStringPathogen(String symbol){
        double value;

        if (symbol.equals("++")){
            value = VERY_HIGH_VALUE_PATHOGEN;
        }
        else if (symbol.equals("+")){
            value = HIGH_VALUE_PATHOGEN;
        }
        else if (symbol.equals("o")){
            value = MID_VALUE;
        }
        else if (symbol.equals("-")){
            value = LOW_VALUE_PATHOGEN;
        }
        else{
            value = VERY_LOW_VALUE_PATHOGEN;
        }

        return value;
    }

    public static double getValueEqualToStringCity(String symbol){
        double value;

        if (symbol.equals("++")){
            value = VERY_HIGH_VALUE_CITY;
        }
        else if (symbol.equals("+")){
            value = HIGH_VALUE_CITY;
        }
        else if (symbol.equals("o")){
            value = MID_VALUE;
        }
        else if (symbol.equals("-")){
            value = LOW_VALUE_CITY;
        }
        else{
            value = VERY_LOW_VALUE_CITY;
        }

        return value;
    }

    public static double getVeryHighValuePathogen() {
        return VERY_HIGH_VALUE_PATHOGEN;
    }

    public static double getHighValuePathogen() {
        return HIGH_VALUE_PATHOGEN;
    }

    public static double getLowValuePathogen() {
        return LOW_VALUE_PATHOGEN;
    }

    public static double getVeryLowValuePathogen() {
        return VERY_LOW_VALUE_PATHOGEN;
    }

    public static double getMidValue() {
        return MID_VALUE;
    }

    public static double getVeryHighValueCity() {
        return VERY_HIGH_VALUE_CITY;
    }

    public static double getHighValueCity() {
        return HIGH_VALUE_CITY;
    }

    public static double getLowValueCity() {
        return LOW_VALUE_CITY;
    }

    public static double getVeryLowValueCity() {
        return VERY_LOW_VALUE_CITY;
    }
}
