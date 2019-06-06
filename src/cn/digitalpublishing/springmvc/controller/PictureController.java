package cn.digitalpublishing.springmvc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Picture;
import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.springmvc.form.PictureForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * 图片信息
 * @author CuiXian
 */
@Controller
@RequestMapping("/pages/picture")
public class PictureController extends BaseController {
	
	/**
	 * 显示图片首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index(PictureForm form) throws Exception {
		String forwardString = "picture/PictureList.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
        try {
        	//获取分类列表
			condition.put("code", "1");
			List<ProductType> ptypeList = this.productTypeService.getList(condition, " order by t.order ");
			model.put("ptypeList", ptypeList);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 获取图片列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	@ResponseBody
	public PictureForm manage(PictureForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			if (!Strings.isNullOrEmpty(form.getTitle())) {
				condition.put("title", "%" + form.getTitle() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getKeyword())) {
				condition.put("keyword", "%" + form.getKeyword() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getProductTypeId())) {
				condition.put("productTypeId", form.getProductTypeId());
			}
			form.setiTotalDisplayRecords(this.pictureService.getCount(condition));
			form.setiTotalRecords(form.getiTotalDisplayRecords());
			List<Picture> picList = new ArrayList<Picture>();
			if (form.getiTotalRecords() > 0) {
				picList = this.pictureService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(picList);
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
	public ModelAndView edit(PictureForm form) throws Exception {
		String forwardString = "picture/PictureEdit.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
        try {
        	//获取分类列表
			condition.put("code", "1");
			List<ProductType> typeList = this.productTypeService.getList(condition, " order by t.order ");
			model.put("typeList", typeList);
			// 修改
			String id = request.getParameter("id");
			if (!Strings.isNullOrEmpty(id)) {
				form.setId(id);
				Picture pic = this.pictureService.getById(id);
				form.setObj(pic);
		    }
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 编辑图片信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/save")
	@ResponseBody
	public PictureForm save(PictureForm form) throws Exception {
		try {
			Picture picture = form.getObj();
			// 当前资源的前缀命名
			String prefix = DateFormatUitl.formatString();
			//资源文件和封面图片存放路径
			String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.IMAGE).append(File.separator).toString();
			if(form.getFilePic()!=null){
			  //获取图片名称
			  String picName = prefix + form.getFilePic().getOriginalFilename().substring(form.getFilePic().getOriginalFilename().lastIndexOf("."));
			  UploadFileUtil.writeFile(filePath, picName, form.getFilePic().getBytes());
			  picture.setPic(picName);
			}else{
				picture.setPic(form.getObj().getPic());
			}
			// 修改
			if (!Strings.isNullOrEmpty(picture.getId())) {
				this.pictureService.update(picture, picture.getId(), null);
				form.setMsg("修改图片信息成功！");
				// 新增
			} else {
				this.pictureService.insert(picture);
				form.setMsg("新增图片信息成功!");
			}
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}finally {
			form.setFilePic(null);
		}
		return form;
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/delete")
	@ResponseBody
	public PictureForm delete(PictureForm form) throws Exception {
		try {
			for(int i = 0; i<form.getIds().length; i++){
				this.pictureService.delete(form.getIds()[i]);
			}
			form.setMsg("删除图片信息成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}

}