/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stis.schedule.controller;

import com.stis.schedule.dto.JadwalDto;
import com.stis.schedule.dto.UserDto;
import com.stis.schedule.service.JadwalService;
import com.stis.schedule.service.UserService;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping
public class JadwalController {

    @Autowired
    private JadwalService jadwalService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Melihat semua jadwal yang ada. Hanya bisa diakses admin.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "semua jadwal", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = JadwalDto.class)))
        })
            }
    )
    @GetMapping("/jadwal")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAll(Principal principal) {
        List<JadwalDto> jadwals = jadwalService.getAllJadwal();
        return ResponseEntity.ok().body(jadwals);
    }

    @Operation(summary = "Membuat jadwal. Hanya bisa diakses oleh admin.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "201", description = "berhasil membuat jadwal", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = JadwalDto.class))
        })
            }
    )
    @PostMapping("/jadwal")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> create(Principal principal, @RequestBody @Valid JadwalDto jadwal) {
        JadwalDto result = jadwalService.createJadwal(jadwal);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(summary = "Mengubah pengawas/dosen pada jadwal yang sudah ada.")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "berhasil mengubah laporan", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = JadwalDto.class))
        })
            }
    )
    @PutMapping("/jadwal/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> edit(@Parameter(description = "id dari jadwal yang ingin diubah") @PathVariable("id") Long jadwalID, @RequestBody @Valid JadwalDto request) {
        JadwalDto jadwal = jadwalService.getJadwal(jadwalID);
        jadwal.setNama(request.getNama());

        jadwal = jadwalService.updateJadwal(jadwal);
        return ResponseEntity.ok().body(jadwal);
    }
    
    @GetMapping("/jadwal/search/findByDosenID")
    @PreAuthorize("hasRole('ROLE_DOSEN') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findByDosenID(@RequestParam("dosenid") String dosenId) {
        List<JadwalDto> jadwalDtos = jadwalService.search(dosenId);
        return ResponseEntity.ok(jadwalDtos);
    }
    
}
