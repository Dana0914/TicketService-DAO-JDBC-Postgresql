package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Ticket implements Serializable {
    private long id;
    private long userId;
    private TicketType ticketType;
    private LocalDate creationDate;

    private User user;


    public Ticket() {

    }
    public Ticket(long userId,TicketType ticketType,LocalDate creationDate) {
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public Ticket(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public Ticket(TicketType ticketType, LocalDate of) {
        this.ticketType = ticketType;
        this.creationDate = of;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public TicketType getTicketType() {
        return ticketType;
    }
    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
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
