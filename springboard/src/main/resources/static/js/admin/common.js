const commonLib = {
    responseType : "json",
    // 응답 형식
    setResponseType(type) {
        this.responseType = type;
        return this;
    },
    ajaxLoad(url, method, data) {
        method = method || "GET";
        data = data || null;
        const { header, token } = this.getCsrf();

        return new Promise((resolve, reject) => {
            const xhr = new XMLHttpRequest();
            xhr.open(method, url);

            xhr.setRequestHeader(header, token);

            xhr.onreadystatechange = function() {
                if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
                    let res = xhr.responseText;
                    const type = fileManager.responseType;
                    if (type && type.toLowerCase() == 'json') res = JSON.parse(res);

                    resolve(res); // 성공시 응답
                }
            };

            xhr.onerror = function(err) {
                reject(err);
            };

            xhr.onabort = function(err) {
                reject(err);
            };

            xhr.send(data);
        });
    },
    getCsrf() {
        const csrfEl = document.querySelector("meta[name='_csrf']");
        const csrfHeader = document.querySelector("meta[name='_csrf_header']");

        const token = csrfEl ? csrfEl.content : "";
        const header = csrfHeader ? csrfHeader.content : "";

        return { token, header };
    }
};


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