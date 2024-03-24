# Documentation Group 22

## atoi

#### Specification-based testing
At first, I read the description of the MyAtoi function without having an actual look at the code itself.

The partitions to test came in two ways:
1. Value-based partitions, for example 0 or positive and negative numbers as well as the upper and lower ranges of the Integer class
2. Type- and grammar based partitions, for example non-digit inputs or leading whitespaces

I decided to first test the special cases for the string input: null, empty and 0. Each of these should return 0 (and they did).

Afterward, I combined some of the partitions and tested leading whitespaces with digit and non-digit input.
Then, positive and negative numbers were tested and in order to have good reliability, I moved the sign around and also included whitespaces
at times. Additionally, the string reading requirement was tested by making sure the reading stops after the first non-digit number and
that zeros at the beginning are ignored.

Finally, the Integer boundaries were tested by using the first off-point of the positive and negative numbers range.

#### Structural testing
With all this tests, the branch coverage reported by JaCoCo was already at 100% (18/18).

#### Mutation testing
At first, the mutation coverage reported 95% meaning that 20/21 mutations were successfully killed and only 1 survived.
The surviving mutation was a changed conditional boundary in line 28. This was an easy case to get rid of and also meaningful
to include in testing. Therefore, two new tests were added which also checked **valid** boundary conditions (number is equal to boundary and just below) which 
in the end resulted in a 100% mutation coverage with 21/21 mutations killed.


## combination_sum
#### Specification-based testing
At first, I read the description (ReadMe) of the CombinationSum function without having an actual look at the code itself.
As a second step, I analyzed the program and its inputs/outputs to identify key partitions and boundaries. I devised test cases based on 
these partitions and boundaries, ensuring coverage of various scenarios. Finally, I implemented these tests using frameworks like 
JUnit to automate the testing process.

The partitions to test:

1. The input "target"

Here, I explored various scenarios, including negative target values, which should result in an empty list, as observed 
in the tests (the getResult() function will not do anything, since the case negative target is not explicitly handled). 
Additionally, I examined typical cases where the target aligns with different combinations of candidates, 
ensuring correct outcomes. Furthermore, the tests also covered instances where a candidate exactly matched the target, 
validating its inclusion as a solution. Special consideration was given to edge cases, such as when the target was 
smaller than the smallest candidate, ensuring appropriate handling.

Following thorough testing of the input "target", all test cases produced expected outcomes without necessitating any 
modifications to the code.

2. The input "candidates"

When testing the input array "candidates", I explored various scenarios. Initially, providing an empty array as input 
yielded no issues. However, two test cases uncovered bugs:

(i) It is clearly specified that "candidates" must be distinct, yet the test failed due to duplicate candidates. 
Therefore, I updated the code to detect and handle duplicate candidates, adhering to the requirement. After implementing 
the necessary modifications, the test passed successfully.

(ii) Testing with only negative "candidates" and a positive target failed, revealing an undocumented constraint. 
After checking the Code (see 1.), I found out that the target must be greater than or equal to 0. Extending this assumption, 
I enforced that "candidates" must also be non-negative. Subsequently, I modified the code to throw an IllegalArgumentException for 
negative candidates, resolving the issue. After implementing the necessary modifications, the test passed successfully.

3. Possible combinations

In this scenario focusing on possible combinations, I initially verified whether the output would correctly return an 
empty array list when the target cannot be achieved with the provided candidates. This test passed as expected.
However, upon testing for constraints, specifically when the number of unique combinations exceeds or equals 150, the 
test case failed (boundary testing; on point 149 and off point 150). This indicated a bug in the code. Subsequently, 
I updated the code to throw an exception error when the number of unique combinations surpasses 150. 
Following the implementation of these modifications, the test passed successfully.

#### Structural testing
With all this tests, the branch coverage reported by JaCoCo was already at 100% (18/18). However, I could only resolve 
two open questions after examining the source code.

Target Validity: Although the branch coverage was already at 100%, upon inspecting the source code, it became apparent 
that the target can only be greater than or equal to zero; otherwise, the method would return an empty list. 
No modifications were made to the target input handling. However, adjustments were made to the code to ensure that both 
candidates and target are positive in order that the function works as intended. With a negative target the function 
still works, but it returns an empty list (the "candidates" have to be positive).

Input Type Handling: The code robustly handled already valid input types, making the need for specific tests like 
"testNonIntegerCandidates" or "testNonIntegerTarget" redundant. This ensures that the method functions appropriately 
with the expected input types without explicit testing.

#### Mutation testing
Initially, the mutation coverage reported 86%, indicating that 14 out of 17 mutations were successfully killed, 
while 3 mutants survived:
(i) One surviving mutation resulted from a modification in the code where the sort(candidates) function was removed.
Consequently, the removal of duplicates did not function properly without the sorted list. However, this issue was 
addressed by adopting a new approach to remove duplicates using a hash set. Subsequently, the mutation coverage increased 
to 88%, indicating that 15 out of 17 mutations were effectively neutralized. Therefore, this mutant was killed successfully.
(ii)
The second surviving mutant arose from an unhandled edge case where there is only one candidate with the value 0 and 
the target is also 0. This scenario was not previously accounted for, leading to a gap in test coverage. However, a 
new test case was written specifically to address this edge case. As a result, the mutation coverage improved to 96%, 
indicating that 16 out of 17 mutations were effectively neutralized. 
Consequently, this mutant was successfully killed.
(iii)
The third surviving mutant is not practically relevant. The requirement explicitly states that the array size must 
be less than 150, making it a fixed constraint. Altering this requirement would not be meaningful. As a result, 
this surviving mutant is disregarded. 

In conclusion, we achieved a 94% mutation coverage, successfully neutralizing 16 out of 17 mutations.

## frac2dec
#### Specification-based testing
The first step was to understand the requirements, inputs, and outputs of the given function. To gain an initial
understanding about these, the description about the function was analyzed. Since the problem description seemed rather
simple, the code itself was instantly taken into the analysis as well. Based on these, 4 partitions have been
identified:

1. "numerator" or "denominator" equal 0:
Here, the focus was on cases where either the numerator or denominator equaled zero. It was explicitly stated that when 
the denominator is zero, the function should return null. Additionally, it was tested that when the numerator is zero, 
the output should be "0". After modifying the code to handle the denominator being zero, all test cases passed successfully.

2. Repeating fractional & multiple answers:
The code was evaluated to ensure it correctly enclosed repeating fractional parts within parentheses. It was also 
verified that the function handled cases where multiple answers were possible, which it did successfully.

3. String length < 104 constraint:
In the requirements it was clearly stated that the string length must be less than 104. This test case failed initially, 
but after adding this constraint to the code, the test case run successfully.

4. Fraction Output:
Lastly, general test cases were conducted to assess the function's handling of fraction outputs. This included scenarios 
with negative numerators, denominators or both numerator and denominator being negative. All test cases produced the 
expected outcomes without necessitating any modifications to the code.

#### Structural testing
With the previously created tests, the branch coverage reported by JaCoCo was already at 100% (18/18).

#### Mutation testing
Our mutation coverage reported 86%, with 19 out of 22 mutations successfully neutralized. 
However, three mutants survived:

(i)The first surviving mutant, the class Frac2Dec itself, requires no further action.

(ii)Another surviving mutant involves a change to the operator >=, which is irrelevant due to the guaranteed 
length constraint of the answer string (less than 104) (explicitly given in the requirements). Therefore, this mutation 
can be disregarded.

(iii) The final surviving mutation, known as changed conditional boundary, alters the condition 
((numerator > 0) ^ (denominator > 0)). Despite extensive testing across various scenarios and values, 
this mutation persisted. However, given that all possible combinations are covered by our test cases, we conclude 
that this mutation does not warrant additional consideration.

In summary, our mutation coverage of 86% reflects the successful elimination of 19 out of 22 mutations.
The remaining mutants can be disregarded, confirming the adequacy of our testing efforts.

## generate_parentheses
#### Specification-based testing
The first step was to understand the requirements, inputs, and outputs of the given function. To gain an initial
understanding about these, the description about the function was analyzed. Since the problem description seemed rather
simple, the code itself was instantly taken into the analysis as well. Based on these, only one partition has been
identified, because only one input type (an int) - which is also restricted - is possible.

No special cases have been declared in the description and none are to be expected in the code. Two boundaries are
clearly noticeable when looking at the description and the code; one between 0 (on point) and 1 (off point), another one
between 8 (on point) and 9 (off point). Tests for all of those points and for n=3 were written. Since the amount of
combinations in the resulting list increases exponentially, n=8 would result in 1430 possible combinations, where it
is not feasible to write such a test by hand. As an alternative, the length of the resulting list is asserted to be the
expected 1430 combinations. As a proof of concept, the length is also asserted for n=3 with 5 possible combinations.

Testing the boundary for n=9 revealed a bug. Even though the description does not mention what should happen when n is
bigger than 8, it was assumed that an Exception should be raised. However, when the test was executed, it failed as none
was actually raised. For this reason, the code had to be adjusted and throw that exception in the case when n is bigger
than 8.
#### Structural testing
With the previously created tests, the branch coverage reported by JaCoCo was already at 100% (16/16).
#### Mutation testing
With the given tests, the mutation coverage reported by Pitest was at 95% (21/22). Looking into the details did not
immediately reveal what was wrong or missing. The initially created empty ArrayList was replaced with an empty list of
the collections class, but the unit test already checked for an empty list of the collection class. Upon investigating
the function further based on the Pitest report, there was a hint that it might be safer to directly return an empty
list of the collections class instead of the initially created empty ArrayList. With this change, the mutation coverage
reached 100% (21/21) as the mutation testing no longer replaced the adjusted return value.

## maximum_subarray
#### Specification-based testing
The first step, again, was to understand the requirements, inputs, and outputs of the given function. To gain an initial
understanding about these, the description about the function was analyzed. The problem description seemed rather simple
again, thus the code itself was instantly taken into the analysis as well. Based on these, only one partition has been
identified, because only one input type (an array of type int) is possible.

No special cases have been declared in the description and none are to be expected in the code. One boundary is clearly
noticeable when looking at the description and the code; an array with a length between 0 (on point) and 1 (off point).
Test for these points were written with arrays of length 0 and 1. Additionally, since arrays might behave differently
with a single element compared to multiple elements, the decision was made to add another test that takes an array as
input with multiple elements. Since negative integers might also have an impact on the result, the tests for non-empty
arrays have been augmented with the same assertion again, but with an inverted sign.

Testing the boundary for the array with length 0 revealed a bug. The documentation states that 0 is returned when an
empty array is given. However, there is no code that checks this edge case. For this reason, the function was modified
to comply with the requirements of the description.
#### Structural testing
With the previously created tests, the branch coverage reported by JaCoCo was already at 100% (4/4).
#### Mutation testing
With the given tests, the mutation coverage reported by Pitest was already at 100% (5/5).

This result raised a little of suspicion that something might not be correct. Taking another look at the tests and
analyzing them came with the conclusion that the tests were written for only linear increasing or decreasing values.
Since the arrays are processed dynamically in the code though, the decision was made to mix positive and negative values
in an array of a test. For a broader coverage, the resulting sequence was also inverted with the signs staying the same
to achieve two assertions in one test. To broaden the coverage even further, this test has been duplicated and all signs
have been reversed. However, these newly written tests did not reveal any new bugs.

## median_of_arrays
#### Specification-based testing
Based on the specifications, the input consisting the two integer arrays was tested in various ways:
1. One of the two arrays is null which in both cases returns 0.
2. Either both or one of the arrays is empty. If both are empty, the code returns -1 and since there was no specification
how to handle this case, it was not changed further. If only one is empty, the function call works as intended.
3. Either one of the arrays is not sorted in ascending order in which case the returned value is 0.
4. All combinations of array lengths: meaning both arrays are odd/even, only one of them is odd/even and also arrays 
containing a single element
5. Integers can also be negative and therefore f.e. the sorting test or arrays with mixed integers (+ and -) were
also tested

A special case, which was not specified in the README, is non-unique integers inside an array. An array which is {2, 2}
still fulfills the requirement of being sorted in ascending order. The test case failed and in our opinion revealed a
bug. A small adjustment to the boundary in line 19 of MedianOfArrays solved the issue.

#### Structural testing
The tests done for the specification-based testing together already resulted in a branch coverage of 100% (28/28).

#### Mutation testing
The mutation coverage was 96% with 44/46 mutations being killed. One of the surviving mutations was a changed conditional
boundary in line 8 in the getMin function. However, many different cases and combinations were already tested and therefore forcing
the mutation to be tested would require more effort than it would help improve code quality.
The second surviving mutation is a replaced integer addition with subtraction in line 32
of the findMedianSortedArrays function. This can be ignored because for the modulo calculation it does not matter if it is
2+1 or 2-1 the outcome stays the same.

## needle_in hay
#### Specification-based testing
At first, we read the requirements specification of the given function and identified the inputs and outputs for the programs. We identified the
given partitions of the program.

1. If "haystack" or "needle" is null, return -1: If either the supplied haystack or the needle is null the program should return -1 and should not proceed further. Here the "null"
   is different from empty string.
2. If both "haystack" and "needle" are empty, return 0: It was explicitly stated in the specification that in a case where
   both "haystack" and "needle" are empty string, the function should return 0. But upon testing the function, it was found that
   if any one of either "haystack" and "needle" is empty and other not, then the execution of program was getting aborted while throwing
   an error. Wee resolved this issue in the function and the test were getting passed.
3. If "needle" not present in "haystack", return -1: In the case where the supplied "needle" is not present inside the "haystack", the program should return -1
4. Return the index of starting position if the needle is present in the haystack.

Along with the requirements, the special case for function throwing an error when either one of "needle" or "haystack"  was empty was also identified and fixed.
#### Structural testing
With all the created test, the branch coverage reported by JaCoCo was already at 100% (17/17)
#### Mutation testing
Our mutation coverage reported 94% with 16 out of 17 mutations getting neutralized. With the single mutation surviving.
The changed conditional boundary for the "for loop". We tested many different inputs for the function and came to the conclusion 
that trying to kill the mutant would only waste the effort.

## palindrome
#### Specification-based testing
We first read the specification of the function and understood the requirements without looking at the code as they were simple and the input was
only allowing the "int" datatype, which further narrows the requirements. But there was a "Constraints" in the requirement, which said that the input "x"
should be `>=-2^20` and `<=2^20-1`. 
While testing we found that these constraints were not getting fulfilled in the program, so we added the constraints and tested the program accordingly.

There were two different programs, `PalindromeOne` and `PalindromeTwo`, both of them were implementing the specification differently, therefore the test for both were written different.

#### Structural testing
For `PalindromeOne` we wrote 8 test all of which passed and JaCoCo showed a branch coverage of 100%

For `PalindromeTwo`, JaCoCo showed a branch coverage of 100% as well with all the test passing (16/16)

#### Mutation testing
The overall mutation coverage for both the `Palindrome` programs was 85%.

The Mutation test for `PalindromeOne` showed a coverage of 92% with 12/13 mutants getting killed. The only surviving mutant was changed 
conditional boundary in the while loop, which we found out could not be killed. There were no need for any further test for `PalindromeOne`.

For `PalindromeTwo`, initial mutation coverage was revealed to be 61%. But upon further testing and changes in the test, the mutation coverage 
increased to 85%. After which the mutants were not getting killed, the surviving mutants were under the following categories:

(i) changed conditional boundary: The mutant under this category survived on line 14 and line 16, for both of these changed conditional boundaries we did test and found they were hard to kill within the context.
For the condition `if (x < 0)`, we wrote tests for the mutated conditions `if (x <=  0)` and `if (x > 0)` and found that the mutant was not getting killed despite the test. Similarly test were written for mutated condition of `if (x < 100 && x % 11 == 0)`.

(ii) replaced mathematical operations: The second category of surviving mutants were the "replaced mathematical operations". On line 16, there was a surviving mutant `Replaced integer modulus with multiplication` on the condition `if (x < 100 && x % 11 == 0)`,
now reaching this would not be possible because the only scenario where this would be true was when `x` was 0, and if `x` was 0 the program would not go past line 15. Similarly there was a surviving mutant on line 17, which was `if (x < 1000 && ((x / 100) * 10 + x % 10) % 11 == 0)`,


(iii)

of ""
and "". The surviving mutants were hard to kill in the context and we did extensive testing to ensure the program was fully
tested, therefore we decided to leave the surviving mutants as it is. Because it was not revealed that they would damage the execution of program.