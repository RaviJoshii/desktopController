var express=require('express');
var app=express();
const mouse=require('./router/mouse');
const keyboard=require('./router/keyboard');

mouse(app);
keyboard(app);
app.listen(9000);
console.log("app is lisntening on port 9000");