package com.alexandrafernandez.sweproject;

import junit.framework.TestCase;

public class OwnerSittingDataTest extends TestCase
{
    /**
     * General Test
     */
    public void testTestToString() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 10:00", "12/21/2020 12:00", true, "test");
        assertEquals("ownerSittingData toString Failed", ownerSittingData.toString(), "Start:  " + "12/20/2020 10:00" + "\nEnd:    " + "12/21/2020 12:00" + "\nSitter: " + "test");

    }
}
