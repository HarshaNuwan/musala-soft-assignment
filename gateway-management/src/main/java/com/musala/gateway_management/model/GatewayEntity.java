package com.musala.gateway_management.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

@Entity
@Table(name = "gateway")
public class GatewayEntity {

	@Id
	@Column(name = "serial_number")
	private String serialNumber;

	@Column(name = "human_readable_name")
	private String humanReadableName;

	@Column(name = "ipv4_address")
	private String ipv4Address;
	
	@JoinColumn(referencedColumnName = "serial_number", name = "serial_number", insertable=false, updatable=false)
	@OneToMany(targetEntity=GatewayDeviceAsocEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection gatewayDeviceAsocRecords;

	public GatewayEntity() {
		// TODO Auto-generated constructor stub
	}

	public GatewayEntity(String serialNumber, String humanReadableName, String ipv4Address) {
		super();
		this.serialNumber = serialNumber;
		this.humanReadableName = humanReadableName;
		this.ipv4Address = ipv4Address;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getHumanReadableName() {
		return humanReadableName;
	}

	public void setHumanReadableName(String humanReadableName) {
		this.humanReadableName = humanReadableName;
	}

	public String getIpv4Address() {
		return ipv4Address;
	}

	public void setIpv4Address(String ipv4Address) {
		this.ipv4Address = ipv4Address;
	}
	
	public Collection getGatewayDeviceAsocRecords() {
		return gatewayDeviceAsocRecords;
	}

	public void setGatewayDeviceAsocRecords(Collection gatewayDeviceAsocRecords) {
		this.gatewayDeviceAsocRecords = gatewayDeviceAsocRecords;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return serialNumber + " | " + humanReadableName + " | " + ipv4Address + " >> gatewayDeviceAsocRecords count = ";
	}
}
