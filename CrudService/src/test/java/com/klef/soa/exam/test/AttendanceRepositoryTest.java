package com.klef.soa.exam.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import com.klef.soa.exam.entity.Attendance;
import com.klef.soa.exam.repository.AttendanceRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AttendanceRepositoryTest 
{
    @Autowired
    private AttendanceRepository repository;

    // Test 1 - Find All
    @Test
    void testFindAllAttendance() 
    {
        List<Attendance> list = repository.findAll();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        System.out.println("Total Attendance Records: " + list.size());
    }

    // Test 2 - Find by ID
    @Test
    void testFindAttendanceById() 
    {
        Optional<Attendance> attendance = repository.findById(1L);
        assertTrue(attendance.isPresent());
        assertTrue(repository.findById(100L).isEmpty());
        System.out.println("Name: " + attendance.get().getName());
    }

    // Test 3 - Find by Derived Query (findByName)
    @Test
    void testFindByName() 
    {
        List<Attendance> list = repository.findByName("Priya");
        assertFalse(list.isEmpty());
        assertEquals("Priya", list.get(0).getName());
        System.out.println("Type: " + list.get(0).getType());
    }

    // Test 4 - Count
    @Test
    void testAttendanceCount() 
    {
        long count = repository.count();
        assertEquals(3, count);
        System.out.println("Total Attendance in Database: " + count);
    }

    // Test 5 - Entity Details / Property-Based Result (findByType)
    @Test
    void testAttendanceDetails() 
    {
        Attendance attendance = repository.findById(3L).get();
        assertNotNull(attendance.getName());
        assertEquals(2, repository.findByType("Lecture").size());
        System.out.println("Name: " + attendance.getName());
    }
}
