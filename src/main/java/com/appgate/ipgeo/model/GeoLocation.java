package com.appgate.ipgeo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Country_code;
    private String Country;
    private String Region;
    private String City;
    private Double Latitude;
    private Double Longitude;
    private String TimeZone;
    private Long TotalRanges;
    private char status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "geoLocation")
    private List<GeoLocationIp> geoLocationIps;
}
