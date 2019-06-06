package cn.digitalpublishing.springmvc.form;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.Copyright;
import cn.digitalpublishing.springmvc.form.DataTableForm;
import cn.digitalpublishing.util.DateFormatUitl;

public class CopyrightForm extends DataTableForm<Copyright> {

	private Copyright obj;
	private String author;
	private String contractName;
    private Date startDate;
    private Date stopDate;
	private String startStr;
	private String stopStr;
	private CommonsMultipartFile UploadFile = null;
	
	public Copyright getObj() {
		return obj;
	}
	public void setObj(Copyright obj) {
		this.obj = obj;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public String getStartStr() {
		return startStr;
	}
	public void setStartStr(String startStr) {
		this.startDate=DateFormatUitl.stringToDate(startStr);
		this.startStr = startStr;
	}
	public String getStopStr() {
		return stopStr;
	}
	public void setStopStr(String stopStr) {
		this.stopDate=DateFormatUitl.stringToDate(stopStr);
		this.stopStr = stopStr;
	}
	public CommonsMultipartFile getUploadFile() {
		return UploadFile;
	}
	public void setUploadFile(CommonsMultipartFile uploadFile) {
		UploadFile = uploadFile;
	}
	
}