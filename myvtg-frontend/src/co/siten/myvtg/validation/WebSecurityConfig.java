package co.siten.myvtg.validation;

import javax.servlet.Filter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Filter customFilter = new CustomFilter();
		http.addFilter(customFilter);
	//	http.addFilterAfter( CustomFilter, CsrfFilter.class);
	}

}