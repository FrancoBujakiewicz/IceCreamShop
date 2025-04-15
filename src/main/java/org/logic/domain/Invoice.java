package org.logic.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private float total_cost;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Invoice() {}

    public Invoice(float total_cost, Date created_at) {

        this.total_cost = total_cost;
        this.created_at = created_at;

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public float getTotal_cost() { return total_cost; }

    public void setTotal_cost(float total_cost) { this.total_cost = total_cost; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

}
