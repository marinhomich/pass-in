package com.marinhomich.passin.repositories;

import com.marinhomich.passin.domain.checkin.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {
}
