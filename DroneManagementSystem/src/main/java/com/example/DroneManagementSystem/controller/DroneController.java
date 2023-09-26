package com.example.DroneManagementSystem.controller;

import com.example.DroneManagementSystem.model.DroneDTO;
import com.example.DroneManagementSystem.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drones")
public class DroneController {
    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping("/register")
    public ResponseEntity<DroneDTO> registerDrone(@RequestBody DroneDTO droneDTO) {
        DroneDTO registeredDroneDTO = droneService.registerDrone(droneDTO);
        return ResponseEntity.ok(registeredDroneDTO);
    }

    @PutMapping("/{droneId}/move")
    public ResponseEntity<DroneDTO> moveDrone(@PathVariable Long droneId, @RequestParam int newX, @RequestParam int newY) {
        DroneDTO updatedDroneDTO = droneService.moveDrone(droneId, newX, newY);
        return ResponseEntity.ok(updatedDroneDTO);
    }

    @GetMapping("/allDrones")
    public List<DroneDTO> getAllDrones() {
        List<DroneDTO> drones = droneService.getAllDrones();
        return drones;
    }

    @GetMapping("/{droneId}/position")
    public ResponseEntity<DroneDTO> getCurrentPosition(@PathVariable Long droneId) {
        DroneDTO currentPosition = droneService.getCurrentPosition(droneId);
        if (currentPosition != null) {
            return ResponseEntity.ok(currentPosition);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
