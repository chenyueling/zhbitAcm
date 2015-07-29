package com.xinhuo.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileUtil {
	public static final String rootPath = "../KEJI_File";
	//允许上传的图片类型
	public static final String allowType = "image/bmp,image/png,image/gif,image/jpeg,image/pjpeg,image/x-png,application/octet-stream";
	
	/**
	 * 判断图片是否是合法类型
	 * @param fileType
	 * @return
	 */
	public static boolean validateImageType(String fileType) {
		return allowType.contains(fileType);
	}
	
	/**
	 * 使用UUID生成随机名称
	 * @param fileName 原始文件名
	 * @return
	 */
	public static String createRandomName(String fileName) {
		String randomName = "";
		String[] s = fileName.split("\\.");
		randomName = UUID.randomUUID().toString() + "." + s[s.length - 1];
		return randomName;
	}

	/**
	 * 获取服务器路径
	 * @return
	 */
	public static String getServerPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
	}
	
	/**
	 * 获取服务器根路径
	 * @param savePath
	 * @param fileName
	 * @return
	 */
	public static String getServerRoot(String savePath) {
		savePath = rootPath + File.separator + savePath;
		String uploadPath = ServletActionContext.getServletContext().getRealPath(savePath) + "\\";
		// 如果文件目录 不存在则创建
		if (!new File(uploadPath).isDirectory()) {
			new File(uploadPath).mkdirs();
		}
		return uploadPath;
	}

	/**
	 * 获取服务器根路径
	 * @param savePath
	 * @param fileName
	 * @return
	 */
	public static String getServerRoot(String savePath, String fileName) {
		savePath = rootPath + File.separator + savePath;
		String uploadPath = ServletActionContext.getServletContext().getRealPath(savePath) + "\\";
		// 如果文件目录 不存在则创建
		if (!new File(uploadPath).isDirectory()) {
			new File(uploadPath).mkdirs();
		}
		return uploadPath + fileName;
	}
	
	/**
	 * 将临时文件A复制给目标文件B
	 * @param tempFile
	 * @param saveFile
	 * @throws FileNotFoundException,IOException 
	 */
	public static void saveFile(File tempFile, File saveFile) throws FileNotFoundException,IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(tempFile);
			fos = new FileOutputStream(saveFile);
			IOUtils.copy(fis, fos);

		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}
	
	//获取加密文本
	public static String getKeyPairFilePath(){
		//return ServletActionContext.getRequest().getRealPath("WEB-INF/classes/RSAKey");
		return ServletActionContext.getServletContext().getRealPath("WEB-INF/classes/RSAKey");
	}
	
	 /**
     * 图片压缩
     * @param originalFile 原图片
     * @param outputPath 输出路径
     * @param outputWidth 小图宽
     * @param outputHeight 小图高
     * @param proportion 是否等比压缩
     * @return
     */
	public static void compressPic(File originalFile, String outputPath, int outputWidth, int outputHeight, boolean proportion){
		
		
		//ImageUtil.compressPic(originalFile, outputPath, outputWidth, outputHeight);
		
    	try {   
            if (!originalFile.exists()) {   
                return;  
            }   
            Image img = ImageIO.read(originalFile);   
            // 判断图片格式是否正确   
            if (img.getWidth(null) == -1) {  
               System.out.println("压缩过程出现图片格式出错");
            } else {   
                int newWidth; int newHeight;   
                // 判断是否是等比缩放   
                if (proportion == true) {   
                    // 为等比缩放计算输出的图片宽度及高度   
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
                    // 根据缩放比率大的进行缩放控制   
                    double rate = rate1 > rate2 ? rate1 : rate2;   
                    newWidth = (int) (((double) img.getWidth(null)) / rate);   
                    newHeight = (int) (((double) img.getHeight(null)) / rate);   
                } else {   
                    newWidth = outputWidth; // 输出的图片宽度   
                    newHeight = outputHeight; // 输出的图片高度   
                }   
               BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
                 
                
               //Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 
               //优先级比速度高 生成的图片质量比较好 但速度慢 
                   
               tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
               FileOutputStream out = new FileOutputStream(outputPath);  
               // JPEGImageEncoder可适用于其他图片类型的转换   
               JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
               encoder.encode(tag);   
               out.close();   
            }   
        } catch (IOException ex) {   
            ex.printStackTrace();   
        }   
   }  
	
}
