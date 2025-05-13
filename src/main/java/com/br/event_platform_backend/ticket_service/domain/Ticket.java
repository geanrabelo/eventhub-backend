package com.br.event_platform_backend.ticket_service.domain;

import com.br.event_platform_backend.event_service.domain.Event;
import com.br.event_platform_backend.ticket_service.enums.TicketStatus;
import com.br.event_platform_backend.user_service.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "purchase_date_time")
    private LocalDateTime purchaseDateTime;

    @Column(name = "ticket_status")
    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @Column(name = "price_paid")
    private BigDecimal pricePaid;
}
