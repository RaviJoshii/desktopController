var bodyParser=require('body-parser');
var robot = require('robotjs');
module.exports=function(app) {
	
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded( {extended: true}));

	app.post('/mouse/ul',function(req,res){

	});
	app.post('/mouse/uc',function(req,res){
		
	});
	app.post('/mouse/ur',function(req,res){
		
	});
	app.post('/mouse/ml',function(req,res){
		
	});
	app.post('/mouse/mr',function(req,res){
		
	});
	app.post('/mouse/ll',function(req,res){
		
	});
	app.post('/mouse/lc',function(req,res){
		
	});
	app.post('/mouse/lr',function(req,res){
		
	});
	app.post('/mouse/leftclick',function(req,res){
		
	});
	app.post('/mouse/rightclick',function(req,res){
		
	});

}