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

import com.javaweb.repository.BuildingrenttypeRepository;
import com.javaweb.repository.entity.BuildingrenttypeEntity;
import com.javaweb.repository.entity.RentareaEntity;


@Repository
public class BuildingrenttypeRepositoryImpl implements BuildingrenttypeRepository{

	
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estabasic";
	
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";
	
	@Override
	public List<Integer> findAll(Map<String, Object> request) {
		String sql="select buildingid from buildingrenttype BR\r\n"
				+ "inner join renttype RT ON RT.id=BR.renttypeid\r\n"
				+ "inner join building BD ON BD.id=BR.buildingid";
		List<Integer> result=new ArrayList<Integer>();
		String s="";
		if(request.containsKey("tang-tret")) {
			sql +=" and renttypeid=1";
			s += "and";
					
		}
		if(request.containsKey("nguyen-can")&&s.equals("")) {
			sql +=" and renttypeid=2";
			s +="and";
		}
		else if(request.containsKey("nguyen-can")&&!s.equals("")) {
			sql +=" or renttypeid=2";
		}
		
		if(request.containsKey("noi-that")&&s.equals("")) {
			sql +=" and renttypeid=3";
		}
		else if(request.containsKey("noi-that")&&!s.equals("")){
			sql +=" or renttypeid=3";
		}
		
		System.out.println(sql);
		if(sql.equals("")) {
			return result;
		}
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)){
			while(rs.next()) {
				BuildingrenttypeEntity buildingrenttype=new BuildingrenttypeEntity();
				result.add(rs.getInt("buildingid"));
				buildingrenttype.setBuildingid(result);
			}
			System.out.println("Connected database successfully...");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		return result;
	}

}
