package com.example.DroneManagementSystem;

import com.example.DroneManagementSystem.model.Direction;
import com.example.DroneManagementSystem.model.Drone;
import com.example.DroneManagementSystem.model.DroneDTO;
import com.example.DroneManagementSystem.repository.DroneRepository;
import com.example.DroneManagementSystem.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneService droneService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterDrone() {

        // Test for Registering a Drone
        DroneDTO droneDTO = new DroneDTO(1, 2, Direction.NORTH);
        Drone drone = new Drone();
        drone.setX(1);
        drone.setY(2);
        drone.setDirection(Direction.NORTH);

        when(droneRepository.save(any(Drone.class))).thenReturn(drone);

        DroneDTO registeredDrone = droneService.registerDrone(droneDTO);

        verify(droneRepository).save(any(Drone.class));

        assertEquals(1, registeredDrone.getX());
        assertEquals(2, registeredDrone.getY());
        assertEquals(Direction.NORTH, registeredDrone.getDirection());
    }

    @Test
    public void testInvalidMoveOutsideBoundaries() {

        // Test for Invalid Move outside Boundaries
        Drone drone = new Drone();
        drone.setDirection(Direction.EAST);
        String errorMessage = droneService.isValidMove(drone, 12, 5);

        assertEquals("Move is outside the field boundaries.", errorMessage);
    }

    @Test
    public void testValidMoveWithinBoundaries() {

        // Test for Valid Move within Boundaries
        Drone drone = new Drone();
        drone.setDirection(Direction.WEST);
        String errorMessage = droneService.isValidMove(drone, 5, 6);

        assertNull(errorMessage);
    }

    @Test
    public void testInvalidMoveNorthToSouth() {

        // Test for Invalid Move in NORTH or SOUTH
        Drone drone = new Drone();
        drone.setDirection(Direction.NORTH);
        String errorMessage = droneService.isValidMove(drone, 5, 4);

        assertEquals("Invalid move: Drone must first be pointed to East or West before moving to South or North.", errorMessage);
    }

    @Test
    public void testValidMoveInAllowedDirection() {

        // Test for Valid Move in Allowed Direction - EAST or WEST
        Drone drone = new Drone();
        drone.setDirection(Direction.EAST);
        String errorMessage = droneService.isValidMove(drone, 6, 5);

        assertNull(errorMessage);
    }

    @Test
    public void testGetDroneById() {

        // Create a mock Drone to get CurrentPosition and Direction
        Long droneId = 1L;
        Drone existingDrone = new Drone();
        existingDrone.setId(droneId);
        existingDrone.setX(4);
        existingDrone.setY(5);
        existingDrone.setDirection(Direction.SOUTH);

        when(droneRepository.findById(droneId)).thenReturn(Optional.of(existingDrone));

        DroneDTO droneInfo = droneService.getCurrentPosition(droneId);

        verify(droneRepository).findById(droneId);

        assertEquals(4, droneInfo.getX());
        assertEquals(5, droneInfo.getY());
        assertEquals(Direction.SOUTH, droneInfo.getDirection());
    }

    @Test
    public void testGetDroneByIdNotFound() {

        // Create a mock Drone for Not Found case
        Long droneId = 1L;
        when(droneRepository.findById(droneId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            droneService.getCurrentPosition(droneId);
        });

        verify(droneRepository).findById(droneId);
    }

    @Test
    public void testGetAllDrones() {

        // Create some mock Drone entities
        Drone drone1 = new Drone();
        drone1.setX(1);
        drone1.setY(2);
        drone1.setDirection(Direction.NORTH);

        Drone drone2 = new Drone();
        drone2.setX(3);
        drone2.setY(4);
        drone2.setDirection(Direction.WEST);

        when(droneRepository.findAll()).thenReturn(List.of(drone1, drone2));

        List<DroneDTO> allDrones = droneService.getAllDrones();

        verify(droneRepository, times(1)).findAll();

        assertEquals(2, allDrones.size());
    }

}
