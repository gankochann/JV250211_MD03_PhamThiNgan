package studentBusiness;

import entity.Student;
import studentDAO.StudentDAO;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentBusiness {
    public static void displayStudent(){
        List<Student> listStudent = StudentDAO.findAll();
        if(listStudent.isEmpty()){
            System.err.println("danh sach sinh vien trong!");
        }else {
            listStudent.forEach(System.out::println);
        }
    }

    public static void addStudent(Scanner scanner){
        Student student = new Student();
        student.inputData(scanner);
        boolean result = StudentDAO.add(student);
        if (result) {
            System.out.println("them moi sinh vien thanh cong!");
        }else {
            System.err.println("co loi trong qua trinh them moi!");
        }
    }

    public static void updateStudent(Scanner scanner){
        System.out.println("moi nhap vao id sinh vien can update : ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = StudentDAO.findStudentById(id);
        if(student != null){
            boolean isExit = true;
            do {
                System.out.println("chon danh muc muon cap nhat : \n" +
                        "1. ten\n" +
                        "2. email\n" +
                        "3. phone\n" +
                        "4.date\n" +
                        "5. status\n" +
                        "6. thoat \n" +
                        "lua chon cua ban : ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice){
                    case 1:
                        student.setName(student.inputName(scanner));
                        break;
                    case 2:
                        student.setEmail(student.inputEmail(scanner));
                        break;
                    case 3:
                        student.setPhone(student.inputPhone(scanner));
                        break;
                    case 4:
                        student.setRegisterDate(student.inputRegisterDate(scanner));
                        break;
                    case 5:
                        System.out.println("trang thai moi ");
                        student.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 6:
                      isExit = false;
                      break;
                    default:
                        System.err.println("vui long chon 1-6");
                }
            }while (isExit);

            boolean result = StudentDAO.update(student);
            if(result){
                System.out.println("cap nhat thanh cong!");
            }else {
                System.err.println("co loi xay ra");
            }
        }
    }

    public static void deleteStudent(Scanner scanner){
        System.out.println("moi nhap vao ma sinh vien can xoa : ");
        int id = Integer.parseInt(scanner.nextLine());
        Student student = StudentDAO.findStudentById(id);
        if(student != null){
            System.out.println("ban co chac muon xoa khong ?\n" +
                    "chon yes | no ");
            String choice = scanner.nextLine();
            boolean isExit = true;
            if(choice.equalsIgnoreCase("yes")){
                boolean result = StudentDAO.delete(id);
                if(result){
                    System.out.println("xoa thanh cong!");
                }else {
                    System.err.println("co loi xay ra trong qua trinh xoa!");
                }
            }
            else {
                isExit = false;
            }
        }else {
            System.err.println("khong tim thay sinh vien nao phu hop");
        }
    }

    public static void findAllStudent(Scanner scanner){
        System.out.println("nhap vao ten sinh vien can tim kiem : ");
        String name = scanner.nextLine();
        List<Student> list = StudentDAO.searchStudentByName(name);
        if(list.isEmpty()){
            System.err.println("khong tim thay ten sinh vien phu hop");
        }else {
            System.out.println("danh sach sinh vien tim thay la :");
            list.forEach(System.out::println);
        }
    }

    public static void sortStudentByDateDesc(){
        List<Student> list = StudentDAO.findAll();
        System.out.println("danh sach sinh vien sap xep theo ngay thang giam dan la ");
        list.stream().sorted(Comparator.comparing(Student::getRegisterDate).reversed()).forEach(System.out::println);
    }
}
