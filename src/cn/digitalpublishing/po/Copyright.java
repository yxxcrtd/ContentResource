package cn.digitalpublishing.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.digitalpublishing.util.DateFormatUitl;

/**
 * @name 作者版权信息
 * @table COPYRIGHT
 */
@SuppressWarnings("serial")
public class Copyright implements Serializable {

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 联系方式
	 */
	private String phone;
	
	/**
	 * 地址
	 */
	private String address;
	
	/**
	 * 版权归属
	 */
	private String performer;
	
	/**
	 * 版权所在地
	 */
	private String area;
	
	/**
	 * 合同名称
	 */
	private String contractName;
	
	/**
	 * 合同签订日期
	 */
    private Date signingDate;
    private String signingStr;
    
    /**
	 * 合同来源
	 */
    private String source;
    
    /**
	 * 合同签订原因
	 */
    private String cause ;
    
    /**
	 * 合同状态（0、未开始 1、执行中 2、已结束 3、意外中止）
	 */
    private String status ;
	
    /**
	 * 合同开始时间
	 */
    private Date startDate;
    private String startStr;
    
    /**
	 * 合同结束时间
	 */
    private Date stopDate;
    private String stopStr;
    
    /**
	 * 合同保证金
	 */
    private BigDecimal margin;
    
    /**
	 * 合同最多金额
	 */
    private BigDecimal largeAmount;
    
    /**
	 * 合同最少金额
	 */
    private BigDecimal minAmount;
    
	/**
	 * 合同附件原名
	 */
	private String originName;
	
	/**
	 * 合同附件存放的系统名
	 */
	private String systemName;
	
	/**
	 * 合同过期天数
	 */
	private Integer days = 0;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getSigningStr() {
		return signingStr;
	}

	public void setSigningStr(String signingStr) {
		this.signingDate=DateFormatUitl.stringToDate(signingStr);
		this.signingStr = signingStr;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public BigDecimal getMargin() {
		return margin;
	}

	public void setMargin(BigDecimal margin) {
		this.margin = margin;
	}

	public BigDecimal getLargeAmount() {
		return largeAmount;
	}

	public void setLargeAmount(BigDecimal largeAmount) {
		this.largeAmount = largeAmount;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
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

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date time1=null;Date time2=null;
		try {
			time1 = format.parse(format.format(this.stopDate));
			time2 = format.parse(format.format(new Date()));
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		long time = time1.getTime() - time2.getTime();
		long day = time / (24 * 60 * 60 * 1000L);
		int date = new Long(day).intValue();
		days = new Integer(date);
		this.days = days;
	}
    
}