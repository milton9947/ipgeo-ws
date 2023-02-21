package com.appgate.ipgeo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long IP_from;
    private Long IP_to;
    private String Country_code;
    private String Country;
    private String Region;
    private String City;
    private Double Latitude;
    private Double Longitude;
    private String TimeZone;
    private char status;
}
