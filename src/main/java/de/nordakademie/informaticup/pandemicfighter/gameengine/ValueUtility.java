package de.nordakademie.informaticup.pandemicfighter.gameengine;

public class ValueUtility {
    public static double getValueEqualToStringPathogen(String symbol){
        double value;

        if (symbol.equals("++")){
            value = 1.1;
        }
        else if (symbol.equals("+")){
            value = 1.05;
        }
        else if (symbol.equals("o")){
            value = 1;
        }
        else if (symbol.equals("-")){
            value = 0.95;
        }
        else{
            value = 0.9;
        }

        return value;
    }

    public static double getValueEqualToStringCity(String symbol){
        double value;

        if (symbol.equals("++")){
            value = 0.9; //TODO: define final static double
        }
        else if (symbol.equals("+")){
            value = 0.95;
        }
        else if (symbol.equals("o")){
            value = 1;
        }
        else if (symbol.equals("-")){
            value = 1.05;
        }
        else{
            value = 1.1;
        }

        return value;
    }
}
