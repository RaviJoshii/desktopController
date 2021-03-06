var bodyParser=require('body-parser');
var robot = require('robotjs');
var ncmd= require('node-cmd');
var cmd= require('cmd');
module.exports=function(app) {
	
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded( {extended: true}));

	var status="1";
	app.post('/checkconnection',function(req,res){
		console.log("mobile connected");
			appdata={
		 "error":status
		}
		res.json(appdata);
	});
	app.post('/control',function(req,res){

		var code=req.body.code;
		code=code.toString();
		var data=req.body.data;
		data=data.toString();
		appdata={
		 "error":status
		}
		

		var mouse = robot.getMousePos();
		var x=mouse.x;
		var y= mouse.y;
		if(code=="mouse")
		{
			
		if(data=='ul'){//ul
			robot.moveMouse(x-6,y-6);


		}
		else if(data=='uc'){//uc
			robot.moveMouse(x,y-6);
			//console.log("uc");

		}
		else if(data=='ur'){//ur
			robot.moveMouse(x+6,y-6);
			//console.log("ur");
			
		}
		else if(data=='ml'){//ml
			robot.moveMouse(x-6,y);
			//console.log("ml");
			
		}
		else if(data=='mr'){//mr
			robot.moveMouse(x+6,y);
			//console.log("mr");
			
		}
		else if(data=='ll'){//ll
			robot.moveMouse(x-6,y+6);
			//console.log("ll");
			
		}
		else if(data=='lc'){//lc
			robot.moveMouse(x,y+6);
			//console.log("lc");
			
		}
		else if(data=='lr'){//lr
			robot.moveMouse(x+6,y+6);
			//console.log("lr");
			
		}
		else if(data=='leftclick'){//leftclick
			robot.mouseClick("left");
			//console.log("left");
			
		}
		else if(data=='rightclick'){//rightclick
			robot.mouseClick("right");
			//console.log("right");

			
		}
		else if(data=='doubleleft'){//doubleleft
			robot.mouseClick("left",true);
			//console.log("double left");

			
		}
		else if(data=='doubleright'){//doubleright
			//console.log("double right");
			robot.mouseClick("right",true);
		}
		else {
			console.log("nothing");

		}
	}
	else if(code=="keyboard"){
		
		if(data=='upkey'){
			robot.keyTap("up");
			
		}
		else if (data=="downkey"){
			robot.keyTap("down");
		}
		else if(data=='leftkey'){
			robot.keyTap("left");
			
		}
		else if (data=="rightkey"){
			robot.keyTap("right");
		}
		else if (data=="enter"){

			robot.keyTap("enter");
		}
		
		
		else if (data=="backspace"){
			robot.keyTap("backspace");
		}
		
		else{
			console.log("invalid");
		}

	}
	else if(code == "type"){
		robot.typeString(data);

	}
	res.json(appdata);


	});
	
	

}