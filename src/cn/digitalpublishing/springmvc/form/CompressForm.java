package cn.digitalpublishing.springmvc.form;

import cn.digitalpublishing.po.Compress;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class CompressForm extends DataTableForm<Compress> {
	
	private Compress obj;
	
	private String title;
	
	private String[] ids;

	public Compress getObj() {
		return obj;
	}

	public void setObj(Compress obj) {
		this.obj = obj;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}