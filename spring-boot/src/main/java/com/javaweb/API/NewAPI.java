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
import java.util.*;

import javax.swing.plaf.basic.BasicComboBoxUI.ListDataHandler;

import org.springframework.web.bind.annotation.RequestBody;

import com.javaweb.Beans.BuildingDTO;
//import com.javaweb.Beans.BuildingDTO;
import com.javaweb.Beans.ErrorReponseDTO;
import com.javaweb.Beans.MarketDTO;
import com.javaweb.Beans.response.BuildingResponseDTO;
import com.javaweb.customexception.FileRequireException;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

import ch.qos.logback.core.joran.action.NewRuleAction;

@RestController
public class NewAPI {

	
	@Autowired
	public BuildingService buildingservice;
	

	@GetMapping(value = "/api/building/")
	public Object postBuilding2(@RequestParam(value="id",required = false) Long id,@RequestParam(value="districtId",required=false) String districtId) {
		List<BuildingResponseDTO> results=buildingservice.findAll(id,districtId);
		return results;
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
	public void deleteBuilding(@PathVariable Integer id) {
		System.out.print("đã xóa thành công tòa nhà có id là: " + id);
	}

}
