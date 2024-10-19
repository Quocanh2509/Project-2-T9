package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.Beans.response.RentareaResponseDTO;

public interface RentareaService {
	public List<RentareaResponseDTO> findAll(Map<String,Object> request);
}
