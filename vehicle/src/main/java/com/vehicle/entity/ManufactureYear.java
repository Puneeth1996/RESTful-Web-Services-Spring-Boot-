package com.vehicle.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Table(name = "manufactur_year")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufactureYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int year;
}