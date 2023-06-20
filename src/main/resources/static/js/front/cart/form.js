const cart = {
    // 전체 선택
    checkAll() {
        const ids = document.querySelectorAll("#frmCart input[name='id']");
        for (const id of ids) {
            id.checked = true;
        }
    },
    checkSelected() {
        const ids = document.querySelectorAll("#frmCart input[name='id']:checked");
        if (ids.length == 0) { // 체크된 상품이 없는 경우
            throw new Error("상품을 선택하세요.");
        }
    }
};

window.addEventListener("DOMContentLoaded", function() {
    /** 처리 버튼 클릭 S */
    const proccessBtns = document.getElementsByClassName("processBtn");
    for (const el of proccessBtns) {
        el.addEventListener("click", function() {
            let mode = this.dataset.mode;
            if (mode == 'all_delete') {
                mode = 'delete';
                cart.checkAll();
            }
            try {
                cart.checkSelected(); // 처리전 상품 선택 여부
                Swal.fire({
                  title: '정말 처리하시겠습니까?',
                  showCancelButton: true,
                }).then((result) => {
                  if (result.isConfirmed) {
                    frmCart.mode.value = mode;
                    frmCart.submit();
                  }
                })
            } catch (err) {
                Swal.fire(err.message);
                console.error(err);
            }
        });
    }
    /** 처리 버튼 클릭 E */

    /** 장바구니 수량 변경 버튼 처리 S */
    const changeCnts = document.getElementsByClassName("changeCnt");
    for (const el of changeCnts) {
        el.addEventListener("click", function() {
            const target = this.parentElement.querySelector("input[type='number']");
            let cnt = target.value;
            console.log(cnt);
            if (this.classList.contains("down")) { // 1감소
                cnt--;
            } else { // 증가
                cnt++;
            }
            if (cnt < 1) cnt = 1;
            target.value = cnt;
        });
    }
    /** 장바구니 수량 변경 버튼 처리 E */
});