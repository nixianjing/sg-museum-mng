package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.utils.DateUtil;
import com.example.museum.common.utils.FileUtils;
import com.example.museum.common.utils.word.WordJsoupUtils;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.RelicDTO;
import com.example.museum.dto.RelicScanningDTO;
import com.example.museum.dto.RelicWordDTO;
import com.example.museum.dto.base.CodeTable;
import com.example.museum.dto.base.Response;
import com.example.museum.service.RelicScanningService;
import com.example.museum.service.RelicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * 上传文档
 *
 * @author xianjing.n
 * @date 2019-11-20 02:06
 **/
@RestController
@RequestMapping("uploadFile")
public class UploadFileController extends BaseWebController {
    /**
     * 文物文件临时路径
     */
    @Value("${relic.temporary.path}")
    private String relicTemporaryPath;

    /**
     * 文物文件正式路径
     */
    @Value("${relic.formal.path}")
    private String relicFormalPath;

    /**
     * 文物文件删除存放路径
     */
    @Value("${relic.del.path}")
    private String relicDelPath;

    /**
     * 文件上传返回状态
     */
    private static String RESULT_STATUS = "status";

    /**
     * 文件上传返回URL
     */
    private static String RESULT_FILE_URL = "fileUrl";

    /**
     * 文件上传返回路径
     */
    private static String RESULT_FILE_PATH = "filePath";

    /**
     * 文件上传返回名称
     */
    private static String RESULT_FILE_NAME = "fileName";

    /**
     * 上传文件的原名称
     */
    private static String PRIMARY_NAME = "primaryName";

    @Resource
    private RelicService relicService;

    @Resource
    private RelicScanningService relicScanningService;

    /**
     * 上传文物word文档
     *
     * @param file
     * @param projectName
     * @param projectTime
     * @return
     */
    @RequestMapping("/uploadRelicWord")
    @ResponseBody
    public Response uploadRelicWord(@RequestParam("file") MultipartFile file, String projectName, String projectTime) {
        AdminUserDTO userToken = this.getUserToken();
        Response response = new Response<>(CodeTable.SUCCESS);
        /** 参数验证 **/
        if (StringUtils.isBlank(projectName) || StringUtils.isBlank(projectTime)) {
            response.setCode(CodeTable.ERROR.getCode());
            response.setMessage("请输入项目名称和项目时间");
            return response;
        }
        /** 文件上传 **/
        Map<String, Object> result = FileUtils.uploadFileMethod(file, relicTemporaryPath);
        if (!(boolean) result.get(RESULT_STATUS)) {
            response.setCode(CodeTable.FILE_ERROR.getCode());
            response.setMessage("word上传失败！请稍候重试。");
            return response;
        }
        String wordUrl = result.get(RESULT_FILE_URL).toString();
        String wordPath = result.get(RESULT_FILE_PATH).toString();
        String wordName = result.get(RESULT_FILE_NAME).toString();
        String primaryName = result.get(PRIMARY_NAME).toString();
        /** 解析word **/
        RelicWordDTO relicWordDTO = WordJsoupUtils.getRelicWordDTO(wordPath, wordName);
        if (Objects.isNull(relicWordDTO)) {
            return new Response<>(CodeTable.WORD_ERROR);
        }
        /** 验证文物是否存在 **/
        RelicDTO relicDTO = relicService.getRelicByName(relicWordDTO.getRelicName());
        if (Objects.nonNull(relicDTO)) {
            return new Response<>(CodeTable.WORD_ERROR);
        }
        relicWordDTO.setProjectName(projectName);
        relicWordDTO.setProjectTime(projectTime);
        String path = relicFormalPath + relicWordDTO.getRelicName() + "/" + relicWordDTO.getProjectTime() + "/";
        String name = primaryName;
        String url = path + name;
        if (FileUtils.transferFile(wordUrl, path, name)) {
            relicWordDTO.setWordUrl(url);
            relicWordDTO.setWordPath(path);
            relicWordDTO.setWordName(name);
            if (relicService.saveRelicWord(relicWordDTO, userToken.getId())) {
                FileUtils.deleteFile(wordUrl);
                return new Response<>(CodeTable.SUCCESS);
            }
        }
        return new Response<>(CodeTable.WORD_ERROR);
    }


    /**
     * 上传文物修复记录word文档
     *
     * @param file
     * @param relicId
     * @param projectName
     * @param projectTime
     * @return
     */
    @RequestMapping("/uploadScanningWord")
    @ResponseBody
    public Response uploadScanningWord(@RequestParam("file") MultipartFile file, Long relicId, String projectName, String projectTime) {
        AdminUserDTO userToken = this.getUserToken();
        Response response = new Response<>(CodeTable.SUCCESS);
        /** 参数验证 **/
        if (Objects.isNull(relicId) || StringUtils.isBlank(projectName) || StringUtils.isBlank(projectTime)) {
            response.setCode(CodeTable.ERROR.getCode());
            response.setMessage("请输入项目名称和项目时间");
            return response;
        }
        /** 文件上传 **/
        Map<String, Object> result = FileUtils.uploadFileMethod(file, relicTemporaryPath);
        if (!(boolean) result.get(RESULT_STATUS)) {
            response.setCode(CodeTable.FILE_ERROR.getCode());
            response.setMessage("word上传失败！请稍候重试。");
            return response;
        }
        String wordUrl = result.get(RESULT_FILE_URL).toString();
        String wordPath = result.get(RESULT_FILE_PATH).toString();
        String wordName = result.get(RESULT_FILE_NAME).toString();
        String primaryName = result.get(PRIMARY_NAME).toString();
        /** 判断word是否上传过 **/
        RelicScanningDTO relicScanningDTO = relicScanningService.getRelicScanningByProjectNameAndTime(relicId, projectName, projectTime);
        if (Objects.nonNull(relicScanningDTO)) {
            return new Response<>(CodeTable.WORD_ERROR);
        }
        /** 解析word **/
        RelicWordDTO relicWordDTO = WordJsoupUtils.getRelicWordDTO(wordPath, wordName);
        if (Objects.isNull(relicWordDTO)) {
            return new Response<>(CodeTable.WORD_ERROR);
        }
        relicWordDTO.setProjectName(projectName);
        relicWordDTO.setProjectTime(projectTime);
        String path = relicFormalPath + relicWordDTO.getRelicName() + "/" + relicWordDTO.getProjectTime() + "/";
        String name = primaryName;
        String url = path + name;
        relicWordDTO.setWordUrl(url);
        relicWordDTO.setWordPath(path);
        relicWordDTO.setWordName(name);
        /** word复制到正式目录下 **/
        if (FileUtils.transferFile(wordUrl, path, name)) {
            if (relicScanningService.saveRelicScanningWord(relicId, relicWordDTO, userToken.getId())) {
                FileUtils.deleteFile(wordUrl);
                return new Response<>(CodeTable.SUCCESS);
            }
        }
        return new Response<>(CodeTable.WORD_ERROR);
    }


    /**
     * 上传word文档
     *
     * @param file
     * @return
     */
    @RequestMapping("/uploadWord")
    @ResponseBody
    public Response<Map<String, Object>> uploadWord(@RequestParam("file") MultipartFile file) {
        System.out.println("文件名：" + file.getOriginalFilename());
        Map<String, Object> result = FileUtils.uploadFileMethod(file, relicTemporaryPath);
        return getResult(result);
    }

    private static Response<Map<String, Object>> getResult(Map<String, Object> result) {
        Response<Map<String, Object>> response = new Response<>(CodeTable.SUCCESS);
        if ((boolean) result.get(RESULT_STATUS)) {
            response.setData(result);
            return response;
        }
        response.setCode(CodeTable.FILE_ERROR.getCode());
        response.setMessage(CodeTable.FILE_ERROR.getMessage());
        return response;
    }

}
