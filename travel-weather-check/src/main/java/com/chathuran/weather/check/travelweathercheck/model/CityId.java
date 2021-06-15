package com.chathuran.weather.check.travelweathercheck.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class CityId implements Serializable {
    @Column(name = "CITY_NAME")
    private String cityName;
    @Column(name = "COUNTRY_CODE")
    private String countryCode;


}
