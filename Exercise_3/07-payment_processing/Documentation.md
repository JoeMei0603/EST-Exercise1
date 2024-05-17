# Exercise 07: Payment Processing System

## Setup and Builder Class
In order to make testing easier the `PaymentProcessorBuilder.java` class was created. It acts as a builder for the `PaymentProcessor.java` class.
This improves readability and also opens the builder for extension or changes in for example the valid transaction logic. In its current implementation,
the builder can define the transaction evaluation, subscribe to event publishers and be built (even with empty events).

A second setup decision is the `setupPaymentProcessor` method which, before each test, initializes an auditService by mocking it and
then builds the `PaymentProcessor.java` class via the builder. That way the *same, new* instances are available for each test.

## Transaction class and assumptions
Since there was no existing class for a transaction, the `Transaction.java` class was created. A transaction has a  `transactionId` which can
be accessed via the  `getId` method.

Assumption 1: With that class ready, it was then assumed in the `PaymentProcessorBuilder.java` that any transaction with `id > 0` is valid and that any
transaction with `id <= 0` is invalid. The mocking of the `fraudDetectionService` manifested this assumption.

Assumption 2: The other methods were not further tested as it stated specifically to write Unit tests for the `onTransactionComplete` method.

### A. Number of invocations
The testing of the number of invocations was done via Mockito by making sure valid, invalid and mixed sets of transactions have the
correct amount of calls of the `onTransactionComplete` method. The boundary cases 0 and 1 were of course parts of these tests and 
worked fine via the mocked class.

### B. Content of invocations—`ArgumentCaptor`
The test ensuring arguments were captured was done by processing several transactions, where 2 out of 3 were valid. With that, both
the occurrences and actual values, via the `getId` method, could be tested and showed that thea actual values/instances are correct. In case of
an invalid transaction, no call to the method `onTransactionComplete` is made.

### C. Content of invocations—Increasing observability
Increasing the observability could have been achieved with several methods. An easy way would be to just return a boolean however that would only
give information about the completion of the transaction but not its actual content. I therefore decided to return a transaction after completing it,
if it is valid, and otherwise return null. 

This was then tested by making sure a transaction with its corresponding id is returned in case of a valid transaction and otherwise the
returned value is `null`.

### D. Comparison
What are the advantages and what are the disadvantages of the techniques you used in B. and C.?

As implemented in B, the ArgumentCaptor is a valid way of making sure all instances passed through a mock are captured. Especially if a 
method does not directly return anything we can capture variables and make sure the changes and make-up are what we are looking for. 
The downside is that it can add some complexity as it requires a bit of code and making sure a good test set up is available.

On the other hand, the solution of C where wer make sure the method returns a value, testing becomes way simpler. As long as we have
the method compiles we can check what values, including errors or empty values, are returned. This is of course very useful to directly
and easily test outputs of methods. A possible downside is that refactoring a class to "force" a return value might be not a good idea
which is why depending on the context, the ArgumentCaptor might be the better solution.
