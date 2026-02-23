package com.nnk.springboot.domain;

//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
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
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    Integer id;
    String name;
    String description;
    String json;
    String template;
    String sqlStr;
    String sqlPart;
}
