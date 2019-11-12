package com.example.cxfdemo.utils;


import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * zip文件处理工具类
 *
 * @author Sean
 * 2019/11/12
 */
public final class ZipUtils {

    private ZipUtils() {
    }

    /**
     * 多文件压缩下载
     *
     * @param srcPaths 文件路径集合
     * @param response 响应对象
     * @param fileName 文件名
     */
    public static void compressAndDownLoad(List<String> srcPaths, HttpServletResponse response, String fileName) {
        try (
                ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())
        ) {
            setResponseHead(response, fileName);
            for (String srcPath : srcPaths) {
                File file = new File(srcPath);
                if (file.isDirectory()) {
                    compressDir(zos, file, file.getName() + File.separator);
                } else {
                    compressFile(zos, file, file.getName() + File.separator);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩并下载文件
     *
     * @param srcPath  文件路径
     * @param response 响应头
     * @param fileName 压缩后文件名
     */
    public static void compressAndDownLoad(String srcPath, HttpServletResponse response, String fileName) {
        try (
                ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())
        ) {
            setResponseHead(response, fileName);
            File file = new File(srcPath);
            if (file.isDirectory()) {
                compressDir(zos, file, file.getName() + File.separator);
            } else {
                compressFile(zos, file, file.getName() + File.separator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置文件下载响应头
     *
     * @param response 响应对象
     * @param fileName 文件名
     * @throws Exception 异常
     */
    private static void setResponseHead(HttpServletResponse response, String fileName) throws Exception {
        // 清空response
        response.reset();
        // 设置response的Header
        if (!fileName.endsWith(".zip")) {
            fileName = fileName + ".zip";
        }
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        response.setContentType("application/octet-stream");
    }

    /**
     * 压缩zip文件工具类
     *
     * @param srcPath  源文件路径存放路径
     * @param descPath 压缩后文件路径存放路径
     */
    public static void compress(String srcPath, String descPath) {
        try (
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(descPath))
        ) {
            File file = new File(srcPath);
            if (file.isDirectory()) {
                compressDir(zos, file, file.getName() + File.separator);
            } else {
                compressFile(zos, file, file.getName() + File.separator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩zip文件工具类
     *
     * @param srcPaths 源文件路径存放路径集合
     * @param descPath 压缩后文件路径存放路径
     */
    public static void compress(List<String> srcPaths, String descPath) {
        try (
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(descPath))
        ) {
            for (String srcPath : srcPaths) {
                File file = new File(srcPath);
                if (file.isDirectory()) {
                    compressDir(zos, file, file.getName() + File.separator);
                } else {
                    compressFile(zos, file, file.getName() + File.separator);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩目录
     *
     * @param zos      ZipOutputStream流
     * @param dir      目录文件
     * @param descPath 文件压缩后路径
     */
    public static void compressDir(ZipOutputStream zos, File dir, String descPath) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                compressDir(zos, file, descPath + file.getName() + File.separator);
            } else {
                compressFile(zos, file, descPath);
            }
        }
    }

    /**
     * 压缩文件
     *
     * @param zos      ZipOutputStream流
     * @param file     文件
     * @param descPath 文件压缩后路径
     */
    public static void compressFile(ZipOutputStream zos, File file, String descPath) {
        try (FileInputStream fis = new FileInputStream(file)) {
            zos.putNextEntry(new ZipEntry(descPath + file.getName()));
            FileChannel channel = fis.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            while (read != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    zos.write(byteBuffer.get());
                }
                byteBuffer.clear();
                read = channel.read(byteBuffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
