package com.chathuran.weather.check.travelweathercheck.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "TEMPERATURE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private LocalDate date;
    private Double temp;
    private String weather;
    private String weatherIcon;
    private int clouds;
    private LocalTime time;

    @JsonBackReference
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="cityName"),
            @JoinColumn(name="countryCode")
    })
    private City city;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdDate;




}
