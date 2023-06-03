window.addEventListener("DOMContentLoaded", function() {
    const terms_use = document.querySelectorAll('.footer_p p')
    console.log(terms_use);
    for (const el of terms_use) {
        el.addEventListener('click', ()=> {
            Swal.fire({
                title : '이용약관',
                text: '이용약관 입니다.',
            })
        });
    }
});
