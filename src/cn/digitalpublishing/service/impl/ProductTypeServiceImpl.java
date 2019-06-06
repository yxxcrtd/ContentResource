package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;
import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.service.ProductTypeService;

/**
 * @name 分类信息
 * @Autor CuiXian
 */
public class ProductTypeServiceImpl extends BaseServiceImpl implements ProductTypeService {
	
	@Override
	public void update(ProductType obj, String id,String[] properties) throws Exception {
		try {
			this.daoFacade.getProductTypeDao().update(obj,ProductType.class.getName(), id, properties);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "更新信息出错", e);
		}
	}

	@Override
	public void insert(ProductType obj) throws Exception {
		try {
			this.daoFacade.getProductTypeDao().insert(obj);
	} catch (Exception e) {
		throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "插入信息出错", e);
		}
		
	}

	@Override
	public void delete(String id) throws Exception {
		try {
			this.daoFacade.getProductTypeDao().delete(ProductType.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "删除信息出错", e);
		}
		
	}

	@Override
	public List<ProductType> getPagingList(Map<String, Object> condition, String sort, Integer pageCount,Integer page) throws Exception {
		List<ProductType> list=null;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ProductTypeDao").get("getPagingList");
		try{
			list=this.daoFacade.getProductTypeDao().getPagingList(condition,sort,pageCount,page,hqlBean);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "获取产品类型列表出错", e);
		}
		return list;
	}

	@Override
	public Integer getCount(Map<String, Object> condition) throws Exception {
		Integer num = 0;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ProductTypeDao").get("getCount");
		try {
			num = this.daoFacade.getProductTypeDao().getCount(condition,hqlBean);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "获取列表总数出错", e);
		}
		return num;
	}
	
    public ProductType getById(String id) throws Exception{
    	ProductType cla = null;
		try {
			cla = (ProductType) this.daoFacade.getProductTypeDao().get(ProductType.class.getName(), id);
		} catch (Exception e) {
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "根据Id获取信息出错", e);
		}
		return cla;
    }

	@Override
	public List<ProductType> getList(Map<String, Object> condition, String sort) throws Exception {
		List<ProductType> list = null;
		HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ProductTypeDao").get("getList");
		try{
			list = this.daoFacade.getProductTypeDao().getList(condition,sort,hqlBean);
		}catch(Exception e){
			throw new CcsException((e instanceof CcsException) ? ((CcsException)e).getPrompt() : "获取分类信息出错", e);
		}
		return list;
	}

}