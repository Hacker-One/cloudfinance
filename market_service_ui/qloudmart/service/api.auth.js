var request=require('../util/requestHandle.js');
var baseConfig=require('../config/baseConfig.json');
var handleRes=require('../util/handleResUtil.js');
var uri=baseConfig.url;


module.exports = function attachHandlers(router) {
    process.env.NODE_TLS_REJECT_UNAUTHORIZED = 0;
    //新增订单
    router.post('/v1/login',
        function(req, res) {
            //数据改装成接口需要的格式
            var options =
                {
                    url: uri+'/login',
                    method: 'POST',
                    json:true,
                    body: req.body
                };
            function callback(error, response, data) {
                console.log(data);
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);
        });
};
