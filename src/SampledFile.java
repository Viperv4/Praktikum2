import studiplayer.basic.BasicPlayer;

import static studiplayer.basic.BasicPlayer.getPosition;

public abstract class SampledFile extends AudioFile {

    BasicPlayer player = new studiplayer.basic.BasicPlayer();
    private String pathname;
    private long duration;

    public SampledFile() {
    }

    public SampledFile(String path) {
        super(path);
        pathname = path;
    }

    public void play() {
        player.play(pathname);
    }

    public void togglePause() {
        player.togglePause();
    }

    public void stop() {
        player.stop();
    }

    public String formatDuration() {
        return timeFormatter(getDuration());
    }

    public String formatPosition() {
        return timeFormatter(getPosition());
    }

    public static String timeFormatter(long timeInMicroSeconds) {
        long fD = timeInMicroSeconds;
        String min1;
        String sek1;
        if (fD < 0) {
            throw new RuntimeException("Chef, Laufzeit ist negativ");
        }
        fD = fD / 1000000;
        if (fD >= 6000) {
            throw new RuntimeException("Chef, Laufzeit zu lang");
        }
        int min = (int) (fD / 60);
        int sek = (int) (fD - min * 60);
        if (min < 10) {
            min1 = "0" + String.valueOf(min);
        } else {
            min1 = String.valueOf(min);
        }
        if (sek < 10) {
            sek1 = "0" + String.valueOf(sek);
        } else {
            sek1 = String.valueOf(sek);
        }
        return min1 + ":" + sek1;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long e) {
        duration = e;
    }
}
