package com.marinhomich.passin.services;

import com.marinhomich.passin.domain.attendee.Attendee;
import com.marinhomich.passin.domain.checkin.CheckIn;
import com.marinhomich.passin.dto.attendee.AttendeesDetails;
import com.marinhomich.passin.dto.attendee.AttendeesListResponseDTO;
import com.marinhomich.passin.repositories.AttendeeRepository;
import com.marinhomich.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private AttendeeRepository attendeeRepository;
    private CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId){
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);
        List<AttendeesDetails> attendeesDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkinRepository.findByAttendId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
            return new AttendeesDetails(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreateAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeesDetailsList);
    }
}
