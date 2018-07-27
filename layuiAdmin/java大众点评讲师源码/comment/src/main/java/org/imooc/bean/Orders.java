package org.imooc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Orders {
	private Long id;
	private Long memberId;
	private Long businessId;
	private Integer num;
	private Integer commentState;
	private Double price;
	private Business business;
	private Member member;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Integer getNum() {
	    return num;
	}
	public void setNum(Integer num) {
	    this.num = num;
	}
	public Integer getCommentState() {
		return commentState;
	}
	public void setCommentState(Integer commentState) {
		this.commentState = commentState;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
}