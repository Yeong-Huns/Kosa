package app.labs.member.service;

import java.util.List;

import app.labs.member.model.Member;

public interface IMemberService {
	void insertMember(Member member) ;
	Member selectMember(String userid);
	List<Member> selectAllMembers();
	void updateMember(Member member);
	void deleteMember(Member member);
	String getPassword(String userid);
}