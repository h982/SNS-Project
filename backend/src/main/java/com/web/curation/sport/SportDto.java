package com.web.curation.sport;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sport")
public class SportDto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sportId;

    @Column(name = "sport_name")
    private String name;

    public SportDto(int sportId) {
        this.sportId = sportId;
    }
}
