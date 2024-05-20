# Documentation Exercise 8: Bus Tracking

#### A. Accuracy of Location Updates
For this purpose, we setup our test environment using the `beforeRach` method. After which we start testing whether the `updateBusLocation` 
method is getting called correctly and if it is updating the location accurately by calling the `updateMap` function of Map Service.
To verify it accordingly, we first updated the location one time and verified if the `updateMap` method of Map Service was invoke one time. 
Once it was found out to be correct, we then tested the whole process again and called the method 3 times more.
It was found out that the location was getting updated accurately.


#### B. Notification of Key Events
To test whether the notification was getting sent correctly, we make use of `AugumentCaptor` as mentioned by the requirements. 
We carried out this process by updating the location of the bus, and then capturing the notification that is sent via the `notifyPassengers`
method of Notification Service.
For this test, we used the same mocks that we used in the previous test. It was found that the notification of events was successfully getting
sent whenever the bus was getting arrived at a waypoint. We verified the notifications using an assert statement.


#### C. Response to GPS Signal Loss
The test for cases where the GPS was down were written and they are successfully getting executed. We used a simple case, where 
the `getCurrentLocation` of GPS Service was returning null and used that to test whether the Bus Tracker would be able to distinguish the result. 
It was accurate in finding out the GPS is down and successfully showed an error message about signal loss.


#### D. Comparison
If we consider both, event-driven and direct message calls, according to the current scenario. Then there are more benefits of 
an event-driven approach, as the whole process of getting notified about updates would be more easy and automated as compared to a 
direct message approach and most importantly there would be less noise. But on the other hand, an event-driven approach could be a bit 
complex to implement when compared to direct message calls. But for a system which notifies about location changes, an event-driven 
approach would be much more effective and preferable.
