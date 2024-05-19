# Documentation Exercise 6: Movie Streaming

#### 1. Use of Doubles for FileStreamService and CacheService
During our testing we identified two external dependencies that should be mocked in order to ease the complexity of testing process,
which were `CacheService` and `FileStreamService`. Other than these two dependencies, there were no other that was required
to be mocked. The `MovieMetadata` and `StreamingDetails` were entities that could easily be implemented. Similarly, the `MovieStreamingManager`
also has simple methods, due to this it was not required to mock them.

For the mock of `FileStreamService`, a new method `ValidToken` was written, as it was within the requirements of the system.
After implementing all the abstract methods of these dependencies in the mock, the tests for these mocks were created in order
to ensure that they are working as expected by us, and they are ready to be integrated with other classes.

For the test, we did basic direct interaction testing with both of the mocks and made sure that they are showing the correct data.
For these tests, we also once again added the `testSetup` which would run before every test for making the whole process as smooth and
easy to understand as possible.


#### 2. Mocking File System and Cache Interactions
After testing the mocks individually, we crafted tests that allowed us to test whether they are integrated correctly with each other and
with the `MovieStreamingManager` class. For this purpose we first wrote a test that would test whether the stream data of the movie getting
retrieved by `MovieStreamingManager` is up to date and valid.

The documentation of the system also has two additional methods for `MovieStreamingManager`, which were `updateMovieMetadata` and `validStreamingToken`.
We added these additional methods into the manager class. The integration tests for these new methods were also written and verified afterwards.

All the results from these tests showed that we were successful in mocking the dependencies and then integrating them with the class.


#### 3. Handling of Failures
In our mocks for dependencies we introduced a basic check variable `reachable` that was acting as a switch whether its service
is down or up. This allowed us to properly test the failure situation and return the `RuntimeException` error. Similarly, failures
for each method were introduced and tested, as it was necessary to make sure the system is able to handle the failures.

Using a similar pattern of introducing a `reachable` check, we tested the mocks for `CacheService` and `FileStreamService` downtime.

Our test correctly tested the system and throwed the error in situations where any of the two services were down. This allowed us to
successfully mock the scenarios where service was not available for any particular reason and correctly validate their downtime.
