package com.alexandrafernandez.sweproject;

import junit.framework.TestCase;

public class SitterAvailabilityDataTest extends TestCase {

    /**
     * General Test
     */
    public void testTestToString() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertEquals("SitterAvailabilityData toString Failed", data.toString(), "12/6/2020 9:00 - 12/7/2020 10:00");
    }

    /**
     * Test Same time
     */
    public void testTestToString2() {
        SitterAvailabilityData data  = new SitterAvailabilityData("12/6/2020 9:00", "12/6/2020 9:00", "26246fg4632r63");
        assertEquals("SitterAvailabilityData toString Failed", data.toString(),"12/6/2020 9:00 - 12/6/2020 9:00");
    }

    /**
     * False Test
     */
    public void testTestToString3() {
        SitterAvailabilityData data  = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertFalse("SitterAvailabilityData toString Failed", data.toString().equals("12/6/2020 9:00 - 12/8/2020 11:00"));
    }

    /**
     * Not Null test
     */
    public void testTestToString5() {
        SitterAvailabilityData data = new SitterAvailabilityData(null, null, null);
        assertNotNull("SitterAvailabilityData toString Failed", data.toString());
    }

    /**
     * General Test
     */
    public void testTestGetID() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertEquals("NeedSitterEventData getID Failed", data.getNotes(), "26246fg4632r63");
    }

    /**
     * False Test
     */
    public void testTestGetID2() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertFalse("NeedSitterEventData getID Failed", data.getNotes().equals("66246fg3612r71"));
    }

    /**
     * Null Test
     */
    public void testTestGetID3() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", null);
        assertNull("NeedSitterEventData getID Failed", data.getNotes());
    }

    /**
     * Not Null test
     */
    public void testTestGetID4() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "");
        assertNotNull("NeedSitterEventData getID Failed", data.getNotes());
    }

    /**
     * Combo: Null test
     */
    public void testTestCombo() {
        SitterAvailabilityData data = new SitterAvailabilityData(null, null, null);
        assertTrue(data.startDate == null && data.endDate  == null && data.notes == null);
    }

    /**
     * Combo: Not Null test
     */
    public void testTestCombo2() {
        SitterAvailabilityData data = new SitterAvailabilityData("", "", "");
        assertTrue(data.startDate  != null && data.endDate  != null && data.notes != null);
    }

    /**
     * Combo: General test
     */
    public void testTestCombo3() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "ds4klj2432n4l6n42ds");
        assertTrue(data.startDate.equals("12/6/2020 9:00") && data.endDate.equals("12/7/2020 10:00") && data.notes.equals("ds4klj2432n4l6n42ds"));
    }

    /**
     * Combo: False test
     */
    public void testTestCombo4() {
        SitterAvailabilityData data = new SitterAvailabilityData("12/6/2020 9:00", "12/7/2020 10:00", "ds4klj2432n4l6n42ds");
        assertFalse(data.startDate.equals("12/4/2020 9:00") && data.startDate.equals("12/2/2020 10:00") && data.notes.equals("ds4kljbadIDn42ds"));
    }
}