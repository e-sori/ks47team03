package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import ks47team03.admin.mapper.AdminCupMapper;
import ks47team03.admin.mapper.AdminFileMapper;
import ks47team03.admin.utill.FileUtil;
import ks47team03.admin.utill.SpreadsheetFilePasing;
import ks47team03.user.dto.Cup;
import ks47team03.user.dto.FileDto;
import ks47team03.user.dto.Static;


@Service
@Transactional
public class AdminCupService {
	// 로그 찍을 준비
	private static final Logger log = LoggerFactory.getLogger(AdminCupService.class);
	//의존성 주입
	private final FileUtil fileUtil;
	private AdminCupMapper adminCupMapper;
	private AdminFileMapper adminFileMapper;
	private SpreadsheetFilePasing spreadsheetFilePasing;
	
	// 의존성 주입 끝 (생성자 메소드 방식)
	public AdminCupService(AdminCupMapper adminCupMapper,SpreadsheetFilePasing spreadsheetFilePasing,AdminFileMapper adminFileMapper,FileUtil fileUtil) {
		this.adminCupMapper = adminCupMapper;
		this.adminFileMapper = adminFileMapper;
		this.spreadsheetFilePasing = spreadsheetFilePasing;
		this.fileUtil=fileUtil;
	}
	//파일 삭제
	public void deleteFileByIdx(FileDto fileDto) {
		Boolean isDelete = fileUtil.deleteFileByIdx(fileDto);
		if(isDelete) adminFileMapper.deleteFileByIdx(fileDto.getFileIdx());
	}
	
	//파일 다운로드
	public FileDto getFileInfoByIdx(String fileIdx) {
		FileDto downloadFile = adminFileMapper.getFileInfoByIdx(fileIdx);
		 return downloadFile;
	}
	
	
	//폐기컵 업로드된 파일 리스트 
	public List<FileDto> getFileList(){
		List<FileDto> fileList = adminFileMapper.getFileList();
		
		return fileList;
	}
	
	//폐기컵 관련 파일 업로드
	public void fileUpload(MultipartFile[] uploadfile) {
		
		List<FileDto> fileList= fileUtil.parseFileInfo(uploadfile);
				
		if(fileList != null) adminFileMapper.addFile(fileList);
		
	}
	//엑셀파일 업로드
	public boolean addDiscardCupByExcelFile(MultipartFile file) {
		boolean isRead = false;
		List<Cup> discardCupQRList = adminCupMapper.getAllDiscardCupQRList();
		if(file != null) {
			String contentType = file.getContentType();
			if(contentType != null && (contentType.indexOf("spreadsheet") > -1 || contentType.indexOf("xlsx") > -1)) {
				List<Cup> discardCupList = spreadsheetFilePasing.pasingToDiscardCupList(file);
				//업로드하는 파일에서 담아준 리스트가 비어 있지 않다면 
				if(discardCupList != null) {
					if(discardCupQRList == null) {
						adminCupMapper.addDiscardCupByExcelFile(discardCupList);
						isRead = true;
						return isRead;
					}
					for(Cup cup : discardCupList) {
						String insertQR =cup.getCupQR();
							for(Cup discardCup : discardCupQRList) {
								String discardCupQR =discardCup.getCupQR();	
									if(insertQR.equals(discardCupQR)) {
										isRead = false;
										return isRead;
									}
									
									
							}
							adminCupMapper.addDiscardCupByExcelFile(discardCupList);
							isRead = true;	
					}

				}
				
				
			}
		}
		
		return isRead;
	}
	
	
	//폐기컵 조회
	public Map<String,Object> getDiscardCupList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCupMapper.getDiscardCupListCount();
		//int보다 더 넓은 자료형을 담아 줄 수 있는게 double 타입 int넣으면 소숫점 절삭
		// ex) 102/5 =20.4 int로 담을경우 소숫점 절삭되서 20으로 됨
		//2. 마지막 페이지
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
		// 처음 번호
        int startPageNum = 1;

        // 마지막 번호
        int endPageNum = (lastPage < 10)? lastPage : 10;

        if(currentPage >= 7 && lastPage > 10) {
        	startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            if(endPageNum >= lastPage) {
            	startPageNum = lastPage - 9;
            	endPageNum = lastPage;
            }
        }
        
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("startIndex", startIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> discardCupList = adminCupMapper.getDiscardCupList(paramMap);
		log.info("폐기컵 리스트:{}",discardCupList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("discardCupList", discardCupList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
		
	}
	//체크된 컵 삭제
	public void removeCupState(List<String> cupQRArr) {
		adminCupMapper.removeCupState(cupQRArr);
	}
	//한개 컵 상태 상태 수정 
	public void modifyCupState(Cup cup) {
		adminCupMapper.modifyCupState(cup);
	}
	//한개 컵 현재 상태 조회
	public Cup getCupInfoByQR(String cupQR) {
		Cup cupInfo= adminCupMapper.getCupInfoByQR(cupQR);
		return cupInfo;
	}
	//컵 상태 코드 리스트 조회
	public List<Static> getCupStaticList(){
		List<Static> cupStaticList = adminCupMapper.getCupStaticList();
		return cupStaticList;
	};
	//컵 상태 조회
	public Map<String,Object> getCupStateList(int currentPage,String searchKey, String searchValue) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(searchValue != null) {
			switch(searchKey) {
				case "cupQR"->{
					searchKey="cm.cup_QR_code";
				}
				case "staticCode"->{
					searchKey="sc.static_code_content";				
								}
				case "adminId"->{
					searchKey="cm.admin_id";				
								}
				case "upDateTime"->{
					searchKey="cm.up_datetime";				
								}				
			}
			
			paramMap.put("searchKey", searchKey);
			paramMap.put("searchValue", searchValue);
		
		}		
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCupMapper.getCupStateListCount();
		//int보다 더 넓은 자료형을 담아 줄 수 있는게 double 타입 int넣으면 소숫점 절삭
		// ex) 102/5 =20.4 int로 담을경우 소숫점 절삭되서 20으로 됨
		//2. 마지막 페이지
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
		// 처음 번호
        int startPageNum = 1;

        // 마지막 번호
        int endPageNum = (lastPage < 10)? lastPage : 10;

        if(currentPage >= 7 && lastPage > 10) {
        	startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            if(endPageNum >= lastPage) {
            	startPageNum = lastPage - 9;
            	endPageNum = lastPage;
            }
        }
		paramMap.put("startIndex", startIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> cupStateList = adminCupMapper.getCupStateList(paramMap);
		log.info("컵 상태 리스트:{}",cupStateList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("cupStateList", cupStateList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
		
	}
	//컵 전체 이용 내역 조회
	public Map<String,Object> getCupManageList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCupMapper.getCupManageListCount();
		//int보다 더 넓은 자료형을 담아 줄 수 있는게 double 타입 int넣으면 소숫점 절삭
		// ex) 102/5 =20.4 int로 담을경우 소숫점 절삭되서 20으로 됨
		//2. 마지막 페이지
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
		// 처음 번호
        int startPageNum = 1;

        // 마지막 번호
        int endPageNum = (lastPage < 10)? lastPage : 10;

        /*
         * if(currentPage >= 7 && lastPage > 10) {
            if(currentPage < lastPage - 4) {
                startPageNum = currentPage - 5;
                endPageNum = currentPage + 4;
            }else {
                startPageNum = lastPage - 9;
                endPageNum = lastPage;
            }
        }*/
        
        if(currentPage >= 7 && lastPage > 10) {
        	startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            if(endPageNum >= lastPage) {
            	startPageNum = lastPage - 9;
            	endPageNum = lastPage;
            }
        }
        
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("startIndex", startIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> cupManageList = adminCupMapper.getCupManageList(paramMap);
		log.info("컵 상태 리스트:{}",cupManageList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("cupManageList", cupManageList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
	}

	//컵 재고 조회
	public Map<String,Object> getCupStockList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCupMapper.getCupStockListCount();
		//int보다 더 넓은 자료형을 담아 줄 수 있는게 double 타입 int넣으면 소숫점 절삭
		// ex) 102/5 =20.4 int로 담을경우 소숫점 절삭되서 20으로 됨
		//2. 마지막 페이지
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
		// 처음 번호
		int startPageNum = 1;
		
		// 마지막 번호
		int endPageNum = (lastPage < 10)? lastPage : 10;
		
		if(currentPage >= 7 && lastPage > 10) {
			startPageNum = currentPage - 5;
			endPageNum = currentPage + 4;
			if(endPageNum >= lastPage) {
				startPageNum = lastPage - 9;
				endPageNum = lastPage;
			}
		}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("startIndex", startIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> cupStockList = adminCupMapper.getCupStockList(paramMap);
		log.info("컵 상태 리스트:{}",cupStockList);
		
		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("cupStockList", cupStockList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
		
	}

	
}
