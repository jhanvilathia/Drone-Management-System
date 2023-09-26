# Drone Management System

This application simulates the management of drone within a farm field. The field has a designated area of 10x10 square meters. The API endpoints can perform various functions on the drone.

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

