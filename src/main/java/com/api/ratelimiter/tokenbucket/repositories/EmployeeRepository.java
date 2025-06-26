package com.api.ratelimiter.tokenbucket.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.ratelimiter.tokenbucket.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>, JpaSpecificationExecutor<Employee>{

	Optional<Employee> findByEmpId(String empId);
}
