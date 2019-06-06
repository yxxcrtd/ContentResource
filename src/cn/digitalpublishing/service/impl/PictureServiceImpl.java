package cn.digitalpublishing.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Picture;
import cn.digitalpublishing.service.PictureService;

/**
 * @name 图片信息
 * @Autor CuiXian
 */
public class PictureServiceImpl extends BaseServiceImpl implements PictureService {

    @Override
    public void update(Picture obj, String id, String[] properties) throws Exception {

        try {
            this.daoFacade.getPictureDao().update(obj, Picture.class.getName(), id, properties);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "更新信息出错", e);
        }

    }
    
    @Override
    public void insert(Picture obj) throws Exception {
        try {
            this.daoFacade.getPictureDao().insert(obj);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "插入信息出错", e);
        }

    }

    @Override
    public void delete(String id) throws Exception {

        try {
            this.daoFacade.getPictureDao().delete(Picture.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除信息出错", e);
        }
    }
    
    @Override
    public List<Picture> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {

        List<Picture> list = null;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PictureDao").get("getPagingList");
        try {
            list = this.daoFacade.getPictureDao().getPagingList(condition, sort, pageCount, page, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取列表出错", e);
        }
        return list;
    }

    @Override
    public Integer getCount(Map<String, Object> condition) throws Exception {

        Integer num = 0;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.PictureDao").get("getCount");
        try {
            num = this.daoFacade.getPictureDao().getCount(condition, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取总数出错", e);
        }
        return num;
    }
    @Override
    public Picture getById(String id) throws Exception {

    	Picture cla = null;
        try {
            cla = (Picture) this.daoFacade.getPictureDao().get(Picture.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "根据Id查询信息出错", e);
        }
        return cla;
    }

}