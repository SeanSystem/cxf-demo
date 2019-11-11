package com.example.cxfdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CxfDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testZip() {

        String srcPath = "F:\\W3school";
        String descPath = "F:\\W3school.zip";
        try (
                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(descPath));
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

    public void compressDir(ZipOutputStream zos, File dir, String descPath) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                compressDir(zos, file, descPath + file.getName() + File.separator);
            } else {
                compressFile(zos, file, descPath);
            }
        }
    }

    public void compressFile(ZipOutputStream zos, File file, String descPath) {
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

