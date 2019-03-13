var bodyParser=require('body-parser');
var robot = require('robotjs');

module.exports=function(app) {
	
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded( {extended: true}));



	app.post('/speak',function(req,res){



		var code=req.body.code;
		code=code.toString();
		var data=req.body.data;
		data=data.toString();
		var appdata1={
			"error":code,
			"data":data
		}

		var str= data.split(" ");
		var stringLength= str.length;
		var firstword=str[0];
		var secondword=str[1];
		if(firstword=="press")
		{		
				if(secondword=="enter"){

					robot.keyTap("audio_mute");

				}
				else if(secondword=="page"){
						if(str[3]=="up"){
								robot.keyTap("pageup");
						}
						if(str[3]=="down"){
								robot.keyTap("pagedown");
						}
				}
				else if(secondword=="F1"){
					robot.keyTap("f1");

				}
				else if(secondword=="F2"){
					robot.keyTap("f2");
				}
				else if(secondword=="F3"){
					robot.keyTap("f3");
				}
				else if(secondword=="F4"){
					robot.keyTap("f4");
				}
				else if(secondword=="F5"){
					robot.keyTap("f5");
				}
				else if(secondword=="F6"){
					robot.keyTap("f6");
				}
				else if(secondword=="F7"){
					robot.keyTap("f7");
				}
				else if(secondword=="F8"){
					robot.keyTap("f8");

				}
				else if(secondword=="F9"){
					robot.keyTap("f9");

				}
				else if(secondword=="F10"){
					robot.keyTap("f10");

				}
				else if(secondword=="F11"){
					robot.keyTap("f11");

				}
				else if(secondword=="F12"){
					robot.keyTap("f12");

				}
				else if(secondword=="capslock"){
					robot.keyTap("command");
				}
				
				
				else if(secondword=="backspace"){
					robot.keyTap("backspace");

				}
				else if(secondword=="tab"){
					robot.keyTap("tab");

				}
				else if(secondword=="escape"){
					robot.keyTap("escape");

				}
				else if(secondword=="home"){
					robot.keyTap("home");

				}
				else if(secondword=="end"){
					robot.keyTap("end");

				}
				else if(secondword=="alt"){
					robot.keyTap("alt");

				}
				else if(secondword=="shift"){
					robot.keyTap("shift");

				}
				else if(secondword=="mute"){
					robot.keyTap("mute");

				}
				

		}
		else if(firstword=="type"){
			for(var i=1;i<stringLength;i++){
				robot.typeString(str[i]);
			}

		}
		else if(firstword=="increase"){
			if(secondword=="volume"){

				robot.keyTap("audio_vol_up");
			}
			else if(secondword=="brightness"){
				console.log("increase brightness");

			}

		}

		else if(firstword=="decrease"){
			if(secondword=="volume"){
				robot.keyTap("audio_vol_down");
			}
			else if(secondword=="brightness"){
				console.log("decrease brightness");
			}

		}
		else if(firstword=="do"){
			if(secondword=="shutdown"){

			}
			else if(secondword=="restart"){
				
			}

		}
		res.json(appdata1);
	});
}