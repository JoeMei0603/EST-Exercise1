# Documentation Group 22

## Exercise 2: Course Schedule
The methods would have required to create an object in order to be testable.
For this reason, the methods had to be changed to be static and are now accessible without an object.

### Task 1: Code Coverage
Analyzing the code and the examples reveals that there is one partition with a `Valid Requirement`, and another with a `Cycle Dependency`.
Based on this, two testcases can be devised, both using 2 for `numCourses`.
The testcase for the `Valid Requirement` will assert that two situations are covered; one with a `{{1,0}}` dependency, and a reversed one with a `{{0,1}}` dependency.
Running those tests reveals that already 100% line coverage is achieved. No further augmentation with (creative) testcases is needed.

### Task 2: Designing Contracts
Understanding the requirements and their constraints leads to the pre- & post-condition, whereas understanding the code leads to the invariants.
- **Pre-conditions:** The given integer `numCourses` must be positive and the provided arrays cannot be empty.
For the pair `[a, b]`, `a` cannot equal `b` and `0 ≤ a, b < numCourses` must hold.
- **Post-condition:** The method is required to return a boolean value: `true` if it is possible to finish all courses without encountering prerequisite cycles, and `false` otherwise.
- **Invariants**: After initializing a graph (ArrayList) and appending a `numCourses` amount of elements, the **invariant** must hold that the size of that graph is equal to `numCourses`.
Furthermore, each list of the graph should only contain elements that are valid (i.e., `0 ≤ element < numCourses`).
Lastly, when visiting nodes of a graph, their attributes `visited` and `onPath` change status.
It must be ensured that they have updated their status correctly for each node being visited and during each iteration of the attempted cycle detection of a node (`hasCycle`).

Using the now defined contracts leads to their implementation.
The function only allows for integers and integers in lists of lists as input, which means that the IDE already recognizes any input that is not conforming to this function signature and won't allow the user to run the program.
Further enforcing the **pre-conditions** is done by throwing an IllegalArgumentException when one of the following checks fails: `numCourses < 0`, `prerequisite.length == 0`, `prerequisite[0] == prerequisite[1]`, `prerequisite[0] < 0 || prerequisite[0] >= numCourses`, or `prerequisite[1] < 0 || prerequisite[1] >= numCourses`.\
The **post-conditions** can be checked in a similar way with either the condition `!visited[i]` or `onPath[i]` for each node, where a RunTimeException would be thrown if these checks do not hold.\
To ensure the **invariants** hold, the first condition `graph.size() != numCourses` checks the size of the state-modified graph.
Enforcing valid elements (course numbers) in the graph can be achieved with `prerequisiteList.get(prerequisiteList.size() - 1) < 0 || prerequisiteList.get(prerequisiteList.size() - 1) >= numCourses`.
Additionally, to ensure a correct attribute state update for the nodes, the same check as for the **post-condition** can be used.
As each node needs to be checked, the condition can directly be appended as the last step of the iteration through the graph.
Since the **post-condition** check directly follows the **invariant** check of the last iteration, the IDE already recognizes that the conditions are checked twice.
Therefore, the **post-condition** would always hold and the explicit **post-condition** check is omitted.
Lastly, the node attributes `visited` and `onPath` additionally change their status during each `hasCycle` call.
In this case, the **invariants** are not quite the same as the **post-condition** and can be checked with `!visited[current]` and `!onPath[current]`.
All conditions to enforce the **invariants** throw a RunTimeException otherwise.

### Task 3: Testing Contracts
Having introduced the **pre-condition** checks, there is a new boundary between `-1` and `0` for the `numCourses`.
There are also new boundaries for `prerequisites`; one where a prerequisites length is `0` or `1`, and one where the prerequisites are as long as `numCourses` or shorter. 
Based on this, there are new `on-points` and `off-points`, some of which are already tested through previous tests and some that are not.
For this reason, tests have been created that check if `numCourses` is `-1` or `0`.
Further tests have been created that check if the `prerequisites` contain a course number that is bigger than the `numCourses` (off-points) and tests that check if `prerequisites` contain a negative course number (off-point).
Furthermore, tests for new edge-cases, such as an empty `prerequisites` array or `prerequisites` referencing themselves have been created.
Running those tests reveals that 6 lines/7 branches are not tested.
However, it won't be possible to create tests for these untested lines (invariants), as it is not possible to create situation where the **invariants** do not hold.

### Task 4: Property-Based Testing
For the Property-Based Testing, six properties have been identified.
- **Can Finish:** All inputs are valid and the inputs fulfill requirements they must fulfill based on each other.
- **Invalid Cases - Negative numCourses:** A negative integer is provided for `numCourses`.
- **Invalid Cases - Negative num in prerequisites:** A negative integer in any `prerequisites` array of the arrays is provided.
- **Invalid Cases - Prerequisites larger than numCourses:** An integer in any `prerequisites` array of the arrays is larger then `numCourses`.
- **Invalid Cases - Prerequisite Itself:** The integers in any `prerequisites` array of the arrays are equivalent. 
- **Invalid Cases - Empty Arrays:** Any `prerequisites` array of the arrays is empty.

Creating most of these tests additionally involved creating providers, since the tests needed random arrays which were complicated to create.
Upon creating and running those tests, jqwik reported that all the tests passed.
Thus, no further boundaries or partitions could be identified.
Also, property-based testing was not able to break an **invariant**, which means that the previous coverage stays the same.