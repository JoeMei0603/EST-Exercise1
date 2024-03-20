package zest;
import org.junit.jupiter.api.*;

import java.util.Collections;


class MedianOfArraysTest {

    @Test
    void testNullArray() {
        int[] arrayOne = {1, 2};
        MedianOfArrays classInstance = new MedianOfArrays();
        double resultSecondArrayNull = classInstance.findMedianSortedArrays(arrayOne, null);
        double resultFirstArrayNull = classInstance.findMedianSortedArrays(null, arrayOne);


        Assertions.assertEquals(0, resultFirstArrayNull);
        Assertions.assertEquals(0, resultSecondArrayNull);
    }

    @Test
    void testTwoEmtpyArrays() {
        int[] emptyArray = {};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(emptyArray, emptyArray);

        Assertions.assertEquals(-1, result);
    }

    @Test
    void testOneEmptyArray() {
        int[] arrayOne = {1, 2, 3};
        int[] emptyArray = {};
        MedianOfArrays classInstance = new MedianOfArrays();
        double resultFirstArrayEmpty = classInstance.findMedianSortedArrays(emptyArray, arrayOne);
        double resultSecondArrayEmpty = classInstance.findMedianSortedArrays(arrayOne, emptyArray);

        Assertions.assertEquals(2, resultFirstArrayEmpty);
        Assertions.assertEquals(2, resultSecondArrayEmpty);
    }
    @Test
    void testNotSorted() {
        int[] arrayNotSorted = {2, 1};
        int[] arraySorted = {3, 4};
        MedianOfArrays classInstance = new MedianOfArrays();
        double resultFirstArrayNotSorted = classInstance.findMedianSortedArrays(arrayNotSorted, arraySorted);
        double resultSecondArrayNotSorted = classInstance.findMedianSortedArrays(arraySorted, arrayNotSorted);

        Assertions.assertEquals(0, resultFirstArrayNotSorted);
        Assertions.assertEquals(0, resultSecondArrayNotSorted);
    }

    @Test
    void testNonDistinctInt() {
        int[] arrayNonDistinct = {2, 2, 2};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayNonDistinct, arrayNonDistinct);

        // Failed at first, fixed in MedianOfArrays class
        Assertions.assertEquals(2.0, result);
    }

    @Test
    void testEvenArrayLength() {
        int[] arrayOne = {1, 2, 3, 4};
        int[] arrayTwo = {5, 6, 7, 8};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayOne, arrayTwo);

        // Failed at first, fixed in MedianOfArrays class
        Assertions.assertEquals(4.5, result);
    }

    @Test
    void testOddArrayLength() {
        int[] arrayOne = {1, 2, 3};
        int[] arrayTwo = {4, 5, 6};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayOne, arrayTwo);

        // Failed at first, fixed in MedianOfArrays class
        Assertions.assertEquals(3.5, result);
    }

    @Test
    void testArrayOneElement() {
        int[] arrayOne = {4};
        int[] arrayTwo = {2, 3};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayOne, arrayTwo);

        Assertions.assertEquals(3.0, result);
    }

    @Test
    void testArrayOneAndEmpty() {
        int[] arrayOne = {1};
        int[] arrayEmpty = {};
        MedianOfArrays classInstance = new MedianOfArrays();
        double resultFirstArrayEmpty = classInstance.findMedianSortedArrays(arrayOne, arrayEmpty);
        double resultSecondArrayEmpty = classInstance.findMedianSortedArrays(arrayEmpty, arrayOne);

        Assertions.assertEquals(1, resultFirstArrayEmpty);
        Assertions.assertEquals(1, resultSecondArrayEmpty);
    }

    @Test
    void testNegativeNumbers() {
        int[] arrayOne = {-3, -2, -1};
        int[] arrayTwo = {-3, -1};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayOne, arrayTwo);

        Assertions.assertEquals(-2.0, result);
    }

    @Test
    void testNegativeNumbersNotSorted() {
        int[] arrayOne = {-1, -2, -3};
        int[] arrayTwo = {-3, -1};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayOne, arrayTwo);

        Assertions.assertEquals(0, result);
    }
    @Test
    void testMixedNumbers() {
        int[] arrayOne = {-10, 1, 10};
        int[] arrayTwo = {-3, 3};
        MedianOfArrays classInstance = new MedianOfArrays();
        double result = classInstance.findMedianSortedArrays(arrayOne, arrayTwo);

        Assertions.assertEquals(1, result);
    }
}