package com.haige.luban;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSessionListener;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.SessionEventHttpSessionListenerAdapter;

import com.haige.luban.listener.OnLineCounter;

@EnableRedisHttpSession
public class RedisSessionConfig {
	@Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
            //cookie名字
	        //CookieHttpSessionIdResolver里面定义的名字是SESSION,而Tomcat默认的名字是JSESSIONID，
        	//所以会导致服务器无法读取之前的sessionId从而每次请求都重新创建新的session，所以最终导致之前放进Session里的对象无法取出
            defaultCookieSerializer.setCookieName("JSESSIONID");
            //DefaultCookieSerializer里定义的默认值是true，而Tomcat默认值是false，为了保持向之前版本的兼容性，这里设置为false
            defaultCookieSerializer.setUseBase64Encoding(false);
            //域
            //defaultCookieSerializer.setDomainName("xxx.com");
            //存储路径
            //defaultCookieSerializer.setCookiePath("/");
        
        return defaultCookieSerializer;
    }
	
	//原先的HttpSessionListener在使用redis作为session存储后不再起作用，需要注入到下面的SessionEventHttpSessionListenerAdapter里，方可生效
	@Bean
	public SessionEventHttpSessionListenerAdapter redisSessionListener() {
		List<HttpSessionListener> listeners=new LinkedList<HttpSessionListener>();
		listeners.add(new OnLineCounter());
		SessionEventHttpSessionListenerAdapter sessionEventHttpSessionListenerAdapter=new SessionEventHttpSessionListenerAdapter(listeners);
		return sessionEventHttpSessionListenerAdapter;
	}
}
