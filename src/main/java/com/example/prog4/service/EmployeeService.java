package com.example.prog4.service;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.EmployeeRepository;
import com.example.prog4.repository.base.entity.Employee;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  private final EmployeeRepository repository;

  public EmployeeService(EmployeeRepository repository) {
    this.repository = repository;
  }


  public Employee getOne(String id) {
    return repository.findById(id);
  }

  public List<Employee> getAll(EmployeeFilter filter) {
    return repository.findByCriteria(filter);
  }

  public void saveOne(Employee employee) {
    repository.save(employee);
  }
}
