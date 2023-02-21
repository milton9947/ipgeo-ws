package com.appgate.ipgeo.dao;

import com.appgate.ipgeo.model.GeoLocationIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GeoLocationIpRepository extends JpaRepository<GeoLocationIp, Long> {
    @Query(value = "SELECT * FROM geo_location_ip WHERE ip_from <= :ip and ip_to >= :ip", nativeQuery = true)
    List<GeoLocationIp> getIpRange(@Param("ip") Long ip);
}
