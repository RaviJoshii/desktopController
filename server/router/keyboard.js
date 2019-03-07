var bodyParser=require('body-parser');
var robot = require('robotjs');
var cmd=require('cmd');
var findit=require('findit');

module.exports=function(app) {
	
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded( {extended: true}));

	app.post('/keyboard/search',function(req,res){

	});
	app.post('/keyboard/type',function(req,res){
		
	});


}