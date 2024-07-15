package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestAddress;
import com.oms.wms.persistence.payload.response.DTOResponseAddress;
import com.oms.wms.service.ServiceAddress;
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

@RestController @RequestMapping("/address") @RequiredArgsConstructor
public class ControllerAddress implements ControllerInterface<DTOResponseAddress, DTORequestAddress> {

    private final ServiceAddress serviceAddress;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseAddress> create(@RequestBody @Valid DTORequestAddress created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/address").toUriString());
        return ResponseEntity.created(uri).body(serviceAddress.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<Page<DTOResponseAddress>> retrieve(@RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceAddress.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseAddress> update(@RequestBody @Valid DTORequestAddress updated){
        return ResponseEntity.accepted().body(serviceAddress.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseAddress> delete(@PathVariable UUID id){
        return ResponseEntity.accepted().body(serviceAddress.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceAddress.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}