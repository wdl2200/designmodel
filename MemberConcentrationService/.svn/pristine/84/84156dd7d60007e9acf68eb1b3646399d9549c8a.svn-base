/* ========================================================
 * 哈尔滨富利通科技有限公司研发二部
 * 日 期：2015-11-25
 * 功能：
 * 作 者：苏少轩
 * 版 本：1.0
 * =========================================================
 */
package com.cmts.xm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

	/**
     * 通过HttpServletRequest返回IP地址
     * @param request HttpServletRequest
     * @return ip String
     * @throws Exception
     */
    public static String getIpAddr(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
	/**
	 * 获取mac地址
	 * 
	 * @param ip
	 * @return
	 * @throws IOException
	 */
	public static String getMacAddress(String ip) throws IOException {
		String headIp = ip.substring(0, 3);
		if (headIp.equalsIgnoreCase("0:0") || headIp.equalsIgnoreCase("127")) {
			return "";
		}
		byte[] t_ns = new byte[50];
		t_ns[0] = 0x00;
		t_ns[1] = 0x00;
		t_ns[2] = 0x00;
		t_ns[3] = 0x10;
		t_ns[4] = 0x00;
		t_ns[5] = 0x01;
		t_ns[6] = 0x00;
		t_ns[7] = 0x00;
		t_ns[8] = 0x00;
		t_ns[9] = 0x00;
		t_ns[10] = 0x00;
		t_ns[11] = 0x00;
		t_ns[12] = 0x20;
		t_ns[13] = 0x43;
		t_ns[14] = 0x4B;
		for (int i = 15; i < 45; i++) {
			t_ns[i] = 0x41;
		}
		t_ns[45] = 0x00;
		t_ns[46] = 0x00;
		t_ns[47] = 0x21;
		t_ns[48] = 0x00;
		t_ns[49] = 0x01;

		int iRemotePort = 137;
		byte[] buffer = new byte[1024];
		DatagramSocket ds = new DatagramSocket();
		DatagramPacket dpk = new DatagramPacket(t_ns, t_ns.length,
				InetAddress.getByName(ip), iRemotePort);
		ds.send(dpk);

		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);

		byte[] brevdata = dp.getData();
		int i = brevdata[56] * 18 + 56;
		String sAddr = "";
		StringBuffer sb = new StringBuffer(17);
		for (int j = 1; j < 7; j++) {
			sAddr = Integer.toHexString(0xFF & brevdata[i + j]);
			if (sAddr.length() < 2) {
				sb.append(0);
			}
			sb.append(sAddr.toUpperCase());
			if (j < 6)
				sb.append(':');
		}

		try {
			ds.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
     * 执行单条指令
     * @param cmd 命令
     * @return 执行结果
     * @throws Exception
     */
	public static String command(String cmd) throws Exception{
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        InputStream in = process.getInputStream();
        StringBuilder result = new StringBuilder();
        byte[] data = new byte[256];
        while(in.read(data) != -1){
            String encoding = System.getProperty("sun.jnu.encoding");
            result.append(new String(data,encoding));
        }
        return result.toString();
    }
	
	/**
     * 获取mac地址
     * @param ip
     * @return
     * @throws Exception 
     */
    public static String getMacAddresstwo(String ip) throws Exception{
        String result = command("ping "+ip+" -n 2");
        if(result.contains("TTL")){
            result = command("arp -a "+ip);
        }
        String regExp = "([0-9A-Fa-f]{2})([-:][0-9A-Fa-f]{2}){5}";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(result);
        StringBuilder mac = new StringBuilder();
        while (matcher.find()) {
            String temp = matcher.group();
            mac.append(temp);
        }
        return mac.toString();
    }
}
