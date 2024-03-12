package zest;
import org.junit.jupiter.api.*;

class MyAtoiTest {

    @Test
    void testStrIsNull() {
        int result = MyAtoi.myAtoi(null);
        Assertions.assertEquals(0, result);
    }

    @Test
    void testStrIsEmpty() {
        int result = MyAtoi.myAtoi("");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testStrLeadingWhiteSpace() {
        int resultEmptyString = MyAtoi.myAtoi(" ");
        int resultWithNumber = MyAtoi.myAtoi(" 2");
        int resultWithChars = MyAtoi.myAtoi(" abc");

        Assertions.assertEquals(0, resultEmptyString);
        Assertions.assertEquals(2, resultWithNumber);
        Assertions.assertEquals(0, resultWithChars);
    }

    @Test
    void testPositiveResult() {
        int resultWithPlus = MyAtoi.myAtoi(" +51");
        int resultWithoutPlus = MyAtoi.myAtoi("51");
        int resultWithPlusAtEnd = MyAtoi.myAtoi("51+");
        int resultWithMinusAtEnd = MyAtoi.myAtoi("51-");

        Assertions.assertEquals(51, resultWithPlus);
        Assertions.assertEquals(51, resultWithoutPlus);
        Assertions.assertEquals(51, resultWithPlusAtEnd);
        Assertions.assertEquals(51, resultWithMinusAtEnd);
    }

    @Test
    void testNegativeResult() {
        int resultWithMinus = MyAtoi.myAtoi(" -51");

        Assertions.assertEquals(-51, resultWithMinus);
    }

    @Test
    void testStrReading() {
        int resultMixedTypes = MyAtoi.myAtoi("95a34");
        int resultSpaceInBetween = MyAtoi.myAtoi("95 34");

        Assertions.assertEquals(95, resultMixedTypes);
        Assertions.assertEquals(95, resultSpaceInBetween);
    }

    @Test
    void testStrWithZeros() {
        int resultZeroInFront = MyAtoi.myAtoi("0057");
        int resultZeroInBetween = MyAtoi.myAtoi("570068");
        int resultZeroAndChar = MyAtoi.myAtoi("5700ab");

        Assertions.assertEquals(57, resultZeroInFront);
        Assertions.assertEquals(570068, resultZeroInBetween);
        Assertions.assertEquals(5700, resultZeroAndChar);
    }

    @Test
    void testIntTooLarge() {
        // 2147483647 is 2^31-1
        int result = MyAtoi.myAtoi("2147483648");

        Assertions.assertEquals(2147483647, result);
    }

    @Test
    void testIntTooSmall() {
        // -2147483648 is -2^31
        int result = MyAtoi.myAtoi("-2147483649");

        Assertions.assertEquals(-2147483648, result);
    }
}