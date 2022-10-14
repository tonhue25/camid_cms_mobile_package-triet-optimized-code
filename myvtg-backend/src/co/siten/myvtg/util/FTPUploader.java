/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

/**
 * @author duyth
 */
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

@Component
@PropertySource(value = {"classpath:ftp.properties"})
public class FTPUploader {

    private static final Logger logger = Logger.getLogger(FTPUploader.class);

    @Autowired
    private Environment environment;
// daibq backup error
//    FTPClient ftp = null;
//
//    public void connect() throws Exception {
//        ftp = new FTPClient();
//        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
//
////        String host = AES.decrypt(environment.getProperty("ftp.mymetfone.host"));
////        String username = AES.decrypt(environment.getProperty("ftp.mymetfone.username"));
////        String pwd = AES.decrypt(environment.getProperty("ftp.mymetfone.password"));
//
//        String host = environment.getProperty("ftp.mymetfone.host");
//        String username = environment.getProperty("ftp.mymetfone.username");
//        String pwd = environment.getProperty("ftp.mymetfone.password");
//
//        int reply;
//        ftp.connect(host);
//        reply = ftp.getReplyCode();
//        if (!FTPReply.isPositiveCompletion(reply)) {
//            ftp.disconnect();
//            throw new Exception("Exception in connecting to FTP Server");
//        }
//        ftp.login(username, pwd);
//        ftp.setFileType(FTP.BINARY_FILE_TYPE);
//        ftp.enterLocalPassiveMode();
//    }
//
//    public void uploadFile(InputStream inputStream, String fileName) throws Exception {
//        try {
//            String hostDir = environment.getProperty("ftp.mymetfone.path");
//            ftp.storeFile(hostDir + "/" + fileName, inputStream);
//        } finally {
//            disconnect();
//        }
//    }
//
//    public void disconnect() {
//        if (this.ftp.isConnected()) {
//            try {
//                this.ftp.logout();
//                this.ftp.disconnect();
//            } catch (IOException f) {
//                // do nothing as file is already saved to server
//            }
//        }
//    }
//
//    public String getRemoteFile(String avatarFileName) throws Exception {
//        try {
//            String hostDir = environment.getProperty("ftp.mymetfone.path");
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            boolean success = ftp.retrieveFile(hostDir + "/" + avatarFileName, outputStream);
//            if (!success) {
//                logger.info("Can't get avatar file from FTP server");
//                return "";
//            }
//
//            byte[] avatarBytes = outputStream.toByteArray();
//            outputStream.close();
//
//            return Base64.toBase64String(avatarBytes);
//        } finally {
//            disconnect();
//        }
//    }

    // daibq fix backup error
    /**
     * connect
     *
     * @param ftp
     * @return
     * @throws Exception
     */
    public boolean connect(FTPClient ftp) throws Exception {
        try {
            ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            String host = environment.getProperty("ftp.mymetfone.host");
            String username = environment.getProperty("ftp.mymetfone.username");
            String pwd = environment.getProperty("ftp.mymetfone.password");

            int reply;
            ftp.connect(host);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                disconnect(ftp);
                return false;
            }
            ftp.login(username, pwd);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.enterLocalPassiveMode();
            return true;
        } catch (Exception e) {
            logger.error("Exception ftp 56 ", e);
            throw e;
        }
    }


    /**
     * uploadFile
     *
     * @param inputStream
     * @param fileName
     * @param ftp
     * @throws Exception
     */
    public void uploadFile(InputStream inputStream, String fileName, FTPClient ftp) throws Exception {
        try {
            String hostDir = environment.getProperty("ftp.mymetfone.path");
            ftp.storeFile(hostDir + "/" + fileName, inputStream);
        } catch (Exception e) {
            logger.error("Exception ftp 56", e);
            throw e;
        } finally {

            disconnect(ftp);
        }
    }

    /// nghi altek
    public void uploadFTPFileFromBase64(String video, String fileName) throws Exception {
        String ftpHostName = environment.getProperty("ftp.new.host");
        int ftpPort = Integer.parseInt(environment.getProperty("ftp.new.port"));
        String ftpUserName = environment.getProperty("ftp.new.username");
        String ftpPassword = environment.getProperty("ftp.new.password");

        FTPClient ftpClient = new FTPClient();
        try {

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] decodedBytes = decoder.decodeBuffer(video);
            ftpClient.connect(ftpHostName, ftpPort);
            boolean successLogin = ftpClient.login(ftpUserName, ftpPassword);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            if (successLogin) {
                System.out.println("success login");
            } else {
                System.out.println("fail login");
            }
            for (FTPFile file : ftpClient.listFiles("/")) {
                System.out.println("File: " + file);
            }
            ftpClient.enterLocalPassiveMode();
            String dir = environment.getProperty("ftp.new.path");

            String[] arr = format.format(new Date()).split("/");
            //dir = dir + "/" + arr[2] + "/" + arr[1] + "/" + arr[0];
            dir = dir + "/" + arr[2];
            makeDir(ftpClient, dir);
            dir = dir + "/" + arr[1];
            makeDir(ftpClient, dir);
            dir = dir + "/" + arr[0];
            makeDir(ftpClient, dir);
            ftpClient.changeWorkingDirectory(dir);
            dir += fileName;
            InputStream inputStream = new ByteArrayInputStream(decodedBytes);
            OutputStream outputStream = ftpClient.storeFileStream(dir);

            byte[] bytesIn = new byte[4096];
            int read = 0;

            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                System.out.println("loging out");
                ftpClient.logout();
                System.out.println("disconecting");
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

/// nghi altek
    /**
     * disconnect
     *
     * @param ftp
     * @throws java.lang.Exception
     */
    public void disconnect(FTPClient ftp) throws Exception {
        if (ftp.isConnected()) {
            try {
                ftp.logout();
                ftp.disconnect();
            } catch (Exception f) {
                logger.error("Exception ftp 56", f);
                throw f;
            }
        }
    }

    public String getRemoteFile(String avatarFileName, FTPClient ftp) throws Exception {
        try {
            String hostDir = environment.getProperty("ftp.mymetfone.path");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            boolean success = ftp.retrieveFile(hostDir + "/" + avatarFileName, outputStream);
            if (!success) {
                logger.info("Can't get avatar file from FTP server");
                return "";
            }
            byte[] avatarBytes = outputStream.toByteArray();
            outputStream.close();

            return Base64.toBase64String(avatarBytes);
        } catch (Exception e) {
            logger.error("Exception ftp 56", e);
            throw e;
        } finally {
            disconnect(ftp);
        }
    }
    public String getRemoteFileFTP(String pathFileName, FTPClient ftp) throws Exception {
        String ftpHostName = environment.getProperty("ftp.new.host");
        int ftpPort = Integer.parseInt(environment.getProperty("ftp.new.port"));
        String ftpUserName = environment.getProperty("ftp.new.username");
        String ftpPassword = environment.getProperty("ftp.new.password");

        try {
            ftp.connect(ftpHostName, ftpPort);
            boolean successLogin = ftp.login(ftpUserName, ftpPassword);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            if (successLogin) {
                System.out.println("success login");
            } else {
                System.out.println("fail login");
            }
            ftp.enterLocalPassiveMode();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            boolean success = ftp.retrieveFile(pathFileName, outputStream);
            if (!success) {
                logger.info("Can't get video from FTP server");
                return "";
            }
            byte[] videoBytes = outputStream.toByteArray();
            outputStream.close();

            return Base64.toBase64String(videoBytes);
        } catch (Exception e) {
            logger.error("Exception ftp 56", e);
            throw e;
        } finally {
            disconnect(ftp);
        }
    }
    private static void makeDir(FTPClient ftpClient, String dir) throws Exception {
        if (!ftpClient.changeWorkingDirectory(dir)) {
            ftpClient.makeDirectory(dir);
        }
    }
    public static void main(String[] args) {

    }


}
