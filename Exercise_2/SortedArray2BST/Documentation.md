# Documentation Group 22

## Exercise 6: Sorted Array to BST

### Task 1: Code Coverage
In order to achieve the most code coverage for Task 1, the requirements show that testing an array `{1}` as well as a valid
multi-sized array, for example `{1, 2, 3, 4, 5, 6, 7}`, already covers almost everything. Only the `root == null` check never
applies and testing an empty array `{}` takes care of that to. With those 3 tests we achieve 100% code coverage for Task 1.

### Task 2: Designing Contracts
The pre- and post-conditions can be interpreted from the README and what statements should hold at which times whereas the invariants
are deducted from the logical steps taken throughout the code.

#### Pre-conditions
- The input array nums must not be null
- The length of input array nums should be [0, 10^4]
- The input array nums must be sorted in ascending order 
- The input array nums must not contain duplicates

The implementation of the pre-conditions can be seen in the `sortedArrayToBST` method. At first, we check in the `checkIfArrayNull` method
if our input array is in fact `null`, in which case an `IllegalArgumentException` is thrown and right after that if the array is empty, we return
`null`.
The length of the array cannot be below 0, therefore we only need to check the length of the array to make sure the length does
not exceed 10^4 entries, as seen in the private `checkIfArrayTooLong` method.

The check of the sorted order, as well as the duplicates can be combined in one method, `checkIfArrayIsSortedAndUnique`, so that we
only iterate through the list once (and not twice). We make sure each value following another value is strictly larger and
we create a `HashSet` which checks for unique integer values.

#### Post-conditions
- The size of the BST tree has to be the same as the input list nums
- The resulting tree is a valid height-balanced BST tree

The code should create an BST tree out of the values in the input list `nums` and therefore before returning the BST tree
we check in the `compareInputAndResultSize` method if the size/length of the input list and the output tree are the same to
make sure no values were added or removed.

#### Invariants
- The BST tree must be height-balanced and the depth difference of nodes should be at most 1
- The left-child subtree of the root should only contain numbers `< root`
- The right-child subtree of the root should only contain numbers `> root`

The invariants are checked inside the recursive `constructBSTRecursive` method before each return. First we check
if a node's left-child value is smaller and right-child value is larger which is one of the requirements of a BST. 
After that, by getting the depth of the left and right child calling `getNodeDepth` we can compare, before the return, whether the difference in depth is actually larger
than 1, in which case an `IllegalArgumentException` is thrown.

### Task 3: Testing Contracts
The pre-conditions were tested with a null array, an array with boundary length, i.e. with 10^4 elements, and also an array which is
too long and therefore throws an `IllegalArgumentException` with length 10^4+1. On top of that, tests involving a non-sorted array
as well as tests non-unique array elements were implemented to check the `IllegalArgumentException` instances. The non-unique array 
implementation was done with a duplicate in the middle and also at the end to make sure the loops reach those as well.

The post-condition is tested via a valid call to the function. Since the code is correct and outputs an array with equal length
as the input array we can't reach the `IllegalArgumentException` which shows that the post-condition holds in our case. 

The same applies for the invariants which pass for a valid input list of integers, but we never reach any of the Exceptions
which is a good sign and proves that they hold against all test cases.

Code coverage after Task 3 is 93% with only 4 lines not being covered. The 4 lines correspond to the invariant Exceptions as well
as the post-condition Exception which are never reached. 

### Task 4: Property-Based Testing
For the Property-Based Testing, the following tests were written:
- A sorted, unique array containing between 0 and 10^4 elements in `testRandomValidIntegers`
- An array containing more than 10^4 elements

The first property test involves integers from `MIN` to `MAX` by creating a random list, consisting of random integers and then sorting
it. The second one was simply created with an array which is too large, so even if the elements were, by chance, unique and sorted,
all tests fail because the array is too large (>10^4).

Unfortunately the line coverage could not be improved since the invariants did hold and was the same as in Task 3 (93%).