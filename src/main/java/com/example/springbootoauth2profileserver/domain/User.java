package com.example.springbootoauth2profileserver.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @Column
    private String uid;

    @Column
    private String name;

    @Column
    private String email;
}
