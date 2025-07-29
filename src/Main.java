import studentBusiness.StudentBusiness;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("********QUAN LY DANH SACH SINH VIEN********\n" +
                    "1. danh sach sinh vien\n" +
                    "2. them moi sinh vien\n" +
                    "3. cap nhat sinh vien\n" +
                    "4. xoa sinh vien\n" +
                    "5. sap xep sinh vien theo ngay dang ki\n" +
                    "6. tim kiem sinh vien theo ten\n" +
                    "7. thoat\n" +
                    "lua chon cua ban : ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    StudentBusiness.displayStudent();
                    break;
                case 2:
                    StudentBusiness.addStudent(scanner);
                    break;
                case 3:
                    StudentBusiness.updateStudent(scanner);
                    break;
                case 4:
                    StudentBusiness.deleteStudent(scanner);
                    break;
                case 5:
                    StudentBusiness.sortStudentByDateDesc();
                    break;
                case 6:
                    StudentBusiness.findAllStudent(scanner);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("vui long chon 1-7");
            }
        }while (true);
    }
}