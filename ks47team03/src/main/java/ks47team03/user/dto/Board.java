package ks47team03.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity(name = "community_now_board")
@Data
public class Board {

	@Id
	@GeneratedValue(generator = "custom-generator")
	@GenericGenerator(name = "custom-generator", strategy = "ks47team03.user.dto.CustomIdGenerator")
	@Column(name = "community_now_board_code")
	private String community_now_board_code;
	private String user_id;
	private String community_now_board_title;
	private String community_now_board_content;
	private String admin_id;
	private int community_now_board_view;
	private int community_now_board_like;
	private LocalDateTime community_now_board_datetime;
	// 게시글 추가시 현재 시간 추가
	public void setBoardDatetime(LocalDateTime boardDatetime) {
		this.community_now_board_datetime = boardDatetime;
	}

	public String getSimpleCode() {
		return this.community_now_board_code.replace("community_now_board_code", "");
	}
}
