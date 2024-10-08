package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestCountry;
import com.oms.wms.persistence.payload.response.DTOResponseCountry;
import com.oms.wms.service.ServiceCountry;
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

@RestController @RequestMapping("/country") @RequiredArgsConstructor
public class ControllerCountry implements ControllerInterface<DTOResponseCountry, DTORequestCountry> {

    private final ServiceCountry serviceCountry;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseCountry> create(@RequestBody @Valid DTORequestCountry created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/country").toUriString());
        return ResponseEntity.created(uri).body(serviceCountry.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<Page<DTOResponseCountry>> retrieve(@RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceCountry.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseCountry> update(@RequestBody @Valid DTORequestCountry updated){
        return ResponseEntity.accepted().body(serviceCountry.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseCountry> delete(@PathVariable UUID id){
        return ResponseEntity.accepted().body(serviceCountry.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceCountry.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}