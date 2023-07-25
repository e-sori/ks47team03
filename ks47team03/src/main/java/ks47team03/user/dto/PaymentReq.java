package ks47team03.user.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReq {
	 @ApiModelProperty("지불방법")
	 private PayType payType;
	 @ApiModelProperty("지불금액") 
	 private Long amount;
	 @ApiModelProperty("주문 상품 이름")
	 private OrderNameType orderName;
	 @ApiModelProperty("구매자 이메일")
	 private String customerEmail;
	 @ApiModelProperty("구매자 이름")
	 private String customerName;
	
	 public Payment toEntity() {
		 return Payment.builder()
				 .orderId(UUID.randomUUID().toString())
				 .payType(payType)
				 .amount(amount)
				 .orderName(orderName)
				 .customerEmail(customerEmail)
				 .customerName(customerName)
				 .paySuccessYn("Y")
				 .createDate(new DateConfig().getNowdate())
				 .build();
	 }
}
		 
		 
		 
		 
		 
		 return Payment.builder()
	 * .orderId(UUID.randomUUID().toString()) .payType(payType) .amount(amount)
	 * .orederName(orderName) .customerEmail(customerEmail)
	 * .customerEmail(customerName) .paySuccessYn("Y") .createDate(new
	 * DateConfig().getNowDate()) .build(); }
	 * 
	 */

}
	
}
