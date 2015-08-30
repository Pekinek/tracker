package pl.mocek.tracker.utils;

/**
 * Created by Michał on 2015-08-07.
 */
public class OsUtils {
    private static String OS;
    public static String getOsName(){
        if(OS == null){
            OS = System.getProperty("os.name");
        }
        return OS;
    }
    public static boolean isWindows(){
        return getOsName().startsWith("Windows");
    }

    public static boolean isLinux(){
        return getOsName().startsWith("Linux");
    }
}
