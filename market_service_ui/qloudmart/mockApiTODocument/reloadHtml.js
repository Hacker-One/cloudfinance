var fs = require("fs");
var aglio = require('./aglio');
module.exports = function(mdFileUrl) {
  if (mdFileUrl) {
    fs.readdir(mdFileUrl, function(err,files){
      if(err){
        console.log("error:\n"+err);
        return;
      }
      files.forEach(function(file){
        if(file.indexOf('.md')>-1){
            aglio(mdFileUrl+'/'+file);
        }
      });
      console.log("接口文档初始化完成")
    });
  }

}
