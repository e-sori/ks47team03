package ks47team03.user.dto;

public class Static {
	private String staticCode;
	private String staticCodeContent;
	public String getStaticCode() {
		return staticCode;
	}
	public void setStaticCode(String staticCode) {
		this.staticCode = staticCode;
	}
	public String getStaticCodeContent() {
		return staticCodeContent;
	}
	public void setStaticCodeContent(String staticCodeContent) {
		this.staticCodeContent = staticCodeContent;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Static [staticCode=");
		builder.append(staticCode);
		builder.append(", staticCodeContent=");
		builder.append(staticCodeContent);
		builder.append("]");
		return builder.toString();
	}

}
