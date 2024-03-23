package com.example.olympandreyzmushko.controller;

import com.example.olympandreyzmushko.controller.dto.request.AddDirectorInfoRequest;
import com.example.olympandreyzmushko.controller.dto.request.UpdateDirectorInfoRequest;
import com.example.olympandreyzmushko.exception.NoSuchDirectorException;
import com.example.olympandreyzmushko.model.DirectorEntity;
import com.example.olympandreyzmushko.service.abstraction.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Andrew Zmushko (andrewzmushko@gmail.com)
 */
@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    private DirectorService directorService;

    @Autowired
    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping
    public ResponseEntity<?> getAllDirector() {
        var allDirectors = directorService.getAllDirectors();
        return ResponseEntity.ok(Map.of("list", allDirectors));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDirectorById(@PathVariable("id") Long id) {
        DirectorEntity director;
        try {
            director = directorService.getDirectorById(id);
        } catch (NoSuchDirectorException e) {
            return new ResponseEntity<>(String.format("Cannot find director with id=%s", id), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("director", director));
    }

    @PostMapping
    public ResponseEntity<?> addDirectorInfo(@RequestBody AddDirectorInfoRequest request) {
        DirectorEntity createdDirectorInfo;
        try {
            createdDirectorInfo = directorService.addDirector(request.getDirector());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("director", createdDirectorInfo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDirectorInfo(@PathVariable("id") Long id, @RequestBody UpdateDirectorInfoRequest request) {
        DirectorEntity updatedDirectorInfo;
        try {
            updatedDirectorInfo = directorService.updateDirectorInfo(id, request.getDirector());
            if (updatedDirectorInfo == null) {
                return ResponseEntity.internalServerError().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return ResponseEntity.ok(Map.of("director", updatedDirectorInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDirectorInfo(@PathVariable("id") Long id) {
        try {
            boolean deletedDirectorInfo = directorService.deleteDirectorInfo(id);
            if (!deletedDirectorInfo) {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("status", 500, "reason", e.getMessage()));
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
