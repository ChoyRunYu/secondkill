package com.secondkill.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

/**
 * 文件工具类
 * @author choy
 * @date 2021/3/19
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 图片格式
     */
    private static final String[]  IMAGE_TYPE = new String[]{".bmp", ".jpg", ".png", ".jpeg", ".gif"};

    /**
     * 校验图片类型
     * @param file
     * @return
     */
    public static boolean checkImg(File file){
        if (file.exists()){
            return false;
        }
        Image img = null;
        try{
            img = ImageIO.read(file);
            if (img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0){
                return false;
            }
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }finally {
            img = null;
        }
    }


    /**
     * 校验图片的类型
     * @param multipartFile
     * @return
     */
    public static boolean checkImgType(MultipartFile multipartFile){
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(multipartFile.getOriginalFilename(), type)){
                isLegal = true;
                break;
            }
        }
        return isLegal;
    }
}
