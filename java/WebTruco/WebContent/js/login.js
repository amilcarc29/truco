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
				if (data.ERROR){
					
					
					$.alertable.alert('El usuario no existe o la password es incorrecta').always(function() {
						
					});
					
				}else{
				
					
					window.location.replace("/WebTruco/main.jsp");

				}
			}
		});
		
	} else {
		$.alertable.alert('Ingrese usuario y password').always(function() {
			
		});
	}
	



}