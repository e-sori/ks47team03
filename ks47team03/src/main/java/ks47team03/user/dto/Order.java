/*
 * package ks47team03.user.dto;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.Table; import
 * lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter; import
 * lombok.ToString; import lombok.experimental.SuperBuilder;
 * 
 * @Entity
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @SuperBuilder
 * 
 * @ToString(callSuper = true)
 * 
 * @Table(name = "product_order") public class Order extends BaseEntity {
 * 
 * @ManyToOne(fetch = LAZY) private Member buyer;
 * 
 * @Builder.Default
 * 
 * @OneToMany(mappedBy = "order", cascade = ALL, orphanRemoval = true) private
 * List<OrderItem> orderItems = new ArrayList<>();
 * 
 * public void addOrderItem(OrderItem orderItem) { orderItem.setOrder(this);
 * 
 * orderItems.add(orderItem); }
 * 
 * public int calculatePayPrice() { int payPrice = 0;
 * 
 * for (OrderItem orderItem : orderItems) { payPrice +=
 * orderItem.getSalePrice(); }
 * 
 * return payPrice; }
 * 
 * public void setPaymentDone() { for (OrderItem orderItem : orderItems) {
 * orderItem.setPaymentDone(); } }
 * 
 * public void setRefundDone() { for (OrderItem orderItem : orderItems) {
 * orderItem.setRefundDone(); } }
 * 
 * public int getPayPrice() { int payPrice = 0; for (OrderItem orderItem :
 * orderItems) { payPrice += orderItem.getPayPrice(); }
 * 
 * return payPrice; }
 * 
 * public String getName() { String name =
 * orderItems.get(0).getProduct().getSubject();
 * 
 * if ( orderItems.size() > 1 ) { name += " 외 %d곡".formatted(orderItems.size() -
 * 1); }
 * 
 * return name; } } }
 */