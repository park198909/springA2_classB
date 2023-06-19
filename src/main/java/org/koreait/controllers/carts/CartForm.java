package org.koreait.controllers.carts;

import lombok.Data;

@Data
public class CartForm {

    private Long id;
    private Long pNo;
    private String mode;
    private long productCnt = 1;
}
