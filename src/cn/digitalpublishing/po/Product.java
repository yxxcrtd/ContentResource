package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import cn.digitalpublishing.util.DateFormatUitl;

/**
 * @name 产品信息
 * @table PRODUCT
 */
@SuppressWarnings("serial")
public class Product implements Serializable {

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 产品分类
	 */
	private ProductType productType;
	
	/**
	 * ISBN
	 */
	private String isbn;
	
	/**
	 * 书名
	 */
	private String title;
	
	/**
	 * 译名
	 */
	private String translation;
	
	/**
	 * 页数
	 */
	private Integer pageNum;
	
	/**
	 * 开本
	 */
	private String format;
	
	/**
	 * 尺寸
	 */
	private String size;
	
	/**
	 * 排版
	 */
	private String compose;
	
	/**
	 * 版别版次
	 */
	private String edition;
	
	/**
	 * 装帧
	 */
	private String binding;
	
	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 印张
	 */
	private String sheet;
	
	/**
	 * 商品重量
	 */
	private String weight;
	
	/**
	 * 语种
	 */
	private String language;
	
	/**
	 * 译者
	 */
	private String translator;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 出版社
	 */
	private String publisher;
	
	/**
	 * 出版日期
	 */
	private Date datePublication;
	private String dataPublicationStr;
	
	/**
	 * 图书封面
	 */
	private String bookCovers;
	
	/**
	 * 资源文件原名
	 */
	private String originName;
	
	/**
	 * 资源文件存放的系统名
	 */
	private String systemName;
	
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

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCompose() {
		return compose;
	}

	public void setCompose(String compose) {
		this.compose = compose;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getDatePublication() {
		return datePublication;
	}

	public void setDatePublication(Date datePublication) {
		this.datePublication = datePublication;
	}

	public String getDataPublicationStr() {
		return dataPublicationStr;
	}

	public void setDataPublicationStr(String dataPublicationStr) {
		this.datePublication=DateFormatUitl.stringToDate(dataPublicationStr);
		this.dataPublicationStr = dataPublicationStr;
	}

	public String getBookCovers() {
		return bookCovers;
	}

	public void setBookCovers(String bookCovers) {
		this.bookCovers = bookCovers;
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