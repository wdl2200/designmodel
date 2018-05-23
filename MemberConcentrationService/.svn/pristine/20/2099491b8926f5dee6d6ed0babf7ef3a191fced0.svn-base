/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2015-12-7
* 功能：
* 作 者：苏少轩
* 版 本：1.0
* =========================================================
*/
package com.cmts.xm.utils;

import java.awt.Color;

import jxl.format.Colour;

public class ColorUtil { 
    
    // 把字符串表达的颜色值转换成java.awt.Color 
    public static Color parseToColor(String c) { 
        Color convertedColor = Color.ORANGE; 
        try { 
            convertedColor = new Color(Integer.parseInt(c, 8)); 
        } catch (NumberFormatException e) { 
            e.getStackTrace(); 
        } 
        return convertedColor; 
    } 
        
    public static Colour getNearestColour(Color awtColor) { 
        Colour color = null; 
        Colour[] colors = Colour.getAllColours(); 
        if ((colors != null) && (colors.length > 0)) { 
            Colour crtColor = null; 
            int[] rgb = null; 
            int diff = 0; 
            int minDiff = 999; 
            for (int i = 0; i < colors.length; i++) { 
                crtColor = colors[i]; 
                rgb = new int[3]; 
                rgb[0] = crtColor.getDefaultRGB().getRed(); 
                rgb[1] = crtColor.getDefaultRGB().getGreen(); 
                rgb[2] = crtColor.getDefaultRGB().getBlue(); 
                diff = Math.abs(rgb[0] - awtColor.getRed()) 
                + Math.abs(rgb[1] - awtColor.getGreen()) 
                + Math.abs(rgb[2] - awtColor.getBlue()); 
                if (diff < minDiff) { 
                    minDiff = diff; 
                    color = crtColor; 
                } 
            } 
        } 
        if (color == null) 
            color = Colour.BLACK; 
        return color; 
    } 
    
    // Color转换为16进制显示 
    public static String toHexEncoding(Color color) { 
        String R, G, B; 
        StringBuffer sb = new StringBuffer(); 
        R = Integer.toHexString(color.getRed()); 
        G = Integer.toHexString(color.getGreen()); 
        B = Integer.toHexString(color.getBlue()); 
        R = R.length() == 1 ? "0" + R : R; 
        G = G.length() == 1 ? "0" + G : G; 
        B = B.length() == 1 ? "0" + B : B; 
        sb.append("0x"); 
        sb.append(R); 
        sb.append(G); 
        sb.append(B); 
        return sb.toString(); 
    } 
    
    public static Colour getColour(final String c) { 
        Color cl = parseToColor(c); 
        return getNearestColour(cl); 
    } 
    
    public static String convertRGBToHex(int r, int g, int b) {    
        String rFString, rSString, gFString, gSString, bFString, bSString, result;
        int red, green, blue;
        int rred, rgreen, rblue;
        red = r / 16;
        rred = r % 16;
        if (red == 10) rFString = "A";
        else if (red == 11) rFString = "B";
        else if (red == 12) rFString = "C";
        else if (red == 13) rFString = "D";
        else if (red == 14) rFString = "E";
        else if (red == 15) rFString = "F";
        else rFString = String.valueOf(red);

        if (rred == 10) rSString = "A";
        else if (rred == 11) rSString = "B";
        else if (rred == 12) rSString = "C";
        else if (rred == 13) rSString = "D";
        else if (rred == 14) rSString = "E";
        else if (rred == 15) rSString = "F";
        else rSString = String.valueOf(rred);

        rFString = rFString + rSString;

        green = g / 16;
        rgreen = g % 16;

        if (green == 10) gFString = "A";
        else if (green == 11) gFString = "B";
        else if (green == 12) gFString = "C";
        else if (green == 13) gFString = "D";
        else if (green == 14) gFString = "E";
        else if (green == 15) gFString = "F";
        else gFString = String.valueOf(green);

        if (rgreen == 10) gSString = "A";
        else if (rgreen == 11) gSString = "B";
        else if (rgreen == 12) gSString = "C";
        else if (rgreen == 13) gSString = "D";
        else if (rgreen == 14) gSString = "E";
        else if (rgreen == 15) gSString = "F";
        else gSString = String.valueOf(rgreen);

        gFString = gFString + gSString;

        blue = b / 16;
        rblue = b % 16;

        if (blue == 10) bFString = "A";
        else if (blue == 11) bFString = "B";
        else if (blue == 12) bFString = "C";
        else if (blue == 13) bFString = "D";
        else if (blue == 14) bFString = "E";
        else if (blue == 15) bFString = "F";
        else bFString = String.valueOf(blue);

        if (rblue == 10) bSString = "A";
        else if (rblue == 11) bSString = "B";
        else if (rblue == 12) bSString = "C";
        else if (rblue == 13) bSString = "D";
        else if (rblue == 14) bSString = "E";
        else if (rblue == 15) bSString = "F";
        else bSString = String.valueOf(rblue);
        bFString = bFString + bSString;
        result = "#" + rFString + gFString + bFString;
        return result;

    }
    
    /**
     * 六位颜色(不带#)转成八位不带#
     * @param color
     * @return
     */
    public static String sixToEight(String color){
    	if(color.length() >= 6){
    		String one = color.substring(0, 2);
    		String two = color.substring(2, 4);
    		String three = color.substring(4, 6);    	
        	return "00" + three + two + one;
    	}
    	return "";
    }
    
    /**
     * 八位颜色转成六位带#
     * @param color
     * @return
     */
    public static String eightToSix(String color){
    	if(color.length() >= 8){
    		color = color.substring(2);
    		String one = color.substring(0,2);
    		String two = color.substring(2,4);
    		String three = color.substring(4,6);
        	return "#"+three+two+one;
    	}
    	return "";
    }
    
    
    //测试 
    public static void main(String[] args){ 
//      Color cs = ColorUtil.parseToColor("0000D5D5"); 
//      System.out.println(cs); 
//      
      String v = convertRGBToHex(255,200,0);
      System.out.println(v);
     
      Color cs = ColorUtil.parseToColor("00DF209C"); 
      
    } 
    
}