package com.musala.gateway_management.model;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Harsha Hulangamuwa
 *
 */

@Entity
@Table(name = "device")
public class DeviceEntity {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String uid;

	private String vendor;

	private String date;

	private boolean status;

	@JoinColumn(referencedColumnName = "uid", name = "uid", insertable = false, updatable = false)
	@OneToMany(targetEntity = GatewayDeviceAsocEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Collection gatewayDeviceAsocEntities;

	public DeviceEntity() {
		// TODO Auto-generated constructor stub
	}

	public DeviceEntity(String vendor, String date, boolean status) {
		super();
		this.vendor = vendor;
		this.date = date;
		this.status = status;
	}

	public DeviceEntity(String uid, String vendor, String date, boolean status) {
		super();
		this.uid = uid;
		this.vendor = vendor;
		this.date = date;
		this.status = status;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Collection getGatewayDeviceAsocEntities() {
		return gatewayDeviceAsocEntities;
	}

	public void setGatewayDeviceAsocEntities(Collection gatewayDeviceAsocEntities) {
		this.gatewayDeviceAsocEntities = gatewayDeviceAsocEntities;
	}

}
