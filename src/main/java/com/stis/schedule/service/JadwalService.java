/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.stis.schedule.service;

import com.stis.schedule.dto.JadwalDto;
import com.stis.schedule.dto.UserDto;
import java.util.Date;
import java.util.List;

public interface JadwalService {
    
//    public void createJadwal(JadwalDto jadwalDto);
    JadwalDto createJadwal(JadwalDto report);
    JadwalDto updateJadwal(JadwalDto report);
    boolean deleteJadwal(Long id);
    JadwalDto getJadwal(Long id);
    List<JadwalDto> getAllJadwal();
    List<JadwalDto> getAllJadwalByUser(UserDto user);
    public List<JadwalDto> getJadwals();
    public List<JadwalDto> search(String keyword);
}
