package com.ctfs.qloudMarket.market_service.sys_service.endpoint;

import com.ctfs.qloudMarket.market_service.permission_service.service.PermissionServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/11/30
 * Time: 11:07
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public class SystemInitial {
    private static Logger logger = LoggerFactory.getLogger(SystemInitial.class);
    private PermissionServer permissionServer=new PermissionServer();
    public SystemInitial() throws Exception {
        logger.info("\n" +
                "                            _ooOoo_\n" +
                "                           o8888888o\n" +
                "                           88\" . \"88\n" +
                "                           (| -_- |)\n" +
                "                            O\\ = /O\n" +
                "                        ____/`---'\\____\n" +
                "                      .   ' \\\\| |// `.\n" +
                "                       / \\\\||| : |||// \\\n" +
                "                     / _||||| -:- |||||- \\\n" +
                "                       | | \\\\\\ - /// | |\n" +
                "                     | \\_| ''\\---/'' | |\n" +
                "                      \\ .-\\__ `-` ___/-. /\n" +
                "                   ___`. .' /--.--\\ `. . __\n" +
                "                .\"\" '< `.___\\_<|>_/___.' >'\"\".\n" +
                "               | | : `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
                "                 \\ \\ `-. \\_ __\\ /__ _/ .-` / /\n" +
                "         ======`-.____`-.___\\_____/___.-`____.-'======\n" +
                "                            `=---=' \n" +
                "           .............................................\n" +
                "                  佛祖镇楼                  BUG辟易\n" +
                "          佛曰:\n" +
                "                   写字楼里写字间，写字间里程序员；\n" +
                "                   程序人员写程序，又拿程序换酒钱。\n" +
                "                   酒醒只在网上坐，酒醉还来网下眠；\n" +
                "                   酒醉酒醒日复日，网上网下年复年。\n" +
                "                   但愿老死电脑间，不愿鞠躬老板前；\n" +
                "                   奔驰宝马贵者趣，公交自行程序员。\n" +
                "                   别人笑我忒疯癫，我笑自己命太贱；\n" +
                "                   不见满街漂亮妹，哪个归得程序员？\n" +
                "           --------------------- ");
        logger.info("initial user permission");
       // permissionServer.writeAllUserResource();

    }
}
