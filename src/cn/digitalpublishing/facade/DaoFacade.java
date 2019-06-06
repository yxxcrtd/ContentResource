package cn.digitalpublishing.facade;

import cn.digitalpublishing.dao.AuthorizationDao;
import cn.digitalpublishing.dao.CompressDao;
import cn.digitalpublishing.dao.CopyrightDao;
import cn.digitalpublishing.dao.LatestDao;
import cn.digitalpublishing.dao.PictureDao;
import cn.digitalpublishing.dao.ProductDao;
import cn.digitalpublishing.dao.ProductTypeDao;

/**
 * @author Stone
 */
public class DaoFacade {

	/**
	 * 图书信息
	 */
	ProductDao productDao;
	
	/**
	 * 分类信息
	 */
	ProductTypeDao productTypeDao;
	
	/**
	 * 图片信息
	 */
	PictureDao pictureDao;
	
	/**
	 * 最新活动信息
	 */
	LatestDao latestDao;
	
	/**
	 * 作者版权信息
	 */
	CopyrightDao copyrightDao;
	
	/**
	 * 授权信息
	 */
	AuthorizationDao authorizationDao;
	
	/**
	 * 产品包
	 */
	CompressDao compressDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ProductTypeDao getProductTypeDao() {
		return productTypeDao;
	}

	public void setProductTypeDao(ProductTypeDao productTypeDao) {
		this.productTypeDao = productTypeDao;
	}

	public PictureDao getPictureDao() {
		return pictureDao;
	}

	public void setPictureDao(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	public LatestDao getLatestDao() {
		return latestDao;
	}

	public void setLatestDao(LatestDao latestDao) {
		this.latestDao = latestDao;
	}

	public CopyrightDao getCopyrightDao() {
		return copyrightDao;
	}

	public void setCopyrightDao(CopyrightDao copyrightDao) {
		this.copyrightDao = copyrightDao;
	}

	public AuthorizationDao getAuthorizationDao() {
		return authorizationDao;
	}

	public void setAuthorizationDao(AuthorizationDao authorizationDao) {
		this.authorizationDao = authorizationDao;
	}

	public CompressDao getCompressDao() {
		return compressDao;
	}

	public void setCompressDao(CompressDao compressDao) {
		this.compressDao = compressDao;
	}
	
}