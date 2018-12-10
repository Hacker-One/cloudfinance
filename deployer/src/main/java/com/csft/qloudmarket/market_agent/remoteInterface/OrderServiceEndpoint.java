package com.csft.qloudmarket.market_agent.remoteInterface;

import com.qloudfin.qloudbus.reactive.Callback;
import com.qloudfin.qloudbus.reakt.promise.Promise;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ambitious Chen
 * Date: 2018/9/19
 * Time: 11:59
 * Corporation:Chian soft finance tech
 * To change this template use File | Settings | File Templates.
 */
public interface OrderServiceEndpoint {
     Promise<Map> notify(final Callback<Map> callback, final Map<String, Object> data);
}
