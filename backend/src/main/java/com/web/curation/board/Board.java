package com.web.curation.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.curation.team.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "Board")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer boardId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String contents;

    @JsonIgnore
    @Column(name = "write_date", insertable = false, updatable = false)
    private LocalDateTime writeDate;

    private String writer;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
