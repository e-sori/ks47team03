package ks47team03.user.mapper;

import ks47team03.user.dto.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBoardMapper extends JpaRepository<Board, String> {
    List<Board> findByIsDeletedFalse();
    @Query("SELECT b FROM community_now_board b WHERE b.community_now_board_code = :boardCode")
    Board findByCommunityNowBoardCode(@Param("boardCode") String boardCode);
}
