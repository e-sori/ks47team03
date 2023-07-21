package ks47team03.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.FileDto;

@Mapper
public interface AdminFileMapper {
	//파일 삭제
	public int deleteFileByIdx(String fileIdx);
	//파일 다운로드
	public FileDto getFileInfoByIdx(String fileIdx);
	//파일 업로드
	public int addFile(List<FileDto> fileList); 
	//파일 리스트 조회
	public List<FileDto> getFileList();
}
