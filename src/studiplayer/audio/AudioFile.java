import java.io.File;
import java.util.Objects;

public abstract class AudioFile {
    private String filename;
    private String pathname;
    private String author = "";
    private String title = "";

    //BABYLON-player, only engage if BABYLON mp3-data is provided or failsafe is implemented
    //private boolean babylon = false;

    public AudioFile() {

    }

    public AudioFile(String path11) {
        parsePathname(path11);
        parseFilename(getFilename());

        File a = new File(pathname);
        boolean cR = a.canRead();
        if (!cR) {
            throw new RuntimeException("Chef, wo Mukke");
        }
    }

    public void parsePathname(String path2) {
        pathname = path2;
        if (pathname == "") {
            filename = "";
        } else {
            boolean end = false;
            char[] path1 = pathname.toCharArray();
            pathname = "";
            for (int i = 0; i < path1.length; i++) {
                if (path1[i] == '/') {
                    path1[i] = '\\';
                }

                String p1 = String.valueOf(path1[i]);
                pathname = pathname + p1;

            }
            if (path1[path1.length - 1] == '\\') {
                end = true;
            }
            String[] paths = pathname.split("\\\\+");
            filename = paths[paths.length - 1];
            pathname = "";
            if (!isWindows()) {
                if (paths.length > 1)
                    if (paths[0].length() > 0) {
                        if (paths[0].charAt(paths[0].length() - 1) == ':') {
                            String[] p3 = paths[0].split(":");
                            paths[0] = "/" + p3[0];
                        }
                    }
            }
            for (int i = 0; i < paths.length - 1; i++) {
                pathname = pathname + paths[i] + "\\";
            }

            path1 = pathname.toCharArray();
            pathname = "";
            for (int i = 0; i < path1.length; i++) {
                if (path1[i] == '\\') {
                    path1[i] = '\\';
                }
                if (path1[i] != ' ') {
                    String p1 = String.valueOf(path1[i]);
                    pathname = pathname + p1;
                }
            }

            if (!Objects.equals(filename, " -")) {
                filename = filename.trim();
            }
            pathname = pathname + filename;
            filename = filename.trim();

            if (end) {
                pathname = pathname + "\\";
                filename = "";
            }
            if (!isWindows()) {
                char[] path4 = pathname.toCharArray();
                pathname = "";
                for (int i = 0; i < path4.length; i++) {
                    if (path4[i] == '\\') {
                        path4[i] = '/';
                    }
                    String p2 = String.valueOf(path4[i]);
                    pathname = pathname + p2;
                }
            }
        }
    }

    private boolean isWindows() {
        return System.getProperty("os.name").toLowerCase()
                .indexOf("win") >= 0;
    }

    public void parseFilename(String file) {
        String filename = file;
        String[] file1 = filename.split("\\.");
        if (file1.length > 1) {
            filename = "";
            for (int i = 0; i < file1.length - 1; i++) {
                filename = filename + file1[i] + ".";
            }
        }
        char[] file2 = filename.toCharArray();
        if (file2.length > 0) {
            if (file2[file2.length - 1] == '.') {
                filename = "";
                for (int i = 0; i < file2.length - 1; i++) {
                    filename = filename + file2[i];
                }
            }
        }

        String[] file3 = filename.split(" - ");
        if (file3.length == 1) {
            author = "";
            title = file3[0];
        } else if (file3.length == 0) {
            author = "";
            title = "";
        } else {
            author = file3[0];
            title = file3[1];
        }
        author = author.trim();
        title = title.trim();
    }

    public String toString() {
        if (getAuthor() == "") {
            return getTitle();
        } else {
            return getAuthor() + " - " + getTitle();
        }
    }

    public abstract void play();

    public abstract void togglePause();

    public abstract void stop();

    public abstract String formatDuration();

    public abstract String formatPosition();

    public String getFilename() {
        return filename;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String e) {
        if (e != null) {
            author = e;
        }
    }

    public void setTitle(String e) {
        if (e != null) {
            title = e;
        }
    }

    public String getTitle() {
        return title;
    }

    public String getPathname() {
        return pathname;
    }

//    iltam zumrā rašubti ilātim
//    litta''id bēlet iššī rabīt igigī
//    ištar zumrā rašubti ilātim
//    litta''id bēlet ilī nišī rabīt igigī
//
//    šāt mēleṣim ruāmam labšat
//    za'nat inbī mīkiam u kuzbam
//    šāt mēleṣim ruāmam labšat
//    za'nat inbī mīkiam u kuzbam
//
//    šaptīn duššupat balāṭum pīša
//    simtišša ihannīma ṣīhātum
//    šarhat irīmū ramû rēšušša
//    banâ šimtāša bitrāmā īnāša šitārā
//
//    eltum ištāša ibašši milkum
//    šīmat mimmami qatišša tamhat
//    naplasušša bani bu'āru
//    baštum mašrahu lamassum šēdum
//
//    tartāmī tešmê ritūmī ṭūbī
//    u mitguram tebēl šīma
//    ardat tattadu umma tarašši
//    izakkarši innišī innabbi šumša
//
//    ayyum narbiaš išannan mannum
//    gašrū ṣīrū šūpû parṣūša
//    ištar narbiaš išannan mannum
//    gašrū ṣīrū šūpû parṣūša
//
//    gaṣṣat inilī atar nazzazzuš
//    kabtat awassa elšunu haptatma
//    ištar inilī atar nazzazzuš
//    kabtat awassa elšunu haptatma
//
//    šarrassun uštanaddanū siqrīša
//    kullassunu šâš kamsūšim
//    nannarīša illakūši
//    iššû u awīlum palhūšīma
//
//    puhriššun etel qabûša šūtur
//    ana anim šarrīšunu malâm ašbassunu
//    uznam nēmeqim hasīsam eršet
//    imtallikū šī u hammuš
//
//    ramûma ištēniš parakkam
//    iggegunnim šubat rīšātim
//    muttiššun ilū nazzuizzū
//    epšiš pîšunu bašiā uznāšun
//
//    šarrum migrašun narām libbīšun
//    šarhiš itnaqqišunūt niqi'ašu ellam
//    ammiditāna ellam niqī qātīšu
//    mahrīšun ušebbi li'ī u yâlī namrā'i
//
//    išti anim hāmerīša tēteršaššum
//    dāriam balāṭam arkam
//    madātim šanāt balāṭim ana ammiditāna
//    tušatlim ištar tattadin
//
//    siqrušša tušaknišaššu
//    kibrat erbe'im ana šēpīšu
//    u naphar kalīšunu dadmī
//    taṣammissunūti ana nīrīšu
//
//    bibil libbīša zamar lalêša
//    naṭumma ana pîšu siqri ea īpuš
//    ešmēma tanittaša irissu
//    libluṭmi šarrašu lirāmšu addāriš
//
//    ištar ana ammiditāna šarri rā'imīki
//    arkam dāriam balāṭam šurqī
}
