package com.colmena.video_juego.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table ( name = "game" )
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game extends Base {

    @NotEmpty ( message = "The title can't be empty" )
    private String title;

    @Min ( value = 5, message = "The price must be ' 5 ' as minimum" )
    @Max ( value = 10000, message = "The price must be ' 10 000 ' as maximum" )
    private float price;

    @Min ( value = 5, message = "The game stock must be ' 5 ' minimum" )
    @Max ( value = 50, message = "The game stock must be ' 50 ' minimum" )
    private short amount;

    @Size ( min = 5, max = 100, message = "The description must be between 5 and 100 words" )
    private String description;

    private boolean offer;

    @DateTimeFormat ( pattern = "yyyy-MM-dd" )
    @NotNull ( message = "The launch can't be null" )
    @PastOrPresent ( message = "The launch can't be of future" )
    @PastOrPresent ( message = "The launch can't be of future" )
    private Date launch;

    private boolean active = true;

    private String path_img;

    @NotNull ( message = "The studio is a requirement, can't be null" )
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_studio", nullable = false )
    private Studio studio;

    @NotNull ( message = "The category is a requirement, can't be null" )
    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_category", nullable = false )
    private Category category;
}
