package org.beeapp.backend.api.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.Set;

@Data
@Entity
@Table(name="hive")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Hive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hive_id")
    private Integer id;

    private String name;

    @Column(name = "frames_count")
    private Integer framesCount;

    @Column(name = "location_latitude")
    private double locationLatitude;

    @Column(name = "location_longitude")
    private double locationLongitude;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "hive", cascade = CascadeType.ALL)
    private Set<Inspection> inspections;

    @OneToMany(mappedBy = "hive", cascade = CascadeType.ALL)
    private Set<Task> tasks;

    @OneToMany(mappedBy = "hive", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Health> healthInspections;

    @OneToMany(mappedBy = "hive", cascade = CascadeType.ALL)
    private Set<Feeding> feedings;
}
