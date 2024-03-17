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

With all this tests, the branch coverage reported by JaCoCo was already at 100% (18/18).
#### Structural testing
TODO

#### Mutation testing
TODO

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
#### Structural testing
#### Mutation testing

## maximum_subarray
#### Specification-based testing
#### Structural testing
#### Mutation testing

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
