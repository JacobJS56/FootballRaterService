package com.jacob.footballrater.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(name="id", updatable = false)
    private UUID id;

    @Column(name="team_name", nullable=false, columnDefinition = "TEXT")
    private String teamName;

    @Column(name="league")
    private String league;

    @Column(name="manager")
    private String manager;

    @Column(name="stadium")
    private String stadium;

    @Value("${some.key:0}")
    @Column(name="rating")
    private Double rating;

}