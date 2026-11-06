package com.klef.soa.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.soa.exam.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByName(String name);

    List<Attendance> findByType(String type);
}
