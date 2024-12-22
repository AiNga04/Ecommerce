package hcmute.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "review_text")
    private String reviewText;

    private String comment;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "milk_tea_id") // Đây là khóa ngoại đến bảng MilkTeaEntity
    private MilkTeaEntity milkTea;

    @ManyToOne
    @JoinColumn(name = "user_id") // Đây là khóa ngoại đến bảng UserEntity
    private UserEntity user;


}
