# Exercise 02: Cat Facts

## External Dependencies
The main external dependency in the `CatFactsRetriever` class is the `HttpUtil` class which calls an external API and
we also have a json dependency. 

The `get` method of the `HttpUtil` class has to be mocked because it creates an instance of an actual Http request and executes
it which is why we use a double in this case. The json libraries do not need to be mocked since they do not involve any 
external calls directly, only to the library which is very reliable and does not involve networking processes.

**Assumption**: Since we only need to write Unit tests the `HttpUtil` class will not be covered due to its instance being a mock.
If we wanted to test it properly, we would need to write integration tests which mock a web service so that the actual API call can be
made.

## Refactoring and testing
In order to make testing the mocked class `HttpUtil` easier and also adhere to the principle of dependency injection two things
were refactored:

- The `HttpUtil` instance is now injected via the constructor of the `CatFactsRetriever`
- The `get` method of the `HttpUtil` is not static anymore

Before each test, a small setup method called `setupClasses` initialized a mocked instance of the `HttpUtil` class as well
as an instance of the `CatFactsRetriever` class which receives the mock as input. That way each test has the new instances of
the main classes.

At first, the `retrieveRandom` method was tested by checking the retrieval of a fact and also the `IOException` in case of an
unavailable API service for example. The responses of the mocked Http request were provided via private methods and injected into the
"when"..."thenReturn"/"thenThrow" clauses of Mockito.

For the `retrieveLongest` method a similar approach was chosen. However, a small difference was that in this case we had edge
cases to be tested due to the additional `limit` parameter. 

**Assumption**: The `limit` parameter has to larger equal 0

In order for this condition to apply, a small pre-condition inside the method checks the factor and if not adhered to throws an
`IllegalArgumentException`. With that in mind, we tested *valid* fact lists with `limit` equaling 1 and 3 as well as an *invalid*
`limit` of 0 which throws the exception. Then, similar to before, the `IOException` was also tested. Last but not least, the 
implementation of the `retrieveRandom` method parses the response for json objects which is why we also included a test where no
json object is in the response, in which case an empty string `""` is returned.

## Disadvantages of using test doubles
In case of the `CatFactsRetriever` the disadvantages of using a mocked `HttpUtil` instance are as follows:

- We assume that the implementation of the `get` method is correct and provide return values based on what we think the `get` method would return
- We do not actually call the external dependency, in this case the Cat Facts API, which means if the url or the parameters of an API call change there
is a possibility of failure in the actual implementation because the API has somehow changed
- The mock is now directly linked to the implementation of the `CatFactsRetriever` class and making big changes inside the `CatFactsRetriever` class
could directly affect the amount of maintenance because chances are we need to change its dependency, the `HttpUtil` instance, as well.
