package id.test.crud.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "nama")
    private String nama;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Integer status;
}
