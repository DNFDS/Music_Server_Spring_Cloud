package com.example.zuulgateway;

import com.google.common.io.Resources;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String realIp = IPUtils.getRealIP(request);
        List<String> ipList =new ArrayList<>();
        if(!"0:0:0:0:0:0:0:1".equals(realIp)) {
            try{
                ipList = Resources.readLines(Resources.getResource("ipConfig.properties"), Charset.forName("utf-8"));
            }catch(Exception e){
                log.info(e.toString());
            }
            if (!IPWhiteListUtil.checkIpList(realIp, ipList)){
                HashMap responseObj = new HashMap<String,String>();
                responseObj.put("code", "ILLEGAL_IP");
                responseObj.put("msg", "非法IP,请联系管理员");
                return responseObj.toString();
            }
        }
        log.info("ok");
        return null;
    }
}

