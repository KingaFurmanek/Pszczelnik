package org.beeapp.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.beeapp.backend.api.external.HiveDTO;
import org.beeapp.backend.api.internal.Users;
import org.beeapp.backend.service.HiveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/hive")
@RequiredArgsConstructor
public class HiveController {

    private final HiveService hiveService;

    @GetMapping("/get")
    public ResponseEntity<List<HiveDTO>> getUsersHives(Authentication authentication) {
        Users currentUser = (Users) authentication.getPrincipal();
        List<HiveDTO> hives = hiveService.getUsersHives(currentUser);
        return ResponseEntity.ok(hives);
    }

    @GetMapping("/get/{hiveId}")
    public ResponseEntity<HiveDTO> getHiveInfo(@PathVariable("hiveId") Integer hiveId) {
        HiveDTO hive = hiveService.getHiveInfo(hiveId);
        return ResponseEntity.ok(hive);
    }

    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public ResponseEntity<String> addHive(Authentication authentication,
                                          @RequestParam("data") String data,
                                          @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        Users currentUser = (Users) authentication.getPrincipal();

        // JSON -> HiveDTO
        ObjectMapper objectMapper = new ObjectMapper();
        HiveDTO addHiveRequestDTO = objectMapper.readValue(data, HiveDTO.class);

        try {
            hiveService.addHive(addHiveRequestDTO, currentUser, file);
            return ResponseEntity.ok("Hive added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding hive: " + e.getMessage());
        }
    }

    @PutMapping(value = "/edit/{hiveId}", consumes = {"multipart/form-data"})
    public ResponseEntity<String> editHive(@PathVariable Integer hiveId,
                                          @RequestParam("data") String data,
                                          @RequestParam(value = "file", required = false) MultipartFile file) throws JsonProcessingException {
        // JSON -> HiveDTO
        ObjectMapper objectMapper = new ObjectMapper();
        HiveDTO addHiveRequestDTO = objectMapper.readValue(data, HiveDTO.class);

        try {
            hiveService.editHive(hiveId,addHiveRequestDTO,file);
            return ResponseEntity.ok("Hive edited successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error editing hive: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{hiveId}")
    public ResponseEntity<String> deleteHive(@PathVariable Integer hiveId) {
        try {
            hiveService.deleteHive(hiveId);
            return ResponseEntity.ok("Hive deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error editing hive: " + e.getMessage());
        }
    }
}
