package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminKioskMapper {
		// 설치된 무인기기 목록 조회
		public List<Map<String,Object>> getInstalledKioskListList (Map<String,Object>paramMap);
		
		public int getInstalledKioskListListCount();



	}

