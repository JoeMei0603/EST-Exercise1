package zest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClimbingStairsTest {

    @Test
    void climbStairsOnPointTest(){
        long result = ClimbingStairs.climbStairs(2);

        Assertions.assertEquals(2, result);
    }

    @Test
    void climbStairsOffPointTest(){
        long result = ClimbingStairs.climbStairs(3);

        Assertions.assertEquals(3, result);
    }

}