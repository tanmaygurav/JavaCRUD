import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

// Compile -- javac CRUD.java
// Run -- java CRUD

class Employee{
    private int empno;
    private String ename;
    private int salary;

    Employee(int empno,String ename,int salary){
        this.empno=empno;
        this.ename=ename;
        this.salary=salary;
    }

    public int getEmpno(){
        return empno;
    }
    public int getSalary(){
        return salary;
    }
    public String getEname(){
        return ename;
    }

    public String toString(){
        return empno+" "+ename+" "+salary;
    }
}

class CRUD{
    public static void main(String[] args) {

        // database connection
        // Mysql
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "admin");
            Statement stmt = conn.createStatement();
            ResultSet rSet = stmt.executeQuery("select * from employees");
            while (rSet.next()) {
                
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

        List<Employee> c = new ArrayList<Employee>();
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        int ch;
        do{
            System.out.println("1. Insert");
            System.out.println("2. Display");
            System.out.println("3. Search");
            System.out.println("4. Delete");
            System.out.println("5. Update");
            System.out.println("Enter Your Choice : ");
            ch=s.nextInt();

            switch (ch) {
                case 1:
                    // get from user
                    System.out.print("Enter Emp no : ");
                    int empno =s.nextInt();
                    System.out.print("Enter Emp name : ");
                    String ename =s1.nextLine();
                    System.out.print("Enter Emp salary : ");
                    int salary =s.nextInt();

                    // pass to model
                    c.add(new Employee(empno, ename, salary));
                    break;
                case 2:

                    System.out.println("--------------------");
                    Iterator<Employee> i = c.iterator();
                    while (i.hasNext()) {
                        Employee e = i.next();
                        System.out.println(e);
                    }
                    System.out.println("--------------------");
                    break;

                case 3:
                    boolean found=false;
                    System.out.println("Enter Empno to Search : ");
                    empno = s.nextInt();
                    System.out.println("--------------------");
                    i = c.iterator();
                    while (i.hasNext()) {
                        Employee e = i.next();
                        if (e.getEmpno() == empno) {
                            System.out.println(e);
                            found=true;
                        }
                    }
                    if (!found) {
                        System.out.println("Record Not Found");
                    }
                    System.out.println("--------------------");
                    break;

                case 4:
                    found=false;
                    System.out.println("Enter Empno to Delete : ");
                    empno = s.nextInt();
                    System.out.println("--------------------");
                    i = c.iterator();
                    while (i.hasNext()) {
                        Employee e = i.next();
                        if (e.getEmpno() == empno) {
                            i.remove();
                            found=true;
                        }
                    }
                    if (!found) {
                        System.out.println("Record Not Found");
                    }else{
                        System.out.println("Reacor is Deleted Succesfully");
                    }
                    System.out.println("--------------------");
                    break;

                case 5:
                    found=false;
                    System.out.println("Enter Empno to Update : ");
                    empno = s.nextInt();
                    ListIterator<Employee> li = c.listIterator();
                    while (li.hasNext()) {
                        Employee e = li.next();
                        if (e.getEmpno() == empno) {
                            System.out.print("Enter New Name : ");
                            ename=s1.nextLine();
                            System.out.println("Enter New Salary : ");
                            salary=s.nextInt();
                            li.set(new Employee(empno, ename, salary));
                            found=true;
                        }
                    }
                    if (!found) {
                        System.out.println("Record Not Found");
                    }else{
                        System.out.println("Record is Updated Succesfully");
                    }
                    break;
                default:
                    break;
            }

        }while(ch!=0);
    }    
}