package com.dwang.app.rest.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String songTitle;

    @Column
    private String artistName;

    @Column
    private String userId;
}
