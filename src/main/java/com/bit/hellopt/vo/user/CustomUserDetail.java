package com.bit.hellopt.vo.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("serial")
public class CustomUserDetail implements UserDetails{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	// userId
	private String username;
	private String password;
	private String auth;
	// temporary
	private boolean enable;
	// real name
	private String name;
	
	public CustomUserDetail(User user) {
		this.username = user.getUserId();
		this.password = user.getUserPw();
		this.auth = user.getUserRole();
		this.name = user.getUserName();
		this.enable = (user.getUserEnable() == 1);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(auth));
		return authList;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enable;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	// 테스트 중( 서나)
	
	public String getAuth() {
		return this.auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}
