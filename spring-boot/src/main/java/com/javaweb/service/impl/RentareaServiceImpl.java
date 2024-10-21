package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.service.RentareaService;

@Service
public class RentareaServiceImpl implements RentareaService {

	@Autowired
	public RentareaRepository rentarearepository;
	
	
	
	@Override
	public List<RentareaResponseDTO> findAll(Map<String, Object> request) {
		// TODO Auto-generated method stub
		List<RentareaEntity> arr=rentarearepository.findAlll(request);
		
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
		
		return results;
	}

}
