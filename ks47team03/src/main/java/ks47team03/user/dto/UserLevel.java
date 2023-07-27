package ks47team03.user.dto;


public class UserLevel {
	private int levelNum;
	private String levelName;
	private String levelRegDate;
	
	public int getLevelNum() {
		return levelNum;
	}
	public void setLevelNum(int levelNum) {
		this.levelNum = levelNum;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getLevelRegDate() {
		return levelRegDate;
	}
	public void setLevelRegDate(String levelRegDate) {
		this.levelRegDate = levelRegDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserLevel [levelNum=");
		builder.append(levelNum);
		builder.append(", levelName=");
		builder.append(levelName);
		builder.append(", levelRegDate=");
		builder.append(levelRegDate);
		builder.append("]");
		return builder.toString();
	}
}	
