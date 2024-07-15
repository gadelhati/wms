package com.oms.wms.controller;

import com.oms.wms.persistence.payload.request.DTORequestPerson;
import com.oms.wms.persistence.payload.response.DTOResponsePerson;
import com.oms.wms.service.ServicePerson;
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

@RestController @RequestMapping("/person") @RequiredArgsConstructor
public class ControllerPerson implements ControllerInterface<DTOResponsePerson, DTORequestPerson> {

    private final ServicePerson servicePerson;

    @PostMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponsePerson> create(@RequestBody @Valid DTORequestPerson created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/person").toUriString());
        return ResponseEntity.created(uri).body(servicePerson.create(created));
    }
    @GetMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<Page<DTOResponsePerson>> retrieve(@RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(servicePerson.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponsePerson> update(@RequestBody @Valid DTORequestPerson updated){
        return ResponseEntity.accepted().body(servicePerson.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<DTOResponsePerson> delete(@PathVariable UUID id){
        return ResponseEntity.accepted().body(servicePerson.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('8652ec73-0a53-433c-93be-420f1d90c681')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            servicePerson.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}