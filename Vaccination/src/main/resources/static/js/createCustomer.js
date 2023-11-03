const fullName = document.querySelector("#fullName");
const dob = document.querySelector("#dob");
const gender = document.querySelector("#gender");
const identity = document.querySelector("#identity");
const username = document.querySelector("#username");
const password = document.querySelector("#password");
const confirmPassword = document.querySelector("#confirmPassword");
const email = document.querySelector("#email");
const phone = document.querySelector("#phone");
const capchaInput = document.querySelector("#capchaInput");
const capchaVal = document.querySelector("#capchaVal");
const reloadCapcha = document.querySelector(".reload-capcha");
const form = document.querySelector(".customerForm");

/** reload the code in capcha */
const char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
// get random 6 char
function generateCode() {
  let code = "";
  for (let i = 0; i < 6; i++) {
    code += char.charAt(Math.floor(Math.random() * char.length));
  }
  return code;
}

document.addEventListener("DOMContentLoaded", () => {
  capchaVal.value = generateCode();
});

reloadCapcha.addEventListener("click", () => {
  capchaVal.value = generateCode();
});

// validate the form
form.addEventListener("submit", async (e) => {
  // check if any fields empty
  if (
    fullName.value === "" ||
    dob.value === "" ||
    gender.value === "" ||
    identity.value === "" ||
    username.value === "" ||
    password.value === "" ||
    confirmPassword.value === "" ||
    email.value === "" ||
    phone.value === "" ||
    capchaInput.value === ""
	) {
		e.preventDefault();
		alert("Please fill all fields");
		return;
  }

  // check if password or confirm password is empty
  if (password.value === "" || confirmPassword.value === "") {
    e.preventDefault();
    alert("Password or confirm password is empty");
  }

  // check password is the same with confirm password
  if (password.value !== confirmPassword.value) {
    e.preventDefault();
    alert("Password and confirm password must be the same");
  }

  // check the email is valid
  const emailRegex = new RegExp(/^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/);
  if (!emailRegex.test(email.value)) {
    e.preventDefault();
    alert("Email is not valid");
  }

  // check the capcha is valid
  if (capchaInput.value !== capchaVal.value) {
    e.preventDefault();
    alert("Capcha is not valid");
    capchaVal.value = generateCode();
  }
});
