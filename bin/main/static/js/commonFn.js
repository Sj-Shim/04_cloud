function changeUrlSearchPage(page) {
	const urlParams = new URLSearchParams(window.location.search);
	urlParams.set('page', page);
	window.location.search = urlParams.toString();
}

function removeEmptyInputs(form){
	const inputs = form.querySelectorAll('input');
	const selects = form.querySelectorAll('select');
	let rt = true;
	inputs.forEach(input => {
		input.value = input.value.trim();
		if(input.value == null || input.value == ''){
			input.name = '';
		}
		else{
			if(input.type != 'date' && !isValidInput(input)){
				rt = false;
			}
		}
	});
	selects.forEach(select => {
		if(select.value == null || select.value == ''){
			select.name = '';
		}
	});
	
	return rt;
}
function isValidInput(input) {
    // 허용하지 않을 특수 문자 목록
    const forbiddenChars = /['"\\;%\-]/;

    // 입력값에 금지된 문자가 포함되어 있는지 확인
    if (forbiddenChars.test(input.value)) {
    	
        alert("입력에 허용되지 않은 문자가 포함되어 있습니다.");
        input.focus();
        return false;
    }

    // 유효한 입력일 경우 true 반환
    return true;
}

function searchValidationCheck(frm){
	
	const stDt = frm.querySelector('input[name="s"]') ? frm.querySelector('input[name="s"]').value : null;
	const edDt = frm.querySelector('input[name="e"]') ? frm.querySelector('input[name="e"]').value : null;
	let rt = true;
	if(stDt != null && stDt != '' && edDt != null && edDt != '' && stDt > edDt){
		alert("시작 날짜는 종료 날짜보다 이전이거나 같아야 합니다.")
		rt = false;
	}
	if(!removeEmptyInputs(frm)) {
		rt = false;
	}
	return rt;
}
function logout() {
	let frm = document.createElement("form");
	frm.action = "/logout";
	frm.method = "post";
	
	let token = document.createElement("input");
	token.type = "hidden";
	token.name = csrfParameterName;
	token.value = csrfToken;
	
	frm.appendChild(token);
	document.body.appendChild(frm);
	frm.submit();
}

function fn_search_submit() {
	let frm = document.querySelector("form#frm");
	if(!searchValidationCheck(frm)){
		return false;
	}
	frm.action = window.location.pathname; 
	frm.method = 'get';
	frm.submit();
}
