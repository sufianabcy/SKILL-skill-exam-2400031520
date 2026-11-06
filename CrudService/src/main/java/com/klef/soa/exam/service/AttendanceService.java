package com.klef.soa.exam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.soa.exam.entity.Attendance;
import com.klef.soa.exam.repository.AttendanceRepository;


@Service
public class AttendanceService 
{
    @Autowired
    private AttendanceRepository repository;

    public Attendance addAttendance(Attendance a) 
    {
        a.setAttendanceId(null);
        return repository.save(a);
    }

    public List<Attendance> getAllAttendance() 
    {
        return repository.findAll();
    }

    public boolean deleteAttendance(Long id) 
    {
        if (!repository.existsById(id)) 
        {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public Attendance updateAttendance(Long id, Attendance a) 
    {
        Attendance existing = repository.findById(id).orElse(null);
        if (existing == null) 
        {
            return null;
        }
        existing.setName(a.getName());
        existing.setType(a.getType());
        existing.setStatus(a.isStatus());
        existing.setLocation(a.getLocation());
        return repository.save(existing);
    }

    public Attendance getAttendanceById(Long id) 
    {
        return repository.findById(id).orElse(null);
    }

    public List<Attendance> getAttendanceByName(String name) 
    {
        return repository.findByName(name);
    }

    public List<Attendance> getAttendanceByType(String type) 
    {
        return repository.findByType(type);
    }
}
