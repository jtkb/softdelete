package com.example.repository;

import com.example.entity.DeletedEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletedEmployeeRepository extends JpaRepository<DeletedEmployee, Long>
{
}
