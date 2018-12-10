var request=require('../util/requestHandle.js');
var baseConfig=require('../config/baseConfig.json');
var handleRes=require('../util/handleResUtil.js');
var uri=baseConfig.url;


module.exports = function attachHandlers(router) {
    process.env.NODE_TLS_REJECT_UNAUTHORIZED = 0;
    //获取订单
    router.get('/v1/orders',
        function(req, res) {
            var start=0;
            if(req.query.page){
                start=(req.query.page-1)*12;
            }
            var options =
                {
                    url: uri+'/orders?accountId=10001&start='+start+'&count=12',
                    method: 'GET'
                };
            function callback(error, response, data) {
                var handleData=handleRes.handleRes(error, response, data);
                if(handleData.statusCode=="200") {
                    if (handleData.code == "000") {
                        for(var i=0;i< handleData.data['orders:'].length;i++){
                            handleData.data['orders:'][i].createDate=new Date(handleData.data['orders:'][i].createDate).getTime();
                            if(handleData.data['orders:'][i].orderStatus=='paid'){
                                handleData.data['orders:'][i].orderStatusName='成功';
                            }else{
                                handleData.data['orders:'][i].orderStatusName='挂起';
                            }
                        }

                    }
                }
                res.send(handleData);

            }

            request(options, callback);

        });
    //获取订单详情
    router.get('/v1/orders/:id',
        function(req, res) {
            var id=encodeURI(req.params.id);
            var options =
                {
                    url: uri+'/products/'+id,
                    method: 'GET'
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);
        });
    //新增订单
    router.post('/v1/orders',
        function(req, res) {
            //数据改装成接口需要的格式
            var options =
                {
                    url: uri+'/orders',
                    method: 'POST',
                    json:true,
                    body: req.body
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);
        });
};
