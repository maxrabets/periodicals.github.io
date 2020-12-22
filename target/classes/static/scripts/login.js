window.addEventListener( "load", function () {
  function sendData() {
  		const message = document.getElementById( "message" );
  		message.innerHTML="";
		const form = document.getElementById( "login form" );
		const FD = new FormData( form );
		let url = '/auth';
		let edit = document.getElementById("email");
    	if(checkEmpty(edit))
    		return;
		fetch(url, {		
			method: "POST",
			body: FD
		})
		.then((response) => {
			if(response.ok){				
//				message.style.color = "darkgreen";
//				message.style.backgroundColor="lightgreen";
//				message.innerHTML="Account successfully created";
				document.location.href = "../index.html";
			}
			else{
				message.style.color = "darkred";
				message.style.backgroundColor = "lightpink";
				
				if(response.status == 400)
					message.innerHTML="Wrong e-mail or password.";
				else
					message.innerHTML="Something went wrong:(";
			}
		});		
  }
  
    function checkEmpty(edit){  	
	  	edit.setCustomValidity("");
	  	let value = edit.value;
	  	if(value.trim().length == 0){
			edit.setCustomValidity("Invalid field.");
			edit.reportValidity();
			return true;
		}
		return false;
	}
  
  const button = document.getElementById( "login button" );
  button.addEventListener( "click", function ( event ) {
    event.preventDefault();
    sendData();
  } );
} );

