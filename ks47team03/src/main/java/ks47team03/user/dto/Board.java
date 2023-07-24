package ks47team03.user.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

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
	private String community_now_board_datetime;

	public String getSimpleCode() {
		return this.community_now_board_code.replace("community_now_board_code", "");
	}
}
