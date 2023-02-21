package com.appgate.ipgeo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocationIpDto {
    private Long IP_from;
    private Long IP_to;
    private GeoLocationDto geoLocation;
}
