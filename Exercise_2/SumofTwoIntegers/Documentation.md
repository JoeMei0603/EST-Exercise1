# Documentation Group 22

## Exercise 7: Sum Of Two Integers
Problem: Calculate the sum of two integers without using the operators + and -

### Task 1: Code Coverage
To get 100% code coverage and branch coverage for this solution, only 1 test was required but we decided to add few more test
cases for negative integers and 0 as input. The provided test covered addition of positive integers, negative integers and a 0 as input.

### Task 2: Designing Contracts
Understanding the requirements and their constraints leads to the pre- & post-condition, whereas understanding the code leads to the invariants.
The class SumofTwoIntegers defines the preconditions and post-conditions clearly.
For the **pre-condition**, the input `a` and `b` must be within the 32-bit range.
For the **post-condition**, the solution should work for both positive and negative integers.
For the **invariant**, the output should be a correct addition.

For the implementation of **pre-condition**, it was not implemented but the program would have not accepted `a` or `b` as input which is 
outside the range of 32-bit. And it would have failed to run. But we decided to enforce a stronger **pre-condition** here and throw an illegal Exception 
if any of the input is outside the 32-bit range.
The **post-condition** said that the solution should work for positive and negative integer. This **post-condition** was not specifically implemented in the solution, 
we enforced it by adding an assertion that would be check if the generated sum is correct for all the integers.

The **invariant** was implicitly assumed to be true here and need no further implementation.


### Task 3: Testing Contracts
Having introduced the **pre-condition** checks, which could not be reached because an integer larger than 32-bit could not be passed into the function. 
The line coverage of our test suite decreased to 84%.
The **post-condition** tests were getting passed. In the case where the checks for **pre-conditions** were omitted, the line-coverage 
and branch coverage of our test suite would have been 100%.

### Task 4: Property-Based Testing
Property-based testing involves verifying that certain properties hold true for a given piece of code across a
wide range of inputs. For the "SumofTwoIntegers" problem, we can identify 3 properties that should
hold true for any inputs:

- **Base Case:** Addition of any two integers should correctly calculate their output using bitwise operators.


Upon creating and running this property based test, jqwik reported that the test passed and it was successful. No edge-cases were identified in the solution.
