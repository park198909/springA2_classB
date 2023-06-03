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
