package com.example.DroneManagementSystem;

import com.example.DroneManagementSystem.controller.DroneController;
import com.example.DroneManagementSystem.model.Direction;
import com.example.DroneManagementSystem.model.DroneDTO;
import com.example.DroneManagementSystem.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterDrone() {

        // Create a mock DroneDTO
        DroneDTO mockDroneDTO = new DroneDTO(1, 2, Direction.NORTH);

        when(droneService.registerDrone(mockDroneDTO)).thenReturn(mockDroneDTO);

        ResponseEntity<DroneDTO> response = droneController.registerDrone(mockDroneDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockDroneDTO, response.getBody());
    }

    @Test
    public void testMoveDrone() {

        // Mock request parameters
        Long droneId = 1L;
        int newX = 3;
        int newY = 4;

        DroneDTO mockUpdatedDroneDTO = new DroneDTO(3, 4, Direction.EAST);

        when(droneService.moveDrone(droneId, newX, newY)).thenReturn(mockUpdatedDroneDTO);

        ResponseEntity<DroneDTO> response = droneController.moveDrone(droneId, newX, newY);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUpdatedDroneDTO, response.getBody());
    }


    @Test
    public void testGetAllDrones() {

        // Mock list of DroneDTOs
        List<DroneDTO> mockDroneDTOs = new ArrayList<>();
        mockDroneDTOs.add(new DroneDTO(1, 2, Direction.NORTH));
        mockDroneDTOs.add(new DroneDTO(3, 4, Direction.WEST));

        when(droneService.getAllDrones()).thenReturn(mockDroneDTOs);

        List<DroneDTO> response = droneController.getAllDrones();

        assertEquals(mockDroneDTOs, response);
    }

    @Test
    public void testGetCurrentPosition() {

        // Mock DroneDTO to get Current Position
        Long droneId = 1L;
        DroneDTO mockCurrentPosition = new DroneDTO(3, 4, Direction.EAST);

        when(droneService.getCurrentPosition(droneId)).thenReturn(mockCurrentPosition);

        ResponseEntity<DroneDTO> response = droneController.getCurrentPosition(droneId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCurrentPosition, response.getBody());
    }

    @Test
    public void testGetCurrentPositionNotFound() {

        // Mock non existent DroneDTO
        Long nonExistentDroneId = 99L;

        when(droneService.getCurrentPosition(nonExistentDroneId)).thenReturn(null);

        ResponseEntity<DroneDTO> response = droneController.getCurrentPosition(nonExistentDroneId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }



}
