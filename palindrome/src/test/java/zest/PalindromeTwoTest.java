package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTwoTest {

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
    void testSingleDigit(){
        boolean result = PalindromeTwo.isPalindrome(9);
        assertTrue(result);
    }

    @Test
    void testNegativeInteger(){
        boolean result = PalindromeTwo.isPalindrome(-1221);
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
    void testMinNegativeInteger(){

        assertThrows(IllegalArgumentException.class,() -> {
            PalindromeTwo.isPalindrome(-1048577);
        });

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
    void testModulus11LessThan100_2(){
        boolean result = PalindromeTwo.isPalindrome(77);
        assertTrue(result);
    }

    @Test
    void testNotModulus11LessThan1000(){
        boolean result = PalindromeTwo.isPalindrome(981);
        assertFalse(result);
    }

    @Test
    void testModulus11LessThan1000(){
        boolean result = PalindromeTwo.isPalindrome(737);
        assertTrue(result);
    }

}