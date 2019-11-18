package com.example.cxfdemo.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio文件处理工具类
 *
 * @author Sean
 * @date 2019/11/18
 */
public final class NIOFileUtils {

    private NIOFileUtils() {
    }

    public static void copy(String srcPath, String descPath) {
        File file = new File(srcPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            descPath = descPath + File.separator + file.getName();
            File temp = new File(descPath);
            if (!temp.exists()) {
                temp.mkdirs();
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    copy(f.getPath(), descPath);
                } else {
                    fileCopy(f.getPath(), descPath + File.separator + f.getName());
                }
            }
        } else {
            fileCopy(file.getPath(), descPath + File.separator + file.getName());
        }
    }

    public static void fileCopy(String srcPath, String descPath) {
        try (FileChannel outChannel = new FileOutputStream(descPath).getChannel();
             FileChannel inChannel = new FileInputStream(srcPath).getChannel()
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(1024*10);
            int len;
            while ((len = inChannel.read(buffer)) != -1) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    outChannel.write(buffer);
                }
                buffer.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copy2(String srcPath, String descPath) {
        File file = new File(srcPath);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            descPath = descPath + File.separator + file.getName();
            File temp = new File(descPath);
            if (!temp.exists()) {
                temp.mkdirs();
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    copy(f.getPath(), descPath);
                } else {
                    fileCopy(f.getPath(), descPath + File.separator + f.getName());
                }
            }
        } else {
            fileCopy(file.getPath(), descPath + File.separator + file.getName());
        }
    }

    public static void fileCopy2(String srcPath, String descPath) {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(descPath));
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcPath))
        ) {
            byte[] bytes = new byte[1024*10];
            int len;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, bytes.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String src = "F:\\shop-vue";
        String desc = "E:\\";
        copy(src, desc);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + ((end-start)/1000)+"秒");
    }

}
