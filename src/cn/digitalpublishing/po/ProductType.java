package cn.digitalpublishing.po;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @name 产品类型
 * @table PRODUCT_TYPE
 */
@SuppressWarnings("serial")
public class ProductType implements Serializable {

	/**
	 * 主键Id
	 */
	private String id;
	
	/**
	 * 产品分类名称
	 */
    private String name;
    
    /**
	 * 总类编码（0：图书分类，1：图片分类）
	 */
    private String code;
    
    /**
	 * 产品分类排序
	 */
    private Integer order;
    
    /**
	 * 产品分类节点编码
	 */
    private ProductType treeCode;
    
    /**
	 * 国际化参数
	 */
    private String internationCode;
    
    /**
	 * 资源总数
	 */
    private Integer total;
    
    @JsonIgnore
    private Set<ProductType> productTypeSet = new HashSet<ProductType>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public ProductType getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(ProductType treeCode) {
		this.treeCode = treeCode;
	}

	public String getInternationCode() {
		return internationCode;
	}

	public void setInternationCode(String internationCode) {
		this.internationCode = internationCode;
	}

	public Set<ProductType> getProductTypeSet() {
		return productTypeSet;
	}

	public void setProductTypeSet(Set<ProductType> productTypeSet) {
		this.productTypeSet = productTypeSet;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
    
}