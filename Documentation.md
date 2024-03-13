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
