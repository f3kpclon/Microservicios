package com.example.zuulserver.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoFilter extends ZuulFilter {


    private static Logger log = LoggerFactory.getLogger(PreTiempoFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        log.info("Entrando a POST filter ");
        Long tiempoInicio = (Long)request.getAttribute("tiempoInicio");
        Long tiempoFinal = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal-tiempoInicio;
        request.setAttribute("tiempoInicio",tiempoInicio);
        log.info(String.format("Tiempo transcurrido en segundo %s", tiempoTranscurrido.doubleValue()/1000.00));
        return null;
    }
}
