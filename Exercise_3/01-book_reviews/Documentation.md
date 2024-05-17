# Documentation Exercise 1: Book Reviews
### A. Get high-rated books
#### 1. External Dependencies
The external dependencies in the BookManager are the `DatabaseConnection` and the `BookRatingsFetcher`.
Even though `DatabaseConnection` can be seen to act as an adapter for fetching data from the database, `BookRatingsFetcher` is not abstracted behind an interface.
Therefore, no implementation of the Hexagonal Architecture pattern can be found.
This means that `DatabaseConnection` and `BookRatingsFetcher` should be tested with doubles.
The rationale behind choosing test doubles to test the external dependencies is that interacting with real databases can be slow, brittle, and can lead to non-deterministic tests.
Isolating the unit tests for BookManager from these external systems allows the unit tests to focus on the behavior of the BookManager class, rather than external factors like database availability or network issues.
#### 2. Refactoring and Tests
To be able to inject the mocks into the `BookManager`, it was necessary to create a constructor and to extract the dependency creation of `BookRatingsFetcher`.
Compared to this change, `BookRatingsFetcher` did not have to be changed, since it is already implemented in a way that it takes an instance of `DatabaseConnection` in a constructor.
Now, when someone initializes the `BookManager`, this person needs to pass in a new instance of `BookRatingsFetcher` (with another new instance of `DatabaseConnection`) in the constructor.
Simultaneously, mocks and stubs can be injected as dependencies during testing.

After adjusting the code to allow dependency injections, it was ready to be tested.
First, a stub for the `BookRatingsFetcher` had to be created that can be injected into the `BookManager`.
It takes a predefined list of `Books` in its constructor, which will be returned when the method `all` is called.
This is going to be useful for setting up the tests, as each test can therefore be run with a varying amount of different types of books.
Implementing the `BookManagerTest` class includes the `testSetup` method that prepares the list of books (`allBooks`) that shall be returned and initializes the fetcher-injected `BookManager` for each test.
Then, testcases for empty lists, lists being null, lists containing one book (one low and one high rating each), and a list containing mixed books (low and high ratings) were written.
Each of those tests can modify the beforehand prepared list of books (`allBooks`) to simulate potential database responses.
With those tests, the method has been successfully tested with all types of coverages reaching 100%.
#### 3. Disadvantages
A clear disadvantage of using mocks or stubs is the significant coupling with the production code.
In this case, the `BookRatingsFetcherStub` depends on the implementation of `BookRatingsFetcher` itself, meaning that additions, changes, deletions, etc. need to be mirrored by the stub class.
Otherwise, if the behavior of the `BookRatingsFetcher` test double deviates from the actual behavior of fetching data from the database, it may lead to false positives or negatives in tests.
Determining how much the tests need to know about the production code to test it properly is therefore a significant challenge.
Additionally, introducing and maintaining test doubles alongside the production code can add complexity to the codebase.
For instance, if the `BookManager` class undergoes changes in its method signatures or behavior, the test doubles used in its unit tests need to be updated accordingly.
If not properly maintained, this can lead to inconsistencies between tests and actual behavior.
Lastly, test doubles may lack realism compared to real dependencies.
In the `BookManager` class, if the test double for `BookRatingsFetcher` doesn't accurately simulate the behavior of fetching data from the database, it may result in incomplete testing.
For instance, if the stub always returns the same dataset regardless of input or if it doesn't handle error conditions properly, it may not fully exercise the error-handling logic in BookManager.
### B. Get list of all authors
To implement the new feature, a new `author` attribute had to be added to the `Book` class, complemented by a getter for that field.
Besides now being able to instantiate a book with the author, instantiating it the same way as before is still possible, since an overloaded constructor has been added.
Retrieving unique authors with the new method `uniqueAuthors` is similar to `highRatedBooks`, by having removed the filter for the rating and by having added selecting distinct author attributes.
#### 1. External Dependencies
Having implemented the new feature, the external dependencies stay exactly the same.
#### 2. Refactoring and Tests
Since the external dependencies are the same and no new dependencies have been added, it is not necessary to refactor any code.
This also means that the existing tests do not have to be changed.
The previous implementation of the `testSetup` can be reused (with the addition of a new list for expected unique authors) and the style for writing tests can be continued.
Therefore, testcases for empty lists, lists of books without authors, lists containing one book with an author, and lists containing two books with authors (one with unique authors and one with duplicate authors) were written.
Each of those tests basically acts the same as the tests for `highRatedBooks`, with the addition that each test can predefine the `expectedUniqueAuthors` field.
With those tests, the additional method has been successfully tested with all types of coverages reaching 100% in the `BookManager` class.
#### 3. Disadvantages
Since the external dependencies and the mocking stay the same, the risks are the same as well.