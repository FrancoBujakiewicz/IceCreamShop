package org.logic.logic_classes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true)
    private String rol;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @ManyToMany
    @JoinTable(name = "ROL_ACTION", joinColumns = @JoinColumn(name = "ROL_ID"), inverseJoinColumns = @JoinColumn(name = "ACTION_ID"))
    private Set<Action> actions;

    public Rol() {}

    public Rol(String rol, Date created_at) {

        this.rol = rol;
        this.created_at = created_at;

    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRol() { return rol; }

    public void setRole(String rol) { this.rol = rol; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Date getUpdated_at() { return updated_at; }

    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

    public Set<Action> getActions() { return actions; }

    public void setActions(Set<Action> actions) { this.actions = actions; }

  }
