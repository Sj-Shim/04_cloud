<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- file info modal -->
<div class="modal fade" id="fileInfoModal" tabindex="-1" role="dialog" aria-labelledby="fileInfoModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="margin-top:15%">
            <div class="modal-header">
                <h5 class="modal-title" id="fileInfoModalLabel">파일 정보</h5>
            </div>
            <div class="modal-body">
                <!-- Modal body content here -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="hideFileInfoModal()">닫기</button>
                <button type="button" class="btn btn-primary" id="btn-download">다운로드</button>
            </div>
        </div>
    </div>
</div>