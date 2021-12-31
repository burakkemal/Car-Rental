package com.etiya.ReCapProject.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_damages")
public class CarDamage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "damage_description")
    private String damageDescription;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;


}
