package com.klef.soa.exam.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.exam.entity.Attendance;
import com.klef.soa.exam.service.AttendanceService;

@RestController
@RequestMapping("/")
public class AttendanceController 
{
    @Autowired
    private AttendanceService service;
    @GetMapping("/")
    public String home() 
    {
        return "Attendance Project Running";
    }
    
    @PostMapping("/Add")
    public ResponseEntity<Attendance> add(@RequestBody Attendance a) 
    {
        return new ResponseEntity<>(service.addAttendance(a), HttpStatus.CREATED);
    }

    @GetMapping("/DisplayAll")
    public ResponseEntity<List<Attendance>> displayAll() 
    {
        return ResponseEntity.ok(service.getAllAttendance());
    }

    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) 
    {
        if (service.deleteAttendance(id)) 
        {
            return ResponseEntity.ok("Attendance deleted");
        }
        return new ResponseEntity<>("Attendance not found with id : " + id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/Update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Attendance a) 
    {
        Attendance updated = service.updateAttendance(id, a);
        if (updated != null) 
        {
            return ResponseEntity.ok(updated);
        }
        return new ResponseEntity<>("Attendance not found with id : " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/Display/{id}")
    public ResponseEntity<?> displayById(@PathVariable Long id) 
    {
        Attendance attendance = service.getAttendanceById(id);
        if (attendance != null) 
        {
            return ResponseEntity.ok(attendance);
        }
        return new ResponseEntity<>("Attendance not found with id : " + id, HttpStatus.NOT_FOUND);
    }
}
