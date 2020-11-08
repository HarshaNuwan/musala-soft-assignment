package com.musala.gateway_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.musala.gateway_management.model.DeviceEntity;
import com.musala.gateway_management.model.GatewayDeviceAsocEntity;
import com.musala.gateway_management.model.GatewayEntity;
import com.musala.gateway_management.service.GatewayDeviceManagementService;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

@RestController
public class GatewayDeviceManagementController {

	@Autowired
	GatewayDeviceManagementService gatewayDeviceManagementService;

	@RequestMapping(value = "/api/gateway/savegateway", method = RequestMethod.POST)
	public ResponseEntity<Object> saveGateway(@RequestBody GatewayEntity gatewayEntity) {
		return gatewayDeviceManagementService.saveGateway(gatewayEntity);
	}

	@RequestMapping(value = "/api/gateway/deletegateway", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteGateway(@RequestBody GatewayEntity gatewayEntity) {
		return gatewayDeviceManagementService.deleteGateway(gatewayEntity);
	}

	@RequestMapping(value = "/api/gateway/getallgateways", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllGateways() {
		return gatewayDeviceManagementService.getAllGateways();
	}

	@RequestMapping(value = "/api/gateway/savedevice", method = RequestMethod.POST)
	public ResponseEntity<Object> saveDevice(@RequestBody DeviceEntity deviceEntity) {
		return gatewayDeviceManagementService.saveDevice(deviceEntity);
	}

	@RequestMapping(value = "/api/gateway/deletedevice", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteDevice(@RequestBody DeviceEntity deviceEntity) {
		return gatewayDeviceManagementService.deleteDevice(deviceEntity);
	}

	@RequestMapping(value = "/api/gateway/getalldevices", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllDevices() {
		return gatewayDeviceManagementService.getAllDevices();
	}
	
	@RequestMapping(value = "/api/gateway/savegatewaydeviceasoc", method = RequestMethod.POST)
	public ResponseEntity<Object> saveGatewayDeviceAsoc(@RequestBody GatewayDeviceAsocEntity gatewayDeviceAsocEntity) {
		return gatewayDeviceManagementService.saveGatewayDeviceAsoc(gatewayDeviceAsocEntity);
	}
	
	@RequestMapping(value = "/api/gateway/deletegatewaydeviceasoc", method = RequestMethod.POST)
	public ResponseEntity<Object> deleteGatewayDeviceAsoc(@RequestBody GatewayDeviceAsocEntity gatewayDeviceAsocEntity) {
		return gatewayDeviceManagementService.deleteGatewayDeviceAsoc(gatewayDeviceAsocEntity);
	}
	
	@RequestMapping(value = "/api/gateway/getgatewaydeviceasoc", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllGatewaydeviceasoc() {
		return gatewayDeviceManagementService.getAllGatewayDeviceAsocs();
	}
}
