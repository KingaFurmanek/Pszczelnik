package org.beeapp.backend.api.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name="queens")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Queen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "queen_id")
    private Integer id;

    @Column(name = "queen_date")
    private String queenDate;

    @Column(name = "color")
    private String color;

    private String notes;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    private Hive hive;
}
