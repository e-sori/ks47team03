package ks47team03.user.mapper;


import ks47team03.user.dto.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserBoardCommentMapper extends JpaRepository<BoardComment, Long> {
    List<BoardComment> findByboardCode(String boardCode);
}
