var express=require('express');
var robot = require('robotjs');
var app=express();
const mouse=require('./router/mouse');
const keyboard=require('./router/keyboard');

//mouse(app);
//keyboard(app);
robot.scrollMouse(0, 100);
/*setTimeout(function()
{
    robot.scrollMouse(0, 100);
}, 2000);
*/

app.listen(9000);
console.log("app is lisntening on port 9000");
