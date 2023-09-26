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
