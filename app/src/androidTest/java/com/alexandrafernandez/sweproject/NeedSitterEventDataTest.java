package com.alexandrafernandez.sweproject;

import junit.framework.TestCase;

public class NeedSitterEventDataTest extends TestCase {

    /**
     * General Test
     */
    public void testTestToString() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertEquals("NeedSitterEventData toString Failed", data.toString(), "12/6/2020 9:00 - 12/7/2020 10:00");
    }

    /**
     * Test Same time
     */
    public void testTestToString2() {
        NeedSitterEventData data  = new NeedSitterEventData("12/6/2020 9:00", "12/6/2020 9:00", "26246fg4632r63");
        assertEquals("NeedSitterEventData toString Failed", data.toString(),"12/6/2020 9:00 - 12/6/2020 9:00");
    }

    /**
     * False Test
     */
    public void testTestToString3() {
        NeedSitterEventData data  = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertFalse("NeedSitterEventData toString Failed", data.toString().equals("12/6/2020 9:00 - 12/8/2020 11:00"));
    }

    /**
     * Not Null test
     */
    public void testTestToString5() {
        NeedSitterEventData data = new NeedSitterEventData(null, null, null);
        assertNotNull("NeedSitterEventData toString Failed", data.toString());
    }

    /**
     * General Test
     */
    public void testTestGetID() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertEquals("NeedSitterEventData getID Failed", data.getId(), "26246fg4632r63");
    }

    /**
     * False Test
     */
    public void testTestGetID2() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "26246fg4632r63");
        assertFalse("NeedSitterEventData getID Failed", data.getId().equals("66246fg3612r71"));
    }

    /**
     * Null Test
     */
    public void testTestGetID3() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", null);
        assertNull("NeedSitterEventData getID Failed", data.getId());
    }

    /**
     * Not Null test
     */
    public void testTestGetID4() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "");
        assertNotNull("NeedSitterEventData getID Failed", data.getId());
    }

    /**
     * Combo: Null test
     */
    public void testTestCombo() {
        NeedSitterEventData data = new NeedSitterEventData(null, null, null);
        assertTrue(data.start_dateTime == null && data.end_dateTime == null && data.id == null);
    }

    /**
     * Combo: Not Null test
     */
    public void testTestCombo2() {
        NeedSitterEventData data = new NeedSitterEventData("", "", "");
        assertTrue(data.start_dateTime != null && data.end_dateTime != null && data.id != null);
    }

    /**
     * Combo: General test
     */
    public void testTestCombo3() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "ds4klj2432n4l6n42ds");
        assertTrue(data.start_dateTime.equals("12/6/2020 9:00") && data.end_dateTime.equals("12/7/2020 10:00") && data.id.equals("ds4klj2432n4l6n42ds"));
    }

    /**
     * Combo: False test
     */
    public void testTestCombo4() {
        NeedSitterEventData data = new NeedSitterEventData("12/6/2020 9:00", "12/7/2020 10:00", "ds4klj2432n4l6n42ds");
        assertFalse(data.start_dateTime.equals("12/4/2020 9:00") && data.end_dateTime.equals("12/2/2020 10:00") && data.id.equals("ds4kljbadIDn42ds"));
    }
}