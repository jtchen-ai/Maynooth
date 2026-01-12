package experiment8_2;

public class Test {
    public static void main(String[] args) {
        SpeakChinese chineseSpeaker = new ChineseSpeaker();
        SpeakEnglish adapter = new LanguageAdapter(chineseSpeaker);
        adapter.speakEnglish();
    }
}
