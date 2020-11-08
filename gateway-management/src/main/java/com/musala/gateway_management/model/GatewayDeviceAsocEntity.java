package com.musala.gateway_management.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

@Entity
@Table(name = "gateway_device_asoc")
public class GatewayDeviceAsocEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gateway_device_asoc_id")
	private int gatewayDeviceAsocId;

	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "uid")
	private String uid;

	/*
	 * @JoinColumn(referencedColumnName = "uid", name = "uid", insertable = false,
	 * updatable = false)
	 * 
	 * @OneToOne(fetch = FetchType.EAGER) private DeviceEntity deviceEntity;
	 * 
	 * @JoinColumn(referencedColumnName = "serial_number", name = "serial_number",
	 * insertable = false, updatable = false)
	 * 
	 * @OneToOne(fetch = FetchType.EAGER) private GatewayEntity gatewayEntity;
	 */

	public GatewayDeviceAsocEntity() {
		// TODO Auto-generated constructor stub
	}
	
	

	public GatewayDeviceAsocEntity(String serialNumber, String uid) {
		super();
		this.serialNumber = serialNumber;
		this.uid = uid;
	}



	public GatewayDeviceAsocEntity(int gatewayDeviceAsocId, String serialNumber, String uid) {
		super();
		this.gatewayDeviceAsocId = gatewayDeviceAsocId;
		this.serialNumber = serialNumber;
		this.uid = uid;
	}

	public int getGatewayDeviceAsocId() {
		return gatewayDeviceAsocId;
	}

	public void setGatewayDeviceAsocId(int gatewayDeviceAsocId) {
		this.gatewayDeviceAsocId = gatewayDeviceAsocId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	/*
	 * public DeviceEntity getDeviceEntity() { return deviceEntity; }
	 * 
	 * public void setDeviceEntity(DeviceEntity deviceEntity) { this.deviceEntity =
	 * deviceEntity; }
	 * 
	 * 
	 * 
	 * public GatewayEntity getGatewayEntity() { return gatewayEntity; }
	 * 
	 * 
	 * 
	 * public void setGatewayEntity(GatewayEntity gatewayEntity) {
	 * this.gatewayEntity = gatewayEntity; }
	 */
	
	

}
