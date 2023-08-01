package ks47team03.user.mapper;

import ks47team03.user.dto.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBoardLikeMapper extends JpaRepository<BoardLike, Long> {
    BoardLike findByUserIdAndBoardCode(String userId, String boardCode);
}
