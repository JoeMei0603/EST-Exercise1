# Documentation Group 22

## Exercise 1: Climbing Stairs
The method would have required to create an object in order to be testable.
For this reason, the method had to be changed to be static and is now accessible without an object.

### Task 1: Code Coverage
Analyzing the code reveals that a boundary exists between the input 2 and 3.
Based on this, one `on-point` (2) and one `off-point` (3) can be devised, resulting in a total of two tests.
Running those tests reveals that already 100% line coverage is achieved. No further augmentation with (creative) testcases is needed.

### Task 2: Designing Contracts
Understanding the requirements and their constraints leads to the pre- & post-condition, whereas understanding the code leads to the invariants.
The given integer `n` must be positive and the returned value `allWays` must also be positive.
Furthermore, `allWays` gets recalculated with each execution of the loop. For the **invariant** to hold, `allWays` must be positive for each iteration of the loop too, not only at the end for the post-condition.

Using the now defined contracts leads to their implementation.
The function only allows for positive integers as input, which means that the IDE already recognizes any input that is not an integer and won't allow the user to run the program.
To enforce the **pre-condition** even stronger and therefore ensure a positive given integer `n`, a simple check of `n < 0` will throw an IllegalArgumentException if `n` happens to be smaller than 0.
The **post-condition** can be checked in a similar way with `allWays < 0`, where a RunTimeException would be thrown if this check does not hold.
To ensure the **invariant** holds, the check is the same as the **post-condition**, throwing a RunTimeException otherwise.
This check can be appended as the last step of the loop, since the following iterations will continue with the previously checked value.
Since the **post-condition** check directly follows the **invariant** check of the last iteration, the IDE already recognizes that the condition would always be false.
Therefore, the **post-condition** would always hold and the explicit **post-condition** check is omitted.

### Task 3: Testing Contracts
Having introduced the **pre-condition** check, there is a new boundary between `-1` and `0`.
Based on this, there is a new `on-point` (0) and a new `off-point` (-1).
Creating and running tests for them reveals that one line/one branch is not tested.
However, it won't be possible to create a test for the untested line, as it is not possible to create a negative value with the initial definition of `allWays` (0) and the subsequent iterative calculations on it (only additions).

### Task 4: Property-Based Testing
For the Property-Based Testing, three properties have been identified.
- **Base Cases:** Integers from 0 to 2 (inclusive)
- **Recursive Relation:** Integers greater than 2
- **Invalid Cases:** Negative Integers

Upon creating and running those tests, jqwik reported that something went wrong with the **Recursive Relation** property test.
Investigating the error message revealed that the previously created **post-condition** and **invariant** check did not hold as `allWays` became a negative integer.
Using any integer greater than 91 for `n` results in an overflow, meaning that the long cannot hold the result of the calculation without wrapping around to a negative value.
Based on this, a new boundary between `91` and `92` has been identified.
With this new boundary, a further **pre-condition** was added to the code, throwing an IllegalArgumentException if `n` is greater than 91.
Then, the **Recursive Relation** property test has been adjusted to only include integers from 3 to 91 (inclusive).
Additionally, the **Invalid Cases** property test has been adjusted to also include integers greater than 91.

Now an edge-case got found that would allow to test for an **invariant** that does not hold.
However, since we cover that case through a **pre-condition** check, the `allWays` variable can no longer become negative again.