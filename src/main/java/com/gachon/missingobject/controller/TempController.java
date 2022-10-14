package com.gachon.missingobject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping(value="/hello")
    public String hello(){
        return "hello world";
    }

}
