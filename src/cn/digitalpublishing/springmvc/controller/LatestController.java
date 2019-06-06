package cn.digitalpublishing.springmvc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Latest;
import cn.digitalpublishing.springmvc.form.LatestForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * 最新活动信息
 * @author CuiXian
 */
@Controller
@RequestMapping("/pages/latest")
public class LatestController extends BaseController {
	
	/**
	 * 显示最新活动首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("latest/LatestList.ftl");
	}
	
	/**
	 * 获取最新活动列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	@ResponseBody
	public LatestForm manage(LatestForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			if (!Strings.isNullOrEmpty(form.getTitle())) {
				condition.put("title", "%" + form.getTitle() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getStartStr())) {
				condition.put("startDate",  form.getStartDate());
			}
			if (!Strings.isNullOrEmpty(form.getStopStr())) {
				condition.put("stopDate",  form.getStopDate());
			}
			form.setiTotalDisplayRecords(this.latestService.getCount(condition));
			form.setiTotalRecords(form.getiTotalDisplayRecords());
			List<Latest> latestList = new ArrayList<Latest>();
			if (form.getiTotalRecords() > 0) {
				latestList = this.latestService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(latestList);
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		return form;
	}

	/**
	 * 显示编辑首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/edit")
	public ModelAndView edit(LatestForm form) throws Exception {
		String forwardString = "latest/LatestEdit.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
        try {
			// 修改
			String id = request.getParameter("id");
			if (!Strings.isNullOrEmpty(id)) {
				form.setId(id);
				Latest late = this.latestService.getById(id);
				form.setObj(late);
		    }
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 编辑最新活动信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/save")
	@ResponseBody
	public LatestForm save(LatestForm form) throws Exception {
		try {
			Latest latest = form.getObj();
			// 以时间格式的前缀命名
			String prefix = DateFormatUitl.formatString();
			//最新活动附件和活动图片存放路径
			String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.LATEST).append(File.separator).toString();
			if(form.getFilePic()!=null){
			    //获取图片名称
			    String picName = prefix + form.getFilePic().getOriginalFilename().substring(form.getFilePic().getOriginalFilename().lastIndexOf("."));
			    UploadFileUtil.writeFile(filePath, picName, form.getFilePic().getBytes());
			    latest.setPicture(picName);
			}else{
				latest.setPicture(form.getObj().getPicture());
			}
			if(form.getUploadFile()!=null){
				//获取附件原名
				latest.setOriginName(form.getUploadFile().getOriginalFilename());
				//获取附件名称
				String fileName = prefix + form.getUploadFile().getOriginalFilename().substring(form.getUploadFile().getOriginalFilename().lastIndexOf("."));
				UploadFileUtil.writeFile(filePath, fileName, form.getUploadFile().getBytes());
				latest.setSystemName(fileName);
			}else{
				latest.setOriginName(form.getObj().getOriginName());
			}
			
			// 修改
			if (!Strings.isNullOrEmpty(latest.getId())) {
				this.latestService.update(latest, latest.getId(), null);
				form.setMsg("修改信息成功！");
				// 新增
			} else {
				this.latestService.insert(latest);
				form.setMsg("新增信息成功!");
			}
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}finally {
			form.setFilePic(null);
			form.setUploadFile(null);
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
	public LatestForm delete(LatestForm form) throws Exception {
		try {
			String id = request.getParameter("id");
			Latest late = this.latestService.getById(id);
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.LATEST).toString());
			File file = new File(Path+File.separator + late.getSystemName());
		    if(file.exists()){
		        file.delete();
		    }
			this.latestService.delete(id);
			form.setMsg("删除信息成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 下载活动附件
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String id = request.getParameter("id");
		try {
			Latest late = this.latestService.getById(id);
			// 取得文件存储路径
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.LATEST).toString());
			//zip包名
			String name = new String(late.getOriginName().getBytes("gbk"), "iso-8859-1");
			//下载
			header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
			header.set("Content-Disposition", "attachment; filename=" + name);
			data = FileUtils.readFileToByteArray(new File(Path.toString() + File.separator + late.getSystemName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}

}