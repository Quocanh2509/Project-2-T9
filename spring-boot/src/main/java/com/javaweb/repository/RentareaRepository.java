package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import com.javaweb.repository.entity.RentareaEntity;
import com.javaweb.repository.impl.RentareaRepositoryImpl;

public interface RentareaRepository {
	List<RentareaEntity> findAlll(Map<String, Object> request);
}
