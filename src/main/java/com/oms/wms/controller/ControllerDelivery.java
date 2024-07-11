package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestDelivery;
import com.oms.wms.persistence.payload.response.DTOResponseDelivery;
import com.oms.wms.service.ServiceDelivery;
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

@RestController @RequestMapping("/delivery") @RequiredArgsConstructor
public class ControllerDelivery {

    private final ServiceDelivery serviceDelivery;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<DTOResponseDelivery> create(@RequestBody @Valid DTORequestDelivery created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/delivery").toUriString());
        return ResponseEntity.created(uri).body(serviceDelivery.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<Page<DTOResponseDelivery>> retrieve(@RequestParam(name = "value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceDelivery.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<DTOResponseDelivery> update(@RequestBody @Valid DTORequestDelivery updated){
        return ResponseEntity.accepted().body(serviceDelivery.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<DTOResponseDelivery> delete(@PathVariable UUID id){
        return ResponseEntity.accepted().body(serviceDelivery.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceDelivery.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}