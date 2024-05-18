# Documentation Exercise 4: EShop

## A. Number of invocations
The goal is to test whether the `onOrderPlaced` method is called the correct number of times for each subscribed 
listener when an order is published.

(1) Setup: Initialize the `EventPublisher` and mock listeners 
( `emailNotificationServiceMock`and `inventoryManagerMock`) using Mockito.
Create an `Order` object to be used in the test.

(2) Test case `testNumberOfInvocations`: Subscribe the mocks to the `EventPublisher`.
Publish the `Order` using `publishOrderToAllListeners`.
Verify that the `onOrderPlaced` method is called exactly once on each mock listener using the `verify` method.

## B. Content of invocations — `ArgumentCaptor`
The goal is to verify whether the contents of the order passed to the `onOrderPlaced` method are as expected 
using `ArgumentCaptor`.

The implementation involves setting up the test environment by initializing the `EventPublisher` and mock listeners 
using Mockito. An Order object is created for use in the test.

The test method `testContentOfInvocations` subscribes the mock listeners to the `EventPublisher` and publishes an order 
using `publishOrderToAllListeners`. It captures the argument passed to the `onOrderPlaced` method using `ArgumentCaptor`, 
ensuring it is invoked exactly once on each mock listener. The captured Order object is retrieved and verified to 
match the expected orderId and amount.

This approach ensures thorough testing by verifying both the invocation count and the correctness of the arguments 
passed to the `onOrderPlaced` method.

## C. Content of invocations — Increasing observability
The modified Code increased the observability of the classes to be tested (`EmailNotificationService`and `InventoryManager`) 
and facilitated testing. By introducing additional methods specifically designed for testing purposes, we enabled 
access to internal states or information that are not directly observable in normal usage scenarios. This approach enables 
more comprehensive testing of the behavior of the classes and facilitates the verification of the content of messages 
or actions performed.

## D. Comparison
### Technique B (ArgumentCaptor)

++ Advantages: (1) Suitable for scenarios where direct access to internal state or information is not feasible.
(2) Provides a structured way to capture and verify method arguments, enhancing test precision.

-- Disadvantages: (1) Can lead to more verbose test code, especially when dealing with complex method arguments.
(2) Not suitable for all situations, especially when the internal state of the class needs to be directly observed.

### Technique C (increased observability)
++ Advantages: (1) More comprehensive testing by allowing direct access to internal state or 
information of the classes under test. (2) Facilitates Verification of Behavior: Test cases can be designed to verify 
specific aspects of the behavior of the class, such as the content of messages generated, leading to better assurance 
in the accuracy of the implementation

-- Disadvantages: (1) Introducing methods solely for testing purposes may compromise the encapsulation of the classes 
by exposing internal details that are not intended for regular usage. (2) The additional testing methods need to be 
maintained alongside the main codebase, which may increase complexity and maintenance efforts.



