window.addEventListener("DOMContentLoaded", function() {
    /** 상세 설명 에디터 로드 */
    CKEDITOR.replace("description", {
        height:350
    });

    /** 이미지 첨부 클릭시 처리 S */
    const insertEditorEls = document.getElementsByClassName("insert_editor");
    for (const el of insertEditorEls) {
        el.addEventListener("click", function() {
            const url = this.dataset.url;
            insertEditor(url);
        });
    }
    /** 이미지 첨부 클릭시 처리 E */

    /** 재고 선택 클릭시 처리 S */
    const stockTypeEls = document.getElementsByName("stockType");
    const stocksEl = document.querySelector(".stocks");
    const stockEl = document.querySelector(".stocks input[name='stock']");
    for (const el of stockTypeEls) {
        el.addEventListener("click", function() {
            stocksEl.classList.remove("dn")
            stocksEl.classList.add("dn");
            if (this.value == 1) { // 고정 재고
                stocksEl.classList.remove("dn");
            } else { // 무제한
                console.log(stocksEl.value);
                stockEl.value = 0;
            }
        });
    }
    /** 재고 선택 클릭시 처리 E */


});

/** 파일 업로드 콜백 처리 */
function fileUploadCallback(files) {

   const editorAttachFiles = document.querySelector(".attachedFiles.editor");
   const mainImageEl = document.querySelector(".main_image");
   const listImageEl = document.querySelector(".list_image");

   const tpl = document.getElementById("file_tpl").innerHTML;
   const imageTpl = document.getElementById("image_tpl").innerHTML;

   const domParser = new DOMParser();
   for (const file of files) {
        const location = file.location;
        switch(location) {
            case "editor" : // 에디터 첨부 이미지
                const img = `<img src='${file.fileUrl}'>`;
                CKEDITOR.instances.description.insertHtml(img);

                let html = tpl;
                html = html.replace(/<%=id%>/g, file.id)
                            .replace(/<%=fileName%>/g, file.fileName)
                            .replace(/<%=url%>/g, file.fileUrl);

                const dom = domParser.parseFromString(html, "text/html");
                const span = dom.querySelector("span");
                editorAttachFiles.appendChild(span);

                const insertEditorEl = span.querySelector(".insert_editor");
                insertEditorEl.addEventListener("click", function() {
                    insertEditor(this.dataset.url);
                });
                break;
            case "main" : // 메인 이미지
            case "list" : // 목록 이미지
                let html2 = imageTpl;
                html2 = html2.replace(/<%=id%>/g, file.id)
                            .replace(/<%=url%>/g, file.fileUrl);
                const dom2 = domParser.parseFromString(html2, "text/html");
                const span2 = dom2.querySelector("span");
                const targetEl = location == 'list' ? listImageEl : mainImageEl;
                targetEl.innerHTML = "";
                targetEl.appendChild(span2);
                break;
        }
   }
}

/**
* 이미지 본문 추가
*
*/
function insertEditor(url) {
    if (!url || !url.trim()) {
        return;
    }

    const img = `<img src='${url}'>`;
    CKEDITOR.instances.description.insertHtml(img);
}

/** 파일 삭제 콜백 처리 */
function fileDeleteCallback(id) {
    const el = document.getElementById(`file_${id}`);
    if (!el) return;

    el.parentElement.removeChild(el);
}