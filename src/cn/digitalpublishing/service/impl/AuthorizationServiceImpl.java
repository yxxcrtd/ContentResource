package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Authorization;
import cn.digitalpublishing.service.AuthorizationService;

/**
 * @name 授权信息
 * @Autor CuiXian
 */
public class AuthorizationServiceImpl extends BaseServiceImpl implements AuthorizationService {

    @Override
    public void update(Authorization obj, String id, String[] properties) throws Exception {

        try {
            this.daoFacade.getAuthorizationDao().update(obj, Authorization.class.getName(), id, properties);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "更新信息出错", e);
        }

    }
    
    @Override
    public void insert(Authorization obj) throws Exception {
        try {
            this.daoFacade.getAuthorizationDao().insert(obj);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "插入信息出错", e);
        }

    }

    @Override
    public void delete(String id) throws Exception {

        try {
            this.daoFacade.getAuthorizationDao().delete(Authorization.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除信息出错", e);
        }
    }
    
    @Override
    public List<Authorization> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {

        List<Authorization> list = null;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AuthorizationDao").get("getPagingList");
        try {
            list = this.daoFacade.getAuthorizationDao().getPagingList(condition, sort, pageCount, page, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取列表出错", e);
        }
        return list;
    }

    @Override
    public Integer getCount(Map<String, Object> condition) throws Exception {

        Integer num = 0;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.AuthorizationDao").get("getCount");
        try {
            num = this.daoFacade.getAuthorizationDao().getCount(condition, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取总数出错", e);
        }
        return num;
    }
    @Override
    public Authorization getById(String id) throws Exception {

    	Authorization cla = null;
        try {
            cla = (Authorization) this.daoFacade.getAuthorizationDao().get(Authorization.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "根据Id查询信息出错", e);
        }
        return cla;
    }

}