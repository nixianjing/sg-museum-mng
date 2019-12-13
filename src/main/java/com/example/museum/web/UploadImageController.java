package com.example.museum.web;

import com.example.museum.common.BaseWebController;
import com.example.museum.common.enums.ImgTypeEnum;
import com.example.museum.common.utils.DateUtil;
import com.example.museum.common.utils.FileUtils;
import com.example.museum.dto.AdminUserDTO;
import com.example.museum.dto.RelicDTO;
import com.example.museum.dto.RelicScanningDTO;
import com.example.museum.dto.RelicScanningImgDTO;
import com.example.museum.dto.base.CodeTable;
import com.example.museum.dto.base.Response;
import com.example.museum.service.RelicScanningImgService;
import com.example.museum.service.RelicScanningService;
import com.example.museum.service.RelicService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 图片上传
 *
 * @author xianjing.n
 * @date 2019-11-20 02:06
 **/
@RestController
@RequestMapping("uploadImage")
public class UploadImageController extends BaseWebController {
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
     * 变更存放路径
     */
    @Value("${relic.update.path}")
    private String relicUpdatePath;

    /**
     * 图片压缩存放路径
     */
    @Value("${relic.img.compress.path}")
    private String relicImgCompressPath;

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

    public static final double IMG_SIZE = new Double("102400000000000000");

    @Resource
    private RelicService relicService;

    @Resource
    private RelicScanningService relicScanningService;

    @Resource
    private RelicScanningImgService relicScanningImgService;

    /**
     * 上传文物修复扫描文件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "uploadRelicScanningImg", method = RequestMethod.POST)
    public Response<Map<String, Object>> uploadRelicScanningImg(@RequestParam(value = "file") MultipartFile file, Long relicScanningId, String typeName) {
        AdminUserDTO userToken = this.getUserToken();
        double len = file.getSize();
        if (len > IMG_SIZE) {
            return new Response<>(CodeTable.EXCEPTION, "图片上传失败!图片太大,请压缩图片在上传。");
        }
        /** 查询文物信息 **/
        RelicScanningDTO relicScanningDTO = relicScanningService.getRelicScanningById(relicScanningId);
        RelicDTO relicDTO = relicService.getRelicById(relicScanningDTO.getRelicId());
        if (Objects.equals(typeName, "文物LOG")) {
            return relicImg(file, userToken, relicDTO);
        }
        return relicScanningImg(file, userToken, relicScanningDTO, relicDTO, typeName);
    }


    private Response<Map<String, Object>> relicImg(MultipartFile file, AdminUserDTO userToken, RelicDTO relicDTO) {
        String imgPath = relicFormalPath + relicDTO.getRelicName() + "/";
        String imgName = file.getOriginalFilename();
        String imgUrl = imgPath + imgName;
        Response<Map<String, Object>> response = new Response<>(CodeTable.SUCCESS);
        try {
            Map<String, Object> rs = FileUtils.uploadFileMethod(file, imgPath);
            if ((boolean) rs.get(RESULT_STATUS)) {
                /** 上传成功 **/
                if (StringUtils.isNotBlank(relicDTO.getImgUrl())) {
                    String delPath = relicUpdatePath + relicDTO.getRelicName() + "/";
                    String delName = DateUtil.dateFormat(new Date(), DateUtil.F_DATE_YYYYMMDDHHMMSS) + "_" + userToken.getName() + "_" + imgName;
                    if (FileUtils.transferFile(relicDTO.getImgUrl(), delPath, delName)) {
                        FileUtils.deleteFile(relicDTO.getImgUrl());
                    }
                }
                if (FileUtils.transferFile(rs.get(RESULT_FILE_URL).toString(), imgPath, imgName)) {
                    FileUtils.deleteFile(rs.get(RESULT_FILE_URL).toString());
                }
                String compressImgUrl = uploadImgThumbnail(file, imgUrl);
                if (!relicService.updateRelicImg(relicDTO.getId(), imgUrl, compressImgUrl, userToken.getId())) {
                    return new Response<>(CodeTable.EXCEPTION);
                }
            } else {
                return new Response<>(CodeTable.EXCEPTION);
            }
        } catch (Exception e) {
            return new Response<>(CodeTable.EXCEPTION);
        }
        return response;
    }

    private Response<Map<String, Object>> relicScanningImg(MultipartFile file, AdminUserDTO userToken, RelicScanningDTO relicScanningDTO, RelicDTO relicDTO, String typeName) {
        Response<Map<String, Object>> response = new Response<>(CodeTable.SUCCESS);
        /** 图片名称 **/
        String imgName = file.getOriginalFilename();
        /** 判断是否图片 **/
        Integer isImg = ImgTypeEnum.IMG_TYPE_NO.getCode();
        if (isImg(imgName)) {
            isImg = ImgTypeEnum.IMG_TYPE_YES.getCode();
        }
        try {
            /** 文件上传到临时文件目录下 **/
            Map<String, Object> rs = FileUtils.uploadFileMethod(file, relicTemporaryPath);
            if ((boolean) rs.get(RESULT_STATUS)) {
                /**
                 * 临时目录文件
                 */
                String reImgUrl = rs.get(RESULT_FILE_URL).toString();
                /** 正式文件目录 **/
                String imgPath = relicFormalPath + relicDTO.getRelicName() + "/" + relicScanningDTO.getProjectTime() + "/" + typeName + "/";
                String ImgUrl = imgPath + imgName;
                RelicScanningImgDTO relicScanningImgDTO = relicScanningImgService.getRelicScanningImg(relicScanningDTO.getId(), typeName, imgName);
                String compressImgUrl = null;
                if (Objects.equals(isImg, ImgTypeEnum.IMG_TYPE_YES.getCode())) {
                    compressImgUrl = uploadImgThumbnail(file, ImgUrl);
                }
                if (Objects.isNull(relicScanningImgDTO)) {
                    relicScanningImgDTO = new RelicScanningImgDTO();
                    relicScanningImgDTO.setCreateUserId(userToken.getId());
                    relicScanningImgDTO.setUpdateUserId(userToken.getId());
                    relicScanningImgDTO.setRelicScanningId(relicScanningDTO.getId());
                    relicScanningImgDTO.setImgUrl(ImgUrl);
                    relicScanningImgDTO.setImgName(imgName);
                    relicScanningImgDTO.setImgPath(imgPath);
                    relicScanningImgDTO.setType(typeName);
                    relicScanningImgDTO.setIsImg(isImg);
                    relicScanningImgDTO.setCompressImgUrl(compressImgUrl);
                    if (relicScanningImgService.saveRelicScanningImg(relicScanningImgDTO)) {
                        if (FileUtils.transferFile(reImgUrl, imgPath, imgName)) {
                            FileUtils.deleteFile(reImgUrl);
                        }
                    }
                } else {
                    String updatePath = relicUpdatePath + relicDTO.getRelicName() + "/" + relicScanningDTO.getProjectTime() + "/" + typeName + "/";
                    String updateName = DateUtil.dateFormat(new Date(), DateUtil.F_DATE_YYYYMMDDHHMMSS) + "_" + userToken.getName() + "_" + relicScanningImgDTO.getId() + "_" + relicScanningImgDTO.getImgName();
                    if (FileUtils.transferFile(reImgUrl, updatePath, updateName)) {
                        /** 删除原来的 **/
                        FileUtils.deleteFile(reImgUrl);
                        /** 复制到正式 **/
                        if (FileUtils.transferFile(reImgUrl, imgPath, imgName)) {
                            /** 删除临时的 **/
                            FileUtils.deleteFile(reImgUrl);
                        }
                    }
                }
                rs.put(RESULT_FILE_PATH, imgPath);
                rs.put(RESULT_FILE_URL, ImgUrl);
                rs.put(RESULT_FILE_NAME, imgName);
                response.setData(rs);
            } else {
                return new Response<>(CodeTable.EXCEPTION);
            }
        } catch (Exception e) {
            return new Response<>(CodeTable.EXCEPTION);
        }
        return response;
    }


    /**
     * 图片压缩
     *
     * @param imageFile
     */
    private String uploadImgThumbnail(MultipartFile imageFile, String filePathName) {
        try {
            String uuid = UUID.randomUUID().toString();
            //拼接后台文件名称
            String thumbnailPathName = uuid + "."
                    + FilenameUtils.getExtension(imageFile.getOriginalFilename());
            //added by yangkang 2016-3-30 去掉后缀中包含的.png字符串
            if (thumbnailPathName.contains(".png")) {
                thumbnailPathName = thumbnailPathName.replace(".png", ".jpg");
            }
            long size = imageFile.getSize();
            double scale = 1.0d;
            if (size >= 200 * 1024) {
                if (size > 0) {
                    scale = (200 * 1024f) / size;
                }
            }
            //拼接文件路劲
            String thumbnailFilePathName = relicImgCompressPath + File.separator + thumbnailPathName;
            if (size < 200 * 1024) {
                Thumbnails.of(filePathName).scale(0.4f).outputFormat("jpg").toFile(thumbnailFilePathName);
            } else {
                Thumbnails.of(filePathName).scale(0.4f).outputQuality(scale).outputFormat("jpg").toFile(thumbnailFilePathName);
            }
            return thumbnailFilePathName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取本地服务图片
     *
     * @param imgUrl
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "getImgPath")
    public void getImgPath(@RequestParam(required = false) String imgUrl, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (imgUrl != null) {
            response.setContentType("image/jpeg");
            FileInputStream is = this.query_getPhotoImageBlob(imgUrl);
            if (is != null) {
                int i = is.available();
                byte[] fileData = new byte[i];
                is.read(fileData);
                response.setContentType("image/jpeg");
                OutputStream toClient = response.getOutputStream();
                toClient.write(fileData);
                toClient.close();
            }
        }
    }

    /**
     * 读数据库，获取图片输入流
     *
     * @param imgUrl
     * @return
     */
    private FileInputStream query_getPhotoImageBlob(String imgUrl) {
        FileInputStream is = null;
        File filePic = new File(imgUrl);
        try {
            is = new FileInputStream(filePic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 判断是否是图片
     *
     * @param imgName
     * @return
     */
    private static Boolean isImg(String imgName) {
        if (imgName.contains(".jpg")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".png")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".bmp")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".gif")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".JPG")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".PNG")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".BMP")) {
            return Boolean.TRUE;
        }
        if (imgName.contains(".GIF")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


//    /**
//     * 上传文物标题图片
//     *
//     * @param file
//     * @return
//     */
//    @RequestMapping(value = "uploadRelicImg", method = RequestMethod.POST)
//    public Response<Map<String, Object>> uploadRelicImg(@RequestParam(value = "file") MultipartFile file, Long relicId) {
//        AdminUserDTO userToken = this.getUserToken();
//        double len = file.getSize();
//        if (len > IMG_SIZE) {
//            return new Response<>(CodeTable.EXCEPTION, "图片上传失败!图片太大,请压缩图片在上传。");
//        }
//        if (Objects.isNull(relicId)) {
//            return new Response<>(CodeTable.EXCEPTION, "图片上传失败!图片太大,请压缩图片在上传。");
//        }
//        /** 查询文物信息 **/
//        RelicDTO relicDTO = relicService.getRelicById(relicId);
//        String imgPath = relicFormalPath + relicDTO.getRelicName() + "/";
//        String imgName = file.getOriginalFilename();
//        String imgUrl = imgPath + imgName;
//        Response<Map<String, Object>> response = new Response<>(CodeTable.SUCCESS);
//        try {
//            Map<String, Object> rs = FileUtils.uploadFileMethod(file, imgPath);
//            if ((boolean) rs.get(RESULT_STATUS)) {
//                /** 上传成功 **/
//                if (StringUtils.isNotBlank(relicDTO.getImgUrl())) {
//                    String delPath = relicUpdatePath + relicDTO.getRelicName() + "/";
//                    String delName = DateUtil.dateFormat(new Date(), DateUtil.F_DATE_YYYYMMDDHHMMSS) + "_" + userToken.getName() + "_" + imgName;
//                    if (FileUtils.transferFile(relicDTO.getImgUrl(), delPath, delName)) {
//                        FileUtils.deleteFile(relicDTO.getImgUrl());
//                    }
//                }
//                if (FileUtils.transferFile(rs.get(RESULT_FILE_URL).toString(), imgPath, imgName)) {
//                    FileUtils.deleteFile(rs.get(RESULT_FILE_URL).toString());
//                }
//                if (!relicService.updateRelicImg(relicId, imgUrl, userToken.getId())) {
//                    return new Response<>(CodeTable.EXCEPTION);
//                }
//            } else {
//                return new Response<>(CodeTable.EXCEPTION);
//            }
//        } catch (Exception e) {
//            return new Response<>(CodeTable.EXCEPTION);
//        }
//        return response;
//    }
}
