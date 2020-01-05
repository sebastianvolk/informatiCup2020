package de.nordakademie.informaticup.pandemicfighter.gameengine;

public class ValueUtility {
    public static double getValueEqualToString(String symbol){
        double value;

        if (symbol.equals("++")){
            value = 1.5;
        }
        else if (symbol.equals("+")){
            value = 1.25;
        }
        else if (symbol.equals("o")){
            value = 1;
        }
        else if (symbol.equals("-")){
            value = 0.75;
        }
        else{
            value = 0.5;
        }

        return value;
    }
}
