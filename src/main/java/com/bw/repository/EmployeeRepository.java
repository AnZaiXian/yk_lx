package com.bw.repository;

import com.bw.entity.Employee;
import com.bw.entity.Ttree;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lenovo on 2017/7/27.
 */
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
