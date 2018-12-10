var express = require('express');
var cookieParser = require('cookie-parser');
var expressSession = require('express-session');
var path = require('path');
var url = require('url');
var bodyParser = require('body-parser');
var routes = require('./routes/routes.js');
var baseConfig=require('./config/baseConfig.json');
var https = require('https');
var log4js = require('log4js');
var log4jsjson = require('./log4js.json');
var router = express.Router();
var gaze=require('gaze');
var request=require('request');
var watch=require('./mockApiTODocument/watch.js');
var reloadHtml=require('./mockApiTODocument/reloadHtml.js');
var os = require('os');
//var fs=require('fs');

//通过configure()配置log4js
log4js.configure(log4jsjson);
var logger = log4js.getLogger('日志信息');
//根据项目的路径导入生成的证书文件
// var privateKey  = fs.readFileSync(path.join(__dirname, '/private.pem'), 'utf8');
// var certificate = fs.readFileSync(path.join(__dirname, '/file.crt'), 'utf8');
// var credentials = {key: privateKey, cert: certificate};
createServer = function createServer() {
    var server = express();
    // specify middleware
    server.use(bodyParser.json());
    server.use(bodyParser.urlencoded({extended: false}));
    server.use(express.static(__dirname + '/web'));
    server.use(log4js.connectLogger(logger, {level: log4js.levels.INFO}));
   //设置禁用缓存
    server.use(function (req, res, next) {
        res.header('Cache-Control', 'private, no-cache, no-store, must-revalidate');
        res.header('Expires', '-1');
        res.header('Pragma', 'no-cache');
        //允许跨域
        res.header("Access-Control-Allow-Origin", "*");
        res.header('Access-Control-Allow-Methods', 'PUT, GET, POST, DELETE, OPTIONS');
        res.header("Access-Control-Allow-Headers", "X-Requested-With");
        res.header('Access-Control-Allow-Headers', 'Content-Type');
        // 利用闭包的特性获取最新的router对象，避免app.use缓存router对象
        router(req, res, next);

    });
    server.use(cookieParser());
    server.use(expressSession({
    name: 'qloudsession',
    resave: false, // don't save session if unmodified
    saveUninitialized: true,// don't create session until something stored
    secret: 'mdfkldfgkl&'
    }));
  // 错误处理中间件
  server.use(function (err, req, res, next) {
    logger.error('请求的url为：'+req.url+'，错误处理中间捕获异常', err.message);
    res.send('内部处理错误错误');
  });
  //mock初始化和监听，在开发模式下打开下面注释，可以实现前后端分离
  // watch('./mockApiTODocument/mfiles/md');
  // reloadHtml('./mockApiTODocument/mfiles/md')
  //监听文件的变化热部署后台接口
  gaze('*.js',{cwd:'./service'}, function(err, watcher) {
    // On file changed/add/delete
    this.on('all', function(event, filepath) {
      try {
        //router重新初始化
        router=express.Router();
        routes.attachHandlers(router); //, passport);
        logger.info("nodejs服务重新加载，文件操作类型："+event+",文件地址" + filepath);
      } catch (ex) {
        logger.error('Error: %s', ex);
      }
    });
  });
  // attach router handlers
    routes.attachHandlers(router); //, passport);
    //server.use(router);
    //var httpsServer = https.createServer(credentials, server);
    // httpsServer.listen("8081", function() {
    //   console.log('HTTPS Server is running on: https://localhost:%s', "8081");
    // });
    return server;
};
var server = createServer();
var port = Number(process.env.PORT || baseConfig.port);
server.listen(port, function() {
    // console.log(__dirname + '/dist/Qloud')
    //console.log("授权地址："+baseConfig.protocol+"://"+baseConfig.uri+":"+baseConfig.port+'/oauth2/token');
    logger.info("启动端口:" + port);
});
//对抛出异常统一处理
process.on('uncaughtException', function (err) {
    logger.error('Error: %s', err.message);
});
