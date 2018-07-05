/**
 * 
 */
package com.weibiaogan.litong.utils;

import android.text.Html;
import android.text.Spanned;
import android.widget.EditText;

import com.amap.api.maps2d.model.LatLng;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AMapUtil {
	/**
	 * 判断edittext是否null
	 */
	public static String checkEditText(EditText editText) {
		if (editText != null && editText.getText() != null
				&& !(editText.getText().toString().trim().equals(""))) {
			return editText.getText().toString().trim();
		} else {
			return "";
		}
	}

	public static Spanned stringToSpan(String src) {
		return src == null ? null : Html.fromHtml(src.replace("\n", "<br />"));
	}

	public static String colorFont(String src, String color) {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("<font color=").append(color).append(">").append(src)
				.append("</font>");
		return strBuf.toString();
	}

	public static String makeHtmlNewLine() {
		return "<br />";
	}

	public static String makeHtmlSpace(int number) {
		final String space = "&nbsp;";
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < number; i++) {
			result.append(space);
		}
		return result.toString();
	}



	public static boolean IsEmptyOrNullString(String s) {
		return (s == null) || (s.trim().length() == 0);
	}





	/**
	 * long类型时间格式化
	 */
	public static String convertToTime(long time) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return df.format(date);
	}

	public static final String HtmlBlack = "#000000";
	public static final String HtmlGray = "#808080";
	
	public static String getFriendlyTime(int second) {
		if (second > 3600) {
			int hour = second / 3600;
			int miniate = (second % 3600) / 60;
			return hour + "小时" + miniate + "分钟";
		}
		if (second >= 60) {
			int miniate = second / 60;
			return miniate + "分钟";
		}
		return second + "秒";
	}
	

		
		public static String getSimpleBusLineName(String busLineName) {
			if (busLineName == null) {
				return String.valueOf("");
			}
			return busLineName.replaceAll("\\(.*?\\)", "");
		}
	
}
