# Documentation Group 22

## Exercise 8: Unique Paths Grid
Problem: Find possible unique paths a robot can traverse in a m x n grid. The grid is represented with a 2d array.

### Task 1: Code Coverage
We first analyzed the code and to get the 100% line and branch coverage we had to write 2 test cases.
Based on our evaluation we devised two test, first test had more than 1 row and column and while the
second test has 1 row and column. With this we were able to achieve full coverage of the code.

### Task 2: Designing Contracts
Understanding the requirements and their constraints leads to the pre- & post-condition, whereas understanding the code leads to the invariants.

Class defined the **pre-conditions** and **post-conditions** clearly.
For the **pre-condition** the number of rows `m` and number of columns `n` must be within the range of 1-100.
It was also defined that the given inputs should be positive integers. And the start position should always be the top left corner.

For the **post-condition**, the returned output must be a positive integer.
For the **invariants** the solution should only calculate unique paths in the grid from top left corner to bottom right corner.

After designing the contracts, we start implementing the conditions.

Implementation:

We start implementing the **pre-conditions** on the solution, there were no conditions defined we added the condition
where number of rows `m` and number of columns `n` would be in the range of 1 and 100 and if the input is not in the range
the solution would throw an error.
The added checks would also verify the second **pre-condition** so there was no need to add any further strong condition.

For the **post-condition** we added that the bottom right corner of the grid should always be `>= 0` which is also a non-negative integer.
We further identified that there was no need to add any further conditions for the **invariants** as it was getting satisfied.


### Task 3: Testing Contracts
Having introduced the **pre-condition** and **post-condition** checks, we wrote further test to verify the boundaries 
and increase the code coverage to 100%. The test were getting passed and we didn't reveal or catch any further anomalies during this task.
All the tests ran successfully


### Task 4: Property-Based Testing
For the Property-Based Testing, three properties have been identified.

- **Base Cases:** no of rows and columns `m` and `n` is 1
- **Recursive Relation:** `m` and `n` greater than 1 and less than 101
- **Invalid Cases:** Negative Integers and integers greater than 100


Upon creating the test for the **Recursive Relation** it was revealed that the result was getting overflowed and we were 
able to catch it due to the **post-condition**. Upon further investigation with debugging we found their was always a 
possibility of result getting overflowed when the `m` and `n` would be greater than `17`. 
So a new boundary was identified through the property based testing and the solution was adjusted to only accept the parameters
`m` and `n` if they are less than and equal to `17`.

Initially we taught to use **long** in the solution instead of **int**. But that would only have increased the limit to 
`34` without breaking the solution. So we opted to reduce the rows/column limit.

