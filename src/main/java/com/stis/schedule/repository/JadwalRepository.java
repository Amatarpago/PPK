/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.stis.schedule.repository;

import com.stis.schedule.entity.Jadwal;
import com.stis.schedule.entity.User;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "jadwal", path = "jadwal")
public interface JadwalRepository extends PagingAndSortingRepository<Jadwal, Long>, CrudRepository<Jadwal, Long> {
    List<Jadwal> findByMatkul(@Param("matkul") String matkul);
    List<Jadwal> findByJadwalID(@Param("jadwalid") String jadwalID);
    List<Jadwal> findByNama(@Param("name") String nama);
    List<Jadwal> findByDosenID(@Param("dosenid") String dosenID);
    List<Jadwal> findByTgl(@Param("tgl") Date tgl);
    List<Jadwal> findByNama(User nama);
}
