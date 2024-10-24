package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.BuildingrenttypeRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentareaService;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	public BuildingRepository buildingrepository;
	
	
	@Autowired
	public RentareaService rentareaservice;
	
	@Autowired
	public BuildingService buildingservice;
	
	@Autowired
	public BuildingrenttypeRepository buildingrenttyperepository;
	
	@Override
	public List<BuildingResponseDTO> findAll(Map<String,Object> request) {
		List<BuildingEntity> result=buildingrepository.findAll(request);
		List<BuildingResponseDTO> ans=new ArrayList<BuildingResponseDTO>();
		for(BuildingEntity it:result) {
			BuildingResponseDTO buildingreponsedto=new BuildingResponseDTO();
			buildingreponsedto.setId(it.getId());
			buildingreponsedto.setName(it.getName());
			buildingreponsedto.setAddress(it.getStreet()+", "+it.getWard()+", Quận "+it.getDistrictId());
			buildingreponsedto.setNumberofbasement(it.getNumberofbasement());
			buildingreponsedto.setFloorarea(it.getFloorarea());
			buildingreponsedto.setManagername(it.getManagername());
			buildingreponsedto.setManagerphonenumber(it.getManagerphonenumber());
			buildingreponsedto.setRentprice(it.getRentprice());
			buildingreponsedto.setServicefee(it.getServicefee());
			buildingreponsedto.setBrokeragefee(it.getBrokeragefee());
			ans.add(buildingreponsedto);
		}
		List<RentareaResponseDTO> rentarearesponsedto=rentareaservice.findAll(request);
		//List<BuildingResponseDTO> buildingresponsedto=buildingservice.findAll(request);
		for(BuildingResponseDTO it:ans) {
			for(RentareaResponseDTO it2:rentarearesponsedto) {
				if(it.getId().equals(it2.getId())) {
					it.setArea(it2.getArea());
				}
			}
		}
		List<Integer> buildingrenttypenentity =buildingrenttyperepository.findAll(request);
		List<BuildingResponseDTO> convert=new ArrayList<BuildingResponseDTO>();
		for(BuildingResponseDTO it:ans) {
			if(buildingrenttypenentity.contains(it.getId())) {
				convert.add(it);
			}
		}	
		return convert;
	}


	
}
