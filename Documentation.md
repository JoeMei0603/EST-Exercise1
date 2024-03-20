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
in the tests. Additionally, I examined typical cases where the target aligns with different combinations of candidates, 
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
Upon investigation, I found that the target must be greater than or equal to 0. Extending this assumption, I enforced 
that "candidates" must also be non-negative. Subsequently, I modified the code to throw an IllegalArgumentException for 
negative candidates, resolving the issue. After implementing the necessary modifications, the test passed successfully.

3. Possible combinations

In this scenario focusing on possible combinations, I initially verified whether the output would correctly return an 
empty array list when the target cannot be achieved with the provided candidates. This test passed as expected.
However, upon testing for constraints, specifically when the number of unique combinations exceeds or equals 150, the 
test case failed (boundary testing; on point 149 and off point 150. This indicated a bug in the code. Subsequently, 
I updated the code to throw an exception error when the number of unique combinations surpasses 150. 
Following the implementation of these modifications, the test passed successfully.

With all this tests, the branch coverage reported by JaCoCo was already at 100% (18/18).
#### Structural testing
#### Mutation testing

## frac2dec
#### Specification-based testing
#### Structural testing
#### Mutation testing

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
#### Structural testing
#### Mutation testing

## needle_in hay
#### Specification-based testing
#### Structural testing
#### Mutation testing

## palindrome
#### Specification-based testing
#### Structural testing
#### Mutation testing
