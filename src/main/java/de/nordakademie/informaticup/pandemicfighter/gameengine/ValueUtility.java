package de.nordakademie.informaticup.pandemicfighter.gameengine;

public class ValueUtility {
    public int getValueEqualToString(String symbol){
        int value;

        if (symbol.equals("++")){
            value = 5;
        }
        else if (symbol.equals("+")){
            value = 4;
        }
        else if (symbol.equals("o")){
            value = 3;
        }
        else if (symbol.equals("-")){
            value = 2;
        }
        else{
            value = 1;
        }

        return value;
    }
}
