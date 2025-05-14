package com.br.event_platform_backend.services.event_service.repository;

import com.br.event_platform_backend.services.event_service.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {

    boolean existsByTittle(String tittle);

}
