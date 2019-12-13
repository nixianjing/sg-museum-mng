package com.example.museum.common.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 对文件和文件路径处理
 *
 * @author nixianjing
 */
@Component
public class FileUtils {


    /**
     * 判断文件夹是否存在
     *
     * @param filePath return true 存在; false 不存在
     */
    public boolean judeDirExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * 判断文件夹是否存在
     *
     * @param filePath return true 存在; false 不存在
     */
    public boolean judeFileExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * 重新命名文件夹
     *
     * @param filePath    原文件夹路径
     * @param newFilePath 新文件夹路径
     * @return
     */
    public boolean callDirExists(String filePath, String newFilePath) {
        /**
         * 想命名的原文件夹的路径
         */
        File file1 = new File(filePath);
        /**
         * 将原文件夹更改为新文件夹，其中路径是必要的
         */
        file1.renameTo(new File(newFilePath));
        /**
         * 验证是否更改成功
         */
        return judeDirExists(newFilePath);
    }

    /**
     * 重新命名文件
     *
     * @param filePath
     * @param newFilePath
     * @return
     */
    public boolean callFileExists(String filePath, String newFilePath) {
        /**
         * 想命名的原文件的路径
         */
        File file = new File(filePath);
        /**
         * 将原文件更改为命名的原文件的路径，其中路径是必要的。注意
         */
        file.renameTo(new File(newFilePath));
        /**
         * 验证是否更改成功
         */
        return judeFileExists(newFilePath);
    }

    public void getFileAndDirectory(File file) {
        int countDirctory = 0;
        int countFile = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                if (fileIndex.isDirectory()) {
                    countDirctory++;
                } else {
                    countFile++;
                }
            }
        }
        System.out.println("目录文件数目为：" + countDirctory);
    }

    /**
     * 中转文件
     *
     * @param tempPath 需要中转的文件路径  例如：/Users/nixianjing/word.doc
     * @param savePath 中转文件目标路径   例如：/Users/nixianjing/file/
     * @param saveName 中转文件名称      例如：newWord.doc
     */
    public static Boolean transferFile(String tempPath, String savePath, String saveName) {
        try {
            //中转站路径中的文件
            File tempPathFile = new File(tempPath);
            //项目的相对路径（目录）
            File savePathFile = new File(savePath);
            //如果当前项目对于的相对路径下没有对应的图片。就从中转目录下拷贝一份，有的话就跳过此方法
            // 如果中转目录下都没有文件，那么直接跳过。
            File imgFile = new File(savePath + saveName);
            if (!imgFile.exists()) {
                if (tempPathFile.exists()) {
                    //当前项目的相对路径没有对应的目录，新建
                    if (!savePathFile.exists()) {
                        savePathFile.mkdirs();
                    }
                    //得到中转目录的 文件流
                    FileInputStream fis = new FileInputStream(tempPath);
                    //输出到 当前目录下
                    FileOutputStream fos = new FileOutputStream(savePath + saveName);
                    int len = 0;
                    byte[] buf = new byte[1024];
                    while ((len = fis.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fis.close();
                    fos.close();
                    return Boolean.TRUE;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    /**
     * 删除一组文件
     *
     * @param filePath 文件路径数组
     */
    public static void deleteFile(String[] filePath) {
        if (filePath != null && filePath.length > 0) {
            for (String path : filePath) {
                File delfile = new File(path);
                if (delfile.exists()) {
                    delfile.delete();
                }
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 单个文件路径
     */
    public static void deleteFile(String filePath) {
        if (filePath != null && !"".equals(filePath)) {
            String[] path = new String[]{filePath};
            deleteFile(path);
        }
    }

    /**
     * 文件上传
     * 支持图片
     * 文件
     * 压缩包
     *
     * @param file
     * @param filePath
     * @return
     */
    public static Map<String, Object> uploadFileMethod(MultipartFile file, String filePath) {
        Map<String, Object> result = new HashMap<>();
        try {
            String primaryName = file.getOriginalFilename();
            //如果这个文件不存在
            File uploadFile = new File(filePath + file.getOriginalFilename());
            File dest = uploadFile.getParentFile();
            if (!dest.exists()) {
                dest.mkdirs(); //创建
            }
            String fileName = DateUtil.dateFormat(new Date(), DateUtil.F_DATE_YYYYMMDDHHMMSS) + "_" + file.getOriginalFilename();
            String fileUrl = filePath + fileName;
            BufferedInputStream bis = new BufferedInputStream(file.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(fileUrl)));

            byte[] data = new byte[1024];
            int len = 0;
            while ((len = bis.read(data)) != -1) {
                bos.write(data, 0, len);
            }
            bos.flush();
            bos.close();
            bis.close();
            result.put("status", true);
            result.put("fileUrl", fileUrl);
            result.put("filePath", filePath);
            result.put("fileName", fileName);
            result.put("primaryName", primaryName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", false);
            return result;
        }
    }


    public static void main(String[] args) {
        String tempPath = "/Users/nixianjing/word_data_small/20191121115706_毛毡袜.doc";
        String savePath = "/Users/nixianjing/word_member/毛毡袜/2019/";
        transferFile(tempPath, savePath, "毛毡袜.doc");
//        String filePath = "/Users/nixianjing/image_data_small/馆藏书画/";
//        FileUtils fileUtils = new FileUtils();
//        /**
//         * 想命名的原文件的路径
//         */
//        File file = new File(filePath);
//        fileUtils.getFileAndDirectory(file);
//        String filePath = "/Users/nixianjing/image_data_small/馆藏书画/边柜-1-14";
//        String newFilePath = "/Users/nixianjing/image_data_small/馆藏书画/边柜-1-14-00001";
//
//        System.out.println(FileUtils.callDirExists(filePath,newFilePath));
//
    }
}
