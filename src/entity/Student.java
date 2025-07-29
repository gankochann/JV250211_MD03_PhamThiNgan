package entity;

import studentDAO.StudentDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private LocalDate registerDate;
    private boolean status;

    public Student() {
    }

    public Student(int id, String name, String email, String phone, LocalDate registerDate, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.registerDate = registerDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("StudentId : %d | FullName : %s | Email : %s |" +
                "Phone : %s | Date :%s | Status : %s ",
                id,name,email,phone,registerDate,status);
    }

    public void inputData(Scanner scanner){
        this.name = inputName(scanner);
        this.email = inputEmail(scanner);
        this.phone = inputPhone(scanner);
        this.registerDate = inputRegisterDate(scanner);
        this.status = true;
    }

    public String inputName(Scanner scanner){
        System.out.println("moi nhap vao ten : ");
        do {
            String name = scanner.nextLine();
            if(name.isBlank()){
                System.err.println("vui long khong de trong ten!");
            }else {
                return name;
            }
        }while (true);
    }

    public String inputEmail(Scanner scanner){
        System.out.println("moi nhap vao email : ");
        do {
            String regexEmail = "[A-Za-z0-9]{3,}@[A-Za-z0-9]{3,5}\\.[A-Za-z0-9]{2,4}";
            String email = scanner.nextLine();
           if(email.matches(regexEmail)){
               int cntEmail = StudentDAO.checkEmail(email);
               if(cntEmail > 0){
                   System.err.println("email da ton tai!");
               }else {
                   return email;
               }
           }else {
               System.err.println("vui long nhap dung dinh dang email!");
           }

        }while (true);

    }

    public String inputPhone(Scanner scanner){
        System.out.println("moi nhap so dien thoai : ");
        do {
            String regexPhone = "(09|03|02)[\\d]{8,9}";
            String phone = scanner.nextLine();
            if (phone.matches(regexPhone)) {
                return phone;
            } else {
                System.err.println("so dien thoai bat dau bang 09|03|02 va toi da 11 chu so");
            }
        }while (true);
        }

    public LocalDate inputRegisterDate(Scanner scanner){
        System.out.println("moi nhap vao ngay tao 'yyyy-MM-dd':");
        do {
            String birthday = scanner.nextLine();
            if(birthday.isBlank()){
                System.err.println("khong de trong ngay tao");
            }else {
                return LocalDate.parse(birthday , DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
        }while (true);
    }


}
