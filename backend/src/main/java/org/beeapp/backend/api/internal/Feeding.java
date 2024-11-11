package org.beeapp.backend.api.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name="feedings")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Feeding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeding_id")
    private Integer id;

    @Column(name = "feeding_date")
    private String feedingDate;

    @Column(name = "food_type")
    private String foodType;

    @Column(name = "food_amount")
    private double foodAmount;

    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hive hive;
}
