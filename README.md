# Vacation Properties Rental

This project implements a CRUD service able to get, set, update and delete Property Listings.
A listing document is in the form
```json
{
  "listing": {
    "id": "5e22a83a-6f4f-11e6-8b77-86f30ca893d3",
    "contact": {
      "phone": "15126841100",
      "formattedPhone": "+1 512-684-1100"
    },
    "address": {
      "address": "1011 W 5th St",
      "postalCode": "1011",
      "countryCode": "US",
      "city": "Austin",
      "state": "TX",
      "country": "United States"
    },
    "location": {
      "lat": 40.4255485534668,
      "lng": -3.7075681686401367
    }
  }
}
```

### Assumptions

#### Validation
* Address: all fields are mandatory except for `postalCode` and `state` as non US countries might not have 
            those information.
* Contact: everything mandatory
* Location: non mandatory

### Scaling
The service cannot scale at the moment as the data is not centrally stored: each instance will have its own data.
In order to be able to successfully scale the application, a centralised database is neede (based on the type of data, 
probably a noSQL DB like mongodb or dynamoBD).
           

### Running the Application

* Running as a packaged application
Application can be packaged using maven `mvn clean package` and then run using this java command

    ```bash
    $ java -jar target/vacation-properties-rental-0.0.1-SNAPSHOT.jar
    ```


* Using the Maven plugin
The Spring Boot Maven plugin includes a run goal which can be used to quickly compile and run the application.

    ```bash
    $ export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M
    $ mvn spring-boot:run
    ```

