package com.alexandrafernandez.sweproject;

import junit.framework.TestCase;

public class OwnerSittingDataTest extends TestCase
{
    /**
     * General Tests
     */

    public void testTestToString() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        String toString = ownerSittingData.sitterName;
        assertTrue(toString.contains("Nicole"));
    }

    public void testTestToString2() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        String toString = ownerSittingData.startDateTime;
        assertTrue(toString.contains("12/20/2020 9:00"));
    }

    public void testTestToString3() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        String toString = ownerSittingData.endDateTime;
        assertTrue(toString.contains("12/20/2020 10:00"));
    }

    public void testTestToString4() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        String toString = ownerSittingData.endDateTime;
        assertFalse(toString.contains("12/20/2020 11:00"));
    }

    public void testTestToString5() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        String toString = ownerSittingData.startDateTime;
        assertFalse(toString.contains("12/20/2020 10:00"));
    }

    public void testTestToString6() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        String toString = ownerSittingData.sitterName;
        assertFalse(toString.contains("Amanda"));
    }

    public void testTestToString7() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertNotNull("ownerSittingData toString Failed", ownerSittingData.toString());
    }

    /**
     * False Test
     */
    public void testTestGetName() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertFalse("ownerSiting getID Failed", ownerSittingData.sitterName.equals("Amanda"));
    }

    /**
     * False Test
     */
    public void testTestGetStart() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertFalse("ownerSitting getID Failed", ownerSittingData.startDateTime.equals("12/21/2020 9:00"));
    }

    /**
     * False Test
     */
    public void testTestGetEnd() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertFalse("ownerSitting getID Failed", ownerSittingData.endDateTime.equals("12/21/2020 9:00"));
    }

    /**
     * True test
     */
    public void testTestGetNameTrue() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertTrue("ownerSiting getID Failed", ownerSittingData.sitterName.equals("Nicole"));
    }

    /**
     * True test
     */
    public void testTestGetStartTrue() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertTrue("ownerSiting getID Failed", ownerSittingData.startDateTime.equals("12/20/2020 9:00"));
    }

    /**
     * True test
     */
    public void testTestGetEndTrue() {
        OwnerSittingData ownerSittingData = new OwnerSittingData("1", "12/20/2020 9:00", "12/20/2020 10:00", true, "Nicole");
        assertTrue("ownerSiting getID Failed", ownerSittingData.endDateTime.equals("12/20/2020 10:00"));
    }
}
