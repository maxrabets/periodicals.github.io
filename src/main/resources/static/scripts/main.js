var myImage = document.querySelector('img');

myImage.onclick = function() {
    var mySrc = myImage.getAttribute('src');
    if(mySrc === "images/newspaper1.png") {
      myImage.setAttribute ('src', 'images/man-is-reading-newspaper.jpg');
    } else {
      myImage.setAttribute ('src', "images/newspaper1.png");
    }
}

var button = document.querySelector('button');
var header = document.querySelector('h1');

function setUserName() {
	var name = prompt('Please, enter your name');
	localStorage.setItem('name', name);
	header.textContent = 'We\'re glad to see you, ' + name;
}

if(!localStorage.getItem('name')) {
  setUserName();
} else {
  var storedName = localStorage.getItem('name');
  header.textContent = 'We\'re glad to see you, ' + storedName;
}

button.onclick = function() {
  setUserName();
}