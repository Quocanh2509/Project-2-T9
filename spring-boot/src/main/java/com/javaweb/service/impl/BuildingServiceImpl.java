package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<BuildingResponseDTO> findAll(Integer Id, Integer districtId) {
		List<BuildingEntity> result=buildingrepository.findAll(Id, districtId);
		List<BuildingResponseDTO> ans=new ArrayList<BuildingResponseDTO>();
		for(BuildingEntity it:result) {
			BuildingResponseDTO buildingreponsedto=new BuildingResponseDTO();
			buildingreponsedto.setId(it.getId());
			buildingreponsedto.setName(it.getName());
			buildingreponsedto.setDistrictId(it.getDistrictId());
			buildingreponsedto.setAddress(it.getStreet()+", "+it.getWard());
			buildingreponsedto.setNumberofbasement(it.getNumberofbasement());
			//ans.add(buildingreponsedto);
			if(districtId.equals(it.getDistrictId())) ans.add(buildingreponsedto);
		}
		return ans;
	}

	
}
