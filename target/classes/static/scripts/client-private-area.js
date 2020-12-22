window.addEventListener( "load", function () {
	//alert("alarm!");
	let url = '/client/client-private-data';
	
	fetch(url, {credentials: 'include'}).then((response) => {
		return response.text();
	})
	.then((text) => {
		let user = JSON.parse(text);
		let nameEdit = document.getElementById( "name" );
		let surnameEdit = document.getElementById( "surname" );
		let emailEdit = document.getElementById( "mail" );
		let addressEdit = document.getElementById( "address" );
		nameEdit.value = user.name;
		surnameEdit.value = user.surname;
		emailEdit.value = user.email;
		addressEdit.value = user.address;
	});
	
	function logout(){
		var cookies = document.cookie.split(";");
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var eqPos = cookie.indexOf("=");
			var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT;";
			document.cookie = name + '=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
		}
		document.location.href = "../index.html";
	}
	let logoutButton = document.getElementById("logout");
	logoutButton.addEventListener("click", logout);
	
	let deleteButton = document.getElementById("delete");
	deleteButton.addEventListener("click", function(){
		let url = '/client/client-delete';
		message.innerHTML="";
		fetch(url, {		
			method: "DELETE",
			credentials: 'include'
		})
		.then((response) => {
			if(response.ok)
				logout();
			else{
				message.style.color = "darkred";
				message.style.backgroundColor = "lightpinks";
				message.innerHTML="Something went wrong. Account wasn't deleted.";
			}	
		});
		
	});
	
	function changeField(fieldName){
		let edit = document.getElementById(fieldName);
		let message = document.getElementById("message");
		message.innerHTML="";
		edit.setCustomValidity("");
		let newValue = edit.value;
		let url = '/client/change-'+fieldName;
		if(newValue.trim().length == 0){
			edit.setCustomValidity("Invalid field.");
//			edit.reportValidity();
			return;
		}
		fetch(url, {		
			method: "PATCH",
			credentials: 'include',
			body: newValue
		})
		.then((response) => {
			if(response.ok){				
				message.style.color = "darkgreen";
				message.style.backgroundColor="lightgreen";
				message.innerHTML="Data successfully updated";
			}
			else{
				message.style.color = "red";
				message.innerHTML="Data wasn't updated";
			}
		});
	}
	
	let nameButton = document.getElementById("name button");
	nameButton.addEventListener("click", function(){
		event.preventDefault();		
		changeField("name");
	});
	
	let surnameButton = document.getElementById("surname button");
	surnameButton.addEventListener("click", function(){
		event.preventDefault();
		changeField("surname");
	});
	
	let addressButton = document.getElementById("address button");
	addressButton.addEventListener("click", function(){
		event.preventDefault();
		changeField("address");
	});
});