package org.beeapp.backend.api.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TaskDTO {
    private Integer id;
    private String taskDate;
    private String title;
    private Boolean isDone;
}
