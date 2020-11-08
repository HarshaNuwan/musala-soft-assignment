package com.musala.gateway_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musala.gateway_management.model.DeviceEntity;
import com.musala.gateway_management.model.GatewayDeviceAsocEntity;
import com.musala.gateway_management.model.GatewayEntity;
import org.junit.jupiter.api.MethodOrderer;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 *         This unit test uses test execution order, since all the tests are
 *         data base related transactions and therefore there are dependancies
 *         between some test cases.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GatewayDeviceManagementServiceImplTest {

	private static String deviceUid = null;

	@Autowired
	GatewayDeviceManagementService gatewayDeviceManagementService;

	private static ObjectMapper objectMapper;

	@BeforeAll
	public static void setUp() throws Exception {
		objectMapper = new ObjectMapper();
	}

	@Test
	@Order(1)
	void testSaveGateway() {
		// valid gateway record
		GatewayEntity entity = new GatewayEntity("1234567100", "Office Gateway", "0.0.0.0");
		assertEquals(200, gatewayDeviceManagementService.saveGateway(entity).getStatusCodeValue());

		// invalid primary key
		entity = new GatewayEntity("12345671001", "Office Gateway", "128.0.0.1");
		assertEquals(500, gatewayDeviceManagementService.saveGateway(entity).getStatusCodeValue());

		// invalid ipv4 address
		entity = new GatewayEntity("1234567102", "Office Gateway", "123.456.789.100");
		ResponseEntity responseEntity = gatewayDeviceManagementService.saveGateway(entity);
		assertEquals(500, responseEntity.getStatusCodeValue());
		System.out.println(responseEntity.getBody());
	}

	@Test
	@Order(2)
	void testSaveDevice() throws JsonProcessingException {
		// test saving a new device with generated UUID
		DeviceEntity deviceEntity = new DeviceEntity("Test 2 vendor", "2020-10-12", true);
		ResponseEntity responseEntity = gatewayDeviceManagementService.saveDevice(deviceEntity);
		deviceUid = ((DeviceEntity) responseEntity.getBody()).getUid();
		System.out.println(">>>>>>>>>>>>>>> " + deviceUid);
		assertEquals(200, responseEntity.getStatusCodeValue());
		String jsonCompactString = objectMapper.writeValueAsString((DeviceEntity) responseEntity.getBody());
		System.out.println(jsonCompactString);

		// update device info
		deviceEntity = new DeviceEntity(deviceUid, "Test 2 vendor updated 3 times", "2020-10-12", true);
		assertEquals(200, gatewayDeviceManagementService.saveDevice(deviceEntity).getStatusCodeValue());
	}

	@Test
	@Order(3)
	void testSaveGatewayRepositoryAsoc() {
		System.out.println(">>>>>>>>>>>>>>> " + deviceUid);
		// update existing record
		GatewayDeviceAsocEntity gatewayDeviceAsocEntity = new GatewayDeviceAsocEntity(1, "1234567100", deviceUid);
		assertEquals(200,
				gatewayDeviceManagementService.saveGatewayDeviceAsoc(gatewayDeviceAsocEntity).getStatusCodeValue());

		// add new record
		gatewayDeviceAsocEntity = new GatewayDeviceAsocEntity("1234567100", deviceUid);
		assertEquals(200,
				gatewayDeviceManagementService.saveGatewayDeviceAsoc(gatewayDeviceAsocEntity).getStatusCodeValue());

		// try to add invalid record
		gatewayDeviceAsocEntity = new GatewayDeviceAsocEntity(001, "12345671999", deviceUid);
		assertEquals(500,
				gatewayDeviceManagementService.saveGatewayDeviceAsoc(gatewayDeviceAsocEntity).getStatusCodeValue());
	}

	@Test
	@Order(4)
	void testgetGatewayBySerialNumber() throws JsonProcessingException {

		// retrive gateway details by gateway ID
		ResponseEntity responseEntity = gatewayDeviceManagementService.getGatewayBySerialNumber("1234567100");
		Object obj = responseEntity.getBody();
		if (obj instanceof GatewayEntity) {
			String jsonCompactString = objectMapper.writeValueAsString(obj);
			System.out.println(">>>>> " + jsonCompactString);
		}
		assertEquals(200, responseEntity.getStatusCodeValue());

		// check for a gateway that is not available
		responseEntity = gatewayDeviceManagementService.getGatewayBySerialNumber("1234561000");
		assertEquals(500, responseEntity.getStatusCodeValue());
		System.out.println(">>>>> " + responseEntity.getBody());
	}

	@Test
	@Order(5)
	void testGetAllGateways() throws JsonProcessingException {
		ResponseEntity responseEntity = gatewayDeviceManagementService.getAllGateways();
		Object obj = responseEntity.getBody();
		if (null != obj && obj instanceof List) {
			List<GatewayEntity> gatewayList = (List<GatewayEntity>) responseEntity.getBody();
			String jsonCompactString = objectMapper.writeValueAsString(obj);
			System.out.println(">>>>> " + jsonCompactString);
		}
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(6)
	void getAllGatewayRepositoryAsocs() throws JsonProcessingException {
		ResponseEntity responseEntity = gatewayDeviceManagementService.getAllGatewayDeviceAsocs();
		Object obj = responseEntity.getBody();
		if (null != obj && obj instanceof List) {
			List<GatewayDeviceAsocEntity> gatewayList = (List<GatewayDeviceAsocEntity>) responseEntity.getBody();
			String jsonCompactString = objectMapper.writeValueAsString(obj);
			System.out.println(">>>>> " + jsonCompactString);
		}
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(7)
	void testGetAllDevices() throws JsonProcessingException {
		ResponseEntity responseEntity = gatewayDeviceManagementService.getAllDevices();
		Object obj = responseEntity.getBody();
		if (null != obj && obj instanceof List) {
			List<DeviceEntity> gatewayList = (List<DeviceEntity>) responseEntity.getBody();
			String jsonCompactString = objectMapper.writeValueAsString(obj);
			System.out.println(">>>>> " + jsonCompactString);
		}
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

/*	
	@Ignore
	@Test
	@Order(8)
	void testDeleteGateway() {
		GatewayEntity entity = new GatewayEntity("1234567100", "Office Gateway", "0.0.0.0");
		assertEquals(200, gatewayDeviceManagementService.deleteGateway(entity).getStatusCodeValue());

	}*/

	/*
	 * @Ignore
	 * 
	 * @Test
	 * 
	 * @Order(9) void testDeleteGatewayDeviceAsoc() { GatewayDeviceAsocEntity entity
	 * = new GatewayDeviceAsocEntity(5, "", ""); assertEquals(200,
	 * gatewayDeviceManagementService.deleteGatewayDeviceAsoc(entity).
	 * getStatusCodeValue()); }
	 */

	/*@Ignore
	@Test
	@Order(10)
	void testDeleteDevice() {
		DeviceEntity entity = new DeviceEntity("402888e475a965050175a9650c270000", "", "", false);
		assertEquals(200, gatewayDeviceManagementService.deleteDevice(entity).getStatusCodeValue());
	}*/

}
