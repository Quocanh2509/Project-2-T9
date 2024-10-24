package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;

public interface RentareaService {
	public List<BuildingResponseDTO> findAll(Map<String,Object> request);
}
