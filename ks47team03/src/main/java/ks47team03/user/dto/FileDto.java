package ks47team03.user.dto;

public class FileDto {
	private String fileIdx;
	private String fileOriginalName;
	private String fileNewName;
	private String filePath;
	private Long fileSize;
	private boolean fileIsLocal;
	
	public String getFileIdx() {
		return fileIdx;
	}
	public void setFileIdx(String fileIdx) {
		this.fileIdx = fileIdx;
	}
	public String getFileOriginalName() {
		return fileOriginalName;
	}
	public void setFileOriginalName(String fileOriginalName) {
		this.fileOriginalName = fileOriginalName;
	}
	public String getFileNewName() {
		return fileNewName;
	}
	public void setFileNewName(String fileNewName) {
		this.fileNewName = fileNewName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public boolean isFileIsLocal() {
		return fileIsLocal;
	}
	public void setFileIsLocal(boolean fileIsLocal) {
		this.fileIsLocal = fileIsLocal;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FileDto [fileIdx=");
		builder.append(fileIdx);
		builder.append(", fileOriginalName=");
		builder.append(fileOriginalName);
		builder.append(", fileNewName=");
		builder.append(fileNewName);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", fileIsLocal=");
		builder.append(fileIsLocal);
		builder.append("]");
		return builder.toString();
	}
}
