package com.marinhomich.passin.controllers;

import com.marinhomich.passin.domain.event.Event;
import com.marinhomich.passin.dto.event.EventIdDTO;
import com.marinhomich.passin.dto.event.EventRequestDTO;
import com.marinhomich.passin.dto.event.EventRespondeDTO;
import com.marinhomich.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping("/{id}")
    public ResponseEntity<EventRespondeDTO> getEvent(@PathVariable String id){
        EventRespondeDTO event =  this.service.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        EventIdDTO eventIdDTO = this.service.createEvent(body);
        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }



}
