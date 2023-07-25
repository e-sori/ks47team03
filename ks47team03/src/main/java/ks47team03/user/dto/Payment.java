package ks47team03.user.dto;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="seq", nullable =false, unique= true)
	private Long seq;
	
	@Column(nullable =false)
	private PayType payType;
	
	@Column(nullable =false)
	private Long amount;
	
	@Column(nullable =false)
	private String orderId;
	
	@Column(nullable =false)
	private OrderNameType orderName;
	
	@Column(nulladle =false)
	private String customerEmail;
	
	@Column(nullable =false)
	private String customerName;
	
	@Column(nullable = false)
	private String createDate;
	
	@Setter
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.PERSIST)
	private Member customer;
	
	public PaymentRes toDto() {
		return PaymentRes.builder()
				.payType(payType.name())
				.amount(amount)
				.orderId(orderId)
				.orderName(orderName.name())
				.customerEmail(customerEmail)
				.customerName(customerName)
				.createDate(createDate)
				.build();
		
	}
	
	
	
	
}
