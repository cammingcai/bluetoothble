package com.example.admin.mybledemo;

import android.content.Intent;
import android.view.View;

/**
 * 静态数据类，用于保存全局静态变量
 * Created by LiuLei on 2016/9/25.
 */
public class StaticValue {

	public static final String KEY_PATTERN_SHA1 = "pref_key_pattern_sha1";
	public static final String DEFAULT_PATTERN_SHA1 = null;

	public static final String IS_PSW_OPEN = "is_psw_open";//是否打开密码图案验证

	//状态栏和toolbar的颜色
	public static final String TOOL_COLOR = "tool_color";
	public static int THEME_MODE = 0;//主题模式   默认是正常   1为夜间模式

	public final static String OTA_OLD_PATH = "old_ota.bin";//需要更新老版本固件的本地路径
	public final static String OTA_NEW_PATH = "new_ota.bin";//需要更新新版本固件的本地路径
	public final static String IS_FIRST_RUN = "isFirst";//程序是否是第一次运行

}
