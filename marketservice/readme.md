2018/11/09 修复问题<br>
1.校验layer 是否在用户端存在如果存在则不下载layer。<br>
2.修复了manifest无法上传的问题。<br>
3.新增了查询license 接口。<br>
4.新增了异步证书同步。<br>
5.新增了部署状态记录。<br>
6.新增了订单状态回执同步。<br>
2018/11/22 qloud mart 更新的内容
   1. 新增了用户定制产品展示
   2. 新增了用户体验区和用户私有环境下单
   3. 更新了deployer 磁盘挂卷
   4. 新增了产品是否可购买的限制<br>
   
2018/12/6 qloud mart 更新内容<br>
    1. 修复了无排序 分页的bug <br>
    2. 增加了 新增account 接口的api gateway 支持 <br>
 mysql 脚本:<br>
    ALTER TABLE `tb_license`
    ADD COLUMN `pcode`  varchar(64) NULL AFTER `create_date`,
    DROP PRIMARY KEY,
    ADD PRIMARY KEY (`expired_date`, `pcode`);
    
    