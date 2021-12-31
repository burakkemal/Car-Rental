package com.etiya.ReCapProject.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additional_services")
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "daily_price")
    private int dailyPrice;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "additionalService")
    private List<AdditionalRentalItem> additionalRentalItems;
}
