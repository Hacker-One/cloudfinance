2018/11/09 修复问题<br>
1.校验layer 是否在用户端存在如果存在则不下载layer。<br>
2.修复了manifest无法上传的问题。<br>
3.新增了查询license 接口。<br>
4.新增了异步证书同步。<br>
5.新增了部署状态记录。<br>
6.新增了订单状态回执同步。<br>


[program:ingress]
directory=/app/target/paas
command=sh start.sh endpoint.conf