# Documentation Group 22

### LongestIncreasingSubsequence

### Task 1: Code Coverage
Problem Description: The problem requires finding the length of the longest strictly increasing subsequence in 
an unsorted array of integers.

Only four test cases were needed to achieve 100% line coverage and branch coverage for the lengthOfLIS method in the 
LongestIncreasingSubsequence class. The provided test cases covered the empty arrays, the null arrays, arrays with random 
elements and arrays with all elements being the same. No further augmentation with (creative) testcases is needed. 

### Task 2: Designing Contracts
Understanding the requirements and their constraints leads to the pre- & post-condition, whereas understanding
the code leads to the invariants. Now, we have to define a clear contract for the class LongestIncreasingSubsequence. 
This contract clearly establishes what the class requires as (a) pre-conditions, what the class provides as 
(b) post-conditions and what (c) invariants always should hold for the class.

(a) The following pre-conditions can be derived from the description: (i) The input array nums is not null.
(ii) The input array nums is not empty. (iii) Each element in the input array nums is an integer.

(b) The following post-condition can be derived from the description: (i) The method lengthOfLIS returns a non-negative 
integer representing the length of the longest increasing subsequence.

(c) The following invariants can be derived from the initial code (and the description): (i) Each element in the 
input array nums is an integer that can be positive, negative, or zero.

Implementation:

(a) The pre-condition (i) is already implemented. However, we would go for a stronger pre-condition in this case. Using 
an if statement to check for null and returning 0 seems not be the most robust approach because it silently handles 
the null case by returning 0, which might lead to unexpected behavior if the caller is not aware of this behavior.
And it does not explicitly signal to the caller that passing a null array is an invalid input. The programs execution 
should halt, when a null array is passed. Therefore, we changed the code and used an exception to enforce the precondition 
that the input array nums is not null. (ii) This pre-condition is already implemented. The implementation here is reasonable 
since an empty array is a valid input according to the problem description and returning 0, when the input array is empty,
is a clear and expected behavior. (iii) This pre-condition is implemented by iterating through the array and checking 
if each element falls within the range of valid integers.

(b) The post-condition (i) has not been implemented yet. Therefore, we implemented this post-condition using an assertion 
after calculating the maxLength. If the calculated length is negative, an assertion error is thrown. We used an assertion 
for the post-condition because it is a common practice during development and testing to ensure correctness and 
to catch any unexpected behavior early in the development cycle (and due to the current Code maxLength is always true). 
However, in a production environment where runtime error handling is critical, using an if statement with an exception 
might be preferred to provide more robust error handling.

(c) The invariant (i) is implicitly assumed to hold true for all integer arrays. No additional implementation is 
required for this invariant.

### Task 3: Testing Contracts
We developed a suite of JUnit tests to ensure that the contracts of the LongestIncreasingSubsequence class are correctly enforced.
These tests cover the following aspects extensively: (i) Validation of Normal Operation (Pre-Conditions Met) (ii) Confirmation of
Exception Handling (Pre-Conditions Violated) (iii) Ensuring Post-Conditions Hold and (iv) Verification of Invariants.

Through the modification and the new test cases we have now a line coverage of 100% and branch coverage of 91%.
The branch coverage currently stands at 91%, primarily due to the addition of a post-condition assert statement in line 31, 
included for the sake of thoroughness. However, it is important to note that this assert statement is not 
necessary for the functionality of the code (it is actually redundant for this code). In fact, its omission would 
result in 100% branch coverage. Therefore, while the current branch coverage is not 100%, it is important 
to recognize that this particular assert statement does not affect the overall robustness or behavior of the code at all.

### Task 4: Property-Based Testing
Property-based testing involves verifying that certain properties hold true for a given piece of code across a 
wide range of inputs. For the "Longest Increasing Subsequence" problem, we can identify 3 properties that should 
hold true for any inputs:

- **Length Property:** The length of the longest increasing subsequence should never be negative. 
It should represent a valid length of a subsequence (a non-negative integer).
- **Subsequence Property:** A subsequence is a sequence that can be derived from an array by deleting some or 
no elements without changing the order of the remaining elements. This property ensures that the longest increasing 
subsequence is indeed a valid subsequence of the original array.
- **Increasing Property:** The elements within the longest increasing subsequence should strictly increase. This property 
ensures that the subsequence is actually increasing and not just a sequence of equal elements.

**Implementation**:
- The lengthProperty test verifies that the length of the longest increasing subsequence returned by the
algorithm is non-negative.
- The subsequenceProperty test checks whether the result of the algorithm is a valid subsequence of the original array.
- The increasingProperty test verifies if the elements within the manually defined LIS array strictly increase.
- While not directly tied to the algorithm's output, it ensures the correctness of the increasing property of the LIS.
- We had to use helper functions to encapsulate logic that verifies properties of the LIS, such as checking if the 
LIS is a valid subsequence and if its elements strictly increase.
