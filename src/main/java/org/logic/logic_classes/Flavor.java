package org.logic.logic_classes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class Flavor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true)
    private String flavor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @ManyToMany(mappedBy = "flavors")
    private ArrayList<IceCream> ice_creams;

    public Flavor() {}

    public Flavor(String flavor, Date created_at, Date updated_at, ArrayList<IceCream> ice_creams) {

        this.flavor = flavor;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.ice_creams = ice_creams;

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFlavor() { return flavor; }

    public void setFlavor(String flavor) { this.flavor = flavor; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Date getUpdated_at() { return updated_at; }

    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

    public ArrayList<IceCream> getIce_creams() { return ice_creams; }

    public void setIce_creams(ArrayList<IceCream> ice_creams) { this.ice_creams = ice_creams; }

}
