package ks47team03.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks47team03.user.dto.Board;
import ks47team03.user.dto.BoardLike;
import ks47team03.user.mapper.UserBoardLikeMapper;
import ks47team03.user.mapper.UserBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserBoardService {

    @Autowired
    private UserBoardMapper userBoardMapper;
    @Autowired
    private UserBoardLikeMapper userBoardLikeMapper;
    // 게시글 작성
    public void communityBoardWrite(Board board, HttpServletRequest request){
        // 게시글 작성 날짜 추가
        board.setBoardDatetime(LocalDateTime.now());

        // 현재 로그인된 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String currentUserId = (String) session.getAttribute("SID");

        // 게시글 작성자 ID 설정
        board.setUser_id(currentUserId);

        userBoardMapper.save(board);
    }
    // 게시글 리스트
    public List<Board> communityBoardView(){

        return userBoardMapper.findAll();
    }
    // 게시글 상세 조회
    public Board communityBoardDetail(String boardCode){

        return userBoardMapper.findById(boardCode).get();
    }

    // 게시글 삭제
    public void communityBoardDelete(String boardCode, String currentUserId){

        deleteBoard(boardCode, currentUserId);
    }
    // 게시글 카운트
    public long communityBoardCount(){

        return userBoardMapper.count();
    }
    // 게시글 조회 수 증가 후 저장
    public void saveBoard(Board board) {

        userBoardMapper.save(board);
    }
    // isDelete 컬럼 0,1
    public List<Board> getBoards() {
        return userBoardMapper.findByIsDeletedFalse();
    }
    // 게시글 삭제 (소프트 삭제)
    public void deleteBoard(String boardId, String currentUserId) {
        Board board = userBoardMapper.findById(boardId).orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        //작성자, 현재 로그인 사용자 비교
        if (!board.getUser_id().equals(currentUserId)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }
        //예외 처리
        if (board.isDeleted()) {
            throw new IllegalStateException("이미 삭제된 게시글입니다.");
        }

        board.setDeleted(true);
        userBoardMapper.save(board);
    }
    // 게시글 좋아요
    public boolean toggleLike(String userId, String boardCode) {
        try {
            Board board = userBoardMapper.findByCommunityNowBoardCode(boardCode);
            if (board == null) {
                return false;
            }
            BoardLike boardLike = userBoardLikeMapper.findByUserIdAndBoardCode(userId, boardCode);
            if (boardLike == null) {
                boardLike = new BoardLike(userId, boardCode, true); // 좋아요
                board.setCommunity_now_board_like(board.getCommunity_now_board_like() + 1);
                userBoardLikeMapper.save(boardLike); // 좋아요 객체 저장
            } else {
                boardLike.setLiked(!boardLike.isLiked());
                if (boardLike.isLiked()) {
                    board.setCommunity_now_board_like(board.getCommunity_now_board_like() + 1);
                } else {
                    board.setCommunity_now_board_like(board.getCommunity_now_board_like() - 1);
                }
                userBoardLikeMapper.save(boardLike); // 좋아요 객체 업데이트
            }
            userBoardMapper.save(board);
            return boardLike.isLiked(); // 최종 좋아요 상태 반환
        } catch (Exception e) {
            return false;
        }
    }
}
