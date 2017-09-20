# API Reference Implementation

## Prerequisites

To run this reference implementation, you must have downloaded and installed:

* Java 8
* Postman

## Running and playing around with this reference implementation
1. Ensure Java 8 is installed and set to be the default java version (`java -version` will tell you this.
   `EXPORT JAVA_HOME=$(/usr/libexec/java_home -v 8)` will set it to be the active version)
2. Make sure Postman is installed. If you are running it for the first time, note that you don't need to create an
   account. There is a link to skip sign-in at the bottom of the initial page.
3. clone this repo!
4. run the app with the command `./gradlew bootRun`
5. if the app is running successfully, you should see a message in the logs similar to the following
  `INFO 30958 --- [ main] c.t.d.r.RestApiRefImplApplication        : Started RestApiRefImplApplication in 2.822 seconds (JVM running for 3.195)`
6. At this point, you can either exercise the endpoints through your client of choice (against `localhost:8080`, or
   import the Postman collection from the `API Reference Implementation.postman_collection.json` file in the repo.


### Documentation

https://app.swaggerhub.com/apis/thoughtworks-aelab/API-reference-implementation/1.0.0

### Postman Collection

To upload the collection, take the postman_collection JSON file in this repo and 'Import' into Postman.
