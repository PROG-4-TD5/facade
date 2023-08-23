package com.example.prog4.repository;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.base.entity.Employee;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository {
  Employee findById(String id);

  Employee save(Employee employee);

  List<Employee> findByCriteria(EmployeeFilter filter);

  com.example.prog4.repository.cnaps.entity.Employee findByEndToEndId(String endToEndId);
}
