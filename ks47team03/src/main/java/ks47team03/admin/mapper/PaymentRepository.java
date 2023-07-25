package ks47team03.admin.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ks47team03.user.dto.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
