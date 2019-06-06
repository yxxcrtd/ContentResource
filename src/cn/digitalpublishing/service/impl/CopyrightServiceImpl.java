package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Copyright;
import cn.digitalpublishing.service.CopyrightService;

/**
 * @name 作者版权信息
 * @Autor CuiXian
 */
public class CopyrightServiceImpl extends BaseServiceImpl implements CopyrightService {

    @Override
    public void update(Copyright obj, String id, String[] properties) throws Exception {

        try {
            this.daoFacade.getCopyrightDao().update(obj, Copyright.class.getName(), id, properties);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "更新信息出错", e);
        }

    }
    
    @Override
    public void insert(Copyright obj) throws Exception {
        try {
            this.daoFacade.getCopyrightDao().insert(obj);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "插入信息出错", e);
        }

    }

    @Override
    public void delete(String id) throws Exception {

        try {
            this.daoFacade.getCopyrightDao().delete(Copyright.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除信息出错", e);
        }
    }
    
    @Override
    public List<Copyright> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {

        List<Copyright> list = null;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.CopyrightDao").get("getPagingList");
        try {
            list = this.daoFacade.getCopyrightDao().getPagingList(condition, sort, pageCount, page, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取列表出错", e);
        }
        return list;
    }

    @Override
    public Integer getCount(Map<String, Object> condition) throws Exception {

        Integer num = 0;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.CopyrightDao").get("getCount");
        try {
            num = this.daoFacade.getCopyrightDao().getCount(condition, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取总数出错", e);
        }
        return num;
    }
    @Override
    public Copyright getById(String id) throws Exception {

    	Copyright cla = null;
        try {
            cla = (Copyright) this.daoFacade.getCopyrightDao().get(Copyright.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "根据Id查询信息出错", e);
        }
        return cla;
    }

}