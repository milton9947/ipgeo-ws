package com.appgate.ipgeo.service;

import com.appgate.ipgeo.dao.GeoLocationIpRepository;
import com.appgate.ipgeo.dao.GeoLocationRepository;
import com.appgate.ipgeo.dto.GeoLocationIpDto;
import com.appgate.ipgeo.model.GeoLocationIp;
import com.appgate.ipgeo.utils.IPUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class IpgeoServiceImpl implements IpgeoService {

    private final ProcessFileService processFileService;

    private final GeoLocationRepository geoLocationRepository;

    private final GeoLocationIpRepository geoLocationIpRepository;

    private final Mapper mapper;

    @Autowired
    public IpgeoServiceImpl(
            ProcessFileService processFileService, GeoLocationRepository geoLocationRepository, GeoLocationIpRepository geoLocationIpRepository, Mapper mapper) {
        this.processFileService = processFileService;
        this.geoLocationRepository = geoLocationRepository;
        this.geoLocationIpRepository = geoLocationIpRepository;
        this.mapper = mapper;
    }

    @Override
    public String processFile(MultipartFile multipartFile) {
        try {
            String fullPathStoreFile = processFileService.storeFile(multipartFile);
            processFileService.processFile(fullPathStoreFile);

            return "File in process ...";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GeoLocationIpDto getIpRange(String ip) {
        long decimalIp = IPUtils.getDecimalIP(ip);

        List<GeoLocationIp> geoLocationIps = geoLocationIpRepository
                .getIpRange(decimalIp);
        GeoLocationIp geoLocationIp = geoLocationIps.size()==0 ? null: geoLocationIps.get(0);

        return geoLocationIp == null ? null: mapper.map(geoLocationIp, GeoLocationIpDto.class);
    }
}
