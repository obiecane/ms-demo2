package com.ms.core.kit;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Zhu Kaixiao
 * @version 1.0
 * @date 2019/9/2 11:57
 * @copyright 江西金磊科技发展有限公司 All rights reserved. Notice
 * 仅限于授权后使用，禁止非授权传阅以及私自用于商业目的。
 */
@Slf4j
public abstract class BaseController {


    /**
     * 提供文件下载
     * 这个方法会自动根据文件去判断mime类型
     *
     * @param pathname 文件全路径
     * @return org.springframework.http.ResponseEntity<org.springframework.core.io.Resource>
     * @author Zhu Kaixiao
     * @date 2019/9/28 10:19
     **/
    protected ResponseEntity<Resource> provideDownload(String pathname) {
        try {
            String fileName = FilenameUtils.getName(pathname);
            // 获取文件名称，中文可能被URL编码
            fileName = URLDecoder.decode(fileName, "UTF-8");
            // 获取本地文件系统中的文件资源
            FileSystemResource resource = new FileSystemResource(pathname);

            // 解析文件的 mime 类型
            String mediaTypeStr = URLConnection.getFileNameMap().getContentTypeFor(fileName);
            // 无法判断MIME类型时，作为流类型
            mediaTypeStr = (mediaTypeStr == null) ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mediaTypeStr;
            // 实例化MIME
            MediaType mediaType = MediaType.parseMediaType(mediaTypeStr);

            // 构造响应的头
            HttpHeaders headers = new HttpHeaders();
            // 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
            String filenames = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            headers.setContentDispositionFormData("attachment", filenames);
            headers.setContentType(mediaType);

            log.debug("Provide download successfully! [{}]", pathname);
            // 返回资源
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.getInputStream().available())
                    .body(resource);
        } catch (IOException e) {
            log.debug("Provide download failed! [{}]", pathname);
            return null;
        }
    }


    /**
     * @param file 文件
     * @return org.springframework.http.ResponseEntity<org.springframework.core.io.Resource>
     * @author Zhu Kaixiao
     * @date 2019/9/28 10:19
     * @see BaseController#provideDownload(String)
     **/
    protected ResponseEntity<Resource> provideDownload(File file) {
        return provideDownload(FileUtil.getCanonicalPath(file));
    }


    /**
     * @param file     文件
     * @param response response
     * @return boolean 是否提供下载成功
     * @author Zhu Kaixiao
     * @date 2019/9/27 11:21
     * @see BaseController#provideDownload(InputStream, String, HttpServletResponse)
     **/
    protected static boolean provideDownload(@NotNull final File file, @NotNull final HttpServletResponse response) {
        // 实现文件下载
        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))
        ) {
            String filename = file.getName();
            return provideDownload(bis, filename, response);
        } catch (Exception e) {
            log.debug("Provide download failed! [{}]", file);
            return false;
        }
    }


    /**
     * 以流类型提供文件下载
     *
     * @param in       输入流
     * @param filename 文件名
     * @param response response
     * @return boolean
     * @author Zhu Kaixiao
     * @date 2019/9/28 10:21
     **/
    protected static boolean provideDownload(@NotNull final InputStream in, @NotNull final String filename,
                                             @NotNull final HttpServletResponse response) {
        // 实现文件下载
        try (
                OutputStream os = response.getOutputStream()
        ) {
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream; charset=utf-8");
            // 下载文件能正常显示中文
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));

            int read;
            byte[] bytes = new byte[2048];
            while ((read = in.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            os.flush();

            log.debug("Provide download successfully! [{}]", filename);
            return true;
        } catch (Exception e) {
            log.debug("Provide download failed! [{}]", filename);
            return false;
        }
    }

}
