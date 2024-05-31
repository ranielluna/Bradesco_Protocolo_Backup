package com.bradesco.sistemabradesco.repository;

import com.bradesco.sistemabradesco.models.Channels;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ChannelsRepository extends JpaRepository<Channels, Integer> {
    Optional<Channels> findByCode(int code);
}
