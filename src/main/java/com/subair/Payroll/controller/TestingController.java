package com.subair.Payroll.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/data")

public class TestingController {

    @PreAuthorize("hasRole('Admin')")
    @RequestMapping(value = "/adminAccess", method = RequestMethod.GET)
    public ResponseEntity<String> showDataForAdmin(){
        return ResponseEntity.ok("This message is for admin access only");
    }

    @PreAuthorize("hasAnyRole('User')")
    @RequestMapping(value = "/userAccess", method = RequestMethod.GET)
    public ResponseEntity<String> showDataForUser(){
        return ResponseEntity.ok("This message is for user access only");
    }
}