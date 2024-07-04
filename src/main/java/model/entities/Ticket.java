package model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;
    @Column(name = "ticket_type")
    private String ticketType;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}