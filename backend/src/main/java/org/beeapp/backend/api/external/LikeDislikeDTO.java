package org.beeapp.backend.api.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class LikeDislikeDTO {
    private Integer id;
    private Boolean isLike;
    private Integer userId;
    private Integer postId;
}
