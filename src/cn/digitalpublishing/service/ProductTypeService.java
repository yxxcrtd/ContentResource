package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.ProductType;

/**
 * @name 分类信息
 * @Autor CuiXian
 */
public interface ProductTypeService extends BaseService {
	
	/**
	 * 修改信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void update(ProductType obj, String id, String[] properties) throws Exception;
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void insert(ProductType obj) throws Exception;
	
	/**
	 * 删除信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 获取产品类型列表信息
	 * 
	 * @param condition
	 * @throws Exception
	 */
    public List<ProductType> getPagingList(Map<String,Object> condition,String sort,Integer pageCount,Integer page)throws Exception;
    
    /**
	 * 获取产品类型总数
	 * 
	 * @param condition
	 * @throws Exception
	 */
	public Integer getCount(Map<String, Object> condition) throws Exception;
    
    /**
     * 获取产品类型列表
     * @param condition
     * @param sort
     * @return
     * @throws Exception
     */
    public List<ProductType> getList(Map<String,Object> condition, String sort) throws Exception;
	
	/**
	 * 根据Id获取产品类型信息
	 * 
	 * @param condition
	 * @throws Exception
	 */
	public ProductType getById(String id) throws Exception;
	
}