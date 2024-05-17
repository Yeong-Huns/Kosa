function login_check(){ // 아이디와 패스워드 검사 
	/* javascript 방식 */
	if(window.document.f.id.value==""){ // 아이디가 공백인 경우
		alert("아이디를 입력하세요!");  // 경고창
		f.id.focus();  // id 입력창에 커서 이동 시킴
		return false;  // false값 리턴
	}
	/* jQuery 방식 */
	if($.trim($("#pwd").val())==""){  // 비밀번호가 공백인 경우
		alert("비밀번호를 입력하세요!");   // 경고창
		$("#pwd").val("").focus();    // 비밀번호 입력창에 커서 이동
		return false;
	}
	/* jQuery 함수  */
	// $("셀렉터")  : jQuery 함수
	// 셀렉터 : 지시자(적용 대상을 의미한다. 일반적으로 태그이름 또는 속성이름) 
}

