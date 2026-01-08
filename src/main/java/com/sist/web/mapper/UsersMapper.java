package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.UsersVO;

@Mapper
@Repository
public interface UsersMapper {
	@Insert("INSERT INTO users(username,password) VALUES(#{username},#{password})")
	public void usersInsert(UsersVO vo);
	
	@Select("SELECT * FROM users WHERE username = #{username}")
	public UsersVO findByUsername(String username);
	
	@Select("SELECT role_name FROM user_role WHERE user_id=#{userid}")
	public List<String> findRolesByUserId(int userid);
	
	//로그인 ...
}
