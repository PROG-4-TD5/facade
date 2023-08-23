package com.example.prog4.repository;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.base.entity.Employee;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;

public class NotImplementedEmployeeRepositoryImpl implements EmployeeRepository {
  private final String NOT_IMPLEMENTED_MESSAGE = "%s not implemented by %s";

  @Override
  public Employee findById(String id) {
    throw new NotImplementedException(
        String.format(NOT_IMPLEMENTED_MESSAGE, "findById", this.getClass()));
  }

  @Override
  public Employee save(Employee employee) {
    throw new NotImplementedException(
        String.format(NOT_IMPLEMENTED_MESSAGE, "save", this.getClass()));
  }

  @Override
  public List<Employee> findByCriteria(EmployeeFilter filter) {
    throw new NotImplementedException(
        String.format(NOT_IMPLEMENTED_MESSAGE, "findByCriteria", this.getClass()));
  }

  @Override
  public com.example.prog4.repository.cnaps.entity.Employee findByEndToEndId(String endToEndId) {
    throw new NotImplementedException(
        String.format(NOT_IMPLEMENTED_MESSAGE, "findByEndToEndId", this.getClass()));
  }
}
