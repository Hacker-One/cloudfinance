var request=require('../util/requestHandle.js');
var baseConfig=require('../config/baseConfig.json');
var handleRes=require('../util/handleResUtil.js');
var cache = require('memory-cache');
var uri=baseConfig.url;
//获取categorys放入缓存中
initCache();
function initCache(){
    var categorysOptions =
        {
            url: uri+'/products/categorys',
            method: 'GET'
        };
    function categorysCallback(error, response, data) {
        if(response.statusCode==200){
            data=JSON.parse(data);
            if(data.data){
                cache.put("categorys",data.data);
            }else{
                cache.put("categorys",[]);
            }
        }else{
            cache.put("categorys",[]);
        }
    }
    var typesOptions =
        {
            url: uri+'/products/types',
            method: 'GET'
        };
    function typesCallback(error, response, data) {
        if(response.statusCode==200){
            data=JSON.parse(data);
            if(data.data){
                cache.put("types",data.data);
            }else{
                cache.put("types",[]);
            }
        }else{
            cache.put("types",[]);
        }
    }
    request(categorysOptions, categorysCallback);
    request(typesOptions, typesCallback);
}
setTimeout(initCache, 3600000);


module.exports = function attachHandlers(router) {
    process.env.NODE_TLS_REJECT_UNAUTHORIZED = 0;
    //获取products列表
    router.post('/v1/products/list',
        function(req, res) {
            //数据改装成接口需要的格式
            var start=0;
            if(req.body.start){
                start=(req.body.start-1)*15;
            }
             req.body.start=start.toString();
             req.body.count="15";
            var options =
                {
                    url: uri+'/products/list',
                    method: 'POST',
                    json:true,
                    body: req.body
                };
            function callback(error, response, data) {
                var handleData=handleRes.handleRes(error, response, data);
                if(handleData.statusCode=="200") {
                    if (handleData.code == "000") {
                        for(var i=0;i< handleData.data['products:'].length;i++){
                            handleData.data['products:'][i]['tags']=new Array();
                            for(var m=0;m< handleData.data['tags:'].length;m++){
                               if(handleData.data['tags:'][m]['id']!=0&&handleData.data['tags:'][m]['productId']==handleData.data['products:'][i]['id']){
                                   handleData.data['products:'][i]['tags'].push({"name":handleData.data['tags:'][m]['name']})
                               }
                            }
                        }

                    }
                }
                res.send(handleData);
            }

            request(options, callback);
        });
    // //获取products
    // router.get('/v1/products',
    //     function(req, res) {
    //         var start=0;
    //         if(req.query.page){
    //             start=(req.query.page-1)*15;
    //         }
    //         var options =
    //             {
    //                 url: uri+'/products?name='+req.query.name+'&start='+start+'&count=15',
    //                 method: 'GET'
    //             };
    //         function callback(error, response, data) {
    //             var handleData=handleRes.handleRes(error, response, data);
    //             if(handleData.statusCode=="200") {
    //                 if (handleData.code == "000") {
    //                     for(var i=0;i< handleData.data['orders:'].length;i++){
    //                         if(cache.get('types')){
    //                             for(var m=0;m<cache.get('types').length;m++){
    //                                 if(handleData.data['orders:'][i].type==cache.get('types')[m]['id']){
    //                                     handleData.data['orders:'][i].typeName=cache.get('types')[m]['name'];
    //                                 }
    //                             }
    //                         }
    //                         if(cache.get('categorys')){
    //                             for(var m=0;m<cache.get('categorys').length;m++){
    //                                 if(handleData.data['orders:'][i].category==cache.get('categorys')[m]['id']){
    //                                     handleData.data['orders:'][i].categoryName=cache.get('categorys')[m]['name'];
    //                                 }
    //                             }
    //                         }
    //                     }
    //
    //                 }
    //             }
    //             res.send(handleData);
    //         }
    //
    //         request(options, callback);
    //
    //     });
    //获取产品详情
    router.get('/v1/products/:id',
        function(req, res) {
            var id=encodeURI(req.params.id);
            var options =
                {
                    url: uri+'/products/'+id,
                    method: 'GET'
                };
            function callback(error, response, data) {
                var handleData=handleRes.handleRes(error, response, data);
                if(handleData.statusCode=="200") {
                    if (handleData.code == "000") {
                            if(cache.get('types')){
                                for(var m=0;m<cache.get('types').length;m++){
                                    if(handleData.data.type==cache.get('types')[m]['id']){
                                        handleData.data.typeName=cache.get('types')[m]['name'];
                                    }
                                }
                            }
                            if(cache.get('categorys')){
                                for(var m=0;m<cache.get('categorys').length;m++){
                                    if(handleData.data.category==cache.get('categorys')[m]['id']){
                                        handleData.data.categoryName=cache.get('categorys')[m]['name'];
                                    }
                                }
                            }
                    }
                }
                res.send(handleData);
            }

            request(options, callback);
        });
    //获取所有的types
    router.get('/v1/types',
        function(req, res) {
            res.send(cache.get('types'));
        });
    //获取所有的categorys
    router.get('/v1/categorys',
        function(req, res) {
            res.send(cache.get('categorys'));
        });
    //获取供应商信息
    router.get('/v1/vendors/:id',
        function(req, res) {
            var id=encodeURI(req.params.id);
            var options =
                {
                    url: uri+'/vendors/'+id,
                    method: 'GET'
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);
        });

    //删除指定connectors
    router.delete('/v1/aggregators/:id',
        function(req, res) {

            var id=encodeURI(req.params.id);
            var options =
                {
                    url: uri+'/aggregators/'+id,
                    method: 'DELETE'
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);

        });
    //新增aggregators
    router.post('/v1/aggregators',
        function(req, res) {
            //数据改装成接口需要的格式
            // req.body.mapping.map(funvtion(item){
            //     return
            // });
            var mapping={};
            if(req.body.event.mappingType=='1'){

            }else{
                req.body.event.scriptEngine="";
                req.body.event.mappingScript="";
                req.body.event.mapping.forEach(function(item){
                    mapping[item.param_key]=item.param_value;
                });
            }
            req.body.event.mapping=mapping;
            var options =
                {
                    url: uri+'/aggregators',
                    method: 'POST',
                    json:true,
                    body: req.body
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);
        });
    //获取aggregators详情
    router.get('/v1/aggregators/:id',
        function(req, res) {
            var id=encodeURI(req.params.id);
            var options =
                {
                    url: uri+'/aggregators/'+id,
                    method: 'GET'
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }

            request(options, callback);
        });
    //修改aggregators
    router.put('/v1/aggregators/:id',
        function(req, res) {
            var id=encodeURI(req.params.id);
            //数据改装成接口需要的格式
            var mapping={};
            if(req.body.event.mappingType=='1'){

            }else{
                req.body.event.scriptEngine="";
                req.body.event.mappingScript="";
                req.body.event.mapping.forEach(function(item){
                    mapping[item.param_key]=item.param_value;
                });
            }
            req.body.event.mapping=mapping;
            var options =
                {
                    url: uri+'/aggregators/'+id,
                    method: 'PUT',
                    json:true,
                    body:req.body
                };
            function callback(error, response, data) {
                res.send(handleRes.handleRes(error, response, data));
            }
            request(options, callback);
        });
};
