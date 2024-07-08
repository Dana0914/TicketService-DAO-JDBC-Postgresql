package model.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Getter
@Setter

public class User implements Serializable {
    private long id;
    private String username;
    private LocalDate creationDate;

    private Ticket ticket;
    public User() {

    }
    public User(long id, String username, LocalDate creationDate) {
        this.id = id;
        this.username = username;
        this.creationDate = creationDate;
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
