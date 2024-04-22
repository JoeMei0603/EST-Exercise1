# Documentation Group 22

## Exercise 7: Sum Of Two Integers
Problem: Calculate the sum of two integers without using the operators + and -

### Task 1: Code Coverage
We first did an analysis on the code and to get 100% line coverage and branch coverage for this solution, only 1 test was required but we decided to devise and 
add few more test cases for negative integers and 0 as input. These tests allowed us to have a full line and branch coverage.

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

The **invariant** was that a correct output should be generated. Upon further investigation we found that when input `a` or `b` was close to **MAX** or **MIN** 
value of 32-bit signed integer, the result was getting calculated correctly and it was getting overflowed and underflowed.
To solve the issue and make sure that solution was holding the **invariant**, we changed the function  return type from `int` to `long`.
This change ensured that the **invariant** was now holding and the output was getting calculated correctly.
Along with the function return type, we also changed the function parameter type from `int` to `long`, in order to ensure integrity of the solution and to do proper testing.
The **pre-condition** was still there and we were verifying that the provided input is not greater than or less than the range of 32-bit signed integer.

### Task 3: Testing Contracts
Having introduced the **pre-condition** checks, which could not be reached because an integer larger than 32-bit could not be passed into the function. 
But we were able to test it and the code was reachable after we changed the parameter type to `long`.
After having add these **pre-condition** and **post-condition** test, our line coverage was at 100%.
The **post-condition** tests were getting passed and solution was successfully getting executed. Similarly, the solution was holding the 
**invariant** and the output was not getting overflowed or underflowed.

### Task 4: Property-Based Testing
Property-based testing involves verifying that certain properties hold true for a given piece of code across a
wide range of inputs. For the "SumofTwoIntegers" problem, we can identify two different properties that should
hold true for any inputs:

- **Base Case:** Addition of any two integers should correctly calculate their output using bitwise operators.
- **Invalid Case:** The solution should throw an error if either one or both of input `a` or `b` is out of the range of 32-bit signed integer.


Upon creating and running this property based test, jqwik reported that the test passed and it was successful. No edge-cases were identified in the solution.
