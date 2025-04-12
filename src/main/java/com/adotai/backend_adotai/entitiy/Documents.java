package com.adotai.backend_adotai.entitiy;

public class Documents {
    private String boardMeeting;
    private String socialStatute;

    public Documents() {}

    public Documents(String boardMeeting, String socialStatute) {
        this.boardMeeting = boardMeeting;
        this.socialStatute = socialStatute;
    }

    public String getBoardMeeting() {
        return boardMeeting;
    }

    public void setBoardMeeting(String boardMeeting) {
        this.boardMeeting = boardMeeting;
    }

    public String getSocialStatute() {
        return socialStatute;
    }

    public void setSocialStatute(String socialStatute) {
        this.socialStatute = socialStatute;
    }
}
