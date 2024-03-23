package zest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeOneTest {


    @Test
    void testSimplePalindrome(){
        boolean result= PalindromeOne.isPalindrome(33);
        assertTrue(result);
    }

    @Test
    void testSingleDigit(){
        boolean result= PalindromeOne.isPalindrome(5);
        assertTrue(result);
    }

    @Test
    void testNotPalindrome(){
        boolean result= PalindromeOne.isPalindrome(12);
        assertFalse(result);
    }

    @Test
    void testNegativeInteger(){
        boolean result= PalindromeOne.isPalindrome(-22);
        assertFalse(result);
    }

    @Test
    void testPositiveInteger(){
        boolean result= PalindromeOne.isPalindrome(+11);
        assertTrue(result);
    }


    @Test
    void testPositiveMaxConstraints(){
        assertThrows(IllegalArgumentException.class,() ->{
            PalindromeOne.isPalindrome(1048576);
        });

    }

    @Test
    void testNegativeMinConstraints(){
        assertThrows(IllegalArgumentException.class,() ->{
            PalindromeOne.isPalindrome(-1048577);
        });

    }

    @Test
    void testMinNegativeInteger(){

        boolean result = PalindromeOne.isPalindrome(-1048576);
        assertFalse(result);

    }




}