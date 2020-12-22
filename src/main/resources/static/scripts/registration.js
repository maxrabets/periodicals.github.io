window.addEventListener( "load", function () {
  function sendData() {
  		let message = document.getElementById("message");
  		message.innerHTML="";
  		let edit = document.getElementById("name");
    	if(checkEmpty(edit))
    		return;
    	edit = document.getElementById("surname");
    	if(checkEmpty(edit))
    		return;
    	edit = document.getElementById("email");
    	if(checkEmpty(edit))
    		return;
    	edit = document.getElementById("address");
    	if(checkEmpty(edit))
    		return;
    	edit = document.getElementById("password");
    	if(!validatePasword(edit))
    		return;
    	const form = document.getElementById( "registration form" );
		const FD = new FormData( form );
		let url = '/register';
		fetch(url, {		
			method: "POST",
			body: FD
		})
		.then((response) => {
			if(response.ok){
//				if(response.status == 200){
					message.style.color = "darkgreen";
					message.style.backgroundColor="lightgreen";
					message.innerHTML="Account successfully created";
					document.location.href = "../index.html";
//				}
//				else if(response.status == 202){
//					document.location.href = ".html";
//				}
			}
			else{
				message.style.color = "darkred";
				message.style.backgroundColor = "lightpink";
				
				if(response.status == 409)
					message.innerHTML="Account wasn't created. There is already account registered with this email.";
				else
					message.innerHTML="Account wasn't created";
			}
		});
		
//		let response = await fetch(url, {
//			method: 'POST',
//			headers: {
//				'Content-Type': 'application/x-www-form-urlencoded'
//			},
//			body: FD
//		});
  }
  
  function validatePasword(edit){
  	if(edit.value.length < 8){
  		edit.setCustomValidity("Password must contain at list 8 characters.");
  		return false;
  	}
  	edit.setCustomValidity("");
  	return true;
  }
  
  function checkEmpty(edit){
  	let value = edit.value;
  	if(value.trim().length == 0){
  		
		edit.setCustomValidity("Invalid field.");
		return true;
	}
	edit.setCustomValidity("");
	return false;
  }
  
  const button = document.getElementById( "registration button" );
  button.addEventListener( "click", function ( event ) {
    event.preventDefault();
    sendData();
  } );
} );
