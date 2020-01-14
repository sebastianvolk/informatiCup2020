package de.nordakademie.informaticup.pandemicfighter.gameengine;

import de.nordakademie.informaticup.pandemicfighter.gameengine.actions.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DecisionMakerTest {
    private EndRoundAction endRoundAction;
    private ApplyHygienicMeasuresAction applyHygienicMeasuresAction;
    private CloseAirportAction closeAirportAction;
    private DevelopVaccineAction developVaccineAction;

    @Before
    public void setUp() throws Exception {
        endRoundAction = mock(EndRoundAction.class);
        when(endRoundAction.getPoints()).thenReturn(0);
        when(endRoundAction.getThreatIndicator()).thenReturn(1.2);
        applyHygienicMeasuresAction = mock(ApplyHygienicMeasuresAction.class);
        when(applyHygienicMeasuresAction.getPoints()).thenReturn(3);
        when(applyHygienicMeasuresAction.getThreatIndicator()).thenReturn(1.3);
        closeAirportAction = mock(CloseAirportAction.class);
        when(closeAirportAction.getPoints()).thenReturn(20);
        when(closeAirportAction.getThreatIndicator()).thenReturn(1.7);
        developVaccineAction = mock(DevelopVaccineAction.class);
        when(developVaccineAction.getPoints()).thenReturn(40);
        when(developVaccineAction.getThreatIndicator()).thenReturn(1.7);
    }

    @Test
    public void getBestActionFromThreeActionsTest() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(endRoundAction);
        actions.add(applyHygienicMeasuresAction);
        actions.add(closeAirportAction);

        assertEquals(closeAirportAction, new DecisionMaker().getBestAction(actions));
    }

    @Test
    public void getBestActionFromOneActionTest() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(endRoundAction);

        assertEquals(endRoundAction, new DecisionMaker().getBestAction(actions));
    }

    @Test
    public void getBestActionFromTwoActionsWithEqualThreatIndicatorsTest() {
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(developVaccineAction);
        actions.add(closeAirportAction);

        assertEquals(closeAirportAction, new DecisionMaker().getBestAction(actions));
    }
}
