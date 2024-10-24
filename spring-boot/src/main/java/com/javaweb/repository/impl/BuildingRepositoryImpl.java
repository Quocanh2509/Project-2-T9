package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.Beans.MarketDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estabasic";
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";
	
	
	@Override
	public List<BuildingEntity> findAll(Map<String,Object> request) {
		String sql2="select * from building BD\r\n"
				+ "inner join assignmentbuilding AB on AB.buildingid=BD.id\r\n"
				+ "where AB.staffid=3;\r\n"
				+ "";
		
		String sql ="SELECT * FROM building BD where 1=1";
		if (request.get("staffid") != null) {
		    sql += " AND EXISTS (SELECT 1 FROM assignmentbuilding AB WHERE AB.buildingid = BD.id";
		    sql += " AND AB.staffid = " + request.get("staffid") + ")";
		}
		if (request.get("id") != null && !request.get("id").toString().isEmpty()) {
		    sql += " AND BD.id = " + request.get("id") ;//1
		}
		
		if (request.get("ward") != null && request.get("ward").equals("")==false) {
			
		    sql += " AND BD.ward = '"+request.get("ward")+"'";//2
		}

		if (request.get("name")!=null&&request.get("name").equals("")==false) {
		    sql += " AND BD.name LIKE '%" + request.get("name") + "%'";//3
		}
		if(request.get("districtid")!=null&&!request.get("districtid").toString().isEmpty()) {
			sql+= " AND BD.districtid = "+request.get("districtid");//4
		}
		if(request.get("street")!=null&&request.get("street").equals("")==false) {
			sql += " AND BD.street = '"+request.get("street")+"'";//5
		}
		if(request.get("floorarea")!=null&&!request.get("floorarea").toString().isEmpty()) {
			sql += " AND BD.floorarea = "+request.get("floorarea");//6
		}
		if(request.get("numberofbasement")!=null&&!request.get("numberofbasement").toString().isEmpty()) {
			sql += " AND BD.numberofbasement = "+request.get("numberofbasement");
		}
		if(request.get("managername")!=null&&request.get("managername").equals("")==false) {
			sql += " AND BD.managername = '"+request.get("managername")+"'";
		}
		if(request.get("managerphonenumber")!=null&&request.get("managerphonenumber").equals("")==false) {
			sql += " AND BD.managerphonenumber = '"+request.get("managerphonenumber")+"'";
		}
		if(request.containsKey("startprice")&&request.containsKey("endprice")&&!request.get("endprice").equals("")&&!request.get("startprice").equals("")) {
			sql += " AND BD.rentprice>="+request.get("startprice")+" AND BD.rentprice<="+request.get("endprice");
		}
		else if(request.containsKey("startprice")&&!request.get("startprice").equals("")) {
			sql += " AND BD.rentprice>="+request.get("startprice");
		}
		else if(request.containsKey("endprice")&&!request.get("endprice").equals("")) {
			sql += " AND BD.rentprice<="+request.get("endprice");
		}
		
		System.out.println(sql);
		List<BuildingEntity> arr=new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql)) {
			while(rs.next()) {
				BuildingEntity building=new BuildingEntity();
				building.setId(rs.getInt("id"));
				building.setName(rs.getString("name"));
				building.setStreet(rs.getString("street"));
				building.setWard(rs.getString("ward"));
				building.setDistrictId(rs.getInt("districtId"));
				building.setNumberofbasement(rs.getInt("numberofbasement"));
				building.setFloorarea(rs.getInt("floorarea"));
				building.setManagername(rs.getString("managername"));
				building.setManagerphonenumber(rs.getString("managerphonenumber"));
				building.setRentprice(rs.getInt("rentprice"));
				building.setServicefee(rs.getString("servicefee"));
				building.setBrokeragefee(rs.getInt("brokeragefee"));
				arr.add(building);
			}
			System.out.println("Connected database successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}

		return arr;
	}
	
}
