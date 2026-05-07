public class main {
    public static void main(String[] args) throws Exception {
        new ProcessBuilder("javac", "-d", "out", "-sourcepath", "src", "src\\Main.java")
                .inheritIO()
                .start()
                .waitFor();

        new ProcessBuilder("java", "-cp", "out", "Main")
                .inheritIO()
                .start()
                .waitFor();
    }
}