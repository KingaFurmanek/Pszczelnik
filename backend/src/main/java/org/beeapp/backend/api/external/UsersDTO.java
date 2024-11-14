package org.beeapp.backend.api.external;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UsersDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String role;
    private byte[] image;
    private boolean isBlocked;
}
