const productForm = {
    /**
    * 수량 변경
    */
    changeCnt(e) {
        let cnt = frmProduct.productCnt.value;
        const target = e.currentTarget;
        if (target.classList.contains("down")) { // 수량 감소(-1)
            cnt--;
        } else { // 수량 증가(+1)
            cnt++;
        }

        if (cnt < 1) cnt = 1;

        frmProduct.productCnt.value = cnt;
    }
};

window.addEventListener("DOMContentLoaded", function() {
    /** 수량 증감 처리 S */
    const buttonCntEls = document.querySelectorAll(".productCnt_wrap button");
    for (const el of buttonCntEls) {
        el.addEventListener("click", productForm.changeCnt);
    }
    /** 수량 증감 처리 E */

    /** 상품 주문, 장바구니 버튼 처리 S */
    const proccessBtnEls = document.getElementsByClassName("processBtn");
    for (const el of proccessBtnEls) {
        el.addEventListener("click", function() {

            const mode  = this.classList.contains("ord") ? "order" : "cart";

            frmProduct.mode.value = mode;

            frmProduct.submit();
        });
    }
    /** 상품 주문, 장바구니 버튼 처리 E */
});