/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.mapper;

import com.stis.schedule.dto.JadwalDto;
import com.stis.schedule.entity.Jadwal;

public class JadwalMapper {
    
    public static Jadwal mapToJadwal (JadwalDto jadwalDto) {
        return Jadwal.builder()
                .id(jadwalDto.getId())
                .hari(jadwalDto.getHari())
                .jadwalID(jadwalDto.getJadwalID())
                .sesi(jadwalDto.getSesi())
                .matkul(jadwalDto.getMatkul())
                .kelas(jadwalDto.getKelas())
                .ruangan(jadwalDto.getRuangan())
                .tgl(jadwalDto.getTgl())
                .dosenID(jadwalDto.getDosenID())
                .nama(jadwalDto.getNama())
                .build();
    }

    public static JadwalDto mapToJadwalDto(Jadwal jadwal) {
        return JadwalDto.builder()
                .id(jadwal.getId())
                .hari(jadwal.getHari())
                .jadwalID(jadwal.getJadwalID())
                .sesi(jadwal.getSesi())
                .matkul(jadwal.getMatkul())
                .kelas(jadwal.getKelas())
                .ruangan(jadwal.getRuangan())
                .tgl(jadwal.getTgl())
                .dosenID(jadwal.getDosenID())
                .nama(jadwal.getNama())
                .build();
    }
}
