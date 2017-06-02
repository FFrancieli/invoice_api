## Invoice API

#### Dependencies
* Java 8
* Postgres
* [Gradle](https://gradle.org/)

#### Running tests

`gradle test` or
`./gradlew test` (if you don't have gradle installed you may run gradlew script instead. It may take some time when running for the first time.)

#### Building the application

`gradle build` or
`./gradlew build`

#### Running the application

`gradle createDB` or `./gradlew createDB` - creates database. Make sure postgres is up when executing this step.

`gradle bootRun` or `./gradlew bootRun` - starts the application

#### Documentation

Access http://localhost:8080/swagger-ui.html#!/invoice-controller/ for API documentation.
