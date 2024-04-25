package com.marinhomich.passin.dto.event;

import com.marinhomich.passin.domain.event.Event;

public class EventRespondeDTO {
    EventDetailDTO event;

    public EventRespondeDTO(Event event, Integer numberOfAttendees){
        this.event = new EventDetailDTO(event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), numberOfAttendees);
    }
}
