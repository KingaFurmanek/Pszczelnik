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
@Table(name="inspections")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Inspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inspection_id")
    private Integer id;

    @Column(name = "harvest_date")
    private String harvestDate;

    @Column(name = "honey_amount")
    private double honeyAmount;

    @Column(name = "honey_type")
    private String honeyType;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hive hive;
}