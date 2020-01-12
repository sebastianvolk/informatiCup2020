package de.nordakademie.informaticup.pandemicfighter.gameengine;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueUtilityTest {

    @Test
    public void getValueEqualToStringPathogen() {
    }

    @Test
    public void getValueEqualToStringCity() {
    }

    @Test
    public void getVeryHighValuePathogenTest() {
        assertEquals(1.2, ValueUtility.getVeryHighValuePathogen(), 0.0);
    }

    @Test
    public void getVeryHighValuePathogenFalseTest() {
        assertNotEquals(1.1, ValueUtility.getVeryHighValuePathogen(), 0.0);
    }

    @Test
    public void getHighValuePathogenTest() {
        assertEquals(1.1, ValueUtility.getHighValuePathogen(), 0.0);
    }

    @Test
    public void getHighValuePathogenFalseTest() {
        assertNotEquals(1.2, ValueUtility.getHighValuePathogen(), 0.0);
    }

    @Test
    public void getLowValuePathogenTest() {
        assertEquals(0.9, ValueUtility.getLowValuePathogen(), 0.0);
    }

    @Test
    public void getLowValuePathogenFalseTest() {
        assertNotEquals(0.8, ValueUtility.getLowValuePathogen(), 0.0);
    }

    @Test
    public void getVeryLowValuePathogenTest() {
        assertEquals(0.8, ValueUtility.getVeryLowValuePathogen(), 0.0);
    }

    @Test
    public void getVeryLowValuePathogenFalseTest() {
        assertNotEquals(0.9, ValueUtility.getVeryLowValuePathogen(), 0.0);
    }

    @Test
    public void getMidValueTest() {
        assertEquals(1, ValueUtility.getMidValue(), 0.0);
    }

    @Test
    public void getMidValueFalseTest() {
        assertNotEquals(1.1, ValueUtility.getMidValue(), 0.0);
    }

    @Test
    public void getVeryHighValueCity() {
        //assertEquals(1.2, ValueUtility.getVeryHighValuePathogen(), 0.0);
    }

    @Test
    public void getHighValueCity() {
        //assertEquals(1.2, ValueUtility.getVeryHighValuePathogen(), 0.0);
    }

    @Test
    public void getLowValueCity() {
        //assertEquals(1.2, ValueUtility.getVeryHighValuePathogen(), 0.0);
    }

    @Test
    public void getVeryLowValueCity() {
        //assertEquals(1.2, ValueUtility.getVeryHighValuePathogen(), 0.0);
    }
}