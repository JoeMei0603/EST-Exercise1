package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.LongRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class uniquePathsTest {

    @Test
    void testUniquePaths() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        int result = testUniquePathsClass.uniquePaths(2,3);

        Assertions.assertEquals(3, result);
    }

    @Test
    void testUniquePathsOneRowsColumns() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        int result = testUniquePathsClass.uniquePaths(1,1);

        Assertions.assertEquals(1, result);
    }

    @Test
    void testUniquePathsOneRowMultipleColumns() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        int result = testUniquePathsClass.uniquePaths(1,6);

        Assertions.assertEquals(1, result);
    }

    @Test
    void testUniquePathsOneColumnMultipleRows() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        int result = testUniquePathsClass.uniquePaths(8,1);

        Assertions.assertEquals(1, result);
    }

    @Test
    void testUniquePathsZeroRows() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(0,2));
    }

    @Test
    void testUniquePathsZeroColumns() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(2,0));
    }

    @Test
    void testUniquePathsZeroRowsAndColumns() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(0,0));
    }


    @Test
    void testUniquePathsAboveHundredRows() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(101,10));
    }


    @Test
    void testUniquePathsAboveHundredColumns() {
        UniquePaths testUniquePathsClass = new UniquePaths();
        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(4,101));
    }



    // The property does not have the minimum as '1' because the output will be 1 if
    // either row or column will be 1. And this would also break the recursive property
    @Property
    void testUniquePathsRecursiveProperty(@ForAll @IntRange(min = 2, max = 17) int m,@ForAll @IntRange(min = 2, max = 17) int n) {

        UniquePaths testUniquePathsClass = new UniquePaths();
        int result = testUniquePathsClass.uniquePaths(m,n);

        assertEquals(testUniquePathsClass.uniquePaths(m-1,n) + testUniquePathsClass.uniquePaths(m,n-1), result);
    }

    @Property
    void testUniqueInvalidProperty(@ForAll("invalidArguments") int m,@ForAll("invalidArguments") int n) {

        UniquePaths testUniquePathsClass = new UniquePaths();

        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(m,n));
    }

    @Property
    void testUniqueInvalidRowsProperty(@ForAll("invalidArguments") int m,@ForAll @IntRange(min = 2, max = 17) int n) {

        UniquePaths testUniquePathsClass = new UniquePaths();

        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(m,n));
    }

    @Property
    void testUniqueInvalidColumnsProperty(@ForAll @IntRange(min = 2, max = 17) int m,@ForAll("invalidArguments") int n) {

        UniquePaths testUniquePathsClass = new UniquePaths();

        assertThrows(IllegalArgumentException.class, () -> testUniquePathsClass.uniquePaths(m,n));
    }

    @Provide
    private Arbitrary<Integer> invalidArguments() {
        return Arbitraries.oneOf(
                Arbitraries.integers().lessOrEqual(0),
                Arbitraries.integers().greaterOrEqual(101)
        );
    }

 
}