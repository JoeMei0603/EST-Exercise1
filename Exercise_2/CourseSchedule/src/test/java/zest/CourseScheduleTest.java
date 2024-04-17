package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Negative;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseScheduleTest {

    @Test
    void courseScheduleValidRequirementTest() {
        boolean result10 = CourseSchedule.canFinish(2, new int[][] {{1,0}});
        boolean result01 = CourseSchedule.canFinish(2, new int[][] {{0,1}});

        Assertions.assertTrue(result10);
        Assertions.assertTrue(result01);
    }

    @Test
    void courseScheduleCycleDependencyTest() {
        boolean result = CourseSchedule.canFinish(2, new int[][] {{1,0},{0,1}});

        Assertions.assertFalse(result);
    }

    @Test
    void courseScheduleNumCoursesOnPointTest() {
        boolean result = CourseSchedule.canFinish(0, new int[][] {});

        Assertions.assertTrue(result);
    }

    @Test
    void courseScheduleNumCoursesOffPointTest() {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(-1, new int[][] {{1,0}}));
    }

    @Test
    void courseScheduleEmptyArrayOnPointTest() {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(0, new int[][] {{}}));
    }

    @Test
    void courseSchedulePrerequisiteItselfTest() {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(2, new int[][] {{0, 0}}));
    }

    @Test
    void courseSchedulePrerequisitesBiggerThanNumCoursesTest() {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(2, new int[][] {{3, 0}}));
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(2, new int[][] {{0, 3}}));
    }

    @Test
    void courseScheduleNegativePrerequisitesTest() {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(2, new int[][] {{-1, 0}}));
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(2, new int[][] {{0, -1}}));
    }

    @Property
    void courseScheduleCanFinishProperty(@ForAll @IntRange(min = 11, max = 100000) int n, @ForAll("validInputs") int[][] arrays) {
        boolean result = CourseSchedule.canFinish(n, arrays);

        assertTrue(result);
    }

    @Property
    void courseScheduleNegativeNumCoursesInvalidProperty(@ForAll @Negative int n) {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(n, new int[][] {{1, 0}}));
    }

    @Property
    void courseScheduleNegativeCoursesInPrerequisiteInvalidProperty(@ForAll("invalidInputsNegative") int[][] arrays) {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(2, arrays));
    }

    @Property
    void courseSchedulePrerequisiteLargerThanNumCoursesProperty(@ForAll("invalidInputsPrerequisitesLargerThanNumCourses") int[][] arrays) {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(5, arrays));
    }

    @Property
    void courseSchedulePrerequisiteItselfProperty(@ForAll("invalidInputsPrerequisiteItself") int[][] arrays) {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(5, arrays));
    }

    @Property
    void courseScheduleEmptyArrayCaseProperty(@ForAll("randomListOfEmptyArrays") int[][] arrays) {
        assertThrows(IllegalArgumentException.class, () -> CourseSchedule.canFinish(0, arrays));
    }



    @Provide
    private Arbitrary<int[][]> validInputs() {
        return Arbitraries.integers().between(1, 5)
                .flatMap(size -> {
                    Stack<Integer> stack = new Stack<>();
                    for (int i = 0; i < 10; i++) {
                        stack.push(i);
                    }
                    Collections.shuffle(stack);
                    int[][] arrays = new int[size][2];
                    for (int i = 0; i < size; i++) {
                        arrays[i][0] = stack.pop();
                        arrays[i][1] = stack.pop();
                    }
                    return Arbitraries.just(arrays);
                });
    }

    @Provide
    private Arbitrary<int[][]> invalidInputsNegative() {
        return Arbitraries.integers().between(1, 5)
                .flatMap(size -> {
                    int[][] arrays = new int[size][2];
                    for (int i = 0; i < size; i++) {
                        arrays[i][0] = Arbitraries.integers().lessOrEqual(0).sample();
                        arrays[i][1] = Arbitraries.integers().lessOrEqual(0).sample();
                    }
                    return Arbitraries.just(arrays);
                });
    }

    @Provide
    private Arbitrary<int[][]> invalidInputsPrerequisitesLargerThanNumCourses() {
        return Arbitraries.integers().between(1, 5)
                .flatMap(size -> {
                    int[][] arrays = new int[size][2];
                    for (int i = 0; i < size; i++) {
                        arrays[i][0] = Arbitraries.integers().greaterOrEqual(6).sample();
                        arrays[i][1] = Arbitraries.integers().greaterOrEqual(6).sample();
                    }
                    return Arbitraries.just(arrays);
                });
    }

    @Provide
    private Arbitrary<int[][]> invalidInputsPrerequisiteItself() {
        return Arbitraries.integers().between(1, 5)
                .flatMap(size -> {
                    int[][] arrays = new int[size][2];
                    for (int i = 0; i < size; i++) {
                        int n = Arbitraries.integers().lessOrEqual(5).sample();
                        arrays[i][0] = n;
                        arrays[i][1] = n;
                    }
                    return Arbitraries.just(arrays);
                });
    }

    @Provide
    private Arbitrary<int[][]> randomListOfEmptyArrays() {
        return Arbitraries.integers().between(1, 10)
                .flatMap(size -> {
                    int[][] arrays = new int[size][];
                    Arrays.fill(arrays, new int[0]);
                    return Arbitraries.just(arrays);
                });
    }


}
