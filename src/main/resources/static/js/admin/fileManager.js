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
    upload(files, gid, location, imageOnly) {

        try {
            if (!files || files.length == 0) {
                throw new Error("업로드 할 파일을 선택하세요");
            }

            const formData = new FormData(); // content-type -> multipart

            // 이미지 파일만 업로드 체크 S
            if (imageOnly) {
                formData.append("imageOnly", true);
                for (const file of files) {
                    if (file.type.indexOf("image") == -1) { // 이미지가 아닌 다른 형식의 파일
                        throw new Error("이미지 형식의 파일만 업로드 하세요.");
                    }
                }
            }
            // 이미지 파일만 업로드 체크 E

            /** 파일 양식에 첨부 S */
            for (const file of files) {
                formData.append("files", file);
            }
            /** 파일 양식에 첨부 E */

            if (gid && gid.trim()) {
                formData.append("gid", gid);
            }

            if (location && location.trim()) {
                formData.append("location", location);
            }

            /** 파일 업로드 처리 */
            const url = "/file/upload";
            commonLib
                    .setResponseType("json")
                    .ajaxLoad(url, "POST", formData)
                    .then((res) => {
                        console.log(res);
                    })
                    .catch((err) => console.error(err));

        } catch {
            console.error(err);
            alert(err.message);
        }
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

    if (fileEl == null) { // 없는 경우는 파일 DOM 추가
        fileEl = document.createElement("input");
        fileEl.type="file";
        fileEl.multiple = true;
        fileManager.fileEl = fileEl;

    } else { // 있는 경우는 파일 초기화
        fileEl.value = "";
    }

    for (const el of uploadFiles) {
        el.addEventListener('click', function() {
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

        fileManager.upload(files, gid, location, true);
        this.value = "";
     });
     /** 파일 선택 처리 E */
});