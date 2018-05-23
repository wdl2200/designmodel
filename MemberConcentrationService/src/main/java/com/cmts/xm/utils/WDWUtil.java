/* ========================================================
* 哈尔滨富利通科技有限公司研发二部
* 日 期：2015-11-24
* 功能：EXCEL工具类
* 作 者：李勋迎
* 版 本：1.0.0
* =========================================================
*/
package com.cmts.xm.utils;

public class WDWUtil {
	  // @描述：是否是2003的excel，返回true是2003 
    public static boolean isExcel2003(String filePath)  {  
        return filePath.matches("^.+\\.(?i)(xls)$");  
    }  
  
     //@描述：是否是2007的excel，返回true是2007 
    public static boolean isExcel2007(String filePath)  {  
        return filePath.matches("^.+\\.(?i)(xlsx)$");  
    }  
}

