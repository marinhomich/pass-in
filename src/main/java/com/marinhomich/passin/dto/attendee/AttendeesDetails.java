package com.marinhomich.passin.dto.attendee;

import java.time.LocalDateTime;

public record AttendeesDetails(String id, String name, String email, LocalDateTime createdAt, LocalDateTime checkedInAt) {
    
}
