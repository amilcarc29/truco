function registrar() {
	var user = $("#register-user").val();
	var email = $("#email").val();
	var password = $("#register-password").val();
	if (user && password && email) {
		var url = '/WebTruco/Registrar';
		var nuevoUser = { email : email, user : user, password : password};
		$.ajax({
			type : "POST",
			url : url,
			data : nuevoUser,
			success : function(data) {
				if (data.ERROR){
					var reason = data.REASON == 1 ? "el apodo ya esta en uso" : "el email ya esta en uso";
					$.alertable.alert('Error, ' + reason).always(function() {
					});
				}else{
					$.alertable.alert('Registrado.').always(function() {
					});
				}
			}
		});
	} else {
		$.alertable.alert('Faltan completar campos.').always(function() {
		});
	}
}