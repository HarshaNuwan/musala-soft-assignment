package com.musala.gateway_management.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.musala.gateway_management.model.DeviceEntity;
import com.musala.gateway_management.model.GatewayDeviceAsocEntity;
import com.musala.gateway_management.model.GatewayEntity;
import com.musala.gateway_management.repository.DeviceRepository;
import com.musala.gateway_management.repository.GatewayDeviceAsocRepository;
import com.musala.gateway_management.repository.GatewayRepository;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

@Service
public class GatewayDeviceManagementServiceImpl implements GatewayDeviceManagementService {

	private static final String IPV4_REGEX = "^((25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$";

	@Autowired
	private GatewayRepository gatewayRepository;

	@Autowired
	private GatewayDeviceAsocRepository gatewayDeviceAsocRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	/**
	 * saveGateway() This method requires a GatewayEntity instance and it will store
	 * the given object in the database before storing the GatewayEntity object; it
	 * will validate the IPV4 address and if validation failed, respond with an
	 * error message
	 */

	@Override
	public ResponseEntity<Object> saveGateway(GatewayEntity gatewayEntity) {
		Pattern pattern = Pattern.compile(IPV4_REGEX);
		Matcher matcher = pattern.matcher(gatewayEntity.getIpv4Address());

		if (!matcher.find()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("IPV4 Address is invalid");
		}
		
		List<GatewayDeviceAsocEntity> gdList = gatewayDeviceAsocRepository.findByserialNumber(gatewayEntity.getSerialNumber());
		
		if(null != gdList && gdList.size() > 10 ) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gateway with serial number " + gatewayEntity.getSerialNumber() + " can't have more than 10 devices");
		}

		try {
			gatewayRepository.save(gatewayEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Gateway saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	/**
	 * getGatewayBySerialNumber() - search a gateway by it's serial number and
	 * respond back with the result
	 */
	@Override
	public ResponseEntity<Object> getGatewayBySerialNumber(String serialNumber) {
		try {
			GatewayEntity gatewayEntity = gatewayRepository.findBySerialNumber(serialNumber);
			if (null != gatewayEntity) {
				return ResponseEntity.status(HttpStatus.OK).body(gatewayEntity);
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Gateway with " + serialNumber + " not available in the database.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	/**
	 * saveGatewayRepositoryAsoc() This method stored gateway and device associated
	 * records into the database
	 */
	@Override
	public ResponseEntity<Object> saveGatewayDeviceAsoc(GatewayDeviceAsocEntity gatewayDeviceAsocEntity) {
		try {
			gatewayDeviceAsocRepository.save(gatewayDeviceAsocEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Gateway and Device info saved successfully");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> saveDevice(DeviceEntity deviceEntity) {
		try {
			DeviceEntity dEntity = deviceRepository.save(deviceEntity);
			return ResponseEntity.status(HttpStatus.OK).body(dEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> deleteGateway(GatewayEntity gatewayEntity) {
		try {
			gatewayRepository.delete(gatewayEntity);
			;
			return ResponseEntity.status(HttpStatus.OK)
					.body("Device with Serial Number " + gatewayEntity.getSerialNumber() + " deleted");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllGateways() {
		try {
			List<GatewayEntity> gatewayList = gatewayRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(gatewayList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> deleteDevice(DeviceEntity deviceEntity) {
		try {
			deviceRepository.delete(deviceEntity);
	
			return ResponseEntity.status(HttpStatus.OK).body("Device with UUID " + deviceEntity.getUid() + " deleted");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllDevices() {
		try {
			List<DeviceEntity> deviceList = deviceRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(deviceList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> getAllGatewayDeviceAsocs() {

		try {
			List<GatewayDeviceAsocEntity> asocEntities = gatewayDeviceAsocRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(asocEntities);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

	}

	@Override
	public ResponseEntity<Object> deleteGatewayDeviceAsoc(GatewayDeviceAsocEntity gatewayDeviceAsocEntity) {
		try {
			gatewayDeviceAsocRepository.delete(gatewayDeviceAsocEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Gateway Device association record with id " + gatewayDeviceAsocEntity.getGatewayDeviceAsocId() + " deleted");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}
