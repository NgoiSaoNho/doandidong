package khang.ngodinh.doandidong.Model;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private String image;
    private boolean sex;
    private String address;

    private ClassRoom classRoom;

    public Student(String id, String name, String image, boolean sex) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.sex = sex;
    }

    public Student() {
        classRoom = new ClassRoom();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
