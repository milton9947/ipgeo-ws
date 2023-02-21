package com.appgate.ipgeo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocationDto {
    private String Country_code;
    private String Country;
    private String Region;
    private String City;
    private Double Latitude;
    private Double Longitude;
    private String TimeZone;
    private Long TotalRanges;
}
