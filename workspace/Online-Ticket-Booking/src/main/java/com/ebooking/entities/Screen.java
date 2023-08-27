package com.ebooking.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "screens")
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private int screenNo;

    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "seat",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Seat> seats = new HashSet<>();

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Show> shows = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id",nullable = false)
    private Theatre theatre;

    @ManyToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();
}
