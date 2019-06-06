package cn.digitalpublishing.springmvc.form;

import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.springmvc.form.DataTableForm;


public class ProductTypeForm extends DataTableForm<ProductType> {

	private ProductType obj;
	private String name;
	private String code;
	
	public ProductType getObj() {
		return obj;
	}
	public void setObj(ProductType obj) {
		this.obj = obj;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
