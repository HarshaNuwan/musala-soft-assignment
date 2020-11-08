package com.musala.gateway_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musala.gateway_management.model.GatewayEntity;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

public interface GatewayRepository extends JpaRepository<GatewayEntity, String> {
	public GatewayEntity findBySerialNumber(String serialNumberF);
}
