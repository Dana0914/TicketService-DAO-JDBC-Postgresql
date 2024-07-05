package model.impl;

import model.dao.TicketDao;
import model.entities.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class TicketDaoImpl implements TicketDao {
    SessionFactory sessionFactory;
    public TicketDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void findTicketById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            System.out.println(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void findByUserId(long userId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, userId);
            System.out.println(ticket);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("INSERT INTO Ticket (user_id,ticket_type, creation_date) VALUES(?1, CAST(?2 AS ticket_type), ?3)", Ticket.class)
                            .setParameter(1, ticket.getUserId())
                            .setParameter(2, ticket.getTicketType())
                            .setParameter(3, ticket.getCreationDate())
                            .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("UPDATE ticket SET ticket_type = CAST(?1 AS ticket_type) WHERE id = ?2")
                    .setParameter(1, ticket.getTicketType())
                    .setParameter(2, ticket.getId()).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteByTicketId(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM ticket where id = ?1")
                    .setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public void deleteByUsertId(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM ticket where user_id = ?1")
                    .setParameter(1, id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}