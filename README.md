| CS-665 | Software Design & Patterns |
|---|---|
| Name | Tiansheng Fu |
| Date | 10/19/2026 |
| Course | Fall 2026 |
| Assignment # | 2 |

# Assignment Overview

This project implements a delivery notification system for a shop that works with delivery drivers. The shop maintains a list of registered drivers, and each delivery request contains a request ID, a delivery address, and a product description.

When a delivery request is broadcast, the notification service notifies all registered drivers who are currently available. Unavailable drivers do not receive the request. The application does not require a graphical user interface. Its functionality is demonstrated by `Main.java` and verified through automated JUnit tests.

The primary goals are to apply object-oriented design principles, use an appropriate design pattern, minimize duplicated code, and create a simple implementation that can be maintained and extended in the future.

# GitHub Repository Link

https://github.com/tsf25/BU_CS665_Assignment_2_2026.git

# Implementation Description

For this assignment, the following software design principles were considered.

- **Flexibility:** The `Shop` class provides methods to register and remove driver objects. New driver instances can be added without changing the broadcast logic. The `DeliveryObserver` interface defines a common notification operation, and `Driver` implements that interface. If the system later requires separate driver implementation classes, the shop and notification service could be generalized to depend on shared interfaces rather than the concrete `Driver` class. This would allow new driver types to be introduced with fewer changes to existing code.

- **Simplicity and understandability:** Each class has a clear responsibility. `DeliveryRequest` stores and validates delivery information, `Shop` manages driver registration, `Driver` maintains availability and received requests, and `DeliveryNotificationService` broadcasts delivery requests. `Main` demonstrates the basic workflow. Keeping these responsibilities separate makes the code easier to read, test, debug, and maintain.

- **Avoiding duplicated code:** The notification service uses one broadcast method and one loop to process registered drivers, instead of writing separate notification methods for different driver categories. `DeliveryRequest` also uses a shared validation helper for its required string fields. Centralizing common behavior reduces inconsistent implementations and makes future changes easier to maintain.

- **Design patterns:** The project uses the **Observer Pattern** because one delivery request may need to be announced to multiple registered drivers. The `DeliveryObserver` interface defines the notification operation, and `Driver` implements that operation. The notification service coordinates the broadcast and checks driver availability before sending each notification.

## Why We Chose Only the Observer Pattern

The Observer Pattern directly supports the core requirement of this assignment: broadcasting a delivery request to multiple available drivers. It provides a clear way to represent notification behavior without adding unnecessary complexity to the application.

We considered keeping the design limited to this one pattern because the current requirements do not need multiple interchangeable delivery-selection algorithms or a complex state-management system. Adding patterns without a clear use case would make the design harder to understand without providing a meaningful benefit. The Observer Pattern is sufficient for the current notification workflow.

## Composition (Has-a Relationships) Instead of Class Inheritance

The design favors composition and has-a relationships instead of creating a hierarchy of concrete classes through inheritance.

- `DeliveryNotificationService` has a reference to a `Shop`.
- `Shop` maintains a collection of `Driver` objects.
- `Driver` implements the `DeliveryObserver` interface.

There is no class-to-class `extends` relationship in the current implementation. Implementing an interface is different from inheriting implementation from a parent class.

This design follows the principle of keeping responsibilities separate. The shop manages its registered drivers, while the notification service handles the broadcast. These relationships avoid an unnecessary inheritance hierarchy and make the structure easier to understand.

## Main Class Responsibilities

The implementation consists of the following core types:

- **`DeliveryObserver`** — Defines the notification operation for observers.
- **`Driver`** — Represents a driver, tracks availability, and records received delivery requests.
- **`Shop`** — Registers and removes drivers and provides access to the registered driver collection.
- **`DeliveryRequest`** — Represents an immutable delivery request containing validated details.
- **`DeliveryNotificationService`** — Broadcasts a delivery request to registered drivers who are available.
- **`Main`** — Demonstrates one delivery request being sent to an available driver.

The UML class diagram documents the five core types and their essential attributes, methods, and relationships.

## Testing and Iterative Improvement

The project includes five JUnit test cases in `DeliverySystemTest.java`. They cover the following behaviors:

1. Broadcasting a delivery request to five registered drivers.
2. Skipping an unavailable driver.
3. Preventing duplicate driver registration from causing duplicate notifications.
4. Ensuring a removed driver is not notified.
5. Verifying that a driver can receive multiple delivery requests.

These tests verify the core notification workflow and important registration and availability rules without requiring a graphical user interface.

# Maven Commands

## Requirements

- Java JDK 1.8
- Apache Maven
- Eclipse IDE or another Java development environment

The Maven compiler is configured to compile against the Java 8 API and produce Java 8-compatible bytecode.

Check the Java and Maven versions with:

```bash
java -version
javac -version
mvn -version
```

Make sure the runtime, compiler, and Maven environment are configured appropriately for the Java 8 requirement.

Run the following Maven commands from the project root directory, where `pom.xml` is located. In Eclipse, you can run the same Maven goals by right-clicking the project, selecting **Run As → Maven build...**, entering the goal, and clicking **Run**.

## Compile

Run:

```bash
mvn clean compile
```

This removes previous build output and compiles the production Java source code.

## Run the Demonstration

Run:

```bash
mvn clean compile exec:java
```

This compiles the project and runs the configured main class, `edu.bu.met.cs665.Main`.

## JUnit Tests

JUnit is used to verify that the application's behavior matches the requirements. The tests cover broadcasting, driver availability, duplicate registration, driver removal, and handling multiple delivery requests.

Run:

```bash
mvn clean test
```

A successful test run should report zero failures and zero errors. The Surefire test reports are generated under `target/surefire-reports`.

## Test Coverage

The project includes JaCoCo configuration for measuring which parts of the application are exercised by the tests.

To run the tests and generate the coverage report, use:

```bash
mvn clean verify
```

When the JaCoCo report is generated, open `target/site/jacoco/index.html` in a browser. Test coverage helps identify code paths that may need additional tests, but coverage alone does not guarantee correctness.

## SpotBugs

SpotBugs analyzes compiled Java code to identify potential defects, such as suspicious logic or unsafe use of objects.

Run the analysis with:

```bash
mvn spotbugs:spotbugs
```

Review the SpotBugs findings rather than relying on the build status alone. The project was also checked using the SpotBugs interface, which displayed zero defects.

For more information: https://spotbugs.readthedocs.io/en/latest/maven.html

## Checkstyle

Checkstyle checks Java source code against the configured Google Java Style rules. It helps maintain consistent indentation, formatting, naming, and documentation.

Generate the HTML report with:

```bash
mvn checkstyle:checkstyle
```

The report is located at `target/site/checkstyle.html`.

Checkstyle is configured to report violations without necessarily failing the Maven build. Therefore, review the report even when Maven displays `BUILD SUCCESS`. The latest report reviewed for this project listed no violations.

# Potential Future Improvements

Several improvements could extend the application in future assignments:

- **Support additional driver implementations:** Generalize the shop and notification service to use shared interfaces so that different driver types can be introduced with fewer changes.
- **Delivery acceptance and status tracking:** Allow drivers to accept requests and track whether deliveries are pending, accepted, or completed.
- **Smarter delivery selection:** Introduce selection criteria such as distance, workload, or delivery area if the requirements expand beyond broadcasting to every available driver.
- **Persistent storage:** Save driver registrations and delivery requests so that the information can be recovered after the application closes.
- **Additional tests:** Add tests for invalid request details, blank driver identifiers, null arguments, an empty shop, and changes in driver availability.

These improvements would build on the existing design while preserving its focus on simple responsibilities and maintainable code.

# References

- Observer Pattern in Java: https://refactoring.guru/design-patterns/observer/java/example
- Google Java Style Guide: https://google.github.io/styleguide/javaguide.html
- JUnit 4: https://junit.org/junit4/
- Professor-provided project template: https://github.com/edorsini/cs-665-project-template
