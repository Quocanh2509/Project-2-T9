package com.javaweb.Beans.response;




public class BuildingResponseDTO {
	private Integer Id;
	private String name;
	private Integer districtId;
	private String address;
	private Integer numberofbasement;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getNumberofbasement() {
		return numberofbasement;
	}
	public void setNumberofbasement(Integer numberofbasement) {
		this.numberofbasement = numberofbasement;
	}
	
	
	
}
