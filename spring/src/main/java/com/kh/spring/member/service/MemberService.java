package com.kh.spring.member.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	// 로그인 서비스
	Member loginMember(Member m);

	// 아이디 체크
	int idCheck(String checkId);
}
