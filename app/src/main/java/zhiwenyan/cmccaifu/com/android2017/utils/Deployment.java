package zhiwenyan.cmccaifu.com.android2017.utils;

public class Deployment {


    public static boolean isRelease = false;


    public static boolean showLoading = true;

    public static boolean showGuide = true;

    public static boolean logEnabled = !isRelease;

    public static boolean supportInterruptDebugMode = isRelease;

    public static boolean supportCheckSignKey = isRelease;

    public static boolean supportPush = isRelease;

    public static boolean supportStat = isRelease;
}
