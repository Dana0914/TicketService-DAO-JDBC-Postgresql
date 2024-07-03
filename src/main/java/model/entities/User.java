package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class User implements Serializable {
    private long id;
    private String username;
    private LocalDate creationDate;

    private Ticket ticket;
    public User() {

    }
    public User(String username, LocalDate creationDate) {
        this.username = username;
        this.creationDate = creationDate;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(creationDate, user.creationDate) && Objects.equals(ticket, user.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, creationDate, ticket);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", creationDate=" + creationDate +
                ", ticket=" + ticket +
                '}';
    }
}
