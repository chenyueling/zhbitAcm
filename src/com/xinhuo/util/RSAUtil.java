package com.xinhuo.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import javax.crypto.Cipher;


/**
 * 用于前台与后台数据加密使用
 * 
 * RSA 工具类。提供加密，解密，生成密钥对等方法。
 * 需要bcprov(基于jdk的加密算法的具体实现，算法包括MD5,SHA-1,DES,DESede,RSA等-)
 * 
 * 下载地址http://www.bouncycastle.org
 * 
 * 因为后台要转换成bigint，所以对明文长度有限制 总长度不超过126（1汉字长度为9 汉字不要超过14个字）
 * 
 */
public class RSAUtil {
 
	/**
	 * 加密
	 */
	public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();// 获得加密块大小，如：加密前数据为128个byte，而key_size=1024
			// 加密块大小为127
			// byte,加密后为128个byte;因此共有2个加密块，第一个127
			// byte第二个为1个byte
			int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
			int leavedSize = data.length % blockSize;
			int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
			byte[] raw = new byte[outputSize * blocksSize];
			int i = 0;
			while (data.length - i * blockSize > 0) {
				if (data.length - i * blockSize > blockSize)
					cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
				else
					cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
				// 这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把byte[]放到
				// ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到了此时加密块大小很可能已经超出了
				// OutputSize所以只好用dofinal方法。
 
				i++;
			}
			return raw;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
 
	/**
	 * 解密
	 */
	@SuppressWarnings("static-access")
	public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher.init(cipher.DECRYPT_MODE, pk);
			int blockSize = cipher.getBlockSize();
			ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
			int j = 0;
 
			while (raw.length - j * blockSize > 0) {
				bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
				j++;
			}
			return bout.toByteArray();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 使用默认的路径读取RSAkey并解密密文
	 * @param ciphertext
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String ciphertext) throws Exception {
		byte[] en_result = BytesUtil.hexStringToBytes(ciphertext);
		byte[] de_result = null;
		de_result = RSAUtil.decrypt(RSAUtil.getKeyPair(FileUtil.getKeyPairFilePath()).getPrivate(), en_result);
		StringBuffer sb = new StringBuffer();
		sb.append(new String(de_result));
		ciphertext = sb.reverse().toString();
		ciphertext = URLDecoder.decode(ciphertext, "UTF-8");
		return ciphertext;
	}
 
	/**
	 * 
	 * 根据本地的RSAKey文件获取KeyPair
	 * 
	 * @throws Exception
	 */
	public static KeyPair getKeyPair(String rsaKeyStore) throws Exception {
		FileInputStream fis = new FileInputStream(rsaKeyStore);
		ObjectInputStream oos = new ObjectInputStream(fis);
		KeyPair kp = (KeyPair) oos.readObject();
		oos.close();
		fis.close();
		return kp;
	}
 
	/**
	 * 
	 * 存储KeyPair到本地
	 * 
	 * @throws Exception
	 */
	public static void saveKeyPair(KeyPair kp, String path) throws Exception {
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		// 生成密钥
		oos.writeObject(kp);
		oos.close();
		fos.close();
	}
 
	/**
	 * 
	 * 用于生成公匙或私匙
	 * 
	 * @throws NoSuchAlgorithmException
	 * 
	 */
	public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
 
		SecureRandom sr = new SecureRandom();
		KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
		// 注意密钥大小最好为1024,否则解密会有乱码情况.
		kg.initialize(1024, sr);
		KeyPair genKeyPair = kg.genKeyPair();
		return genKeyPair;
 
	}
 
	/**
	 * 
	 * 测试
	 * 
	 */
	public static void main(String[] args) throws Exception {
 
		// 获取公匙及私匙
		KeyPair generateKeyPair = generateKeyPair();
 
		// 公匙 用于前台加密
		PublicKey publicKey = generateKeyPair.getPublic();
		System.out.println(publicKey);
 
		// 私匙 存储在后台用于解密
		PrivateKey privateKey = generateKeyPair.getPrivate();
		System.out.println(privateKey);
 
		// 存储KeyPair到本地用于后期解密 注意修改前台RSAKeyPair
//	    saveKeyPair(generateKeyPair,"D:\\RSAKey");
		//81595192cea41428a8606f15902d0c451100be86022086b5700ff404d9229b01a4d22830af5a53305bdcdde99ec52269bb6dace76cc22bbfec14793afc2f6bc5db4da2922a316de3b330b40ccd3e77e4a1afd65096db5c066734324260825ef9c7d19fb1572e2ff876e4b42f3572ed38aac5d4cb2c2f64598ffae93ab3a2ed59
 
		// 测试加密解密
		String test = "123331344412124";
		// test = "dfadfasdfadfasf";
//		test = "aaaaaaa";
 
		byte[] en_test = encrypt(publicKey, test.getBytes());
		System.out.println("加密后字符:" + new String(en_test));
 
		byte[] de_test = decrypt(privateKey, en_test);
		System.out.println("解密后字符:" + new String(de_test));
 
	}
 
	
	public static String doJieMi(String miwen){
		String mingwen ="";
		byte[] en_result = BytesUtil.hexStringToBytes(miwen);
		byte[] de_result;
		try{
			de_result = RSAUtil.decrypt(RSAUtil.getKeyPair(FileUtil.getKeyPairFilePath()).getPrivate(), en_result);
			StringBuffer sb = new StringBuffer();
			sb.append(new String(de_result));
			mingwen = sb.reverse().toString();
			mingwen = URLDecoder.decode(mingwen, "UTF-8");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return mingwen;
	}
	
}
