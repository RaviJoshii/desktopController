var ncmd= require('node-cmd');
var cmd= require('cmd');
var findit=require('findit');
module.exports=function(app){
		app.post('/command',function(req,res){

		var code=req.body.code;
		code=code.toString();
		var data=req.body.data;
		data=data.toString();
		var appdata1={
			"error":code,
			"data":data
		}

		//var str= data.split(" ");
		if(code=="terminal"){
			console.log("terminal ",data);

		}
		else if(code=="search"){
			console.log("search ",data);

		}
		else if(code=="open"){
			console.log("open",data);


		}


		res.json(appdata1);
	});

}
