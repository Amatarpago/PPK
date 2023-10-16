/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JadwalDto {
    private Long id;
    @NotEmpty(message = "Hari wajib diisi.")
    private String hari;
    @NotNull(message = "ID jadwal wajib diisi.")
    private String jadwalID;
    @NotEmpty(message = "Sesi wajib diisi.")
    private String sesi;
    @NotNull(message = "Mata kuliah wajib diisi.")
    private String matkul;
    @NotEmpty(message = "Kelas wajib diisi.")
    private String kelas;
    @NotNull(message = "Ruangan wajib diisi.")
    private String ruangan;
    @NotNull(message = "Tanggal wajib diisi.")
    private Date tgl;
    @NotNull(message = "ID dosen wajib diisi.")
    private String dosenID;
    @NotEmpty(message = "Nama wajib diisi.")
    private String nama;
}
