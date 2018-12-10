package com.ctfs.qloudMarket.market_service.websocket_interface;

import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.reakt.promise.Promise;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/8/13
 * Time: 0:40
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public interface PurchaseNotifyInf {
    Promise<Map> purchaseNotify(final Callback<Map> callback, final Map<String,Object> req);
}
