package com.nnk.springboot.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    Integer id;
    String moodysRating;
    String sandPRating;
    String fitchRating;
    Integer orderNumber;
}
