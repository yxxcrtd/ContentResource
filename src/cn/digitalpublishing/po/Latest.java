package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

import cn.digitalpublishing.util.DateFormatUitl;

/**
 * @name 最新活动
 * @table LATEST
 */
@SuppressWarnings("serial")
public class Latest implements Serializable {

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 分类（0、综合 1、文档 2、照片 3、图片 4、音视频）
	 */
	private String type;
	
	/**
	 * 活动主题
	 */
	private String title;
	
	/**
	 * 活动发起者
	 */
	private String head;
	
	/**
	 * 活动简介
	 */
	private String content;
	
	/**
	 * 附件包原名
	 */
	private String originName;
	
	/**
	 * 附件包系统名
	 */
	private String systemName;
	
	/**
	 * 活动主题图片
	 */
	private String picture;
	
	/**
	 * 活动开始时间
	 */
    private Date startDate;
    private String startStr;
    
    /**
	 * 活动结束时间
	 */
    private Date stopDate;
    private String stopStr;
    
    /**
	 * 创建日期
	 */
    private Date createOn = new Date();
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getStartStr() {
		return startStr;
	}
	public void setStartStr(String startStr) {
		this.startDate=DateFormatUitl.stringToDate(startStr);
		this.startStr = startStr;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public String getStopStr() {
		return stopStr;
	}
	public void setStopStr(String stopStr) {
		this.stopDate=DateFormatUitl.stringToDate(stopStr);
		this.stopStr = stopStr;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
    
}