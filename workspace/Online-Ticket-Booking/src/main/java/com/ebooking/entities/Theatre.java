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
@Table(name="theatres")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean isHoliday;

    @OneToMany(mappedBy = "screen",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Screen> screens = new HashSet<>();

    @ManyToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Movie> movies = new HashSet<>();
}
