function callUploadModal(){
	$("#fileUploadModal").modal("show");
}
function hideUploadModal(){
	$("#fileUploadModal").modal("hide");
}
function hideFileInfoModal() {
	$("#fileInfoModal").modal("hide");
}
function downloadFile(key) {
	$.ajax({
        type: "POST",
        url: "/api/file/download",
        contentType: "application/json",
        data: JSON.stringify({ "key": key }),
        xhrFields: {
            responseType: 'blob'
        },
        success: function(response, status, xhr) {
        	
        	var disposition = xhr.getResponseHeader('Content-Disposition');
            var matches = /filename="([^;]+)"/.exec(disposition);
            var fileName = (matches != null && matches[1]) ? matches[1] : 'downloadedFile';

            var blob = new Blob([response], { type: xhr.getResponseHeader('Content-Type') });
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = fileName;
            link.click();
        },
        error: function(err){
        	switch(err.status){
        	case 404:
        		alert('파일을 찾을 수 없습니다.');
        		break;
        	case 500:
        		alert('서버에서 문제가 발생하였습니다.');
        		break;
        	}
        	console.log("에러 발생: ", err);
        }
	});
}

$(document).ready(function() {
	$("#fileUploadModal").on('hide.bs.modal', function(e){
		let bars = document.querySelectorAll("#fileUploadModal .statusbar");
		bars.forEach(bar => bar.remove());
		fd = null;
	});
	
	$("td.file").click(function() {
        var key = $(this).data("key");
		
        $.ajax({
            type: "POST",
            url: "/api/file_info",
            contentType: "application/json",
            beforeSend: function(xhr){
            	xhr.setRequestHeader("Accept", "application/json");
            },
            data: JSON.stringify({ "key": key }),
            success: function(response) {                   
                console.log(response);

                $("#fileInfoModal .modal-body").html(
                        "<p>파일 키: " + response.fl_key + "</p>" +
                        "<p>파일 이름: " + response.otxt_flnm + "</p>" +
                        "<p>업로드 시간: " + response.upload_dtm + "</p>" +
                        "<p>등록자: " + response.empe_name + "</p>" +
                        "<div>Preview</div>" + 
                        '<div id="filePreview" class="mt-4"></div>'
                );
                $("#btn-download").off('click').on('click', function() {
                    downloadFile(key);
                });
                $.ajax({
                    type: "POST",
                    url: "/api/file/download",
                    contentType: "application/json",
                    data: JSON.stringify({ "key": key }),
                    xhrFields: {
                        responseType: 'blob'
                    },
                    success: function(response, status, xhr) {
                    	var disposition = xhr.getResponseHeader('Content-Disposition');
                        var matches = /filename="([^;]+)"/.exec(disposition);
                        var fileName = (matches != null && matches[1]) ? matches[1] : 'downloadedFile';
                        var fileType = xhr.getResponseHeader('Content-Type');
                        console.log(fileType);
                        var blob = new Blob([response], { type: fileType });
                        var previewContainer = document.getElementById('filePreview');
                        previewContainer.innerHTML = '';

                        if (fileType.startsWith('image/')) {
                            // 이미지 파일 처리
                            var img = document.createElement('img');
                            img.src = window.URL.createObjectURL(blob);
                            img.style.maxWidth = '100%';
                            previewContainer.appendChild(img);
                        } else if (fileType.startsWith('text/')) {
                            // 텍스트 파일 처리
                            var reader = new FileReader();
                            reader.onload = function(e) {
                                var text = document.createElement('pre');
                                text.textContent = e.target.result;
                                previewContainer.appendChild(text);
                            };
                            reader.readAsText(blob);
                        } else if (fileType.startsWith('video/')) {
                            // 비디오 파일 처리
                            var video = document.createElement('video');
                            video.src = window.URL.createObjectURL(blob);
                            video.controls = true;
                            video.style.maxWidth = '100%';
                            previewContainer.appendChild(video);
                        } else if (fileType.startsWith('audio/')) {
                            // 오디오 파일 처리
                            var audio = document.createElement('audio');
                            audio.src = window.URL.createObjectURL(blob);
                            audio.controls = true;
                            previewContainer.appendChild(audio);
                        }
                    },
                    error: function(error){
                    	document.getElementById('filePreview').innerText = 
                    		"미리보기를 할 수 없는 파일입니다.";
                    }
                });
                $("#fileInfoModal").modal("show");
            },
            error: function(error) {
            	console.log(error);
            }
        });
    });
	
	$('#btn_search').click(function() {
		event.preventDefault();
		fn_search_submit();
	});
});