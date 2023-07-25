package ks47team03.admin.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
	 private final PaymentRepository paymentRepository;
	 private final MemberRepository memberRepository;
	 
	 @Value("${payments.toss.test_client_api_key}")
	 private String testClientApiKey;

	 @Value("${payments.toss.test_secret_api_key}")
	 private String testSecretApiKey;

	 @Value("${payments.toss.test_client_api_key}")
	 private String liveClientApiKey;

	 @Value("${payments.toss.test_secret_api_key}")
	 private String liveSecretApiKey;

	 @Value("${payments.toss.success_url}")
	 private String successCallBackUrl;

	 @Value("${payments.toss.fail_url}")
	 private String failCallBackUrl;

	 @Transactional
	 public PaymentRes requestPayments(PaymentReq paymentReq)
	  Long amount = paymentReq.getAmount();
	  String payType= paymentReq.getPayType().name();
	  String customerEmail = paymentReq.getCustomerEmail();
	  String orderName = paymentReq.getOrderName().name();
	  
	  if(amount ==null || amount != 3000) {
		  throw new BussinessException(ExMessag.PAYMENT_ERROR_ORDER_PRICE);
	
		  
	  }
	  if(!payType.equals("CARD") && !payType.equals("카드")) {
		  throw new BussinessException(ExMessag.PAYMENT_ERROR_ORDER_PAY_TYPE);
	  }
	  
}
