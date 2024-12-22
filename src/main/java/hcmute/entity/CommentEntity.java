package hcmute.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int rating; // Số sao

    @Column(nullable = false)
    private String content; // Nội dung bình luận

    @Column(nullable = true)
    private String images; // Link hình ảnh/video (nếu có)

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private String productVersion;

}

