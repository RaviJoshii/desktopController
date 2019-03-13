var express=require('express');
var app=express();
const dashboard=require('./router/dashboard');
const speak=require('./router/speak');
const commandline=require('./router/commandline');

dashboard(app);
speak(app);
//commandline(app);

app.listen(9000);
console.log("app is lisntening on port 9000");
