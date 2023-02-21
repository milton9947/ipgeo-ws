package com.appgate.ipgeo.dao;

import com.appgate.ipgeo.model.GeoLocationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface GeoLocationRepository extends JpaRepository<GeoLocationData, Long> {
    @Query(value = "SELECT * FROM geo_location_data WHERE ip_from <= :ip and ip_to >= :ip", nativeQuery = true)
    List<GeoLocationData> getIpRange(@Param("ip") Long ip);

    @Query(value = "CALL sp_ipgeolocation()", nativeQuery = true)
    @Transactional
    @Modifying(clearAutomatically = true)
    void generateGeoLocationIP();
}
