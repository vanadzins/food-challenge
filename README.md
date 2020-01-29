## food-challenge

A RESTful Spring-boot application with two GET endpoints:
* To GET food with an ID: /hello/{id}
* To search for food with a query: /hello?query={search_}&page={page_number}
(page parameter is optional)



Requirements
* java 11+
* maven

To start the application:
1) Clone the repository
2) Run `mvn spring-boot:run` in the root folder

There's a trivial UI html created where it is possible to call the API.

To launch the UI open [localhost:8080](http://localhost:8080/) after starting the application.

TODO:

There a several improvements that could be made, like:
* Adding logging
* Using dto's as return objects in the controller
* Creating meaningful exceptions and handling them
* Documenting the public API
* Etc
