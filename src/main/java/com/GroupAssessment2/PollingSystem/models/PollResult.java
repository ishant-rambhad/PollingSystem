package com.GroupAssessment2.PollingSystem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "poll_result")
public class PollResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    private Poll poll;

    private String title;
    private String question;
    private String username;
    private String email;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private String option7;
    private String option8;
    private String option9;
    private String option10;

    private int countoption1 = 0;
    private int countoption2 = 0;
    private int countoption3 = 0;
    private int countoption4 = 0;
    private int countoption5 = 0;
    private int countoption6 = 0;
    private int countoption7 = 0;
    private int countoption8 = 0;
    private int countoption9 = 0;
    private int countoption10 = 0;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    public String getOption6() {
        return option6;
    }

    public void setOption6(String option6) {
        this.option6 = option6;
    }

    public String getOption7() {
        return option7;
    }

    public void setOption7(String option7) {
        this.option7 = option7;
    }

    public String getOption8() {
        return option8;
    }

    public void setOption8(String option8) {
        this.option8 = option8;
    }

    public String getOption9() {
        return option9;
    }

    public void setOption9(String option9) {
        this.option9 = option9;
    }

    public String getOption10() {
        return option10;
    }

    public void setOption10(String option10) {
        this.option10 = option10;
    }

    public int getCountoption1() {
        return countoption1;
    }

    public void setCountoption1(int countoption1) {
        this.countoption1 = countoption1;
    }

    public int getCountoption2() {
        return countoption2;
    }

    public void setCountoption2(int countoption2) {
        this.countoption2 = countoption2;
    }

    public int getCountoption3() {
        return countoption3;
    }

    public void setCountoption3(int countoption3) {
        this.countoption3 = countoption3;
    }

    public int getCountoption4() {
        return countoption4;
    }

    public void setCountoption4(int countoption4) {
        this.countoption4 = countoption4;
    }

    public int getCountoption5() {
        return countoption5;
    }

    public void setCountoption5(int countoption5) {
        this.countoption5 = countoption5;
    }

    public int getCountoption6() {
        return countoption6;
    }

    public void setCountoption6(int countoption6) {
        this.countoption6 = countoption6;
    }

    public int getCountoption7() {
        return countoption7;
    }

    public void setCountoption7(int countoption7) {
        this.countoption7 = countoption7;
    }

    public int getCountoption8() {
        return countoption8;
    }

    public void setCountoption8(int countoption8) {
        this.countoption8 = countoption8;
    }

    public int getCountoption9() {
        return countoption9;
    }

    public void setCountoption9(int countoption9) {
        this.countoption9 = countoption9;
    }

    public int getCountoption10() {
        return countoption10;
    }

    public void setCountoption10(int countoption10) {
        this.countoption10 = countoption10;
    }
    public int getTotalVotes() {
        return countoption1 + countoption2 + countoption3 + countoption4 + countoption5 +
               countoption6 + countoption7 + countoption8 + countoption9 + countoption10;
    }
    
}
