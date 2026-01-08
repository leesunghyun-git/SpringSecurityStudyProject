package com.sist.web.service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sist.web.mapper.*;
import com.sist.web.vo.*;
import java.util.*;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{
	private final UsersMapper uMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UsersVO user = uMapper.findByUsername(username);
		if(user==null)
		{
			throw new UsernameNotFoundException("UserName을 찾을 수 없습니다.");
		}
		List<String> roles=uMapper.findRolesByUserId(user.getId());
		// 권한 관련
		Set<GrantedAuthority> authorities = new HashSet<>();
		for(String role:roles)
		{
			authorities.add(new SimpleGrantedAuthority(role));
		}
 		return new User(user.getUsername(), user.getPasswrod(), user.getEnabled()==0?false:true,true,true,true,authorities);
	}
	
	
}
