package cn.digitalpublishing.springmvc.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.Picture;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class PictureForm extends DataTableForm<Picture> {

	private Picture obj;
	private String title;
	private String keyword;
	private String productTypeId;
	private CommonsMultipartFile FilePic = null;
	private String[] ids;
	
	public Picture getObj() {
		return obj;
	}
	public void setObj(Picture obj) {
		this.obj = obj;
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
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public CommonsMultipartFile getFilePic() {
		return FilePic;
	}
	public void setFilePic(CommonsMultipartFile filePic) {
		FilePic = filePic;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
}