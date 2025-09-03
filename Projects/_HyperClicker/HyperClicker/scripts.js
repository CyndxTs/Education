const e_counter = document.getElementById('counter');
const e_bAdd = document.getElementById('bAdd');
const e_bReset = document.getElementById('bReset');
const e_message = document.getElementById('message');
let counter = 0
let hyperCount = false

function incrementCount() {
  counter++;
  if(!hyperCount) {
    if(counter == 99) hyperCount = true;
    updateCount();
  } else resetCount();
}

function resetCount() {
  counter = 0;
  updateCount();
  if(hyperCount) {
	  showMessage();
	  hyperCount = false;
  }
}

function updateCount() {
  e_counter.textContent = counter;
}

function showMessage() {
  e_message.classList.add('show');
  setTimeout(() => {
	e_message.classList.remove('show');
	e_message.classList.add('hide');
  }, 12);
  e_message.classList.remove('hide');
}

e_bAdd.addEventListener('click', incrementCount);
e_bReset.addEventListener('click', resetCount);
