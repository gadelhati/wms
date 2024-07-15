package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestLot;
import com.oms.wms.persistence.payload.response.DTOResponseLot;
import com.oms.wms.service.ServiceLot;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController @RequestMapping("/lot") @RequiredArgsConstructor
public class ControllerLot implements ControllerInterface<DTOResponseLot, DTORequestLot> {

    private final ServiceLot serviceLot;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseLot> create(@RequestBody @Valid DTORequestLot created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/lot").toUriString());
        return ResponseEntity.created(uri).body(serviceLot.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<Page<DTOResponseLot>> retrieve(@RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceLot.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseLot> update(@RequestBody @Valid DTORequestLot updated){
        return ResponseEntity.accepted().body(serviceLot.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseLot> delete(@PathVariable UUID id){
        return ResponseEntity.accepted().body(serviceLot.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceLot.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}