package com.appgate.ipgeo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocationIp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long IP_from;
    private Long IP_to;

    @ManyToOne
    @JoinColumn(name = "FK_GEO_LOCATION", nullable = false, updatable = false)
    private GeoLocation geoLocation;
}
