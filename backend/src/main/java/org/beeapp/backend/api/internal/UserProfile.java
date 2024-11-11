package org.beeapp.backend.api.internal;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserProfile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String image;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @JsonBackReference
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users user;

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                "image='" + image + '\'' +
                '}';
    }
}

