/**
* 파일 업로드
*
*/
const fileManager = {
    fileEl : null,
    /**
    * 파일 업로드
    *
    */
    update() {

    },
    /**
    * 파일 삭제
    *
    */
    delete() {

    }
};

window.addEventListener("DOMContentLoaded", function() {
    /** 파일 업로드 버튼 처리 S */
    const uploadFiles = document.getElementsByClassName("uploadFiles");
    let fileEl = fileManager.fileEl;
    if (fileEl == null) { //없는 경우는 파일 DOM 추가
        fileEl = document.createElement("input");
        fileEl.type="file";
        fileManager.fileEl = fileEl;
    } else { // 있는 경우는 파일 값 초기화
        fileEl.value = "";
    }

    for (const el of uploadFiles) {
        el.addEventListener("click", function() {


            fileEl.click();
        });
    }
    /** 파일 업로드 버튼 처리 E */
});