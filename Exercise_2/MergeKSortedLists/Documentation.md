# Documentation Group 22

## Exercise 5: Merge KSorted Lists
Important assumption: the case of an empty list or a null list returning null was not further changed since in our opinion
it is ok to return null and not an empty list or otherwise.

### Task 1: Code Coverage
Based on the requirements and looking very broadly at the code, there are two test cases which achieve 100% line coverage. 
One is to test null and an empty list which both return null. The second case is a valid example with a node that should return 0 as well
as a node filled with values.

### Task 2: Designing Contracts
The pre- and post-conditions can be interpreted from the README and what statements should hold at which times whereas the invariants
are deducted from the logical steps taken throughout the code.

#### Pre-conditions
- The lists parameter should not be null or empty
- The number of nodes should be in the range [0, 10^4]
- The node values should be in the range [-10^4, 10^4]

The first pre-condition is already implemented in the code. The second and third can be combined into a loop. From line 19 to 34 in `MergeKSortedLists`
those were implemented. We calculate the total amount of nodes by iterating through the lists parameter and check if it ever goes
beyond 10^4 and at the same time check the value of each node against the correct range of -10^4 up to and including 10^4. If any of
the mentioned conditions is violated, an `IllegalArgumentException` is thrown.

#### Post-conditions
- The method should return a single sorted linked list of type ListNode containing all k input linked lists

Since the returned argument is `dummy.next` we can be sure that the correct type, namely ListNode, is returned and that is also a single
linked list. The sorted attribute of the list will be checked as an invariant as argued below.

#### Invariants
- The returned list is ordered throughout the entire execution of the method
- The amount of iterations through the queue can be at most the amount of nodes in the list

The maintained order of the list is technically given through the `PriorityQueue` which orders the values in ascending order. In order
to further check the invariant the queueing loop in line 49 now also checks whether the element which is about to be added to the list is
smaller than its predecessor. If not, a `RuntimeException` is thrown. The same is done for the iteration where a counter of iterations checks
if it is smaller than the `totalNodes` in the list and throws a `RuntimeException` otherwise.

### Task 3: Testing Contracts
The pre-conditions can now be further tested by implementing writing tests which reach the `IllegalArgumentException` by having values
and input sizes which contradict the provided ranges i.e. the list is too long or the values too large/small. The post-conditions can be checked
with a valid input example, in our case once again testing against the boundaries of -10^4 and 10^4.

This leads to a line coverage of 94% overall and 93% in the `MergeKSortedLists` class. The only two lines (and branches) which are not 
covered are lines which would break the invariant of the exercise: having an iteration count larger than actual node values and
the list not being ordered, which would throw a `RuntimeException`.

### Task 4: Property-Based Testing
For the Property-Based Testing, three properties have been identified.
- Valid combinations of `ListNode` elements with values from -10^4 to 10^4
- Invalid values of `ListNode` which are outside the range above
- Different lengths of the input list `ListNode[]` ranging from 0 to 10^4

The first property was tested by creating a `Arbitrary<ListNode[]>` and the `@Provider` was used to randomly choose three ListNode
values between -10^4 and 10^4. Many edge were covered and the test ran smoothly. For the invalid values the same was done but this time
one of the values was outside the range and, as expected, each time the `IllegalArgumentException` was thrown. The last property test
was done by creating lists of random size, between 0 and 10^4, and checking whether the returned sorted list had the appropriate length.

Unfortunately the line coverage could not be improved since the invariants did hold and was the same as in Task 3.