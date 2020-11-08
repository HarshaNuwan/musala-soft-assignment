package com.musala.gateway_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.gateway_management.model.GatewayDeviceAsocEntity;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

public interface GatewayDeviceAsocRepository extends JpaRepository<GatewayDeviceAsocEntity, Integer>{

	public List<GatewayDeviceAsocEntity> findByserialNumber(String serialNumber);
}
