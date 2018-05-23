package com.cmts.xm.utils.code;

public class SignedByteConverter {

	/**
	 * convert signed one byte into a 32-bit integer
	 * @param b byte
	 * @return convert result
	 */
	public static int unsignedByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	/**
	 * convert signed one byte into a hexadecimal digit
	 * @param b byte
	 * @return convert result
	 */
	public static String byteToHex(byte b) {
		int i = b & 0xFF;
		return Integer.toHexString(i);
	}
	
	public static String ByteArrToHexStr(byte[] buf,int iArrLen){
		String sHexMd5Str = "";
		for(int i =0 ; i < iArrLen ;i++ ){
			sHexMd5Str += byteToHex(buf[i]);
		}
		return sHexMd5Str;
	}

	/**
	 * convert unsigned bytes into a 64-bit unsinged long
	 * @param buf bytes buffer
	 * @param pos beginning <code>byte</code>> for converting
	 * @return convert result
	 * @changed by lc 2010.5.27
	 */
	public static long BytesToulong(byte[] buf, int pos) {
		long firstByte = 0;
		long secondByte = 0;
		long thirdByte = 0;
		long fourthByte = 0;
		long fifthtByte = 0;
		long sixthByte = 0;
		long seventhByte = 0;
		long eighthByte = 0;
		int index = pos;
		firstByte   = (0x00000000000000FF & ((long) buf[index]));
		secondByte  = (0x00000000000000FF & ((long) buf[index + 1]));
		thirdByte   = (0x00000000000000FF & ((long) buf[index + 2]));
		fourthByte  = (0x00000000000000FF & ((long) buf[index + 3]));
		//
		fifthtByte  = (0x00000000000000FF & ((long) buf[index + 4]));
		sixthByte   = (0x00000000000000FF & ((long) buf[index + 5]));
		seventhByte = (0x00000000000000FF & ((long) buf[index + 6]));
		eighthByte  = (0x00000000000000FF & ((long) buf[index + 7]));

		return ((long) (firstByte << 56 | secondByte << 48 | thirdByte << 40
				| fourthByte << 32 | fifthtByte << 24 | sixthByte << 16
				| seventhByte << 8 | eighthByte)) & 0x0FFFFFFFFFFFFFFL;//最高位强制为0
	}
}
