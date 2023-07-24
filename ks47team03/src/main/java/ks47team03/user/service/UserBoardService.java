package ks47team03.user.service;

import ks47team03.user.dto.Board;
import ks47team03.user.mapper.UserBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBoardService {

    @Autowired
    private UserBoardMapper userBoardMapper;

    // 게시글 작성
    public void boardWrite(Board board){

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
