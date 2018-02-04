<!DOCTYPE html>
<html>
<head>
<title>NatureWatch</title>

<style>
html, body {
border: 0;
padding: 0;
margin: 0;
height: 100%;
}
body{
 background: tomato;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 16px;
}
form
{
  background: white;
  width: 40%;
  box-shadow: 0px 0px 20px rgba(#000, .7);
  font-family: lato;
  position: relative;
  color: #333;
  border-radius: 10px;
}
header
{
 background: #a70000;
    padding: 30px 20px;
    color: white;
    font-size: 1.2em;
    font-weight: 600;
    border-radius: 10px 10px 0 0;
}
label
{
margin-left: 20px;
   display: inline-block;
   margin-top: 30px;
   margin-bottom: 5px;
   position: relative;
}
input[type="text"]
{
 display: block;
    width: 78%;
    margin-left: 20px;
    padding: 5px 20px;
    font-size: 1em;
    border-radius: 3px;
    outline: none;
    border: 1px solid #ccc;
}
input[type="password"]
{
 display: block;
    width: 78%;
    margin-left: 20px;
    padding: 5px 20px;
    font-size: 1em;
    border-radius: 3px;
    outline: none;
    border: 1px solid #ccc;
}
input[type="submit"]
{
 position: relative;
    margin-top: 30px;
    margin-bottom: 30px;
    left: 50%;
    transform: translate(-50%, 0);
    font-family: inherit;
    color: white;
    background: #a70000;
    outline: none;
    border: none;
    padding: 5px 15px;
    font-size: 1.3em;
    font-weight: 400;
    border-radius: 3px;
    box-shadow: 0px 0px 10px rgba(#333, 0.4);
    cursor: pointer;
    transition: all 0.15s ease-in-out;
}

</style>

</head>
<body>
<form action="loginAction" method="POST">
	<header>NatureWatch Login</header>
	<label>Benutzername:</label> 
	<input type="text" name="username" required>
	<label> Passwort:</label>
	<input type="password" name="password" required> 
 	<input type="submit" value="Login">
</form>

</body>
</html>