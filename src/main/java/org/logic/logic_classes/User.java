package org.logic.logic_classes;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String username;

    @Column(length = 30, unique = true)
    private Long phone_number;

    @Column(length = 60, unique = true, name = "PASSWD_HASH")
    private String password;

    @Column(length = 50, unique = true)
    private String salt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updated_at;

    @ManyToOne
    @JoinColumn
    private Rol role;
    
    public User() {}

    public User(String username, Long phone_number, String password, Date created_at, Rol role) {

        this.username = username;
        this.phone_number = phone_number;
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, this.salt);
        this.created_at = created_at;
        this.role = role;
        
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Long getPhone_number() { return phone_number; }

    public void setPhone_number(Long phone_number) { this.phone_number = phone_number; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Date getCreated_at() { return created_at; }

    public void setCreated_at(Date created_at) { this.created_at = created_at; }

    public Date getUpdated_at() { return updated_at; }

    public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

    public String getSalt() { return salt; }

    public void setSalt(String salt) { this.salt = salt; }

    public Rol getRole() { return role; }

    public void setRole(Rol role) { this.role = role; }

    public boolean verifiedPassword(String password, String password_hash)

    {
        boolean correctPasswd = BCrypt.checkpw(password, password_hash);
        if(!correctPasswd){ System.out.println("Incorrect password. Try Again."); }
        return correctPasswd;
    }

}
