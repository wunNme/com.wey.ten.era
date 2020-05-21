package com.wey.ten.era.home.controller;

import com.wey.ten.era.home.service.IAdRotationService;
import com.wey.ten.era.home.service.impl.AdRotationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wey.ten.era.common.utils.ErrorCode;
import com.wey.ten.era.common.utils.ResultObject;

/**
 * 模型控制层
 * @author lenovo
 *
 */
@Api(value = "AdRotationController", description = "模型接口")
@RestController
@RequestMapping(value = "/home")
public class AdRotationController {

	@Autowired
	private IAdRotationService iAdRotationService ;

//
//
//	@ApiOperation(value="模型详情", notes="模型详情")
//	@GetMapping(value = "detail/{id}")
//	public ResultObject detail(@PathVariable(name="id") String id){
//		ResultObject resultObject = null;
//		if (StringUtils.isEmpty(id)) {
//			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
//		} else {
//			resultObject.setData(iAdRotationService.getById(AdRotation.class));
//		}
//		return resultObject;
//	}
//	@ApiOperation(value="新建模型", notes="新建模型")
//	@PostMapping(value = "save")
//	public ResultObject saveIndustryModel(@RequestBody IndustryModel industryModel){
//		ResultObject resultObject = null;
//		try {
//			resultObject = industryModelService.saveIndustryModel(industryModel);
//		} catch (Exception e) {
//			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
//		}
//		return resultObject;
//	}
//
//	@ApiOperation(value="修改模型", notes="修改模型")
//	@PostMapping(value = "update")
//	public ResultObject updateIndustryModel(@RequestBody IndustryModel industryModel){
//		ResultObject resultObject = null;
//		try {
//			resultObject = industryModelService.updateIndustryModel(industryModel);
//		} catch (Exception e) {
//			resultObject=new ResultObject(ErrorCode.PARAMS_ERROR);
//		}
//		return resultObject;
//	}
//	@ApiOperation(value="删除模型", notes="删除模型")
//	@GetMapping(value = "del/{id}")
//	public ResultObject delIndustryModel(@PathVariable(name="id") String id){
//		ResultObject resultObject = null;
//		if (StringUtils.isEmpty(id)) {
//			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
//		} else {
//			resultObject = industryModelService.delIndustryModel(Integer.valueOf(id));
//		}
//		return resultObject;
//	}
	@ApiOperation(value="条件查询模型", notes="条件查询模型支持分页，支持一下字段")
	@PostMapping(value = "/queryIndustryModel")
	public ResultObject queryIndustryModel(){
		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
//		industryModel.setStartRow((industryModel.getCurrentPage()-1)*industryModel.getPageSize());
//		industryModel.setEndRow(industryModel.getPageSize());
		resultObject.setData(iAdRotationService	.list());
		return resultObject;
	}

	@ApiOperation(value = "广告轮播查询")
	@PostMapping(value = "/select")
	public ResponseEntity<ResultObject> select(){
		AdRotationServiceImpl adRotationService = new AdRotationServiceImpl();
		ResultObject object = new ResultObject(ErrorCode.SUCCESS);
		try{
			object = adRotationService.select();
		}catch (Exception e){
			object.setErrorCode(e.getMessage());
		}
		return new ResponseEntity<ResultObject>(object, HttpStatus.OK);
	}
//	@ApiOperation(value="条件查询模型", notes="条件查询模型支持分页，支持一下字段")
//	@PostMapping(value = "queryIndustryModel")
//	public ResultObject queryIndustryModel(@RequestBody Page<IndustryModel>  page){
//		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
////		industryModel.setStartRow((industryModel.getCurrentPage()-1)*industryModel.getPageSize());
////		industryModel.setEndRow(industryModel.getPageSize());
//		resultObject.setData(industryModelService.page(page));
//		return resultObject;
//	}

//	/**
//	 * 浏览量
//	 * @param id
//	 * @return
//	 */
//	@ApiOperation(value="新增浏览量", notes="新增浏览量")
//	@GetMapping(value = "addPageviews/{id}")
//	public ResultObject addPageviews(@PathVariable(name="id") String id){
//		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
//		if (StringUtils.isEmpty(id)) {
//			return resultObject = new ResultObject(ErrorCode.PARAMS_ERROR);
//		} else {
//			industryModelService.addPageviews(id);
//		}
//		return resultObject;
//	}
//	@ApiOperation(value="模型上/下架", notes="模型上/下架")
//	@PostMapping(value = "updateUseStatus")
//	public ResultObject updateUseStatus(@RequestBody Map<String, Integer> paramsMap){
//		ResultObject resultObject = new ResultObject(ErrorCode.SUCCESS);
//		industryModelService.updateUseStatus(paramsMap);
//		return resultObject;
//	}

//	@SneakyThrows
//	@ResponseBody
//	@RequestMapping(value = "/path",method = RequestMethod.POST)
//	public void upload(@RequestParam("image") MultipartFile file, HttpServletRequest request, HttpServletResponse response){
//		String path = null;
//		String json = "";
//		if(file!=null){
//			String type = null;
//			String fileName = file.getOriginalFilename();
//			System.out.println("上传的文件原名称:" + fileName);
//			type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
//			if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
//				if (type != null) {
//					String realPath = request.getSession().getServletContext().getRealPath("/");
//					String trueFileName = String.valueOf(System.currentTimeMillis()) + "." + type;
//					path = realPath + trueFileName;
//					System.out.println("存放图片的路径：" + path);
//					file.transferTo(new File(path));
//					System.out.println("文件成功上传到指定目录下");
//				}
//				json = "{\"res\":1}";
//			}else{
//				System.out.println("不是我们想要的文件类型，请按要求重新上传");
//				json = "{\"res\":0}";
//			}
//		}else{
//			System.out.println("没有找到相对应的文件");
//			json = "{\"res\":0}";
//		}
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().print(json);
//	}
}
