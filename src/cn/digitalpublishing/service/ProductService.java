package cn.digitalpublishing.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import cn.digitalpublishing.po.Product;

/**
 * @name 产品信息
 * @Autor  CuiXian
 */
public interface ProductService extends BaseService {

	/**
	 * 修改信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void update(Product obj, String id, String[] properties) throws Exception;
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
    public void insert(Product obj) throws Exception;
    
    /**
	 * 删除信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 获取产品列表
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public List<Product> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 获取列表信息总数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Integer getCount(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据Id获取产品信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Product getById(String id) throws Exception;
	
	/**
	 * 根据条件获取产品信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public List<Product> getList(Map<String, Object> condition, String sort) throws Exception;
	
	/**
	 * 上传excel导入产品信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void upload(InputStream inputStream) throws Exception;
	
	/**
	 * 元数据入库
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public void XMLreader(InputStream inputStream) throws Exception;
	
}