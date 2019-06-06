package cn.digitalpublishing.springmvc.form;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.digitalpublishing.po.Latest;
import cn.digitalpublishing.springmvc.form.DataTableForm;
import cn.digitalpublishing.util.DateFormatUitl;

public class LatestForm extends DataTableForm<Latest> {

	private Latest obj;
	private String title;
    private Date startDate;
    private Date stopDate;
	private String startStr;
	private String stopStr;
	private CommonsMultipartFile FilePic = null;
	private CommonsMultipartFile UploadFile = null;
	
	public Latest getObj() {
		return obj;
	}
	public void setObj(Latest obj) {
		this.obj = obj;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public CommonsMultipartFile getFilePic() {
		return FilePic;
	}
	public void setFilePic(CommonsMultipartFile filePic) {
		FilePic = filePic;
	}
	public CommonsMultipartFile getUploadFile() {
		return UploadFile;
	}
	public void setUploadFile(CommonsMultipartFile uploadFile) {
		UploadFile = uploadFile;
	}
	
}