package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestRole;
import com.oms.wms.persistence.payload.response.DTOResponseRole;
import com.oms.wms.service.ServiceRole;
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

@RestController @RequestMapping("/role") @RequiredArgsConstructor
public class ControllerRole implements ControllerInterface<DTOResponseRole, DTORequestRole> {

    private final ServiceRole serviceRole;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseRole> create(@RequestBody @Valid DTORequestRole created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role").toUriString());
        return ResponseEntity.created(uri).body(serviceRole.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<Page<DTOResponseRole>> retrieve(@RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceRole.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseRole> update(@RequestBody @Valid DTORequestRole updated){
        return ResponseEntity.accepted().body(serviceRole.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @Override @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponseRole> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(serviceRole.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceRole.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}