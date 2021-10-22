package com.kmobile.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kmobile.model.Device;


@Repository
public interface DeviceRepo extends JpaRepository<Device, Long>{

	Optional<Device> findByPhoneNumber(String phoneNumber);

}
