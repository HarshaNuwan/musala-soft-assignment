package com.musala.gateway_management.service;

import org.springframework.http.ResponseEntity;

import com.musala.gateway_management.model.DeviceEntity;
import com.musala.gateway_management.model.GatewayDeviceAsocEntity;
import com.musala.gateway_management.model.GatewayEntity;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

public interface GatewayDeviceManagementService {

	public ResponseEntity<Object> saveGateway(GatewayEntity gatewayEntity);
	public ResponseEntity<Object> deleteGateway(GatewayEntity gatewayEntity);
	public ResponseEntity<Object> getGatewayBySerialNumber(String serialNumber);
	public ResponseEntity<Object> getAllGateways();
	
	public ResponseEntity<Object> saveGatewayDeviceAsoc(GatewayDeviceAsocEntity gatewayDeviceAsocEntity);
	public ResponseEntity<Object> getAllGatewayDeviceAsocs();
	public ResponseEntity<Object> deleteGatewayDeviceAsoc(GatewayDeviceAsocEntity gatewayDeviceAsocEntity);
	
	public ResponseEntity<Object> saveDevice(DeviceEntity deviceEntity);
	public ResponseEntity<Object> deleteDevice(DeviceEntity deviceEntity);
	public ResponseEntity<Object> getAllDevices();
}
