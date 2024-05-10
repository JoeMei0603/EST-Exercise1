# Documentation Exercise 5: Ticket System
#### 1. Use of Doubles for NotificationService and LogService
Ticket is an entity with simple data and methods to manipulate that data.
This class should not be mocked, as it is very simple to recreate that object in tests.
`NotificationService` is an external resource, which might be slow, complex to setup, and hard to simulate.
These reasons stand for doubling this class for testing purposes.
The same applies for the `LogService` class, which is also an external resource and should also be doubled for testing.
`TicketRepository` is similar, but not quite the same. It is a port implemented as an interface, which means that it has abstracted the way it communicates with external resources.
It can easily be mocked by implementing the abstracted methods it provides.

With those dependencies identified, tests can be implemented.
The assumption is made that the services and the repo throw `RuntimeException` if they are unreachable.
The first step is to implement the doubles for the services and the repository.
ALl of them receive a constructor that takes an argument `reachable`.
This facilitates testing by telling the test double if it should act as reachable or not, which dictates its behaviour when called under test.
To mock their behaviour, they also receive a simple print statement that logs what they just did.
Then, the `TicketManagerTest` class is created with a `testSetup` method.
It initiates the test doubles for the services, the repo, a TicketManager, and finally creates a ticket which can be used for the tests.
As a last step, the testcases for special scenarios, these potential failures, and a valid case are written.
The previously provided `testSetup` and the constructors for the doubles facilitate the creation of test methods.
An unreachable service can be easily instantiated and can be used to create a new TicketManager in a simple way.
After running the tests, it is obvious that the implementation does not check if ticket objects are null.
Therefore, the implementation had to be adjusted to take care of that special case.
#### 2. Disadvantages of Using Doubles in Your Tests
A clear disadvantage of using mocks or stubs is the significant coupling with the production code.
In this case, the `LogServiceStub` depends on the implementation of `LogService` itself, meaning that additions, changes, deletions, etc. need to be mirrored by the stub class.
The same applies for the `NotificationService` and the `TicketRepository`.
Otherwise, if the behavior of the test doubles deviate from the actual behavior of the production code classes, it may lead to false positives or negatives in tests.
Determining how much the tests need to know about the production code to test it properly is therefore a significant challenge.
Additionally, introducing and maintaining test doubles alongside the production code can add complexity to the codebase.
For instance, if a production code class undergoes changes in its method signatures or behavior, the test doubles used in its unit tests need to be updated accordingly.
If not properly maintained, this can lead to inconsistencies between tests and actual behavior.
Lastly, test doubles may lack realism compared to real dependencies.
If the test doubles do not accurately simulate the behavior of the production code classes, it may result in incomplete testing.
For instance, if the stub always prints the same regardless of input or if it doesn't handle error conditions properly, it may not fully exercise the error-handling logic in `NotificationService`.
#### 3. Handling of Failures in Notification and Logging
With the previous introduction of the constructors and the throwing of `RuntimeException` for a simulated down-time, failures are already introduced in the code.
However, the test for the failure of the `LogService` and `NotificationService` needs to be changed, as it is favorable to continue with the flow of the code if these services are unreachable.
This means that an info about it not being reachable is enough.
For the `TicketRepository`, however, the flow should be interrupted and the `RuntimeException` should be thrown.
The tests for `LogService` and `NotificationService` need to be adjusted, whereas the test for the `TicketRepository` failure can stay the same as nothing needs to be adjusted in the code.
The failures are now handled in a way it is favorable and successfully pass.