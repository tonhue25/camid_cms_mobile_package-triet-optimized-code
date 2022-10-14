/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.util;

import co.siten.myvtg.dao.MkishareDao;
import com.viettel.bccs.cm.common.util.pre.DateUtils;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Nguyen Tran Minh Nhut <tranminhnhutn@metfone.com.kh>
 */
public class FileUtil {

    String host;
    String user;
    String pass;
    static Logger logger = Logger.getLogger(FileUtil.class.getSimpleName());
    public FileUtil(String host, String user, String pass) {
        this.host = host;
        this.user = user;
        this.pass = pass;
    }
    public FileUtil(){
    
    }
    /**
     * Put file to server
     * @param fileLocal
     * @param fileServer
     * @return
     */
    
    /**
     * Put file to server
     *
     * @param fileLocal
     * @param fileServer
     * @return
     */
    public   int uploadFile(String fileLocal, String fileServer) {
        FTPClient client = new FTPClient();
        FileInputStream fis;
        try {
            logger.info("host= "+host+"; "+user +"= "+user+"; pass= "+pass);
            client.connect(host);
            logger.info("connect to "+host+ " success");
            client.login(user, pass);
            logger.info("login to "+host+ " by user= "+user+ "; pass= "+pass+" success");
            client.enterLocalPassiveMode();

            client.setFileType(FTP.BINARY_FILE_TYPE);
            File firstLocalFile = new File(fileLocal);
            fis = new FileInputStream(firstLocalFile);
            // Store file on server and logout
            boolean result = client.storeFile(fileServer, fis);
            client.logout();
            logger.info("Put file to server Result               " + result);
            fis.close();
            File firstLocalFile1 = new File(fileLocal);
            if (result) {
                try {
                    Thread.sleep(1000*1L);
                } catch (InterruptedException ex) {
                    logger.error("Slepp error");
                }
                if (firstLocalFile1.delete()) {
                    logger.info("Delete file success");
                } else {
                    logger.info("Delete file fail");
                }
            }
        } catch (IOException e) {
            logger.error("Error " + e.getMessage(), e);
        } finally {
            try {

                client.disconnect();
            } catch (Exception e) {
                logger.error("Error " + e.getMessage(), e);
            }
        }
        return 1;
    }

    /**
     * Write file
     * @param fileName
     * @param content
     * @return
     */
    public static int writeFile(String fileName, String content, Logger logger) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            logger.info("Start writeFile fileName ="+fileName);
            logger.info("Start writeFile content = "+fileName);
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (IOException e) {
            logger.error("Error " + e.getMessage(), e);
            return -1;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                logger.error("Error " + ex.getMessage(), ex);
                return -1;
            }

        }
        return 1;
    }
    public String getFileName( MkishareDao mkishareDao, String fileNameTmp) throws Exception {
        String seq = "";
        logger.info("Result getFileName: start getsequences ");
        Long value = mkishareDao.getIndex();
        logger.info("Result getFileName: getsequences "+value);
        
        if (value < 10) {
            seq = "000" + value;
        }
        if (value < 100 && value >= 10) {
            seq = "00" + value;
        }
        if (value < 1000 && value >= 100) {
            seq = "0" + value;
        }
        if (value >= 1000) {
            seq = "" + value;
        }
        String fileName = fileNameTmp.replace("{1}", DateUtils.sysdateString()).
                replace("{2}", seq).replace("{3}", System.currentTimeMillis()+"");
        return fileName;
    }
    
    
//    public static void main(String[] args) throws Exception {
//        DbSession.loadConfig();
//        FileUtil fileUtil = new FileUtil();
//        String folderLocal = "../out_put" ;
//        String content = "0||||4|||||9|10|11|||||||||||||||||||||||34||||||||||44||||||||||||||||60|" ;
//        DbVas dbVas = new DbVas(logger);
//        String fileNameTmp = "mgr{1}_{2}_{3}.unl";
//        String fileName = fileUtil.getFileName(dbVas, fileNameTmp);
//        FileUtil.writeFile(folderLocal + "/" + fileName, content,logger);
//    }
}
