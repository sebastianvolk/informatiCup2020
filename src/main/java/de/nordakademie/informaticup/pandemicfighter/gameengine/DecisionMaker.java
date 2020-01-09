package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.Action;

import java.util.ArrayList;

public class DecisionMaker {
    private static final double FACTOR_POINT = 1.04;

    public Action getBestAction(ArrayList<Action> actions) {
        Action bestAction = actions.get(0);
        for (int i = 1; i < actions.size(); i++) {
            bestAction = getBetterAction(bestAction, actions.get(i));
        }
        return bestAction;
    }

    private Action getBetterAction(Action action1, Action action2) {
        Action betterAction;
        double action1ThreatIndicator = action1.getThreatIndicator();
        double action2ThreatIndicator = action2.getThreatIndicator();
        if (action1.getPoints() < action2.getPoints()) {
            int pointDifference = action2.getPoints() - action1.getPoints();
            action1ThreatIndicator *= Math.pow(FACTOR_POINT, pointDifference);
        } else if (action1.getPoints() > action2.getPoints()) {
            int pointDifference = action1.getPoints() - action2.getPoints();
            action2ThreatIndicator *= Math.pow(FACTOR_POINT, pointDifference);
        }
        if (action1ThreatIndicator >= action2ThreatIndicator) {
            betterAction = action1;
        } else {
            betterAction = action2;
        }
        return betterAction;
    }
}
