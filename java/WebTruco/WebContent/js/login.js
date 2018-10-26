function login() {

	var user = $("#user").val();
	var password = $("#password").val();
	
	
	
	var url = '/WebTruco/Login';
	
	$.ajax({
		type : "POST",
		url : url,
		data : form.serialize(), // serializes the form's elements.
		success : function(data) {
			alert(data); // show response from the php script.
		}
	});

}