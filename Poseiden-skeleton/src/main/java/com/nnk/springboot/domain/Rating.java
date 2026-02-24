package com.nnk.springboot.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "moodys_rating")
    private String moodysRating;

    @Column(name = "sandprating")
    private String sandPRating;

    @Column(name = "fitch_rating")
    private String fitchRating;

    @Column(name = "order_number")
    private Integer order;
}
