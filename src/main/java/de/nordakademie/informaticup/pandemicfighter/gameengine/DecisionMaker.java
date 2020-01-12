package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.Action;

import java.util.ArrayList;

public class DecisionMaker {
    private static final double FACTOR_POINT = 1.0103;
    private int points;

    public DecisionMaker(int points) {
        this.points = points;
    }

    public Action getBestAction(ArrayList<Action> actions) {
        Action bestAction = actions.get(0);
        for (int i = 1; i < actions.size(); i++) {
            bestAction = getBetterAction(bestAction, actions.get(i));
        }
        System.out.println(bestAction.getThreatIndicator());
        return bestAction;
    }

    private Action getBetterAction(Action action1, Action action2) {
        Action betterAction;
        double action1ThreatIndicator = action1.getThreatIndicator();
        double action2ThreatIndicator = action2.getThreatIndicator();
        if (action1.getPoints() < action2.getPoints()) {
            int pointDifference = action2.getPoints() - action1.getPoints();
            /*double factorAvailablePoints = action1.getPoints () != 0 ? (double) action1.getPoints() / points : 0.77;*/
            /*double factorAvailablePoints = (double) (action1.getPoints() + 10) / points;
            factorAvailablePoints = factorAvailablePoints > 1 ? 1 : factorAvailablePoints;*/
            action1ThreatIndicator *= Math.pow(FACTOR_POINT, pointDifference)/*  * factorAvailablePoints*/;
        } else if (action1.getPoints() > action2.getPoints()) {
            int pointDifference = action1.getPoints() - action2.getPoints();
            /*double factorAvailablePoints = (double) (action2.getPoints() + 10) / points;
            factorAvailablePoints = factorAvailablePoints > 1 ? 1 : factorAvailablePoints;*/
            action2ThreatIndicator *= Math.pow(FACTOR_POINT, pointDifference)/* * factorAvailablePoints*/;
        }
        if (action1ThreatIndicator >= action2ThreatIndicator) {
            betterAction = action1;
        } else {
            betterAction = action2;
        }
        return betterAction;
    }
}
