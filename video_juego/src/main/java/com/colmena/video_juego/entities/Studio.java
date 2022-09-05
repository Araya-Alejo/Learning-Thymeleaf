package com.colmena.video_juego.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table ( name = "studio" )
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Studio extends Base {

    private String name;
    private boolean active = true;

    @OneToMany ( mappedBy = "studio" )
    private List< Game > video_juegos;

}
