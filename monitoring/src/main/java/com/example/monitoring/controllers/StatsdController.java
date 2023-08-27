package com.example.monitoring.controllers;

import com.timgroup.statsd.StatsDClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StatsdController {

    @Autowired
    StatsDClient statsDClient;

    @GetMapping("/infa/statsd/push")
    public ResponseEntity<Object> pushStats() throws InterruptedException {

        List<String> tags = new ArrayList<>();
        tags.add("application : " + "DBMI");

        for (int i = 0; i < 1000; i++) {
            statsDClient.incrementCounter("example_metric.increment", new String[]{"environment:dev"});
            statsDClient.recordExecutionTime("appmi.latency",100, new String[]{"environment:dev"});
        }
        return new ResponseEntity<>("Statsd published Successfully", HttpStatus.OK);
    }
}
