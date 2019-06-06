package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Latest;
import cn.digitalpublishing.service.LatestService;

/**
 * @name 最新活动
 * @Autor CuiXian
 */
public class LatestServiceImpl extends BaseServiceImpl implements LatestService {

    @Override
    public void update(Latest obj, String id, String[] properties) throws Exception {

        try {
            this.daoFacade.getLatestDao().update(obj, Latest.class.getName(), id, properties);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "更新信息出错", e);
        }

    }
    
    @Override
    public void insert(Latest obj) throws Exception {
        try {
            this.daoFacade.getLatestDao().insert(obj);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "插入信息出错", e);
        }

    }

    @Override
    public void delete(String id) throws Exception {

        try {
            this.daoFacade.getLatestDao().delete(Latest.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除信息出错", e);
        }
    }
    
    @Override
    public List<Latest> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {

        List<Latest> list = null;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.LatestDao").get("getPagingList");
        try {
            list = this.daoFacade.getLatestDao().getPagingList(condition, sort, pageCount, page, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取列表出错", e);
        }
        return list;
    }

    @Override
    public Integer getCount(Map<String, Object> condition) throws Exception {

        Integer num = 0;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.LatestDao").get("getCount");
        try {
            num = this.daoFacade.getLatestDao().getCount(condition, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取总数出错", e);
        }
        return num;
    }
    @Override
    public Latest getById(String id) throws Exception {

    	Latest cla = null;
        try {
            cla = (Latest) this.daoFacade.getLatestDao().get(Latest.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "根据Id查询信息出错", e);
        }
        return cla;
    }

}