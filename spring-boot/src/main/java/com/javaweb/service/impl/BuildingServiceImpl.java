package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	public BuildingRepository buildingrepository;
	
	
	@Override
	public List<BuildingResponseDTO> findAll(Map<String,Object> request) {
		List<BuildingEntity> result=buildingrepository.findAll(request);
		List<BuildingResponseDTO> ans=new ArrayList<BuildingResponseDTO>();
		for(BuildingEntity it:result) {
			BuildingResponseDTO buildingreponsedto=new BuildingResponseDTO();
			buildingreponsedto.setId(it.getId());
			buildingreponsedto.setName(it.getName());
			buildingreponsedto.setAddress(it.getStreet()+", "+it.getWard()+", "+it.getDistrictId());
			buildingreponsedto.setNumberofbasement(it.getNumberofbasement());
			buildingreponsedto.setFloorarea(it.getFloorarea());
			buildingreponsedto.setManagername(it.getManagername());
			buildingreponsedto.setManagerphonenumber(it.getManagerphonenumber());
			buildingreponsedto.setRentprice(it.getRentprice());
			buildingreponsedto.setServicefee(it.getServicefee());
			buildingreponsedto.setBrokeragefee(it.getBrokeragefee());
			ans.add(buildingreponsedto);
			//ans.add(buildingreponsedto);
			//if(districtId.equals(it.getDistrictId())) ans.add(buildingreponsedto);
		}
		return ans;
	}


	
}
