package com.kiwihouse.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.ResultList;

/**
 * @author xin
 * @date 2020/6/8
 */
@Service
public class ImageService {
    private final String BASE_PATH = "./upload/static_images";
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

	
    /**
     * 上传图片
     *
     * @param image   文件
     * @param userId  用户id
     * @param equipSn 设备序列号
     * @return
     */
    public ResultList upload(MultipartFile image, String userId, String equipSn) {
        if(equipSn.contains(",")){
             return ResultUtil.resp(Code.UPLOAD_IMAGE_FAIL,"序列号不能有,");
        }
        if (image == null) {
            return ResultUtil.resp(Code.UPLOAD_IMAGE_FAIL);
        }
        String originalFilename = image.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            return ResultUtil.resp(Code.UPLOAD_IMAGE_FILENAME_ERROR);
        }
        // 重新生成一个新的文件名
        String name = userId + "_" + UUID.randomUUID().toString() + ".png";
        // 指定存储文件的根目录
        File dirFile = new File(BASE_PATH + "/" + equipSn);
        File absoluteDirFile = new File(dirFile.getAbsolutePath());
        if (!absoluteDirFile.exists() && !absoluteDirFile.mkdirs()) {
            return ResultUtil.resp(Code.UPLOAD_IMAGE_FAIL, "创建目录失败");
        }
        try {
            File f = new File(absoluteDirFile, name);
            image.transferTo(f);
            return ResultUtil.respList(Code.UPLOAD_IMAGE_SUCC, equipSn + "/" + name);

        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.resp(Code.UPLOAD_IMAGE_FAIL);

        }
    }
}
