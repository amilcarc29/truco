function login() {

	var user = $("#user").val();
	var password = $("#password").val();
	

	if ((user)&& (password)) {
		
		
		var url = '/WebTruco/Login';
		var userLogin = { user : user, password : password};

		$.ajax({
			type : "POST",
			url : url,
			data : userLogin, // serializes the form's elements.
			success : function(data) {
				
			}
		});
		
	} else {
		$.alertable.alert('Falta usuario o pass').always(function() {
			console.log('Alert dismissed');
		});
	}
	



}