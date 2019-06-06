package cn.digitalpublishing.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.springmvc.form.ProductTypeForm;

import com.google.common.base.Strings;

/**
 * 分类信息
 * @author CuiXian
 */
@Controller
@RequestMapping("/pages/productType")
public class ProductTypeController extends BaseController {

	/**
	 * 显示分类首页
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/index")
	public ModelAndView index(ProductTypeForm form) throws Exception {
		String forwardString = "productType/ProductTypeList.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			form.setCode("0");
			model.put("form", form);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
			forwardString = "msg";
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 显示分类首页
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/list")
	public ModelAndView list(ProductTypeForm form) throws Exception {
		String forwardString = "productType/ProductTypeList.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			form.setCode("1");
			model.put("form", form);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
			forwardString = "msg";
		}
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 获取分类列表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	@ResponseBody
	public ProductTypeForm manage(ProductTypeForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			if (!Strings.isNullOrEmpty(form.getName())) {
				condition.put("name", "%" + form.getName() + "%");
			}
			condition.put("code", form.getCode());
			form.setiTotalDisplayRecords(this.productTypeService.getCount(condition));
			form.setiTotalRecords(form.getiTotalDisplayRecords());
			List<ProductType> productTypeList = new ArrayList<ProductType>();
			if (form.getiTotalRecords() > 0) {
				productTypeList = this.productTypeService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}

			form.setAaData(productTypeList);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 显示新增大类
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/add")
	public ModelAndView add(ProductTypeForm form) throws Exception {
		String forwardString = "productType/ProductTypeAdd.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			form.setCode(form.getCode());
		} catch (Exception e) {
			form.setMsg("获取数据出错！");
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 显示新增子类型
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/subtype")
	public ModelAndView subtype(ProductTypeForm form) throws Exception {
		String forwardString = "productType/ProductTypeSubtype.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			form.setId(form.getId());
			form.setCode(form.getCode());
		} catch (Exception e) {
			form.setMsg("获取数据出错！");
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 新增分类
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/save")
	@ResponseBody
	public ProductTypeForm save(ProductTypeForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		ProductType type = form.getObj();
		try {
			List<ProductType> typeList = null;
			condition.put("name", type.getName());
			typeList = this.productTypeService.getList(condition, "");
			if(0 < typeList.size()){
				form.setMsg("类型名称已存在!");
			}else{
				if(null==type.getTreeCode()){
					Map<String, Object> cond = new HashMap<String, Object>();
					cond.put("code", type.getCode());
					List<ProductType> typesList = this.productTypeService.getList(cond, " and t.treeCode.id is NULL");
					if(typesList == null || typesList.isEmpty()){
						type.setOrder(100);
					}else{
						for(int j=0;j<typesList.size();j++){
							int cont = typesList.get(j).getOrder();
							type.setOrder(100+cont);
						}
					}
				}else{
					Map<String, Object> model = new HashMap<String, Object>();
					model.put("treeCode",form.getObj().getTreeCode().getId());
					model.put("code", type.getCode());
					List<ProductType> tyList = this.productTypeService.getList(model, "");
					ProductType types =this.productTypeService.getById(type.getTreeCode().getId()) ;
					if(tyList == null || tyList.isEmpty()){
						type.setOrder(types.getOrder()+1);
					}else{
						for(int i=0;i<tyList.size();i++){
							int cont = tyList.get(i).getOrder();
							type.setOrder(++cont);
						}
					}
				}
				type.setTotal(0);
				this.productTypeService.insert(type);
				form.setMsg("新增类型信息成功!");
				form.setIsSuccess(SUCCESS);
			}
		} catch (Exception e) {
			form.setMsg("新增类型信息出错！");
			form.setIsSuccess(FAILURE);
		}
		return form;
	}
	
	/**
	 * 显示修改分类
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/edit")
	public ModelAndView edit(ProductTypeForm form) throws Exception {
		String forwardString = "productType/ProductTypeEdit.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 修改
			String id = request.getParameter("id");
			if (!Strings.isNullOrEmpty(id)) {
				form.setId(id);
				ProductType productType = this.productTypeService.getById(id);
				form.setObj(productType);
			}
		} catch (Exception e) {
			form.setMsg("获取数据出错！");
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 修改分类
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/editSubmit")
	@ResponseBody
	public ProductTypeForm editSubmit(ProductTypeForm form) throws Exception {
		ProductType type = form.getObj();
		try {
			this.productTypeService.update(type, type.getId(), null);
			form.setMsg("修改类型信息成功!");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setMsg("修改类型信息出错！");
			form.setIsSuccess(FAILURE);
		}
		return form;
	}

	/**
	 * 删除
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/delete")
	public ProductTypeForm delete(ProductTypeForm form) throws Exception {
		try {
			this.productTypeService.delete(form.getId());
			form.setMsg("删除类型信息成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
			form.setIsSuccess(FAILURE);
		}
		return form;
	}

}