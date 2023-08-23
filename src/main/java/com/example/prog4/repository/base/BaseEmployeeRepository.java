package com.example.prog4.repository.base;

import com.example.prog4.model.EmployeeFilter;
import com.example.prog4.model.exception.NotFoundException;
import com.example.prog4.repository.NotImplementedEmployeeRepositoryImpl;
import com.example.prog4.repository.base.dao.EmployeeManagerDao;
import com.example.prog4.repository.base.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository(value = "baseEmployeeRepository")
public class BaseEmployeeRepository extends NotImplementedEmployeeRepositoryImpl {
  private final com.example.prog4.repository.base.EmployeeRepository repository;
  private final EmployeeManagerDao dao;

  public BaseEmployeeRepository(
      @Qualifier("baseEmployeeJpaRepository")
      com.example.prog4.repository.base.EmployeeRepository repository,
      EmployeeManagerDao dao) {
    this.repository = repository;
    this.dao = dao;
  }

  @Override
  public Employee findById(String id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Employee.Id=" + id + " not found"));
  }

  @Override
  public Employee save(Employee employee) {
    return repository.save(employee);
  }

  public List<Employee> findByCriteria(EmployeeFilter filter) {
    Sort sort = Sort.by(filter.getOrderDirection(), filter.getOrderBy().toString());
    Pageable pageable = PageRequest.of(filter.getIntPage() - 1, filter.getIntPerPage(), sort);
    return dao.findByCriteria(
        filter.getLastName(),
        filter.getFirstName(),
        filter.getCountryCode(),
        filter.getSex(),
        filter.getPosition(),
        filter.getEntrance(),
        filter.getDeparture(),
        pageable
    );
  }
}
