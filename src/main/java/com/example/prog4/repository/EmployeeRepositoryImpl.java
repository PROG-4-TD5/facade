package com.example.prog4.repository;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.repository.base.BaseEmployeeRepository;
import com.example.prog4.repository.base.entity.Employee;
import com.example.prog4.repository.cnaps.CnapsEmployeeRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@AllArgsConstructor
public class EmployeeRepositoryImpl extends NotImplementedEmployeeRepositoryImpl {
  private final BaseEmployeeRepository baseEmployeeRepository;
  private final CnapsEmployeeRepository cnapsEmployeeRepository;


  @Override
  public Employee findById(String id) {
    Employee base = baseEmployeeRepository.findById(id);
    com.example.prog4.repository.cnaps.entity.Employee cnaps =
        cnapsEmployeeRepository.findByEndToEndId(id);
    if (cnaps != null) {
      base.setCnaps(cnaps.getNumber());
    }
    return base;
  }

  @Override
  public Employee save(Employee employee) {
    return baseEmployeeRepository.save(employee);
  }

  @Override
  public List<Employee> findByCriteria(EmployeeFilter filter) {
    return baseEmployeeRepository.findByCriteria(filter);
  }
}
