package ks47team03.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRes {
	private String payType;
	private Long amount;
	private String orderId;
	private String orderName;
	private String customerEmail;
	private String customerName;
	private String successUrl;
	private String failUrl;
	private String createDate;
	private String paySuccessYn;
	
	

}
