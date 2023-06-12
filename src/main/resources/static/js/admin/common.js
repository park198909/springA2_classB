/** 공통 이벤트 처리 */
window.addEventListener("DOMContentLoaded", function() {
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

/** 푸터 이용약관 */
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
    const terms_use2 = document.querySelectorAll('.ad_explain1')
    for (const el of terms_use2) {
        el.addEventListener('click', ()=> {
            Swal.fire({
                icon : 'info',
                title : '사이트 설정 안내',
                html: '<a href="https://www.github.com/jongpyo-hong">링크</a>',


            })
        });
    }
});

window.addEventListener("DOMContentLoaded", function() {
    const terms_use3 = document.querySelectorAll('.ad_explain2')
    for (const el of terms_use3) {
        el.addEventListener('click', ()=> {
            Swal.fire({
                icon : 'info',
                title : '게시판 관리 안내',
                html: '<a href="https://www.github.com/jongpyo-hong">링크</a>'
            })
        });
    }
});
