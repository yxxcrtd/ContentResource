package cn.digitalpublishing.springmvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.digitalpublishing.config.ProcessQueue;
import cn.digitalpublishing.po.Compress;
import cn.digitalpublishing.po.Product;
import cn.digitalpublishing.po.ProductType;
import cn.digitalpublishing.springmvc.form.CompressForm;
import cn.digitalpublishing.util.DateFormatUitl;
import cn.digitalpublishing.util.FileUtil;

import com.google.common.base.Strings;

/**
 * 产品包信息
 * @author CuiXian
 */
@Controller
@RequestMapping("/pages/compress")
public class CompressController extends BaseController {
	
	/**
	 * 显示产品包首页
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/form/index")
	public ModelAndView indexs() throws Exception {
		return new ModelAndView("compress/CompressList.ftl");
	}
	
	/**
	 * 产品包信息列表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/form/manager", headers = "Accept=application/json")
	public CompressForm manager(CompressForm form) throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		List<Compress> compressList = new ArrayList<Compress>();
		try {
			if (!Strings.isNullOrEmpty(form.getTitle())) {
				condition.put("title", "%"+ form.getTitle() +"%");
			}
			form.setiTotalRecords(this.compressService.getCount(condition));
			form.setiTotalDisplayRecords(form.getiTotalRecords());
			if (0 < form.getiTotalRecords()) {
				compressList = this.compressService.getPagingList(condition, "", form.getiDisplayLength(), form.getiDisplayStart());
			}
		} catch (Exception e) {
			form.setMsg(exMsg(e));
		}
		form.setAaData(compressList);
		return form;
	}
	
	/**
	 * 编辑
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/edit")
	public ModelAndView edit(CompressForm form)throws Exception {
		String forwardString="compress/CompressEdit.ftl"; 
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			//修改
			String id = request.getParameter("id");
			if(!Strings.isNullOrEmpty(id)){
				form.setObj(this.compressService.getById(id));
				form.setId(id);
			}
			} catch (Exception e) {
				form.setMsg(exMsg(e));
		}
		model.put("form", form);
		return new ModelAndView(forwardString, model);
	}
	
	/**
	 * 保存
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/form/save")
	public CompressForm save(CompressForm form) throws Exception {
		Compress compress = form.getObj();
		if(null != form.getIds()){
			// 生成的最终的打包文件名（以时间格式生成）
			String lastCompressFile = DateFormatUitl.formatString();
			
			// 文件压缩后的储存路径
			File compressPath = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.COMPRESS).append(File.separator).toString());
			
			// 拷贝临时文件的存放路径
			File copyfile = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.COMPRESS).append(File.separator).append(lastCompressFile).append(File.separator).toString());
			
			// 判定文件夹是否存在，不存在创建新文件夹
			if (!copyfile.exists()) {
				copyfile.mkdirs();
			}
			
			//开始生成XML-----------------------------------------------------
			Document document = DocumentHelper.createDocument();
			
			//定义一个root作为xml文档的根元素
			Element root = document.addElement("books");
			//循环开始
			for(int i = 0; i<form.getIds().length; i++){
				
				Product product = this.productService.getById(form.getIds()[i]);
				
				// 获得选取文件存储路径
				String path = product.getSystemName();
				
				// 选取的文件，存储的，完整路径
				String temp = new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.RESOURCE).append(File.separator).append(path).toString();
				
				// 将每个文件拷贝到compress下的时间文件夹中
				File copy = new File(temp);
				if (copy.exists()) {
					FileUtils.copyFileToDirectory(copy, copyfile);
				}
				
				Element book = root.addElement("book");
				//在book标签内部添加新的元素，即book的下一级标签
				Element title = book.addElement("title");
				title.setText(String.valueOf("".equals(product.getTitle()) ?  0 : product.getTitle()));
				Element author = book.addElement("author");
				author.setText(String.valueOf("".equals(product.getAuthor()) ?  0 : product.getAuthor()));
				
				//查询分类
                Map<String, Object> condition = new HashMap<String, Object>();
                if (product.getProductType()!=null) {
                	condition.put("id", product.getProductType().getId());
                	List<ProductType> typelist = this.productTypeService.getList(condition, "");
                	if(0<typelist.size()){
                		Element classify = book.addElement("classify");
                		classify.setText(String.valueOf("".equals(typelist.get(0).getName()) ?  0 : typelist.get(0).getName()));
                	}
                }
				
				Element isbn = book.addElement("isbn");
				isbn.setText(String.valueOf("".equals(product.getIsbn()) ?  0 : product.getIsbn()));
				Element price = book.addElement("price");
				price.setText(String.valueOf("".equals(product.getPrice()) ?  0 : product.getPrice()));
				Element publishDate = book.addElement("publishDate");
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				publishDate.setText(String.valueOf("".equals(date.format(product.getDatePublication())) ?  0 : date.format(product.getDatePublication())));
				Element publisher = book.addElement("publisher");
				publisher.setText(String.valueOf("".equals(product.getPublisher()) ?  0 : product.getPublisher()));
				Element edition = book.addElement("edition");
				edition.setText(String.valueOf("".equals(product.getEdition()) ?  0 : product.getEdition()));
				Element page = book.addElement("pages");
				page.setText(String.valueOf("".equals(product.getPageNum()) ?  0 : product.getPageNum()));
				Element frame = book.addElement("frame");
				frame.setText(String.valueOf("".equals(product.getBinding()) ?  0 : product.getBinding()));
				Element format = book.addElement("format");
				format.setText(String.valueOf("".equals(product.getFormat()) ?  0 : product.getFormat()));
				Element sheet = book.addElement("sheet");
				sheet.setText(String.valueOf("".equals(product.getSheet()) ?  0 : product.getSheet()));
				Element size = book.addElement("size");
				size.setText(String.valueOf("".equals(product.getSize()) ?  0 : product.getSize()));
				Element language = book.addElement("language");
				language.setText(String.valueOf("".equals(product.getLanguage()) ?  0 : product.getLanguage()));
				Element cover = book.addElement("cover");
				cover.setText(String.valueOf("".equals(product.getBookCovers()) ?  0 : product.getBookCovers()));
				Element originalName = book.addElement("originalName");
				originalName.setText(String.valueOf("".equals(product.getOriginName()) ?  0 : product.getOriginName()));
				Element systemName = book.addElement("systemName");
				systemName.setText(String.valueOf("".equals(product.getSystemName()) ?  0 : product.getSystemName()));
				Element introduction = book.addElement("introduction");
				introduction.setText(String.valueOf("".equals(product.getRemark()) ?  0 : product.getRemark()));
		    }
			Writer write = new OutputStreamWriter(new FileOutputStream(new File(copyfile + File.separator + lastCompressFile + ".xml")),"UTF-8");
			XMLWriter xmlWriter = new XMLWriter(write,OutputFormat.createPrettyPrint());
			xmlWriter.write(document);
			xmlWriter.close();
			
			// 生成zip压缩包
			new FileUtil().compressedFile(copyfile.toString(), compressPath.toString());
			//存取压缩包系统名
			compress.setPath(lastCompressFile+".zip");
			
			//删除生成的文件夹
			delFolder(copyfile.toString());
		}
		try {
			compress.setStatus(1);
			this.compressService.insert(compress);
			form.setMsg("生成产品压缩包成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/form/delete")
	@ResponseBody
	public CompressForm delete(CompressForm form)throws Exception {
		try{
			String id = request.getParameter("id");
			Compress compress = this.compressService.getById(id);
			File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.COMPRESS).toString());
			File file = new File(Path+File.separator+compress.getPath());
		    if(file.exists()){
		        file.delete();
		    }
			this.compressService.delete(id);
			form.setIsSuccess(SUCCESS);
			form.setMsg("删除成功！");
			
		}
		catch(Exception e){
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 设置允许下载状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/allow")
	@ResponseBody
	public CompressForm allowSta(CompressForm form) throws Exception {
		try {
			for(int i = 0; i<form.getIds().length; i++){
				Compress compress = this.compressService.getById(form.getIds()[i]);
				compress.setStatus(0);
				this.compressService.update(compress, compress.getId(), null);
			}
			form.setMsg("批量设置文件允许下载成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 修改拒绝下载状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/status")
	@ResponseBody
	public CompressForm banSta(CompressForm form) throws Exception {
		try {
			for(int i = 0; i<form.getIds().length; i++){
				Compress compress = this.compressService.getById(form.getIds()[i]);
				compress.setStatus(1);
				this.compressService.update(compress, compress.getId(), null);
			}
			form.setMsg("批量设置不允许下载文件成功！");
			form.setIsSuccess(SUCCESS);
		} catch (Exception e) {
			form.setIsSuccess(FAILURE);
			form.setMsg(exMsg(e));
		}
		return form;
	}
	
	/**
	 * 下载
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/form/download")
	public ResponseEntity<byte[]> downloadFile() throws Exception {
		byte[] data = null;
		HttpHeaders header = new HttpHeaders();
		String id = request.getParameter("id");
		try {
			Compress compress = this.compressService.getById(id);
			if(compress.getStatus()==0){
				// 取得文件存储路径
			    File Path = new File(new StringBuffer(getUploadPath()).append(File.separator).append(ProcessQueue.COMPRESS).toString());
				//ZIP包名
				String name = new String(compress.getTitle().getBytes("gbk"), "iso-8859-1")+".zip";
				//下载
				header.setContentType(MediaType.parseMediaType("application/x-msdownload"));
				header.set("Content-Disposition", "attachment; filename=" + name);
				data = FileUtils.readFileToByteArray(new File(Path.toString()+File.separator+compress.getPath()));
			}else{
				System.err.println("该文件不允许下载");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
	}
	
	/**
	 * 删除新增时产生的临时文件夹
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); //删除空文件夹
		} catch (Exception e) {
			log.error("删除临时文件出错----------------");
		}
	}
	
	/**
	 * 删除新增时产生的临时文件夹里的内容
	 * 
	 * @param request
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public  boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
          return flag;
        }
        if (!file.isDirectory()) {
          return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
           if (path.endsWith(File.separator)) {
              temp = new File(path + tempList[i]);
           } else {
               temp = new File(path + File.separator + tempList[i]);
           }
           try {
                if (temp.isFile()||javax.imageio.ImageIO.read(temp)!=null) {
                    temp.delete();
                }
           } catch (IOException e) {
	                log.error("删除临时文件出错----------------");
           }
           if (temp.isDirectory()) {
              delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
              delFolder(path + "/" + tempList[i]);//再删除空文件夹
              flag = true;
           }
        }
        return flag;
    }
	
}