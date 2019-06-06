package cn.digitalpublishing.springmvc.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import cn.digitalpublishing.po.Product;
import cn.digitalpublishing.springmvc.form.DataTableForm;

public class ProductForm extends DataTableForm<Product> {

	private Product obj;
	private String isbn;
	private String title;
	private String author;
	private CommonsMultipartFile txtFile = null;
	private CommonsMultipartFile FilePic = null;
    private CommonsMultipartFile upLoadFile = null;
    private CommonsMultipartFile xmlFile = null;
	private String txtFormat = "xls,xlsx";
	
	public Product getObj() {
		return obj;
	}
	public void setObj(Product obj) {
		this.obj = obj;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public CommonsMultipartFile getTxtFile() {
		return txtFile;
	}
	public void setTxtFile(CommonsMultipartFile txtFile) {
		this.txtFile = txtFile;
	}
	public CommonsMultipartFile getFilePic() {
		return FilePic;
	}
	public void setFilePic(CommonsMultipartFile filePic) {
		FilePic = filePic;
	}
	public CommonsMultipartFile getUpLoadFile() {
		return upLoadFile;
	}
	public void setUpLoadFile(CommonsMultipartFile upLoadFile) {
		this.upLoadFile = upLoadFile;
	}
	public CommonsMultipartFile getXmlFile() {
		return xmlFile;
	}
	public void setXmlFile(CommonsMultipartFile xmlFile) {
		this.xmlFile = xmlFile;
	}
	public String getTxtFormat() {
		return txtFormat;
	}
	public void setTxtFormat(String txtFormat) {
		this.txtFormat = txtFormat;
	}
	
}