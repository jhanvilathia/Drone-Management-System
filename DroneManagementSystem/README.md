# Drone Management System

## Objective:

- **Create a Spring Boot REST-based application that simulates the management of a drone within a farm field. The field has a designated area of 10x10 square meters. The drone should be able to move within the field, change its position, and provide its current position.**

## Requirements:

### Register a Drone:

Create an API endpoint that allows registering a new drone.
Each drone should have the following attributes:

- **Position (X, Y) within the field**
- **Initial direction (North, East, West, South)**

### Move the Drone:

Create an API endpoint that allows the drone to move to a new position (X, Y) within the field. The drone should adhere to the following rules:

- **The drone cannot move outside the 10x10 field boundaries**
- **If the new position would exceed the boundaries, return an error**
- **The drone cannot move directly from North to South or from South to North. It must first be pointed to East or West and then move to South or North, respectively**

### Get Current Drone Position:

Create an API endpoint that allows retrieving the current position and direction of the drone.

## Constraints:

The field is a 10x10 square grid (X and Y coordinates range from 0 to 9).
The drone's initial position and direction can be set during registration.

## Execution

To start the application just run:

``` bash
mvn clean install
```
``` bash
mvn spring-boot:run
```

## Run Tests

To run the tests:

``` bash
mvn test
```

## APIs

### Register a Drone

<b>Endpoint:</b> `POST /drones/register´ <br/>
<b>Description:</b> Register a new Drone with a specified X, Y value in the field with an initial Direction(EAST, WEST, NORTH, SOUTH).

### Move a Drone

<b>Endpoint:</b> `PUT /drones/{droneId}/move´ <br/>
<b>Description:</b> Move the specified drone by providing the droneId and X and Y as Request Parameters. The drone will move with specific constraints and return a specified message if the move is invalid.

### Get Drone Position

<b>Endpoint:</b> `GET /drones/{droneId}/position´ <br/>
<b>Description:</b> Get the current drone position by providing the droneId.

### Get all Registered Drones

<b>Endpoint:</b> `GET /drones/allDrones´ <br/>
<b>Description:</b> Get all the registered Drones.

## Postman Collection
<a href="https://www.postman.com/payload-astronomer-65399392/workspace/drone-management-system/collection/16336313-306f01cf-f5a5-481e-a02a-3eb75e97652c?action=share&creator=16336313">Postman Collection </a>

