package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.admin.dto.AdminPoint;


@Mapper
public interface AdminPointMapper {
    /* 5-5 포인트 타입 기준 삭제*/
    public void deletePointTypeStandard(List<String> codeList);
    
    /* 4-5 포인트 환급 기준 삭제*/
    public void deletePointRefundStandard(List<String> codeList);
    
    /* 3-5 포인트 적립 기준 삭제*/
    public void deletePointSaveStandard(List<String> codeList);
    
    /* 2-5 포인트 만료 기간 기준  삭제*/
    public void deletePointExpireStandard(List<String> codeList);
    
    /* 1-5 하루 최대 적립 포인트 횟수 기준 삭제*/
    public void deletePointMaxStandard(List<String> codeList);
    
    /* 5-4 포인트 타입 기준 수정*/
    public void modifyPointTypeStandard(AdminPoint adminPoint);
    
    /* 4-4 포인트 환급 기준 수정*/
    public void modifyPointRefundStandard(AdminPoint adminPoint);
    
    /* 3-4 포인트 적립 기준 수정*/
    public void modifyPointSaveStandard(AdminPoint adminPoint);
   
    /* 2-4 포인트 만료 기간 기준  수정*/
    public void modifyPointExpireStandard(AdminPoint adminPoint);
    
    /* 1-4 하루 최대 적립 포인트 횟수 기준 수정*/
    public void modifyPointMaxStandard(AdminPoint adminPoint);
    
    /* 5-3 선택된 포인트 타입 기준 조회 */
    public AdminPoint getPointTypeStandardDetail(String code);
    
    /* 4-3 선택된 포인트 환급 기준 조회 */
    public AdminPoint getPointRefundStandardDetail(String code);
    
    /* 3-3 선택된 포인트 적립 기준  조회 */
    public AdminPoint getPointSaveStandardDetail(String code);
    
    /* 2-3 선택된 포인트 만료 기간 기준 조회 */
    public AdminPoint getPointExpireStandardDetail(String code);
    
    /* 1-3 선택된 하루 최대 적립 포인트 횟수 기준 조회 */
    public AdminPoint getPointMaxStandardDetail(String code);
    
    /* 5-2 포인트 타입 기준 등록*/
    public void addPointTypeStandard(AdminPoint adminPoint);
	
	/* 4-2 포인트 환급 기준 등록*/
	public void addPointRefundStandard(AdminPoint adminPoint);
	
	/* 3-2 포인트 적립 기준 등록*/
	public void addPointSaveStandard(AdminPoint adminPoint);
	
	/* 2-2 포인트 만료 기간 기준 등록*/
	public void addPointExpireStandard(AdminPoint adminPoint);
	
	/* 1-2 하루 최대 적립 포인트 횟수 기준 등록*/
	public void addPointMaxCountStandard(AdminPoint adminPoint);

    /* 5-1 포인트 타입 기준 조회 */
    public List<Map<String,Object>> getPointTypeStandard();
	
	/* 4-1 포인트 환급 기준 조회 */
	public List<Map<String,Object>> getPointRefundStandard();	
	
	/* 3-1-1 등급 기준 조회 */
	public List<AdminPoint> getGradeStandard();
	
	/* 3-1 포인트 적립 기준 조회 */
	public List<Map<String,Object>> getPointSaveStandard();
	
	/* 2-1 포인트 만료 기간 기준 조회 */
	public List<Map<String,Object>> getPointExpireStandard();
	
	/* 1-1 하루 최대 적립 포인트 횟수 기준 조회 */
	public List<Map<String,Object>> getPointMaxCountStandard(String type);
	
	/* 1 하루 최대 적립 포인트 자동 증가 코드 */
	public String getPointMaxIncreaseCode();
	
	/* 데이터 개수 조회 */
	public int getPointStandardCount(String tableName);
	
	/* 컬럼 내 중복 제거된 데이터 조회*/
	public List<String> getDistinctData(String tableName, String columnName);
	
}
