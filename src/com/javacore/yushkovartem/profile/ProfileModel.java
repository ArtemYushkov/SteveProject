package com.javacore.yushkovartem.profile;

import com.javacore.yushkovartem.common.BaseModel;
import com.javacore.yushkovartem.db.Record;

import java.util.Date;

public class ProfileModel extends BaseModel {
    private int id;
    private String firstName;
    private String lastName;
    private String nickname;
    private int numberOfCrimes;
    private Date dateOfBirth;
    private boolean deceased;
    private String description;
    private String placeOfBirth;
    private Date dateOfDeath;
    private String placeOfDeath;


    public static ProfileModel randomModel() {
        int rId =(int) (10 * Math.random());
        boolean rActive = rId % 2 == 0;
        String rFirstName = "Steve_" + rId;
        String rLastName = "Balmer_" + rId;

        return new ProfileModel(rId, rFirstName, rLastName);
    }

    public ProfileModel() {};

    public static ProfileModel modelFromRecord (Record record) throws Record.FieldNotFoundException {
        ProfileModel model = new ProfileModel();
        try {
            model.setDeceased(record.getBoolean("deceased"))
                    .setId(record.getInt("id"));


        } catch (Exception e) {

        }
        return model;
    }



    public ProfileModel(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNumberOfCrimes() {
        return numberOfCrimes;
    }

    public void setNumberOfCrimes(int numberOfCrimes) {
        this.numberOfCrimes = numberOfCrimes;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isDeceased() {
        return deceased;
    }

    public ProfileModel setDeceased(boolean deceased) {
        this.deceased = deceased;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }
}
