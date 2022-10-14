package co.siten.myvtg.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.util.CommonUtil;

public class RequestFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	public void destroy() {
		// Nothing to do
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
			
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		MyvtgRequest wrappedRequest = new MyvtgRequest((HttpServletRequest) request);
		String body = IOUtils.toString(wrappedRequest.getReader());
		
		
		
		if (url.contains("api") && !CommonUtil.isEmpty(body)) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				RequestBean obj = mapper.readValue(body, RequestBean.class);
				obj.setStartTime(CommonUtil.getCurrentTime());
				String contentBody = CommonUtil.convertObjectToJsonString(obj.getWsRequest());
				wrappedRequest.resetInputStream(contentBody.getBytes());
				wrappedRequest.setRequestBean(obj);
				logger.error("loged_user: "+obj.getUsername());
				if (CommonUtil.isEmpty(obj.getUsername())) {
					obj.setUsername("myvtg");
				}
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				UserDetails user = new User(obj.getUsername(), "", authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
						user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);

				chain.doFilter(wrappedRequest, response);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				chain.doFilter(request, response);
			}
		} else {
			
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// Nothing to do
	}

}