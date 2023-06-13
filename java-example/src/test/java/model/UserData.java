package model;

import com.google.gson.annotations.Expose;

public class UserData {

  //private int id; //= Integer.MAX_VALUE;

  @Expose
  private String firstName;
  @Expose
  private String lastName;
  @Expose
  private String address1;
  @Expose
  private String phone;
  @Expose
  private String email;
  @Expose
  private String postcode;
  @Expose
  private String city;
  @Expose
  private String password;

  public String getFirstName() {
    return firstName;
  }

  public UserData setFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public UserData setLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getAddress1() {
    return address1;
  }

  public UserData setAddress1(String address1) {
    this.address1 = address1;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public UserData setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserData setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPostcode() {
    return postcode;
  }

  public UserData setPostcode(String postcode) {
    this.postcode = postcode;
    return this;
  }

  public String getCity() {
    return city;
  }

  public UserData setCity(String city) {
    this.city = city;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData setPassword(String password) {
    this.password = password;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address1='" + address1 + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", postcode='" + postcode + '\'' +
            ", city='" + city + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}