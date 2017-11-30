package com.skeleton.mvp.util.facebookutil;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Developer: Click Labs
 */
public final class FbUserDetails implements Parcelable {

    /**
     * The constant CREATOR.
     */
    public static final Parcelable.Creator<FbUserDetails> CREATOR = new Parcelable.Creator<FbUserDetails>() {
        @Override
        public FbUserDetails createFromParcel(final Parcel source) {
            return new FbUserDetails(source);
        }

        @Override
        public FbUserDetails[] newArray(final int size) {
            return new FbUserDetails[size];
        }
    };

    private String id, accessToken, firstName, lastName, email, gender, picture;

    /**
     * Instantiates a new Fb user details.
     *
     * @param id          the id
     * @param accessToken the access token
     * @param firstName   the first name
     * @param lastName    the last name
     * @param email       the email
     * @param gender      the gender
     * @param picture     the picture
     */
    public FbUserDetails(final String id,
                         final String accessToken,
                         final String firstName,
                         final String lastName,
                         final String email,
                         final String gender,
                         final String picture) {
        this.id = id;
        this.accessToken = accessToken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.picture = picture;
    }


    /**
     * Instantiates a new Fb user details.
     *
     * @param in the in
     */
    protected FbUserDetails(final Parcel in) {
        this.id = in.readString();
        this.accessToken = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
        this.gender = in.readString();
        this.picture = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.id);
        dest.writeString(this.accessToken);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
        dest.writeString(this.gender);
        dest.writeString(this.picture);
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {

        if (firstName == null) {
            return "";
        }
        return firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        if (lastName == null) {
            return "";
        }
        return lastName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        if (email == null) {
            return "";
        }
        return email;
    }

    /**
     * Gets gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets picture.
     *
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }
}
