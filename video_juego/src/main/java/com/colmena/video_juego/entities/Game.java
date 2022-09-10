package com.colmena.video_juego.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table ( name = "game" )
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Game extends Base {

    private String title;
    private String path_img;
    private float price;
    private short amount;
    private String description;
    private boolean offer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date launch;
    private boolean active = true;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_studio", nullable = false )
    private Studio studio;

    @ManyToOne ( fetch = FetchType.EAGER )
    @JoinColumn ( name = "fk_category", nullable = false )
    private Category category;
}
