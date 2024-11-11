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
@Table(name="health_inspections")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Health {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_inspection_id")
    private Integer id;

    @Column(name = "inspection_date", nullable = false)
    private String inspectionDate;

    @Column(name = "health_rating", nullable = false)
    private int healthRating;

    @Column(name = "diseases")
    private String diseases;

    @Column(name = "medication_name")
    private String medicationName;

    @Column(name = "medication_amount")
    private String medicationAmount;

    @Column(name = "additional_notes")
    private String additionalNotes;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hive hive;
}
