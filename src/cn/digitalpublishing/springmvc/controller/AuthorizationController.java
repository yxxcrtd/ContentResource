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
import cn.digitalpublishing.po.Authorization;
import cn.digitalpublishing.springmvc.form.AuthorizationForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.UploadFileUtil;

import com.google.common.base.Strings;

/**
 * 授权信息
 * @author CuiXian
 */
@Controller
@RequestMapping("/pages/authorization")
public class AuthorizationController extends BaseController {
	
	/**
	 * 显示授权信息首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView index() throws Exception {
		return new ModelAndView("authorization/AuthorizationList.ftl");
	}
	
	/**
	 * 获取授权信息列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	@ResponseBody
	public AuthorizationForm manage(AuthorizationForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		try {
			if (!Strings.isNullOrEmpty(form.getCompany())) {
				condition.put("company", "%" + form.getCompany() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getContractName())) {
				condition.put("contractName", "%" + form.getContractName() + "%");
			}
			if (!Strings.isNullOrEmpty(form.getStartStr())) {
				condition.put("startDate",  form.getStartDate());
			}
			if (!Strings.isNullOrEmpty(form.getStopStr())) {
				condition.put("stopDate",  form.getStopDate());
			}
			form.setiTotalDisplayRecords(this.authorizationService.getCount(condition));
			form.setiTotalRecords(form.getiTotalDisplayRecords());
			List<Authorization> authorizationList = new ArrayList<Authorization>();
			if (form.getiTotalRecords() > 0) {
				authorizationList = this.authorizationService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
			form.setAaData(authorizationList);
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
	public ModelAndView edit(AuthorizationForm form) throws Exception {
		String forwardString = "authorization/AuthorizationEdit.ftl";
		Map<String, Object> model = new HashMap<String, Object>();
        try {
			// 修改
			String id = request.getParameter("id");
			if (!Strings.isNullOrEmpty(id)) {
				form.setId(id);
				Authorization authorization = this.authorizationService.getById(id);
				form.setObj(authorization);
		    }
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}

	/**
	 * 编辑授权信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/save")
	@ResponseBody
	public AuthorizationForm save(AuthorizationForm form) throws Exception {
		try {
			Authorization au = form.getObj();
			// 以时间格式的前缀命名
			String prefix = DateFormatUitl.formatString();
			//授权合同存放路径
			String filePath = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.CONTRACT).append(File.separator).toString();
			if(form.getUploadFile()!=null){
				//获取授权合同附件原名
				au.setOriginName(form.getUploadFile().getOriginalFilename());
				//生成授权合同附件系统名称
				String fileName = prefix + form.getUploadFile().getOriginalFilename().substring(form.getUploadFile().getOriginalFilename().lastIndexOf("."));
				UploadFileUtil.writeFile(filePath, fileName, form.getUploadFile().getBytes());
				au.setSystemName(fileName);
			}else{
				au.setOriginName(form.getObj().getOriginName());
			}
			
			// 修改
			if (!Strings.isNullOrEmpty(au.getId())) {
				this.authorizationService.update(au, au.getId(), null);
				form.setMsg("修改信息成功！");
				// 新增
			} else {
				this.authorizationService.insert(au);
				form.setMsg("新增信息成功!");
			}
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}finally {
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
	public AuthorizationForm delete(AuthorizationForm form) throws Exception {
		try {
			String id = request.getParameter("id");
			Authorization author = this.authorizationService.getById(id);
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.CONTRACT).toString());
			File file = new File(Path+File.separator + author.getSystemName());
		    if(file.exists()){
		        file.delete();
		    }
			this.authorizationService.delete(form.getId());
			form.setMsg("删除信息成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 下载授权合同附件
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String id = request.getParameter("id");
		try {
			Authorization author = this.authorizationService.getById(id);
			// 取得文件存储路径
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.CONTRACT).toString());
			//zip包名
			String name = new String(author.getOriginName().getBytes("gbk"), "iso-8859-1");
			//下载
			header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
			header.set("Content-Disposition", "attachment; filename=" + name);
			data = FileUtils.readFileToByteArray(new File(Path.toString() + File.separator + author.getSystemName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}

}