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
        fileEl.multiple = true;
        fileManager.fileEl = fileEl;

    } else { // 있는 경우는 파일 값 초기화
        fileEl.value = "";
    }

    for (const el of uploadFiles) {
        el.addEventListener("click", function() {
            fileEl.gid = el.dataset.gid;
            fileEl.location = el.dataset.location;
            fileEl.click();
        });
    }
    /** 파일 업로드 버튼 처리 E */
    /** 파일 선택 처리 S */
    fileManager.fileEl.addEventListener("change", function(e) {
        const files = this.files;
        const gid = this.gid;
        const location = this.location;

        fileManager.upload(files, gid, location);
    });
    /** 파일 선택 처리 E */
});