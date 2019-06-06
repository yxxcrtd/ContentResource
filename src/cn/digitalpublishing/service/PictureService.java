package cn.digitalpublishing.service;

import java.util.List;
import java.util.Map;
import cn.digitalpublishing.po.Picture;

/**
 * @name 图片信息
 * @Autor  CuiXian
 */
public interface PictureService extends BaseService {

	/**
	 * 修改信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void update(Picture obj, String id, String[] properties) throws Exception;
	
	/**
	 * 新增信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
    public void insert(Picture obj) throws Exception;
    
    /**
	 * 删除信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public void delete(String id) throws Exception;
	
	/**
	 * 获取图片列表
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public List<Picture> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception;

	/**
	 * 获取列表信息总数
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Integer getCount(Map<String, Object> condition) throws Exception;
	
	/**
	 * 根据Id获取图片信息
	 * 
	 * @param obj
	 * @throws Exception
	 */
	public Picture getById(String id) throws Exception;
	
}