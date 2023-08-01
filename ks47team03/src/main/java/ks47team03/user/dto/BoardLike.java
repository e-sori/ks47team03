package ks47team03.user.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "community_now_board_like")
public class BoardLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "community_now_board_like_code")
	private Long id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "community_now_board_code")
	private String boardCode;

	@Column(name = "is_liked")
	private boolean liked;

	public BoardLike(String userId, String boardCode, boolean liked) {
		this.userId = userId;
		this.boardCode = boardCode;
		this.liked = liked;
	}
}
