package org.logic.logic_classes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Size implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, unique = true)
    private String size;

    @Column
    private float price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    public Size() {}

    public Size(String size, float price, Date created_at) {

        this.size = size;
        this.price = price;
        this.created_at = created_at;

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSize() { return size; }

    public void setSize(String size) { this.size = size; }

    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Date getUpdated_at() { return updated_at; }

    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

}

