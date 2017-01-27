package com.fundoohr.bridgeit.fundoohr.model;

/**
 * Created by bridgeit on 12/1/17.
 */
public class ProfileDetailsModel {
    String diploma;
    String degree;
    String discipline;
    String yearOfPassing;
    String aggregateIn;
    String finalYear;
    String trainingInstitute;
    String trainingDuration;
    String training;

    public ProfileDetailsModel() {
    }

    public ProfileDetailsModel(String diploma, String degree, String discipline,
                               String yearOfPassing, String aggregateIn, String finalYear,
                               String trainingInstitute, String trainingDuration, String training) {
        this.diploma = diploma;
        this.degree = degree;
        this.discipline = discipline;
        this.yearOfPassing = yearOfPassing;
        this.aggregateIn = aggregateIn;
        this.finalYear = finalYear;
        this.trainingInstitute = trainingInstitute;
        this.trainingDuration = trainingDuration;
        this.training = training;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getYearOfPassing() {
        return yearOfPassing;
    }

    public void setYearOfPassing(String yearOfPassing) {
        this.yearOfPassing = yearOfPassing;
    }

    public String getAggregateIn() {
        return aggregateIn;
    }

    public void setAggregateIn(String aggregateIn) {
        this.aggregateIn = aggregateIn;
    }

    public String getFinalYear() {
        return finalYear;
    }

    public void setFinalYear(String finalYear) {
        this.finalYear = finalYear;
    }

    public String getTrainingInstitute() {
        return trainingInstitute;
    }

    public void setTrainingInstitute(String trainingInstitute) {
        this.trainingInstitute = trainingInstitute;
    }

    public String getTrainingDuration() {
        return trainingDuration;
    }

    public void setTrainingDuration(String trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }
}