package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @name 图片信息
 * @table PICTURE
 */
@SuppressWarnings("serial")
public class Picture implements Serializable {

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 产品分类
	 */
	private ProductType productType;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 关键词
	 */
	private String keyword;
	
	/**
	 * 图片
	 */
	private String pic;
	
	/**
	 * 备注
	 */
	private String remark;
	
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

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

}