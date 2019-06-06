package cn.digitalpublishing.springmvc.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.daxtech.framework.exception.CcsException;
import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Product;
import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.springmvc.form.ProductForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * 产品信息
 * @author CuiXian
 */
@Controller
@RequestMapping("/pages/product")
public class ProductController extends BaseController {
	
	/**
	 * 显示产品首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("product/ProductList.ftl");
	}
	
	/**
	 * 获取产品列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	@ResponseBody
	public ProductForm manage(ProductForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			if (!Strings.isNullOrEmpty(form.getIsbn())) {
				condition.put("isbn", form.getIsbn());
			}
			if (!Strings.isNullOrEmpty(form.getTitle())) {
				condition.put("title", "%" + form.getTitle() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getAuthor())) {
				condition.put("author", "%" + form.getAuthor() + "%");
			}
			form.setiTotalDisplayRecords(this.productService.getCount(condition));
			form.setiTotalRecords(form.getiTotalDisplayRecords());
			List<Product> infoList = new ArrayList<Product>();
			if (form.getiTotalRecords() > 0) {
				infoList = this.productService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(infoList);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		return form;
	}

	/**
	 * 显示新增首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/edit")
	public ModelAndView edit(ProductForm form) throws Exception {
		String forwardString = "product/ProductEdit.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
        try {
        	//获取分类列表
			condition.put("code", "0");
			List<ProductType> typeList = this.productTypeService.getList(condition, " order by t.order ");
			model.put("typeList", typeList);
			// 修改
			String id = request.getParameter("id");
			if (!Strings.isNullOrEmpty(id)) {
				form.setId(id);
				Product product = this.productService.getById(id);
				form.setObj(product);
		    }
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 编辑产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/save")
	@ResponseBody
	public ProductForm save(ProductForm form) throws Exception {
		try {
			Product pi = form.getObj();
			// 当前资源的前缀命名
			String prefix = DateFormatUitl.formatString();
			//资源文件和封面图片存放路径
			String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.RESOURCE).append(File.separator).toString();
			if(form.getFilePic()!=null){
			  //获取图片名称
			  String picName = prefix + form.getFilePic().getOriginalFilename().substring(form.getFilePic().getOriginalFilename().lastIndexOf("."));
			  UploadFileUtil.writeFile(filePath, picName, form.getFilePic().getBytes());
			  pi.setBookCovers(picName);
			}else{
				pi.setBookCovers(form.getObj().getBookCovers());
			}
			if(form.getUpLoadFile()!=null){
			  //获取资源
			  pi.setOriginName(form.getUpLoadFile().getOriginalFilename());
			  String fileName = prefix + form.getUpLoadFile().getOriginalFilename().substring(form.getUpLoadFile().getOriginalFilename().lastIndexOf("."));
			  UploadFileUtil.writeFile(filePath, fileName, form.getUpLoadFile().getBytes());
			  pi.setSystemName(fileName);
			}else{
				pi.setOriginName(form.getObj().getOriginName());
			}
			// 修改
			if (!Strings.isNullOrEmpty(pi.getId())) {
				this.productService.update(pi, pi.getId(), null);
				form.setMsg("修改产品信息成功！");
				// 新增
			} else {
				this.productService.insert(pi);
				//资源总数累计
				Integer acont = null;
				ProductType types = this.productTypeService.getById(pi.getProductType().getId());
				if(null == types.getTreeCode()){
					acont = types.getTotal();
					if(acont==null){
						types.setTotal(new Integer(1));
					}else{
						types.setTotal(++acont);
					}
					this.productTypeService.update(types, types.getId(), null);
				}else{
					ProductType tp = this.productTypeService.getById(types.getTreeCode().getId());
					acont = tp.getTotal();
					if(acont==null){
						tp.setTotal(new Integer(1));
					}else{
						tp.setTotal(++acont);
					}
					this.productTypeService.update(tp, tp.getId(), null);
				}
				form.setMsg("新增产品信息成功!");
			}
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}finally {
			form.setFilePic(null);
			form.setUpLoadFile(null);
		}
		return form;
	}
	
	/**
	 * 显示Excel批量导入页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/upload")
	public ModelAndView upload() throws Exception {
		return new ModelAndView("product/ProductUpload.ftl");
	}
	
	/**
	 * Excel导入产品信息
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/form/uploadAdd")
    @ResponseBody
    public ProductForm uploadAdd(ProductForm form) throws Exception {
        try {
            InputStream is = form.getTxtFile().getInputStream();
            this.productService.upload(is);
            form.setMsg("上传成功!");
            form.setIsSuccess(SUCCESS);
        } catch (Exception e) {
            form.setIsSuccess(FAILURE);
            form.setMsg((e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
        } finally {
			form.setTxtFile(null);
		}
        return form;
    }
	
	/**
	 * 删除产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/delete")
	@ResponseBody
	public ProductForm delete(ProductForm form) throws Exception {
		try {
			Product product = this.productService.getById(form.getId());
			this.productService.delete(form.getId());
			//资源总数累计
			Integer acont = null;
			ProductType types = this.productTypeService.getById(product.getProductType().getId());
			if(null == types.getTreeCode()){
				acont = types.getTotal();
				types.setTotal(--acont);
				this.productTypeService.update(types, types.getId(), null);
			}else{
				ProductType tp = this.productTypeService.getById(types.getTreeCode().getId());
				acont = tp.getTotal();
				tp.setTotal(--acont);
				this.productTypeService.update(tp, tp.getId(), null);
			}
			form.setMsg("删除产品信息成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 显示统计分析页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/analy")
	public ModelAndView index(ProductForm form) throws Exception {
		ModelAndView mv = new ModelAndView();
		String forwardString = "product/Analysis.ftl";
		Map<String, Object> condition = new HashMap<String, Object>();
		List<ProductType> typesList = null;
        try {
        	//获取分类
			condition.put("code", "0");
			typesList = this.productTypeService.getList(condition, " and t.treeCode.id is NULL");
			mv.addObject("typesList", typesList);
			mv.addObject("total", productService.getCount(condition));
			mv.setViewName(forwardString);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		return mv;
	}
	
	/**
	 * 显示XMl导入页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/xml")
	public ModelAndView xml() throws Exception {
		return new ModelAndView("product/ProductXml.ftl");
	}
	
	/**
	 * XMl批量导入产品信息
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/form/uploadXml")
    @ResponseBody
    public ProductForm uploadXml(ProductForm form) throws Exception {
        try {
            InputStream is = form.getXmlFile().getInputStream();
            this.productService.XMLreader(is);
            form.setMsg("元数据解析入库成功!");
            form.setIsSuccess(SUCCESS);
        } catch (Exception e) {
            form.setIsSuccess(FAILURE);
            form.setMsg((e instanceof CcsException) ? ((CcsException) e).getPrompt() : e.getMessage());
        } finally {
			form.setXmlFile(null);
		}
        return form;
    }
    

}