package com.appgate.ipgeo.service;

import com.appgate.ipgeo.dto.GeoLocationIpDto;
import org.springframework.web.multipart.MultipartFile;

public interface IpgeoService {
    public String processFile(MultipartFile multipartFile);

    public GeoLocationIpDto getIpRange(String ip);
}
