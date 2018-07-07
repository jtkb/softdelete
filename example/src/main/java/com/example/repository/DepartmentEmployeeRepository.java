package com.example.repository;

import com.example.entity.DepartmentEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long>
{
    @Modifying
    @Query("UPDATE DepartmentEmployee de SET de.isDeleted = true where de.departmentId = :departmentId and de.employeeId in :employeeIds"
            + " and de.isDeleted = false")
    void softDeleteRecords(@Param("departmentId") Long departmentId, @Param("employeeIds") List<Long> employeeIds);
}
