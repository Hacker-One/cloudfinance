//对reques统一处理
var request=require('request');


module.exports =function requestHandle(options,callback) {
    request(options,callback);
  //   //先去后台获取认证，将jwt放入请求头中
  //   //获取node服务器地址
  //   var IPv4;
  //   if(os.networkInterfaces().eth0){//ubuntu系统
  //     for(var i=0;i<os.networkInterfaces().eth0.length;i++){
  //       if(os.networkInterfaces().eth0[i].family=='IPv4'){
  //         IPv4=os.networkInterfaces().eth0[i].address;
  //       }}
  //   }else{//mac系统
  //     for(var m=0;m<os.networkInterfaces().en0.length;m++){
  //       if(os.networkInterfaces().en0[m].family=='IPv4'){
  //         IPv4=os.networkInterfaces().en0[m].address;
  //       }}
  //   }
  //   var getToken = function () {
  //   return new Promise(function (resolve, reject) {
  //     request({
  //       url: baseConfig.auth.url+'/security/nodes/token',
  //       method: 'POST',
  //       json: true,
  //       body: {
  //         "servicename":baseConfig.auth.servicename,
  //         "hostname":IPv4
  //       }
  //     }, function callback(error, response, data) {
  //       if(error){
  //         resolve({"error":"获取token失败"});
  //       }else{
  //         resolve(data);
  //       }
  //     });
  //
  //   }).catch( function(err) {
  //     console.log("error:\n"+err);
  //   });
  // };
  //   var getJwt=function (token) {
  //   return new Promise(function (resolve, reject) {
  //     request({
  //       url: baseConfig.auth.url+'/security/pki/sign/'+token.token,
  //       method: 'POST',
  //       json: true,
  //       body: {
  //         "rolename":baseConfig.auth.rolename
  //       }
  //     }, function callback(error, response, data) {
  //       if(error){
  //         resolve({"error":"获取jwt失败"});
  //       }else{
  //         resolve(data);
  //       }
  //     });
  //
  //   }).catch( function(err) {
  //     console.log("error:\n"+err);
  //   });
  // };
  //   async function requestHandlers(){
  //   var token=await getToken();
  //   var jwt=await getJwt(token);
  //     if(!options.headers)options.headers={};
  //     options.headers['X-Qloud-Token']=jwt.jwt;
  //     request(options,callback);
  // }
  // requestHandlers();
}

