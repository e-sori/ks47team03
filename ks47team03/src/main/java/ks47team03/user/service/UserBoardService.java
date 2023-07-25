package ks47team03.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks47team03.user.dto.Board;
import ks47team03.user.mapper.UserBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserBoardService {

    @Autowired
    private UserBoardMapper userBoardMapper;

    // 게시글 작성
    public void boardWrite(Board board, HttpServletRequest request){
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
    //게시글 상세 조회
    public Board communityBoardDetail(String boardCode){

        return userBoardMapper.findById(boardCode).get();
    }
}
