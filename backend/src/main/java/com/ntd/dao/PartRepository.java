package com.ntd.dao;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Part;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartRepository extends JpaRepository<Part,Long> {
    
    @Query("SELECT p FROM Part p WHERE "
	    + " p.partModel LIKE CONCAT('%',:#{#devicePart.part_model},'%')"
	    + " OR p.partManufactor LIKE CONCAT('%',:#{#devicePart.part_manufactor},'%')"
		    )
    List<Part> findByPartOnSearch(@Param("devicePart") DevicePartContainer devicePartContainer);

}
