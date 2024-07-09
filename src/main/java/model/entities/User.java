package model.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor

public class User {
    private long id;
    private String username;
    private LocalDate creationDate;

    private Ticket ticket;
    public User() {

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
