package ks47team03.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserCupMapper {
    // 컵 대여 리스트 조회
    public List<Map<String,Object>> getRentalCupList(String userId);

    //컵 대여 개수 조회
    public int getUseCupCount(String userId);
}
