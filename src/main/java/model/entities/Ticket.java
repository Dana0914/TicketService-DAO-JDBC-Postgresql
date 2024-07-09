package model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
public class Ticket {
    private long id;
    private long userId;
    private TicketType ticketType;
    private LocalDate creationDate;

    private User user;


    public Ticket() {

    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", userId=" + userId +
                ", ticketType=" + ticketType +
                ", creationDate=" + creationDate +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id && userId == ticket.userId && ticketType == ticket.ticketType && Objects.equals(creationDate, ticket.creationDate) && Objects.equals(user, ticket.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, ticketType, creationDate, user);
    }
}
