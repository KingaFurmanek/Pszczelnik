package org.beeapp.backend.api.internal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name="tasks")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer id;

    @Column(name = "task_date")
    private String taskDate;

    @Column(name = "title")
    private String title;

    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "hive_id")
    private Hive hive;
}
