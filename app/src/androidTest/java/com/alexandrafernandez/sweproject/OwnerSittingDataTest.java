package com.alexandrafernandez.sweproject;

import junit.framework.TestCase;

public class OwnerSittingDataTest extends TestCase
{
    /**
     * General Test
     */
    public void testTestToString() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "0", "12/20/2020 10:00", "12/21/2020 12:00", true);
        assertEquals("ownerSittingData toString Failed", ownerSittingData.toString(), "appointment start: 12/20/2020 10:00 appointment end: 12/21/2020 12:00 location: 0 accepted: true");
    }
}
