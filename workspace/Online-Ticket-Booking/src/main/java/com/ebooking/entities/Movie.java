package com.ebooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(nullable = false)
    private double ticketCost;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Show> shows = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_screens",
            joinColumns = {
                    @JoinColumn(name = "movies_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "screens_id")
            }
    )
    private Set<Screen> screens = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "movies_theatres",
            joinColumns = {
                    @JoinColumn(name = "movies_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "theatres_id")
            }
    )
    private Set<Theatre> theatres = new HashSet<>();
}
