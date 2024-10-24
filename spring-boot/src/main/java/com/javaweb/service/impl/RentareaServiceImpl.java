package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;
import com.javaweb.repository.BuildingrenttypeRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.BuildingrenttypeEntity;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentareaService;

@Service
public class RentareaServiceImpl implements RentareaService {

	@Autowired
	public RentareaRepository rentarearepository;
	
	@Autowired
	public BuildingService buildingservice;
	
	@Autowired
	public BuildingrenttypeRepository buildingrenttyperepository;
	
	@Override
	public List<BuildingResponseDTO> findAll(Map<String, Object> request) {
		// TODO Auto-generated method stub
		List<RentareaEntity> arr=rentarearepository.findAlll(request);
		List<BuildingResponseDTO> buildingresponsedto=buildingservice.findAll(request);
		List<RentareaResponseDTO> results = new ArrayList<RentareaResponseDTO>();
		TreeSet<Integer> mark=new TreeSet<>();
		for(RentareaEntity it : arr) {
			RentareaResponseDTO rentarearesponsedto = new RentareaResponseDTO();
			Integer value=it.getBuildingid();
			if(!mark.contains(value)) {
				StringBuilder sb=new StringBuilder();
				for(RentareaEntity item : arr) {
					if(value.equals(item.getBuildingid())) {
						sb.append(item.getValue()+",");
					}
				}
				sb.deleteCharAt(sb.length()-1);
				rentarearesponsedto.setId(value);
				
				rentarearesponsedto.setArea(sb.toString());
				mark.add(value);
				results.add(rentarearesponsedto);
			}
		}
		for(BuildingResponseDTO it:buildingresponsedto) {
			for(RentareaResponseDTO it2:results) {
				if(it.getId().equals(it2.getId())) {
					it.setArea(it2.getArea());
				}
			}
		}
		List<Integer> buildingrenttypenentity =buildingrenttyperepository.findAll(request);
		List<BuildingResponseDTO> convert=new ArrayList<BuildingResponseDTO>();
		for(BuildingResponseDTO it:buildingresponsedto) {
			if(buildingrenttypenentity.contains(it.getId())) {
				convert.add(it);
			}
		}
		return convert;
	}

}
