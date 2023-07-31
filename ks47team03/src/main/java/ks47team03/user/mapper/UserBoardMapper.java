package ks47team03.user.mapper;

import ks47team03.user.dto.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBoardMapper extends JpaRepository<Board, String> {
    List<Board> findByIsDeletedFalse();
}
