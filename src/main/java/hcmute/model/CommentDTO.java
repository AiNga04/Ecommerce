package hcmute.model;

import hcmute.entity.MilkTeaEntity;
import hcmute.entity.UserEntity;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String reviewText;

    private String comment;
    private MilkTeaEntity milkTea;
    private UserEntity user;
    private String imageUrl;

    private String username; // Tên người dùng
    private String name;     // Tên đầy đủ của người dùng
    private String surname;
    private Date createdAt;

    private Date updatedAt;
}
