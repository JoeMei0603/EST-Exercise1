# Documentation Exercise 3 : Messages

## A. Number of invocations
Before we could write the tests, we had to refactor the code of the `MessageProcessor` class to inject the dependency on
`MessageService`. This allows us to use a mock object of `MessageService`.
Then, we have created a unit test to ensure that the `sendmessage` method in the `MessageService` class is called as 
often as messages are to be sent.

## B. Content of invocations—`ArgumentCaptor`
By using `ArgumentCaptor`, we can ensure that the content of the messages passed to the `sendmessage` method is correct.
`testContentOfInvocation` checks both the number of calls and the correctness of the arguments passed.

## C. Content of invocations — Increasing observability
We can modify the `MessageProcessor` class so that it stores a list of sent messages (see `sentMessages` and `getSentMessages` method). 
Then we can check this list in our tests.

In this scenario one specific change happened:

(i) The setUp method remains unchanged and initializes the `MessageService` mock object and the `MessageProcessor`.

(ii) The test for the number of calls remains unchanged as well.

(iii) Modified Code leading to increased observability:
(a) We created three Message objects with the corresponding senders and receivers (Jamo, Jerome, Joel and Faizan).
(b) We passed these messages to the `processMessages` method.
(c) After processing the messages, we called the `getSentMessages` method to retrieve the `sentMessages`.
(d) We checked whether the number of messages sent is correct and whether the receivers and contents of the messages 
correspond to the expected values.

## D. Comparison
### Technique B (ArgumentCaptor) 

++ Advantages: No changes to the original class required. Direct verification of the transferred arguments.

-- Disadvantages: Requires the use of Mockito-specific classes and methods.

### Technique C
++ Advantages: Increased transparency and traceability within the class. Can also be implemented without mocking frameworks.

-- Disadvantages: Requires changes to the original class (storage of sent messages). Slightly increases the complexity of the class.

### Conclusion
Both techniques have their own advantages and disadvantages, and the choice depends on the specific requirements and 
preferences of the project.

