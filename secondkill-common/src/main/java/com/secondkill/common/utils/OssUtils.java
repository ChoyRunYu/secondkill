package com.secondkill.common.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class OssUtils {

    private final static Logger logger = LoggerFactory.getLogger(OssUtils.class);

    @Value("${oss.endpoint:''}")
    private String endpoint;
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    @Value("${oss.accessKeyId:''}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret:''}")
    private String accessKeySecret;
    /**
     * 设置存储空间名称
     */
    @Value("${oss.bucketName:''}")
    private String bucketName;
    /**
     * 上文件存储目录
     */
    @Value("${oss.fileDir:''}")
    private String fileDir;


    /**
     * 上传图片到oss
     * @param file
     * @return
     */
    public String uploadFile2Oss(File file){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileDir + file.getName(), file);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        return getOssFilePath(file.getName());
    }

    /**
     * 生成oss路径
     * @param filename
     * @return
     */
    private String getOssFilePath(String filename){
        String str = endpoint.substring(endpoint.lastIndexOf("/") + 1, endpoint.length());
        String domain = "http://" + bucketName + "." + str;
        return domain + "/" + fileDir + filename;
    }


}
