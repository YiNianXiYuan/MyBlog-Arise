package com.study.api;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Auther: arise
 * @Date: 2021/7/29 上午10:09
 * @Description: 七牛文件上传API
 */
public class QiNiuCloudService {

    /**
     * 密钥凭证
     */
    private static final String ACCESS_KEY = "yRJ48yXCiQYNyzCcTU9fA9E7e9ZvlODUkcwv-Ent";
    private static final String SECRET_KEY = "EoMtgDtYZQL9HRcRYEeqODp3XMMysZHA3OOAJ1MO";
    /**
     * 仓库
     */
    private static final String BUCKET = "pubbishespace";

    /**
     * 七牛外网访问地址
     */
    public static final String QINIU_UPLOAD_SITE = "";

    /**
     * 上传文件到七牛公共方法
     * @param file          上传的文件
     * @param fileName      文件名
     * @return
     */
    public static String upload(MultipartFile file, String fileName) {

        // 构造一个带指定Zone对象的配置类
        // 华东 Zone.zone0()
        // 华北 Zone.zone1()
        // 华南 Zone.zone2()
        // 北美 Zone.zoneNa0()
        Configuration cfg = new Configuration(Zone.zone2());
        // 其它参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String upToken = auth.uploadToken(BUCKET);
        try {
            Response response = null;
            response = uploadManager.put(file.getInputStream(), fileName, upToken,null,null);

            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return  putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.out.println(r.toString());
            try {
                System.out.println(r.bodyString());
            } catch (QiniuException ex2) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String deleteFile(String fileKey){
        // 构造一个带指定Zone对象的配置类
        // 华东 Zone.zone0()
        // 华北 Zone.zone1()
        // 华南 Zone.zone2()
        // 北美 Zone.zoneNa0()
        Configuration cfg = new Configuration(Zone.zone2());
        // 其它参数参考类注释

        String key = fileKey;
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(BUCKET, key);
        }catch (QiniuException ex){
            // 如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
        }
        return "1";
    }
}
