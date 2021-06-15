package com.chathuran.weather.check.travelweathercheck.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoriteCity {
    @EmbeddedId
    private CityId cityId;
    @OneToOne
    private City city;
}
