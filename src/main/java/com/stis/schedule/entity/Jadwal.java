/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jadwal")
public class Jadwal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String jadwalID;
    
    @Column(nullable = false)
    private String hari;
    
    @Column(nullable = false)
    private String sesi;
    
    @Column(nullable = false)
    private String matkul;
    
    @Column(nullable = false)
    private String ruangan;
    
    @Column(nullable = false)
    private String kelas;
    
    @Column(nullable = false)
    private Date tgl;
    
    @Column(nullable = false)
    private String dosenID;
    
    @Column(nullable = false)
    private String nama;
}
