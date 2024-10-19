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

import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.RentareaEntity;

@Repository
public abstract class RentareaRepositoryImpl implements RentareaRepository {

	
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/estatebasic";
	
	static final String USER = "root";
	static final String PASS = "amfrbghaf123@";
	
	
	@Override
	public List<RentareaEntity> findAlll(Map<String,Object> request) {
		String sql="select * from rentarea RA";
		if(request.containsKey("startarea")&&request.containsKey("endarea")) {
			sql+="where RA.value>="+request.get("startarea")+" and RA<="+request.get("endarea");
		}
		else if(request.containsKey("startarea")&&request.get("startarea")!=""&&request.get("endarea")=="") {
			sql+="where RA.value>="+request.get("startarea");
		}
		else if(request.get("startarea")==""&&request.containsKey("endarea")&&request.get("endarea")!="") {
			sql+="where RA.value<="+request.get("endarea");
		}
		List<RentareaEntity> arr=new ArrayList<RentareaEntity>();
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql)){
			while(rs.next()) {
				RentareaEntity rentareaentity = new RentareaEntity();
				rentareaentity.setBuildingid(rs.getInt("buildingid"));
				rentareaentity.setValue(rs.getInt("value"));
				arr.add(rentareaentity);
			}
			System.out.println("Connected database successfully...");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Connected database failed...");
		}
		return arr;
	}
	
	
}
