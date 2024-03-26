package zest;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class NeedleInHayTest {

    @Test
    void testSimpleNeedleAndHay(){
        int result = NeedleInHay.find("effectiveSoftwareTesting","Software");
        assertEquals(9,result);
    }

    @Test
    void testNeedleInBeginning(){
        int result = NeedleInHay.find("effectiveSoftwareTesting","effective");
        assertEquals(0,result);
    }

    @Test
    void testNeedleAtEnd(){
        int result = NeedleInHay.find("effectiveSoftwareTesting","Testing");
        assertEquals(17,result);
    }

    @Test
    void testNeedleNotPresent(){
        int result = NeedleInHay.find("effectiveSoftwareTesting","Needle");
        assertEquals(-1,result);
    }

    @Test
    void testNullNeedle(){
        int result = NeedleInHay.find("effectiveSoftwareTesting",null);
        assertEquals(-1,result);
    }

    @Test
    void testNullHaystack(){
        int result = NeedleInHay.find(null,"Needle");
        assertEquals(-1,result);
    }

    @Test
    void testEmptyNeedleAndHaystack(){
        int result = NeedleInHay.find("","");
        assertEquals(0,result);
    }

    @Test
    void testNeedleAsNumber(){
        int result = NeedleInHay.find("course121","121");
        assertEquals(6,result);
    }


    @Test
    void testCaseSensitiveNeedleAndHaystack(){
        int result = NeedleInHay.find("NeedleAndHAYSTACK","haystack");
        assertEquals(-1,result);
    }

    @Test
    void testLetterAndNumbersAsNeedle(){
        int result = NeedleInHay.find("Haystack_needle121","needle121");
        assertEquals(9,result);
    }


    @Test
    void testNeedleContainsSpecialChar(){
        int result = NeedleInHay.find("Hay_needle_333","needle_333");
        assertEquals(4,result);
    }

    @Test
    void testNeedleContainsSpecialChar_2(){
        int result = NeedleInHay.find("Hay_n33d!e","n33d!e");
        assertEquals(4,result);
    }


    @Test
    void testEmptyNeedle(){
        int result = NeedleInHay.find("effectiveSoftwareTesting","");
        assertEquals(0,result);
    }


    @Test
    void testEmptyHaystack(){
        int result = NeedleInHay.find("","Needle");
        assertEquals(0,result);
    }


    @Test
    void testNeedleBiggerThanHaystack(){
        int result = NeedleInHay.find("Needle","effectiveSoftwareTesting");
        assertEquals(-1,result);
    }
    @Test
    void testNeedleIsCorrectHalfway(){
        int result = NeedleInHay.find("Needle","Neet");
        assertEquals(-1,result);
    }

    @Test
    void testNeedleIsCorrectHalfway_2(){
        int result = NeedleInHay.find("Needle","Need!e");
        assertEquals(-1,result);
    }


}