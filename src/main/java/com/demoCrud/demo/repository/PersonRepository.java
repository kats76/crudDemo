package com.demoCrud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demoCrud.demo.model.Person;


public interface PersonRepository extends JpaRepository<Person, Long> {
}