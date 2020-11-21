package com.ntd.common.query;

import com.ntd.devicePart.params.DevicePartContainer;

public class DevicePartQuery {

    public static String SELECT_PART = "SELECT p FROM Part p";
    
    public static String CONDITION_PART_MANUFACTOR = " p.partManufactor LIKE CONCAT('%',:#{#devicePart.part_manufactor},'%')";
    
    public static String CONDITION_PART_MODEL = "WHERE p.partModel LIKE CONCAT('%',:#{#devicePart.part_model},'%')";
    
    public static String PartWhere(DevicePartContainer devicePartContainer) {
	
	String newQuery = SELECT_PART;
	newQuery += CONDITION_PART_MODEL;
	if (!devicePartContainer.getPartManufactor().isEmpty()) {
	    newQuery += CONDITION_PART_MANUFACTOR;
	}
	return newQuery;
    }
}
