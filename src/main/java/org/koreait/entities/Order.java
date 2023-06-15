package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "_ORDER")
public class Order extends BaseEntity {
 
    @Id @GeneratedValue
    private Long orderNo;   // 주문 번호

    @Column(length = 60, nullable = false)
    private String gid;

    private String orderlist; // 주문품목
    
    private String orderNm; // 주문자명

    @Column(nullable = false, length = 20)
    private String orderId; // 주문자

    @Column(nullable = false, length = 10)
    private String orderStat; // 주문 진행 상태

    @Column(nullable = false, length = 10)
    private long price; // 주문가격



}
