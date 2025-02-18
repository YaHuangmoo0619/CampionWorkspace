package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemberService {
	

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(Integer rank_no, String acc, String pwd, String id, String name, Date bday, Integer sex, String mobile, String mail, String city, String dist, String add, Timestamp join_time, String card, Integer pt, Integer acc_stat, Integer exp, byte[] sticker, String rmk) {

		MemberVO memberVO = new MemberVO();

		memberVO.setRank_no(rank_no);
		memberVO.setAcc(acc);
		memberVO.setPwd(pwd);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setBday(bday);
		memberVO.setSex(sex);
		memberVO.setMobile(mobile);
		memberVO.setMail(mail);
		memberVO.setCity(city);
		memberVO.setDist(dist);
		memberVO.setAdd(add);
		memberVO.setJoin_time(join_time);
		memberVO.setCard(card);
		memberVO.setPt(pt);
		memberVO.setAcc_stat(acc_stat);
		memberVO.setExp(exp);
		memberVO.setSticker(sticker);
		memberVO.setRmk(rmk);
		dao.insert(memberVO);
		
		return memberVO;
	}
	
	public MemberVO updateMember(Integer mbr_no, Integer rank_no, String acc, String pwd, String id, String name, Date bday, Integer sex, String mobile, String mail, String city, String dist, String add, Timestamp join_time, String card, Integer pt, Integer acc_stat, Integer exp, byte[] sticker, String rmk) {

		MemberVO memberVO = new MemberVO();
		
		memberVO.setMbr_no(mbr_no);
		memberVO.setRank_no(rank_no);
		memberVO.setAcc(acc);
		memberVO.setPwd(pwd);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setBday(bday);
		memberVO.setSex(sex);
		memberVO.setMobile(mobile);
		memberVO.setMail(mail);
		memberVO.setCity(city);
		memberVO.setDist(dist);
		memberVO.setAdd(add);
		memberVO.setJoin_time(join_time);
		memberVO.setCard(card);
		memberVO.setPt(pt);
		memberVO.setAcc_stat(acc_stat);
		memberVO.setExp(exp);
		memberVO.setSticker(sticker);
		memberVO.setRmk(rmk);
		dao.update(memberVO);
		
		return memberVO;
	}

	public void updateMember(MemberVO memberVO) {
		dao.update(memberVO);
	}
	
	public void deleteMember(Integer mbr_no) {
		dao.delete(mbr_no);
	}

	public MemberVO getOneMember(Integer mbr_no) {
		return dao.findByPrimaryKey(mbr_no);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public MemberVO loginMember(String acc, String pwd) {
		return dao.findByPrimaryKey_login(acc, pwd);
	}
	
	public MemberVO registerMember(String acc, String pwd, String id, String name, Date bday, Integer sex, String mobile, String mail, String city, String dist, String add) {

		MemberVO memberVO = new MemberVO();

		memberVO.setAcc(acc);
		memberVO.setPwd(pwd);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setBday(bday);
		memberVO.setSex(sex);
		memberVO.setMobile(mobile);
		memberVO.setMail(mail);
		memberVO.setCity(city);
		memberVO.setDist(dist);
		memberVO.setAdd(add);
		dao.register_Member(memberVO);
		
		return memberVO;
	}
	

	public void updateMemberStatus(String acc, Integer acc_stat) {
		dao.updateMemberStatus(acc, acc_stat);
	}

	public MemberVO findByEmail(String mail) {
		return dao.findByEmail(mail);
	}

	public void updatePwd(String mail, String pwd) {
		dao.updatePwd(mail, pwd);
	}

	public MemberVO loginCheck(String acc) {
		return dao.loginCheck(acc);
	}

	//���ĥ[��
	public MemberVO update_info(Integer mbr_no, Integer rank_no, String acc, String pwd, String id, String name, Date bday, Integer sex, String mobile, String mail, String city, String dist, String add, Timestamp join_time, String card, Integer pt, Integer acc_stat, Integer exp,String rmk) {
		
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMbr_no(mbr_no);
		memberVO.setRank_no(rank_no);
		memberVO.setAcc(acc);
		memberVO.setPwd(pwd);
		memberVO.setId(id);
		memberVO.setName(name);
		memberVO.setBday(bday);
		memberVO.setSex(sex);
		memberVO.setMobile(mobile);
		memberVO.setMail(mail);
		memberVO.setCity(city);
		memberVO.setDist(dist);
		memberVO.setAdd(add);
		memberVO.setJoin_time(join_time);
		memberVO.setCard(card);
		memberVO.setPt(pt);
		memberVO.setAcc_stat(acc_stat);
		memberVO.setExp(exp);
		memberVO.setRmk(rmk);
		
		dao.update_info(memberVO);
		
		return memberVO;
	}
	
	public String findByAcc(String acc) {
		return dao.findByAcc(acc);
	}
	//���ĥ[��
}
