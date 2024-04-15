package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClimbingStairsTest {

    @Test
    void climbStairsOnPointTest() {
        long result = ClimbingStairs.climbStairs(2);

        Assertions.assertEquals(2, result);
    }

    @Test
    void climbStairsOffPointTest() {
        long result = ClimbingStairs.climbStairs(3);

        Assertions.assertEquals(3, result);
    }

    @Test
    void climbStairsOnPointZeroInputTest() {
        long result = ClimbingStairs.climbStairs(0);

        Assertions.assertEquals(0, result);
    }

    @Test
    void climbStairsOffPointNegativeInputTest() {
        assertThrows(IllegalArgumentException.class, () -> ClimbingStairs.climbStairs(-1));
    }

    @Property
    void climbStairsBaseCaseProperty(@ForAll @IntRange(max = 2) int n) {
        long result = ClimbingStairs.climbStairs(n);

        assertEquals(n, result);
    }

    @Property
    void climbStairsRecursiveRelationProperty(@ForAll @IntRange(min = 3, max = 91) int n) {
        long result = ClimbingStairs.climbStairs(n);

        assertEquals(ClimbingStairs.climbStairs(n - 1) + ClimbingStairs.climbStairs(n - 2), result);
    }

    @Property
    void climbStairsInvalidCaseProperty(@ForAll("invalidArguments") int n) {
        assertThrows(IllegalArgumentException.class, () -> ClimbingStairs.climbStairs(n));
    }

    @Provide
    private Arbitrary<Integer> invalidArguments() {
        return Arbitraries.oneOf(
                Arbitraries.integers().lessOrEqual(-1),
                Arbitraries.integers().greaterOrEqual(92)
        );
    }
}