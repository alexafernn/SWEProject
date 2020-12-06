package com.alexandrafernandez.sweproject;

import junit.framework.TestCase;

public class PetDataTest extends TestCase {

    /**
     * General Test
     */
    public void testTestToString() {
        PetData petData = new PetData("myPet", "26246fg4632r63");
        assertEquals("PetData toString Failed", petData.toString(), "myPet");
    }

    /**
     * False Test
     */
    public void testTestToString2() {
        PetData petData = new PetData("myPet2", "26246fg4632r63");
        assertFalse("PetData toString Failed", petData.toString().equals("myPet3"));
    }

    /**
     * Null Test
     */
    public void testTestToString3() {
        PetData petData = new PetData(null, "26246fg4632r63");
        assertNull("PetData toString Failed", petData.toString());
    }

    /**
     * Not Null test
     */
    public void testTestToString4() {
        PetData petData = new PetData("", "26246fg4632r63");
        assertNotNull("PetData toString Failed", petData.toString());
    }

    /**
     * General Test
     */
    public void testTestGetID() {
        PetData petData = new PetData("myPet", "26246fg4632r63");
        assertEquals("PetData getID Failed", petData.getId(), "26246fg4632r63");
    }

    /**
     * False Test
     */
    public void testTestGetID2() {
        PetData petData = new PetData("myPet2", "26246fg4632r63");
        assertFalse("PetData getID Failed", petData.getId().equals("66246fg3612r71"));
    }

    /**
     * Null Test
     */
    public void testTestGetID3() {
        PetData petData = new PetData("myPet3", null);
        assertNull("PetData getID Failed", petData.getId());
    }

    /**
     * Not Null test
     */
    public void testTestGetID4() {
        PetData petData = new PetData("myPet3", "");
        assertNotNull("PetData getID Failed", petData.getId());
    }

    /**
     * Combo: Null test
     */
    public void testTestCombo() {
        PetData petData = new PetData(null, null);
        assertTrue(petData.name == null && petData.id == null);
    }

    /**
     * Combo: Not Null test
     */
    public void testTestCombo2() {
        PetData petData = new PetData("", "");
        assertTrue(petData.name != null && petData.id != null);
    }

    /**
     * Combo: General test
     */
    public void testTestCombo3() {
        PetData petData = new PetData("myPet", "26246fg4632r63");
        assertTrue(petData.name.equals("myPet") && petData.id.equals("26246fg4632r63"));
    }

    /**
     * Combo: False test
     */
    public void testTestCombo4() {
        PetData petData = new PetData("myPet", "26246fg4632r63");
        assertFalse(petData.name.equals("myPet2") && petData.id.equals("2624badID3"));
    }
}