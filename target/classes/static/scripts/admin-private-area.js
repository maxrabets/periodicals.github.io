window.addEventListener( "load", function () {
	let url = '/admin/get-users';	
	fetch(url, {credentials: 'include'}).then((response) => {
		return response.text();
	})
	.then((text) => {
		let userArray = JSON.parse(text);
		let mainPart = document.querySelector("main");
		mainPart.insertAdjacentHTML("beforeend", "<table id=users><caption>All users</caption></table>");
		let table = document.getElementById("users");
		table.insertAdjacentHTML("beforeend", 
			"<tr><th>ID</th><th>Email</th><th>Name</th><th>Surname</th><th>Address</th><th>Role</th></tr>");
		userArray.forEach(function(user){
			table.insertAdjacentHTML("beforeend", 
			"<tr><td>"+user.id+"</td><td>"+user.email+"</td><td>"+user.name+"</td><td>"+user.surname+"</td><td>"+user.address+"</td><td>"+user.role.name+"</tr></tr>");
		});
	});
	let logoutButton = document.getElementById("logout");
	logoutButton.addEventListener("click", function(){
//		var cookie_date = new Date();
//		cookie_date.setTime(cookie_date.getTime()-1);
//		alert(document.cookie);
//		//document.cookie = "token=;Max-Age=-9999;";
//		//setCookie("token", "", {expires:-1});
//		document.cookie = "token=;path=/;expires=-1;";
//		alert(document.cookie);
		var cookies = document.cookie.split(";");
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var eqPos = cookie.indexOf("=");
			var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;";
			document.cookie = name + '=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		}
		document.location.href = "../index.html";
	});
});