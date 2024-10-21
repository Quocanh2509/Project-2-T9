package com.javaweb.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;import java.util.concurrent.ConcurrentHashMap.KeySetView;

import javax.swing.plaf.basic.BasicComboBoxUI.ListDataHandler;

import org.springframework.web.bind.annotation.RequestBody;

import com.javaweb.Beans.BuildingDTO;
//import com.javaweb.Beans.BuildingDTO;
import com.javaweb.Beans.ErrorReponseDTO;
import com.javaweb.Beans.MarketDTO;
import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.Beans.response.RentareaResponseDTO;
import com.javaweb.customexception.FileRequireException;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentareaService;

import ch.qos.logback.core.joran.action.NewRuleAction;

@RestController
public class NewAPI {

	
	@Autowired
	public BuildingService buildingservice;
	
	
	@Autowired
	public RentareaService rentareaservice;
	
	@PostMapping(value = "/api/building/")
	public String update() {
		return "Restful-api";
	}
	
//	
	@GetMapping("/api")
	public Object gettt(@RequestParam Map<String,Object> map) {
		List<RentareaResponseDTO> results=rentareaservice.findAll(map);
		return results;
	}
	
	@GetMapping(value = "/api/building/")
	public Object postBuilding2(@RequestParam Map<String,Object> request) {
		List<BuildingResponseDTO> buildingresponsedto=buildingservice.findAll(request);
		List<RentareaResponseDTO> rentarearesponsedto=rentareaservice.findAll(request);
		//System.out.println(results.size());
		for(BuildingResponseDTO it:buildingresponsedto) {
			for(RentareaResponseDTO it2:rentarearesponsedto) {
				if(it.getId().equals(it2.getId())) {
					it.setArea(it2.getArea());
				}
			}
		}
		return buildingresponsedto;
	}

	// @RequestMapping(value = "/api/building/", method = RequestMethod.GET)
	public void validate(BuildingDTO buildingDTO) throws FileRequireException {
		if (buildingDTO.getName() == null || buildingDTO.getName().equals("")) {
			throw new FileRequireException("dữ liệu không hợp lệ");
		}
		// return buildingDTO;
	}

//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	public Object getBuilding(BuildingDTO buildingDTO) {
//		validate(buildingDTO);
//		return buildingDTO;
//	}
//	


//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	public Object getBuilding(@RequestBody BuildingDTO buildingDTO) throws FileRequireException {
//		validate(buildingDTO);
//		return buildingDTO;
//	}

	@DeleteMapping(value = "/api/building/{id}")
	public void deleteBuilding(@PathVariable List<Long> id) {
		System.out.print("đã xóa thành công tòa nhà có id là: " + id);
	}
}
