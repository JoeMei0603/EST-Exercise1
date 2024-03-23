package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTwoTest {



    @Test
    void testSingleDigit(){
        boolean result = PalindromeTwo.isPalindrome(1);
        assertTrue(result);
    }

    @Test
    void testNegativeInteger(){
        boolean result = PalindromeTwo.isPalindrome(-1);
        assertFalse(result);
    }

    @Test
    void testZero(){
        boolean result = PalindromeTwo.isPalindrome(0);
        assertFalse(result);
    }

    @Test
    void testSimplePalindrome(){
        boolean result = PalindromeTwo.isPalindrome(1221);
        assertTrue(result);
    }

    @Test
    void testSimpleNotPalindrome(){
        boolean result = PalindromeTwo.isPalindrome(1223);
        assertFalse(result);
    }



    @Test
    void testPositivePalindrome(){
        boolean result = PalindromeTwo.isPalindrome(+3443);
        assertTrue(result);
    }

    @Test
    void testMaxPositiveInteger(){
        assertThrows(IllegalArgumentException.class,() -> {
            PalindromeTwo.isPalindrome(1048576);
        });
        }


    @Test
    void testMinNegativeIntegerThrowException(){

        assertThrows(IllegalArgumentException.class,() -> {
            PalindromeTwo.isPalindrome(-1048577);
        });

    }

    @Test
    void testMinNegativeInteger(){

        boolean result = PalindromeTwo.isPalindrome(-1048576);
        assertFalse(result);

    }

    @Test
    void testModulus10(){
        boolean result = PalindromeTwo.isPalindrome(1010);
        assertFalse(result);
    }

    @Test
    void testModulus11LessThan100(){
        boolean result = PalindromeTwo.isPalindrome(11);
        assertTrue(result);
    }

    @Test
    void testNotModulus11LessThan100(){
        boolean result = PalindromeTwo.isPalindrome(91);
        assertFalse(result);
    }


    @Test
    void testModulus11LessThan1000(){
        boolean result = PalindromeTwo.isPalindrome(737);
        assertTrue(result);
    }
    @Test
    void testNotModulus11LessThan1000(){
        boolean result = PalindromeTwo.isPalindrome(981);
        assertFalse(result);
    }

    @Test
    void testModulus11GreaterThan1000(){
        boolean result = PalindromeTwo.isPalindrome(1012);
        assertFalse(result);
    }

    @Test
    void testNotModulus11GreaterThan1000(){
        boolean result = PalindromeTwo.isPalindrome(10201);
        assertTrue(result);
    }


}