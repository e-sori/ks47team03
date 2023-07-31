package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AdminPointMapper {
	
	/* 5-2 포인트 환급 기준 조회 */
	public List<Map<String,Object>> getPointRefundStandard();	
	
	/* 4-2 포인트 적립 기준 조회 */
	public List<Map<String,Object>> getPointSaveStandard();
	
	/* 3-2 포인트 타입 기준 조회 */
	public List<Map<String,Object>> getPointTypeStandard();
	
	/* 2-2 포인트 만료 기간 기준 조회 */
	public List<Map<String,Object>> getPointExpireStandard();
	
	/* 1-2 하루 최대 적립 포인트 횟수 기준 조회 */
	public List<Map<String,Object>> getPointMaxCountStandard(String type);
	
	/* 데이터 개수 조회 */
	public int getPointStandardCount(String tableName);
	
	/* 컬럼 내 중복 제거된 데이터 조회*/
	public List<String> getDistinctData(String tableName, String columnName);
	
}
