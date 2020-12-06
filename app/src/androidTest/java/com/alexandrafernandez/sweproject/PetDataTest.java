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
}