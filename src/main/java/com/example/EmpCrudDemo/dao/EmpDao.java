package com.example.EmpCrudDemo.dao;

import com.example.EmpCrudDemo.entity.Emp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDao extends JpaRepository<Emp, Integer>
{

}
