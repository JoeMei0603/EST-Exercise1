# Documentation Group 22

### FindDuplicate
IMPORTANT ASSUMPTION: According to the description, there is only one duplicate number, but it could be repeated 
more than once. 

### Task 1: Code Coverage
In this task, the goal is to achieve the highest possible (ideally, 100%) line coverage for the provided 
Java solution FindDuplicate. This involves analyzing and generating coverage reports using the JaCoCo plugin.

The 'FindDuplicate' class aims to find a duplicate number within an array of integers. Here, it utilizes
the Tortoise and Hare algorithm to achieve this efficiently.

Analyzing only the code (!) reveals that a boundary exists:

(i) On-point test: In the context of the provided code, an on-point test would involve testing with an input array 
where the duplicate number appears at the beginning of the array. This means that the first occurrence of the 
duplicate number should be among the first few elements of the array. The algorithm should correctly identify 
and return this duplicate number.

(ii) Off-point test: In the context of the provided code, an off-point test would involve testing with an input 
array where the duplicate number appears after the beginning of the array. This means that the first occurrence of 
the duplicate number should NOT be among the first few elements of the array. The algorithm should correctly 
identify and return this duplicate number.

Running those tests reveals that already 100% line coverage is achieved. No further augmentation with (creative) 
testcases is needed.

### Task 2: Designing Contracts
Understanding the requirements and their constraints leads to the pre- & post-condition, whereas understanding 
the code leads to the invariants. Now, we have to define a clear contract for the class FindDuplicate. This contract clearly establishes what the class 
requires as (a) pre-conditions, what the class provides as (b) post-conditions and what (c) invariants always should hold 
for the class. 

(a) The following pre-conditions can be derived from the description: (i) The input array 'nums' must not be null.
(ii) The input array nums must have at least two elements, as the size of the array is n + 1 and n must be at least 1.
(iii) Each element in the input array nums must adhere to the range [1, n], where n is the size of the array.

(b) The following post-condition can be derived from the description: (i) The method findDuplicate returns an integer 
that represents the duplicate number found in the input array 'nums'.

(c) The following invariants can be derived from the initial code: (i) The algo correctly implements Tortoise 
and Hare algorithm to find the duplicate number efficiently. (ii)  The algo maintains the integrity of the 
tortoise and hare pointers throughout the execution to detect the cycle in the input array. 

Implementation: 

(a) The question here is, how to implement the pre-conditions, with assert statements or if statements 
with exceptions. The task is to find a duplicate number in an array and the constraints specify conditions that the 
input array must adhere to (e.g., not null, input array has at least two elements), using if statements with exceptions 
would be more appropriate than assert statements. while assert statements can be useful for debugging and validating 
assumptions during development, using if statements with exceptions provides a more robust and flexible approach for 
handling exceptional conditions, making it a better choice for production-ready code (stronger preconditions). 
Therefore, the preconditions (i), (ii) and (iii) have been implemented with if statements with exceptions.

(b) The initial code returns an integer representing the duplicate number found in the array nums. Although the method 
returns an integer, it does not directly address whether the returned value represents the duplicate number. 
This aspect needs to be explicitly checked in the code. Therefore, the post-condition (i) has been implemented by
assigning the found duplicate number to duplicateNumber variable and ensuring it lies within the valid range of the input array.

(c) Regarding the invariants, the initial code already implemented correctly a method (i) to find the duplicate 
number in the input array nums using efficient techniques. Moreover, the initial code already ensures that (ii)
the returned duplicate number is valid and lies within the range of the input array nums. 

### Task 3: Testing Contracts
We developed a suite of JUnit tests to ensure that the contracts of the FindDuplicate class are correctly enforced. 
These tests cover the following aspects: (i) Validation of Normal Operation (Pre-Conditions Met) (ii) Confirmation of 
Exception Handling (Pre-Conditions Violated) (iii) Ensuring Post-Conditions Hold and (iv) Verification of Invariants.

Through the modification and the new test cases we have now a line coverage of 95% and branch coverage of 88%.
The condition if (duplicateNumber < 1 || duplicateNumber > nums.length - 1) in line 38 of the FindDuplicate class is 
essentially a duplicate check of the preconditions. In this specific implementation, since the algo ensures that 
the duplicate number will always be within the range [1, nums.length - 1], covering this line with a test case 
may seem redundant.

### Task 4: Property-Based Testing
Property-based testing involves verifying that certain properties hold true for a given piece of code across a wide 
range of inputs. For the "FindDuplicate" problem, we can identify 3 properties that should hold 
true for any inputs:

- **Existence of Duplicate:** The result should be a number that exists more than once in the input array.
- **Within Range:** The result should be within the range of 1 to n, where n is the size of the array.
- **Correctness:** The result should be a duplicate number present in the input array.

**Implementation**: 
- The existenceOfDuplicate test verifies that the algorithm correctly identifies a duplicate number within the 
input array and that the result falls within the valid range.
- The correctness test ensures that the result returned by the algorithm is indeed a duplicate number present in 
the input array.
- The withinRange test checks if the result returned by the algorithm falls within the valid range of 1 to n.
A generator function validArrays is provided to generate valid input arrays for testing, ensuring diverse inputs.