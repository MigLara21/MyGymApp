package com.example.mygymclub.models;

public class UserMapper {
    private IUser user;

    public UserMapper(IUser user) {
        this.user = user;
    }

    public User toBase() {
        User user = new User(
                this.user.getUserName(),
                this.user.getFirstName(),
                this.user.getLastName(),
                this.user.getBirthday(),
                this.user.getHeight()
        );
        user.setPassword(this.user.getPassword());
        user.setId(this.user.getId());
        return user;
    }

    public UserEntity toEntity() {
        return new UserEntity(
                this.user.getId(),
                this.user.getUserName(),
                this.user.getFirstName(),
                this.user.getLastName(),
                this.user.getBirthday(),
                this.user.getHeight(),
                this.user.getPassword()
        );
    }

}



