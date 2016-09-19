## Running the service
* To build the jar - `./gradlew clean build makeJar`
* To run - `java -jar build/libs/merchant-standalone.jar server`

### Example curl request
`curl -X POST -d '{"description":"my offer", "prices": {"GBP":"3.45", "USD": "5.67"}}' -H 'content-type:application/json' http://localhost:8080/offer -v`

A reference to the offer in returned in the location header.

## Running the tests
* ./gradlew test

## Comments
* At the moment the service is backed by a Map to store the offers to show the concept. If I was to continue this service then I would create an implementation of IOfferRepository which is backed by a database.
* The prices are currently done as key value pairs. I would potentially change this to be an array for example - prices: [{"currency": "GBP", "price":"3.45"}, {"currency": "USD", "price": "5.67"}].
* If I was to continue this I would add validation on the prices/currencies to ensure that only valid currentcies can be entered.
