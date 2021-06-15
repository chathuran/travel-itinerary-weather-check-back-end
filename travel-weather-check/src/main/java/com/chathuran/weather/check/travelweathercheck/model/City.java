package com.chathuran.weather.check.travelweathercheck.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "CITY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class City {

    @EmbeddedId
    private CityId cityId;


    @JsonManagedReference
    @OneToMany(mappedBy = "city",targetEntity = Temperature.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Temperature> temperatures;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;



}
