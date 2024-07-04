package model.entities;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

}