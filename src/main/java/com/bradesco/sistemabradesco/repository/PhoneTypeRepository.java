package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.PhoneType;

@Repository
public interface PhoneTypeRepository extends JpaRepository<PhoneType, Integer> {
 PhoneType findByCode(int code);
}
