package ks47team03.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Point {
	private String pointPastNowHistoryCode;
	private String userId;
	private int currentHoldingPoint;
	private String saveUseType;
	private String pointSaveUseType;
	private int passHoldingpoint;
	private String pointSaveUseHistoryCode;
	private int pointSaveUseHistory;
	private String pointSaveUseTypeCode;
}
