var drakov = require('drakov');
var aglio = require('./aglio');
var path = require('path');
var sourceFiles = path.resolve(__dirname, './mfiles/md/**.md');
var fs = require("fs");
var argv = {
  sourceFiles: sourceFiles,//md文档存储地
  serverPort: 4007,
  disableCORS: false,//false允许跨域访问
  debugMode: true,
  discover: true,
  public: true,//true允许外围ip访问api,false只能本地访问
  watch: true,
  method: ['GET', 'PUT', 'POST', 'DELETE', 'OPTIONS']
};
module.exports = function (mdFileUrl) {
  try {
    if ((mdFileUrl && typeof (mdFileUrl) === "string" && mdFileUrl.indexOf(".md") > -1) || mdFileUrl == '') {
      drakov.stop(function () {
        // stopped Drakov
        console.log('drakov Finished');
      });

      drakov.run(argv, function () {
        console.log('drakov start');
      });
      if (mdFileUrl) {
        aglio(mdFileUrl);
      }
    }

  } catch (e) { } finally { }
}
