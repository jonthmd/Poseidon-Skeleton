package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Integer id;

    @NotBlank(message = "Must not be null.")
    @Column(name="CurveId")
    Integer curveId;
    Timestamp asOfDate;
    Double term;
    Double value;
    Timestamp creationDate;
}
