package com.appgate.ipgeo.controller;

import com.appgate.ipgeo.service.IpgeoService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.Duration;

@RestController
@Validated
@RequestMapping("/ipgeo-ws")
public class IpgeoController {
    private final IpgeoService ipgeoService;

    private final Bucket bucket;

    private final int RPM = 1;
    private final int RPM_MINUTES = 20;

    @Autowired
    public IpgeoController(IpgeoService ipgeoService) {
        this.ipgeoService = ipgeoService;

        Bandwidth limit = Bandwidth.classic(RPM, Refill.greedy(RPM, Duration.ofMinutes(RPM_MINUTES)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping
    public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile multipartFile) {
        if (bucket.tryConsume(1)) {
            return new ResponseEntity<>(ipgeoService.processFile(multipartFile), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping
    public ResponseEntity<Object> getIpRange(
            @RequestParam
            @Valid
            @Pattern(regexp = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$", message = "")
            String ip) {
        return new ResponseEntity<>(ipgeoService.getIpRange(ip), HttpStatus.OK);
    }
}
