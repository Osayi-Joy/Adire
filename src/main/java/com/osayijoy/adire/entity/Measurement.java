package com.osayijoy.adire.entity;

import com.osayijoy.adire.enums.MeasurementType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Measurement extends BaseEntity{
    @Column(name = "measurements")
    @Enumerated(EnumType.STRING)
    private MeasurementType measurementType;


    private double value;



}
