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
}