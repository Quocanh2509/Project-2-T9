package com.javaweb.service;


import java.util.List;

import com.javaweb.Beans.response.BuildingResponseDTO;

public interface BuildingService {
	public List<BuildingResponseDTO> findAll(Long Id,String districtId);
}
