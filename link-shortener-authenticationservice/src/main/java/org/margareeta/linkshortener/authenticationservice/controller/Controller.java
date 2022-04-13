package org.margareeta.linkshortener.authenticationservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/api/v1/")
    private String ping(@RequestParam(name = "ping") String ping){
        return "pong";
    }
}
