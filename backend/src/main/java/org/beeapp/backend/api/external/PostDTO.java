package org.beeapp.backend.api.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PostDTO {
    private Integer id;
    private String title;
    private String content;
    private UsersDTO author;
    private byte[] image;
    private Date createdAt;
    private List<String> tags = new ArrayList<>();
    private List<CommentDTO> comments = new ArrayList<>();
    private int likeCount;
    private int dislikeCount;
}
