package Management;

public class AppConfig {
    public static String votersDataFilePath;
    public static String idsFilePath;
    public static Integer securityGuardNumber = null;
    public static Double timeUntilClosingNumber = null;

    public static void reset() {
        securityGuardNumber = null;
        timeUntilClosingNumber = null;
    }

    public static Boolean isConfigured() {
        return securityGuardNumber != null && timeUntilClosingNumber != null;
    }

    public static Boolean isFilePathsSet() {
        return AppConfig.idsFilePath != "" && AppConfig.votersDataFilePath != "";
    }
}