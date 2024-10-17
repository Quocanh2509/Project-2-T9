package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.Beans.MarketDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{

	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";
	
	
	@Override
	public List<BuildingEntity> findAll(Integer Id, Integer districtId) {
		String sql ="SELECT * FROM estatebasic.building;";
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
