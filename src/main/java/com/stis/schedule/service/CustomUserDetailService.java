/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.service;

import com.stis.schedule.dto.UserDto;
import com.stis.schedule.entity.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("securityService")
public class CustomUserDetailService {

//    @Autowired
//    private ReportService reportService;

    @Autowired
    private UserService userService;
    
//    public boolean canEditReport(Long userId, Long reportId) {
//        ReportDto report = reportService.getReport(reportId);
//
//        return report.getReporter().getId().equals(userId);
//    }

    public boolean hasRole(Long userId, String role) {
        UserDto user = userService.getUser(userId);
        return userService.hasRole(user, ERole.valueOf(role));
    }
}

