package starter.tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.annotations.Subject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static starter.Constants.WAIT_DURATION;

@Subject("Verify if file '#fileName' is downloaded")
public class VerifyFileDownloaded implements Task {
    private final String fileName;
    private final String downloadPath;

    public VerifyFileDownloaded(String fileName, String downloadPath) {
        this.fileName = fileName;
        this.downloadPath = downloadPath;
    }

    public static VerifyFileDownloaded withName(String fileName) {
        String defaultDownloadPath = System.getProperty("user.home") + "/Downloads";
        return instrumented(VerifyFileDownloaded.class, fileName, defaultDownloadPath);
    }

    public static VerifyFileDownloaded withNameInRemotePath(String fileName) {
        String remoteDownloadPath = "/home/seluser/Downloads";
        return instrumented(VerifyFileDownloaded.class, fileName, remoteDownloadPath);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Path filePath = Paths.get(downloadPath, fileName);
        Instant end = Instant.now().plus(WAIT_DURATION);
        boolean fileExists = false;

        while (Instant.now().isBefore(end)) {
            if (Files.exists(filePath)) {
                fileExists = true;
                break;
            }
        }

        if (!fileExists) {
            throw new AssertionError("File '" + fileName + "' was not downloaded to " + downloadPath);
        } else {
            Serenity.recordReportData().withTitle("Download Status")
                    .andContents("File '" + fileName + "' successfully downloaded to " + downloadPath);
        }
    }
}
