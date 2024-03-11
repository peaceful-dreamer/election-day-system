package Project;

public class AppConfig {
    public static String votersDataFilePath;
    public static String idsFilePath;
    public static Integer securityGuardNumber = null;
    public static Double timeUntilClosingNumber = null;

    public static Boolean setSecurityGuardNumber(Integer securityGuardNumber) {
        if (securityGuardNumber >= 1 && securityGuardNumber <= 4) {
            AppConfig.securityGuardNumber = securityGuardNumber;
            return true;
        }
        return false;
    }

    public static Boolean setTimeUntilClosingNumber(Double timeUntilClosingNumber) {
        if (timeUntilClosingNumber >= 0.0 && timeUntilClosingNumber <= 24.0) {
            AppConfig.timeUntilClosingNumber = timeUntilClosingNumber;
            return true;
        }
        return false;
    }

    public static void reset() {
        securityGuardNumber = null;
        timeUntilClosingNumber = null;
    }

    public static Boolean isConfigured() {
        return securityGuardNumber != null && timeUntilClosingNumber != null;
    }

    public static void startApp() {
        Integer securityGuardNumber = AppConfig.securityGuardNumber;
        Double timeUntilClosingNumber = AppConfig.timeUntilClosingNumber;

        System.out.println(String.format("%d Security Guards", securityGuardNumber));
        System.out.println(String.format("%.1f Seconds Until Close",
                timeUntilClosingNumber));

        new SystemManager(AppConfig.votersDataFilePath, AppConfig.idsFilePath,
                securityGuardNumber,
                timeUntilClosingNumber);
    }

    public static Boolean isFilePathsSet() {
        return AppConfig.idsFilePath != "" && AppConfig.votersDataFilePath != "";
    }
}