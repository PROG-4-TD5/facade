package com.example.prog4.repository.cnaps;

import com.example.prog4.repository.NotImplementedEmployeeRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CnapsEmployeeRepository extends NotImplementedEmployeeRepositoryImpl {
  private final com.example.prog4.repository.cnaps.EmployeeRepository repository;

  public CnapsEmployeeRepository(
      @Qualifier("cnapsEmployeeJpaRepository")
      com.example.prog4.repository.cnaps.EmployeeRepository repository) {
    this.repository = repository;
  }

  @Override
  public com.example.prog4.repository.cnaps.entity.Employee findByEndToEndId(String endToEndId) {
    return repository.findByEndToEndId(endToEndId).orElse(null);
  }
}
