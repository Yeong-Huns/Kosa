package com.example.myapp.member.service;


import com.example.myapp.member.model.Member;

import java.util.List;

public interface IMemberService {
	void insertMember(Member member) ;
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member);
	String getPassword(String userid);
}