package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Client
@RestController
@EnableAuthorizationServer
@EnableSpringDataWebSupport
public class ZanduApplication extends WebSecurityConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(ZanduApplication.class, args);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
	  .csrf().disable();
	}
	
	/*
	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	
	@RequestMapping("/app")
    public Principal user(Principal principal) {
		System.out.println("Got user Request");
		return principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(ZanduApplication.class, args);
	}
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/**").authorizeRequests().antMatchers("/","/users**", "/login**", "/webjars/**").permitAll().anyRequest()
		.authenticated().and().exceptionHandling()
		.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/")).and().logout()
		.logoutSuccessUrl("/").permitAll()
		.and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
		;
  }
	
	private Filter ssoFilter() {
		CompositeFilter filter = new CompositeFilter();
		List<Filter> filters = new ArrayList<>();
		filters.add(ssoFilter(facebook(), "/login/facebook"));
		filters.add(ssoFilter(github(), "/login/github"));
		filter.setFilters(filters);
		return filter;
	}

	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(
				path);
		OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		filter.setRestTemplate(template);
		filter.setTokenServices(new UserInfoTokenServices(
				client.getResource().getUserInfoUri(), client.getClient().getClientId()));
		return filter;
	}
	
	
	@Bean
	@ConfigurationProperties("github")
	public ClientResources github() {
	  return new ClientResources();
	}

	@Bean
	@ConfigurationProperties("facebook")
	public ClientResources facebook() {
	  return new ClientResources();
	}
	
	
*/}



class ClientResources {

	  @NestedConfigurationProperty
	  private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	  @NestedConfigurationProperty
	  private ResourceServerProperties resource = new ResourceServerProperties();

	  public AuthorizationCodeResourceDetails getClient() {
	    return client;
	  }

	  public ResourceServerProperties getResource() {
	    return resource;
	  }
}
