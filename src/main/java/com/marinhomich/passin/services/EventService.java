package com.marinhomich.passin.services;

import com.marinhomich.passin.domain.attendee.Attendee;
import com.marinhomich.passin.domain.event.Event;
import com.marinhomich.passin.domain.event.exceptions.EventNotFoundException;
import com.marinhomich.passin.dto.event.EventIdDTO;
import com.marinhomich.passin.dto.event.EventRequestDTO;
import com.marinhomich.passin.dto.event.EventRespondeDTO;
import com.marinhomich.passin.repositories.AttendeeRepository;
import com.marinhomich.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventRespondeDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID: " + eventId));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
        return new EventRespondeDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO){
        Event newEvent = new Event();
        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);

        return normalized
                .replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[^\\w\\s]]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
