package org.beeapp.backend.api.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HiveDTO {
    private int id;
    private String name;
    private int framesCount;
    private double locationLatitude;
    private double locationLongitude;
    private byte[] image;
}
