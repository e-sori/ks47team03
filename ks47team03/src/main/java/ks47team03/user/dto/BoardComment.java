package ks47team03.user.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "community_now_board_comment")
@Data
public class BoardComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "community_now_board_comment_code")
	private Long commentCode;

	@Column(name = "community_now_board_code")
	private String boardCode;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "community_now_board_comment_content")
	private String boardCommentContent;

	@Column(name = "admin_id")
	private String adminId;

	@Column(name = "community_board_comment_datetime")
	private LocalDateTime commentDatetime;

}
