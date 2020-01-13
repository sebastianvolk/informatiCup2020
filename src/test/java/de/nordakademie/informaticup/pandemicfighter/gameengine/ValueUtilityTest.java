package de.nordakademie.informaticup.pandemicfighter.gameengine;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValueUtilityTest {

    @Test
    public void getValueEqualToStringPathogenVeryHighTest() {
        assertEquals(1.2, ValueUtility.getValueEqualToStringPathogen("++"), 0.0);
    }

    @Test
    public void getValueEqualToStringPathogenHighTest() {
        assertEquals(1.1, ValueUtility.getValueEqualToStringPathogen("+"), 0.0);
    }

    @Test
    public void getValueEqualToStringPathogenMiddleTest() {
        assertEquals(1, ValueUtility.getValueEqualToStringPathogen("o"), 0.0);
    }

    @Test
    public void getValueEqualToStringPathogenLowTest() {
        assertEquals(0.9, ValueUtility.getValueEqualToStringPathogen("-"), 0.0);
    }

    @Test
    public void getValueEqualToStringPathogenVeryLowTest() {
        assertEquals(0.8, ValueUtility.getValueEqualToStringPathogen("--"), 0.0);
    }

    @Test
    public void getValueEqualToStringCityVeryHighTest() {
        assertEquals(0.8, ValueUtility.getValueEqualToStringCity("++"), 0.0);
    }

    @Test
    public void getValueEqualToStringCityHighTest() {
        assertEquals(0.9, ValueUtility.getValueEqualToStringCity("+"), 0.0);
    }

    @Test
    public void getValueEqualToStringCityMiddleTest() {
        assertEquals(1, ValueUtility.getValueEqualToStringCity("o"), 0.0);
    }

    @Test
    public void getValueEqualToStringCityLowTest() {
        assertEquals(1.1, ValueUtility.getValueEqualToStringCity("-"), 0.0);
    }

    @Test
    public void getValueEqualToStringCityVeryLowTest() {
        assertEquals(1.2, ValueUtility.getValueEqualToStringCity("--"), 0.0);
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
    public void getVeryHighValueCityTest() {
        assertEquals(0.8, ValueUtility.getVeryHighValueCity(), 0.0);
    }

    @Test
    public void getVeryHighValueCityFalseTest() {
        assertNotEquals(0.9, ValueUtility.getVeryHighValueCity(), 0.0);
    }

    @Test
    public void getHighValueCityTest() {
        assertEquals(0.9, ValueUtility.getHighValueCity(), 0.0);
    }

    @Test
    public void getHighValueCityFalseTest() {
        assertNotEquals(0.8, ValueUtility.getHighValueCity(), 0.0);
    }

    @Test
    public void getLowValueCityTest() {
        assertEquals(1.1, ValueUtility.getLowValueCity(), 0.0);
    }

    @Test
    public void getLowValueCityFalseTest() {
        assertNotEquals(1.2, ValueUtility.getLowValueCity(), 0.0);
    }

    @Test
    public void getVeryLowValueCityTest() {
        assertEquals(1.2, ValueUtility.getVeryLowValueCity(), 0.0);
    }

    @Test
    public void getVeryLowValueCityFalseTest() {
        assertNotEquals(1.1, ValueUtility.getVeryLowValueCity(), 0.0);
    }
}