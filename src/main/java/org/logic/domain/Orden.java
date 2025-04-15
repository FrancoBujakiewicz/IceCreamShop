package org.logic.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Orden implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Invoice invoice;

    public Orden() {}

    public Orden(String address, Date created_at,User user, Invoice invoice) {

        this.address = address;
        this.created_at = created_at;
        this.user = user;
        this.invoice = invoice;

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Invoice getInvoice() { return invoice; }

    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

}
