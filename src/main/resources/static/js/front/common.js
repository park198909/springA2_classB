window.addEventListener("DOMContentLoaded", function() {
    const terms_use = document.querySelectorAll('.footer_p p')
    console.log(terms_use);
    for (const el of terms_use) {
        el.addEventListener('click', ()=> {
            Swal.fire({
                title : '이용안내',
                html: '개인정보수집 범위 : 이름, 연락처.<br/> 개인정보수집 및 이용 목적: 온라인 문의 및 상담 자료와 결과 회신<br/>개인정보수집 및 보유기간 : 개인정보 수집 및 이용에 대한 목적이 달성되면 지체없이 파기하며 최대 보유기간은 1년을 넘기지 아니한다.'
            })
        });
    }
});

window.addEventListener("DOMContentLoaded", function() {
    /** 주소 찾기 팝업 처리 */
    const searchAddressEls = document.getElementsByClassName("search_address");
    for (const el of searchAddressEls) {
        el.addEventListener("click", function() {
            const dataset = this.dataset;
            new daum.Postcode({
                    oncomplete: function(data) {
                        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                        // 예제를 참고하여 다양한 활용법을 확인해 보세요.
                        if (typeof searchAddressCallback == 'function') {
                            searchAddressCallback(data);
                            return;
                        }

                        const addressId = dataset.addressId;
                        const zipcodeId = dataset.zipcodeId;
                        if (!addressId || !zipcodeId) {
                        return;
                        }

                        const zipcodeEl = document.getElementById(zipcodeId);
                        const addressEl = document.getElementById(addressId);
                        if (zipcodeEl) zipcodeEl.value = data.zonecode;
                        if (addressEl) addressEl.value = data.roadAddress;
                    }
                }).open();
        });
    }



    /** 전체 선택 토글 처리 S */
    const chkAlls = document.getElementsByClassName("checkall");
    for (const el of chkAlls) {
        el.addEventListener("click", function() {
            const target = this.dataset.targetName;
            if (!target) return;

            const targets = document.getElementsByName(target);
            for (const ta of targets) {
                ta.checked = this.checked;
            }
        });
    }
    /** 전체 선택 토글 처리 E */

});
