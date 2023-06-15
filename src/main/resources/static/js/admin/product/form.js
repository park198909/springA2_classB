window.addEventListener("DOMContentLoaded", function() {
    /** 상세 설명 에디터 로드 */
    CKEDITOR.replace("description", {
        height:350
    });

});

/** 파일 업로드 콜백 처리 */
function fileUploadCallback(files) {


   for (const file of files) {
        const location = file.location;
        switch(location) {
            case "editor" : // 에디터 첨부 이미지
                const img = `<img src='${file.fileUrl}'>`;
                CKEDITOR.instances.description.insertHtml(img);
                break;
            case "main" : // 메인 이미지

                break;
            case "list" : // 목록 이미지

                break;
        }
   }
}