package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @name 产品信息包
 * @table COMPRESS
 */
@SuppressWarnings("serial")
public class Compress implements Serializable {

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 包名
	 */
	private String title;
	
	/**
	 * 路径
	 */
	private String path;
	
	/**
	 * 状态（0：可以下载，1：不能下载）
	 */
	private Integer status;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

}