package cn.digitalpublishing.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Strings;

import cn.com.daxtech.framework.bean.HqlBean;
import cn.com.daxtech.framework.exception.CcsException;
import cn.com.daxtech.framework.util.hql.HqlBeanCacheUtil;
import cn.digitalpublishing.po.Product;
import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.service.ProductService;
import cn.digitalpublishing.service.ProductTypeService;
import cn.digitalpublishing.util.DateFormatUitl;

/**
 * @name 产品信息
 * @Autor CuiXian
 */
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

    @Override
    public void update(Product obj, String id, String[] properties) throws Exception {

        try {
            this.daoFacade.getProductDao().update(obj, Product.class.getName(), id, properties);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "更新信息出错", e);
        }

    }
    
    @Override
    public void insert(Product obj) throws Exception {
        try {
            this.daoFacade.getProductDao().insert(obj);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "插入信息出错", e);
        }

    }

    @Override
    public void delete(String id) throws Exception {

        try {
            this.daoFacade.getProductDao().delete(Product.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "删除信息出错", e);
        }
    }
    
    @Override
    public List<Product> getPagingList(Map<String, Object> condition, String sort, Integer pageCount, Integer page) throws Exception {

        List<Product> list = null;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ProductDao").get("getPagingList");
        try {
            list = this.daoFacade.getProductDao().getPagingList(condition, sort, pageCount, page, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取列表出错", e);
        }
        return list;
    }

    @Override
    public Integer getCount(Map<String, Object> condition) throws Exception {

        Integer num = 0;
        HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ProductDao").get("getCount");
        try {
            num = this.daoFacade.getProductDao().getCount(condition, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "获取总数出错", e);
        }
        return num;
    }
    @Override
    public Product getById(String id) throws Exception {

        Product cla = null;
        try {
            cla = (Product) this.daoFacade.getProductDao().get(Product.class.getName(), id);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "根据Id查询信息出错", e);
        }
        return cla;
    }
    @Override
    public List<Product> getList(Map<String, Object> condition, String sort) throws Exception {
        List<Product> list = null;
        try {
            HqlBean hqlBean = HqlBeanCacheUtil.gethqlBeanCache().get("cn.digitalpublishing.dao.ProductDao").get("getList");
            list = this.daoFacade.getProductDao().getList(condition, sort, hqlBean);
        } catch (Exception e) {
            throw new CcsException((e instanceof CcsException) ? ((CcsException) e).getPrompt() : "根据条件获取信息出错", e);
        }
        return list;
    }
    @Override
    public void upload(InputStream inputStream) throws Exception {
        try {
            XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
            XSSFSheet sheets = xwb.getSheetAt(0);
            //循环解析Excel
            for (int i = sheets.getFirstRowNum(); i <= sheets.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheets.getRow(i);
                if (row != null) {
                    XSSFCell title = row.getCell(0);
                    XSSFCell author = row.getCell(1);
                    XSSFCell isbn = row.getCell(2);
                    XSSFCell price = row.getCell(3);
                    XSSFCell datePublication = row.getCell(4);
                    XSSFCell publisher = row.getCell(5);
                    XSSFCell pageNum = row.getCell(6);
                    XSSFCell format = row.getCell(7);
                    XSSFCell edition = row.getCell(8);
                    XSSFCell binding = row.getCell(9);
                    XSSFCell weight = row.getCell(10);
                    XSSFCell language = row.getCell(11);
                    XSSFCell bookCovers = row.getCell(12);
                    XSSFCell originName = row.getCell(13);
                    XSSFCell systemName = row.getCell(14);
                    XSSFCell remark = row.getCell(15);
                    XSSFCell sheet = row.getCell(16);
                    XSSFCell size = row.getCell(17);
                    
                    
                    //插入产品信息
                    Product product = new Product();
                    product.setTitle(title.toString());
                    product.setAuthor(author.toString());
                    product.setIsbn(isbn.toString());
                    product.setPrice(new BigDecimal(price.toString()));
                    //product.setDatePublication(datePublication.getDateCellValue()); //数据库导出格式YYYY/MM/DD
                    //product.setDatePublication(DateFormatUitl.stringToDatetime(datePublication.toString()));  //日期格式YYYY-MM-DD
                    product.setDatePublication(DateFormatUitl.stringToDatetime(datePublication.toString(), "YYYY/MM/dd"));//文本格式YYYY/MM/dd
                    product.setPublisher(publisher.toString());
                    product.setPageNum(Integer.valueOf(pageNum.toString()));
                    product.setFormat(format.toString());
                    product.setEdition(edition.toString());
                    product.setBinding(binding.toString());
                    product.setWeight(weight.toString());
                    product.setLanguage(language.toString());
                    product.setBookCovers(bookCovers.toString());
                    product.setOriginName(originName.toString());
                    product.setSystemName(systemName.toString());
                    product.setRemark(remark.toString());
                    product.setSheet(sheet.toString());
                    product.setSize(size.toString());
                    this.daoFacade.getProductDao().insert(product);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CcsException("解析Excel出错！");
        }
    }
    
    @Autowired
    @Qualifier("productTypeService")
    protected ProductTypeService productTypeService;
    
    /**
     *解析Document对象里的内容
     */
    public void XMLreader(InputStream inputStream) throws Exception {
        try {
        	DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            NodeList nl=doc.getElementsByTagName("book");
            int len=nl.getLength();
            for(int i=0;i<len;i++){
                Element book=(Element)nl.item(i);
                Node title = book.getElementsByTagName("title").item(0);
                Node author = book.getElementsByTagName("author").item(0);
                Node type = book.getElementsByTagName("classify").item(0);
                Node isbn = book.getElementsByTagName("isbn").item(0);
                Node price = book.getElementsByTagName("price").item(0);
                Node datePublication = book.getElementsByTagName("publishDate").item(0);
                Node publisher = book.getElementsByTagName("publisher").item(0);
                Node edition = book.getElementsByTagName("edition").item(0);
                Node pageNum = book.getElementsByTagName("pages").item(0);
                Node binding = book.getElementsByTagName("frame").item(0);
                Node format = book.getElementsByTagName("format").item(0);
                Node sheet = book.getElementsByTagName("sheet").item(0);
                Node size = book.getElementsByTagName("size").item(0);
                Node language = book.getElementsByTagName("language").item(0);
                Node bookCovers = book.getElementsByTagName("cover").item(0);
                Node originalName = book.getElementsByTagName("originalName").item(0);
                Node systemName = book.getElementsByTagName("systemName").item(0);
                Node remark = book.getElementsByTagName("introduction").item(0);

               
            	//插入产品信息
                Product product = new Product();
                
                //查询分类
                Map<String, Object> condition = new HashMap<String, Object>();
                if(!Strings.isNullOrEmpty(type.getFirstChild().getNodeValue())){
                	condition.put("name", type.getFirstChild().getNodeValue());
                    List<ProductType> typelist = this.productTypeService.getList(condition, "");
                    if(0<typelist.size()){
                    	product.setProductType(typelist.get(0));
                    }
                }
                product.setTitle(title.getFirstChild().getNodeValue());
                product.setAuthor(author.getFirstChild().getNodeValue());
                product.setIsbn(isbn.getFirstChild().getNodeValue());
                product.setPrice(new BigDecimal(price.getFirstChild().getNodeValue()));
                product.setDatePublication(DateFormatUitl.stringToDate(datePublication.getFirstChild().getNodeValue()));  //日期格式YYYY-MM-DD
                product.setPublisher(String.valueOf("".equals(publisher.getFirstChild().getNodeValue()) ?  0 : publisher.getFirstChild().getNodeValue()));
                product.setPageNum(Integer.valueOf(pageNum.getFirstChild().getNodeValue()));
                product.setFormat(String.valueOf("".equals(format.getFirstChild().getNodeValue()) ?  0 : format.getFirstChild().getNodeValue()));
                product.setSize(String.valueOf("".equals(size.getFirstChild().getNodeValue()) ?  0 : size.getFirstChild().getNodeValue()));
                product.setEdition(String.valueOf("".equals(edition.getFirstChild().getNodeValue()) ?  0 : edition.getFirstChild().getNodeValue()));
                product.setBinding(String.valueOf("".equals(binding.getFirstChild().getNodeValue()) ?  0 : binding.getFirstChild().getNodeValue()));
                product.setSheet(String.valueOf("".equals(sheet.getFirstChild().getNodeValue()) ?  0 : sheet.getFirstChild().getNodeValue()));
                product.setLanguage(String.valueOf("".equals(language.getFirstChild().getNodeValue()) ?  0 : language.getFirstChild().getNodeValue()));
                product.setBookCovers(String.valueOf("".equals(bookCovers.getFirstChild().getNodeValue()) ?  0 : bookCovers.getFirstChild().getNodeValue()));
                product.setOriginName(String.valueOf("".equals(originalName.getFirstChild().getNodeValue()) ?  0 : originalName.getFirstChild().getNodeValue()));
                product.setSystemName(String.valueOf("".equals(systemName.getFirstChild().getNodeValue()) ?  0 : systemName.getFirstChild().getNodeValue()));
                product.setRemark(String.valueOf("".equals(remark.getFirstChild().getNodeValue()) ?  0 : remark.getFirstChild().getNodeValue()));
                this.daoFacade.getProductDao().insert(product);
            }
        } catch (Exception e) {
        	throw new CcsException("解析xml出错！");
        }
    }

}