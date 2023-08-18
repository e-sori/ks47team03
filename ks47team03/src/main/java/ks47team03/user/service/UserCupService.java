package ks47team03.user.service;

import ks47team03.user.mapper.UserCupMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserCupService {
    private final UserCupMapper userCupMapper;

    public UserCupService(UserCupMapper userCupMapper){
        this.userCupMapper = userCupMapper;
    }

    // 컵 대여 리스트
    public List<Map<String,Object>> getRentalCupList(String userId){
        List<Map<String,Object>> userRentalCupList = userCupMapper.getRentalCupList(userId);

        return userRentalCupList;
    }

    // 회원 별 컵 대여 개수 조회
    public int getUseCupCount(String userId){
        int cupRentalCount = userCupMapper.getUseCupCount(userId);

        return cupRentalCount;
    }
}
