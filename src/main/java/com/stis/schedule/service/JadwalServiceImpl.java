/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.service;

import com.stis.schedule.dto.JadwalDto;
import com.stis.schedule.dto.UserDto;
import com.stis.schedule.entity.Jadwal;
import com.stis.schedule.mapper.JadwalMapper;
import com.stis.schedule.mapper.UserMapper;
import com.stis.schedule.repository.JadwalRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JadwalServiceImpl implements JadwalService {
    @Autowired
    private JadwalRepository jadwalRepository;

    @Override
    public JadwalDto createJadwal(JadwalDto jadwal) {
        Jadwal result = jadwalRepository.save(JadwalMapper.mapToJadwal(jadwal));
        return JadwalMapper.mapToJadwalDto(result);
    }

    @Override
    public JadwalDto updateJadwal(JadwalDto jadwal) {
        Jadwal result = jadwalRepository.save(JadwalMapper.mapToJadwal(jadwal));
        return JadwalMapper.mapToJadwalDto(result);
    }

    @Override
    public boolean deleteJadwal(Long id) {
        jadwalRepository.deleteById(id);
        return true;
    }

    @Override
    public List<JadwalDto> getAllJadwal() {
        List<Jadwal> jadwals = (List<Jadwal>) jadwalRepository.findAll();

        return jadwals.stream()
            .map(jadwal -> JadwalMapper.mapToJadwalDto(jadwal))
            .collect(Collectors.toList());
    }

    @Override
    public List<JadwalDto> getAllJadwalByUser(UserDto user) {
        List<Jadwal> jadwals = jadwalRepository.findByNama(UserMapper.mapToEntity(user));

        return jadwals.stream()
            .map(jadwal -> JadwalMapper.mapToJadwalDto(jadwal))
            .collect(Collectors.toList());
    }

    @Override
    public JadwalDto getJadwal(Long id) {
        Optional<Jadwal> jadwal = jadwalRepository.findById(id);

        return jadwal.isPresent() ? JadwalMapper.mapToJadwalDto(jadwal.get()) : null;
    }
    
    @Override
    public List<JadwalDto> getJadwals() {
        List<Jadwal> jadwals = (List<Jadwal>) jadwalRepository.findAll();
        List<JadwalDto> jadwalDtos = jadwals.stream()
                .map((product) -> (JadwalMapper.mapToJadwalDto(product)))
                .collect(Collectors.toList());
        return jadwalDtos;
    }
    
    @Override
    public List<JadwalDto> search(String keyword) {
        List<Jadwal> jadwals = jadwalRepository.findByDosenID(keyword);
        List<JadwalDto> bookDtos = jadwals.stream()
            .map((product) -> (JadwalMapper.mapToJadwalDto(product)))
            .collect(Collectors.toList());        
        return bookDtos;
    }
}
