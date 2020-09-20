package com.ntd.dao;

import com.ntd.devicePart.params.DevicePartContainer;
import com.ntd.entity.Device;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    
    /**
     * 
     * @param devicePartContainer
     * @return
     */
        @Query("SELECT d FROM Device d WHERE "
    	+ "d.deviceModel LIKE CONCAT('%',:#{#devicePart.device_model},'%')"
    	+ " OR d.deviceSerialNumber LIKE CONCAT('%',:#{#devicePart.device_serial_number},'%')"
    	+ " OR d.cpuInfo LIKE CONCAT('%',:#{#devicePart.cpu_info},'%')"
    	+ " OR d.ramInfo LIKE CONCAT('%',:#{#devicePart.ram_info},'%')"
    	+ " OR d.volumeInfo LIKE CONCAT('%',:#{#devicePart.volume_info},'%')"
    	+ " OR d.deviceInfo LIKE CONCAT('%',:#{#devicePart.device_info},'%')"
	    )
    public List<Device> findByDeviceOnSearch(@Param("devicePart") DevicePartContainer devicePartContainer);

}
