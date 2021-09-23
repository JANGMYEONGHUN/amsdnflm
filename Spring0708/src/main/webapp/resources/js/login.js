window.onload= function(){
	document.getElementById("memberId").onkeyup = function(){
		//console.log(e);
		var inputId = this.value;
		if(inputId.length < 3 || inputId.length > 10){
			var str = "";
			str += "<div style='color:red'>";
			str += "    ID는 3~10글자 사이로 입력해주세요.";
			str += "</div>";
			document.getElementById("idCheck").innerHTML = str;
		} else{
			document.getElementById("idCheck").innerHTML = "";
		}
		
		if(inputId.length > 10){
			this.value = inputId.substring(0,10);
		}
	}
	document.getElementById("memberPw").onkeyup = function(){
		//console.log(e);
		var inputPw = this.value;
		if(inputPw.length < 4 || inputPw.length > 12){
			var str = "";
			str += "<div style='color:red'>";
			str += "    비밀번호는 4~12글자 사이로 입력해주세요.";
			str += "</div>";
			document.getElementById("pwCheck").innerHTML = str;
		}else{
			document.getElementById("pwCheck").innerHTML = "";
		}
	}
}