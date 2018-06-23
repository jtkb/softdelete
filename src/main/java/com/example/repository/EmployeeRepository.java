package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    @Modifying
    @Query("update Employee employee set employee.isDeleted = true where employee.id in :ids")
    Integer softDelete(@Param("ids") final List<Long> ids);
}
