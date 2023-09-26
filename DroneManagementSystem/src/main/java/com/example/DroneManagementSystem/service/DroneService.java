package com.example.DroneManagementSystem.service;

import com.example.DroneManagementSystem.model.Direction;
import com.example.DroneManagementSystem.model.Drone;
import com.example.DroneManagementSystem.model.DroneDTO;
import com.example.DroneManagementSystem.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    @Autowired
    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public DroneDTO registerDrone(DroneDTO droneDTO) {
        Drone drone = new Drone();
        drone.setX(droneDTO.getX());
        drone.setY(droneDTO.getY());
        drone.setDirection(droneDTO.getDirection());
        droneRepository.save(drone);
        return droneDTO;
    }

    public DroneDTO moveDrone(Long droneId, int newX, int newY) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();

            // Check if the move is valid
            String errorMessage = isValidMove(drone, newX, newY);

            if (errorMessage != null) {
                throw new IllegalArgumentException(errorMessage);
            }

            // Update the drone's position
            drone.setX(newX);
            drone.setY(newY);
            droneRepository.save(drone);
            return new DroneDTO(newX, newY, drone.getDirection());
        } else {
            throw new NoSuchElementException("Drone not found");
        }
    }

    public DroneDTO getCurrentPosition(Long droneId) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);

        if (optionalDrone.isPresent()) {
            Drone drone = optionalDrone.get();
            return new DroneDTO(drone.getX(), drone.getY(), drone.getDirection());
        } else {
            throw new NoSuchElementException("Drone not found");
        }
    }

    public String isValidMove(Drone drone, int newX, int newY) {
        // Check if the new position is within the 10x10 field boundaries
        if (newX < 0 || newX > 9 || newY < 0 || newY > 9) {
            return "Move is outside the field boundaries.";
        }

        // Check if the move from North to South or South to North is allowed
        if (drone.getDirection() == Direction.NORTH || drone.getDirection() == Direction.SOUTH) {
            if (drone.getX() != newX) {
                return "Invalid move: Drone must first be pointed to East or West before moving to South or North.";
            }
        }

        // Check if the move from East to West or West to East is allowed
        /*if (drone.getDirection() == Direction.EAST || drone.getDirection() == Direction.WEST) {
            if (drone.getY() != newY) {
                return "Invalid move: Drone must first be pointed to North or South before moving to East or West.";
            }
        }*/

        return null; // Move is valid
    }

    public List<DroneDTO> getAllDrones() {
        List<Drone> drones = droneRepository.findAll();
        return drones.stream()
                .map(drone -> new DroneDTO(drone.getX(), drone.getY(), drone.getDirection()))
                .collect(Collectors.toList());
    }

}
