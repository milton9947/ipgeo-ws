package com.appgate.ipgeo.service;

import com.appgate.ipgeo.constants.GeoLocationDataStatus;
import com.appgate.ipgeo.dao.GeoLocationRepository;
import com.appgate.ipgeo.model.GeoLocationData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class ProcessFileServiceImpl implements ProcessFileService {
    private final GeoLocationRepository geoLocationRepository;

    @Value("${ipgeo.path-store-file}")
    private String PATH_STORE_FILE;
    @Value("${ipgeo.pref-store-file}")
    private String PREF_STORE_FILE = "ipgeo";

    @Autowired
    public ProcessFileServiceImpl(GeoLocationRepository geoLocationRepository) {
        this.geoLocationRepository = geoLocationRepository;
    }

    @Override
    public String storeFile(MultipartFile multipartFile) throws IOException {
        Path pathFile = Paths.get(PATH_STORE_FILE);

        if( !Files.exists(pathFile) ) {
            new File(pathFile.toString()).mkdirs();
        }

        String fullPathStoreFile = pathFile + "/" + PREF_STORE_FILE + "-" + System.currentTimeMillis();
        File file = new File(fullPathStoreFile);
        multipartFile.transferTo(file);

        return fullPathStoreFile;
    }

    @Override
    @Async
    public void processFile(String pathFile) throws IOException {
        Path pathProcessFile = Paths.get(pathFile);
        Stream<String> lines = Files.lines(pathProcessFile);
        List<GeoLocationData> geoLocations = new ArrayList<>();

        lines.forEach((line)->{
            List<String> columns = List.of(line.split(",")).stream()
                    .map(i->i.replaceAll("\"", ""))
                    .collect(Collectors.toList());

            GeoLocationData geoLocation = GeoLocationData.builder()
                    .IP_from(Long.parseLong(columns.get(0)))
                    .IP_to(Long.parseLong(columns.get(1)))
                    .Country_code(columns.get(2))
                    .Country(columns.get(3))
                    .Region(columns.get(4))
                    .City(columns.get(5))
                    .Latitude(Double.parseDouble(columns.get(6)))
                    .Longitude(Double.parseDouble(columns.get(7)))
                    .TimeZone(columns.get(8))
                    .status(GeoLocationDataStatus.LOADED.getStatus())
                    .build();
            geoLocations.add(geoLocation);
        });

        geoLocationRepository.saveAll(geoLocations);
        lines.close();

        geoLocationRepository.generateGeoLocationIP();

        log.info("FINISHED: processFile method with file {}", pathFile);
    }
}
