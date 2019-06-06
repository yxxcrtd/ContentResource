package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;
import cn.digitalpublishing.po.Compress;

/**
 * @name 产品信息包
 * @Autor  CuiXian
 */
public interface CompressService extends BaseService {

	/**
	 * 修改信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void update(Compress obj, String id, String[] properties) throws Exception;
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
    public void insert(Compress obj) throws Exception;
    
    /**
	 * 删除信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 获取产品包列表
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public List<Compress> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 获取列表信息总数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Integer getCount(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据Id获取产品包信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Compress getById(String id) throws Exception;
	
}