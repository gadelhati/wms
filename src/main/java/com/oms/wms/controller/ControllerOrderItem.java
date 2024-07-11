package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestOrderItem;
import com.oms.wms.persistence.payload.response.DTOResponseOrderItem;
import com.oms.wms.service.ServiceOrderItem;
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

@RestController @RequestMapping("/orderItem") @RequiredArgsConstructor
public class ControllerOrderItem {

    private final ServiceOrderItem serviceOrderItem;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<DTOResponseOrderItem> create(@RequestBody @Valid DTORequestOrderItem created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/orderItem").toUriString());
        return ResponseEntity.created(uri).body(serviceOrderItem.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<Page<DTOResponseOrderItem>> retrieve(@RequestParam(name = "value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceOrderItem.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<DTOResponseOrderItem> update(@RequestBody @Valid DTORequestOrderItem updated){
        return ResponseEntity.accepted().body(serviceOrderItem.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681', '52c57a80-4e3b-4a41-a864-58d0cea25b14')")
    public ResponseEntity<DTOResponseOrderItem> delete(@PathVariable UUID id){
        return ResponseEntity.accepted().body(serviceOrderItem.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceOrderItem.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}