package org.beeapp.backend.api.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HealthDTO {
    private Integer id;
    private String inspectionDate;
    private int healthRating;
    private String diseases;
    private String medicationName;
    private String medicationAmount;
    private String additionalNotes;
}
