package company;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Repository repo = new Repository();
        repo.createTableIfNotExist();

        // 10 sample students using Builder pattern
        insertSampleStudents(repo);

        // Retrieve and print the master list
        List<Student> students = repo.getAllStudents();
        repo.listStudents(students);

        repo.closeResources();
    }

    private static void insertSampleStudents(Repository repo) {
        Student[] samples = {
                createStudent("S001", "John", "Cena", 20, "Male", "Computer Science", "Sophomore", "123-456-7890"),
                createStudent("S002", "Aron", "Fulay", 20, "Male", "Information Technology", "Sophomore", "092-658-2881"),
                createStudent("S003", "Jhustine", "Genora", 19, "Male", "Mathematics", "Sophomore", "345-678-9012"),
                createStudent("S004", "Julie", "Brown", 22, "Female", "Physics", "Senior", "456-789-0123"),
                createStudent("S005", "Charlie", "Davis", 20, "Male", "Engineering", "Junior", "567-890-1234"),
                createStudent("S006", "Reign", "Evans", 21, "Female", "Chemistry", "Sophomore", "678-901-2345"),
                createStudent("S007", "Rhainier", "Foster", 18, "Male", "History", "Freshman", "789-012-3456"),
                createStudent("S008", "Kenjay", "Green", 23, "Memale", "Literature", "Senior", "890-123-4567"),
                createStudent("S009", "Christopher", "Harris", 20, "Male", "Business", "Sophomore", "901-234-5678"),
                createStudent("S010", "Kyrie", "Irving", 22, "Male", "Art", "Junior", "012-345-6789")
        };

        for (Student s : samples) {
            repo.insertStudent(s);
        }
    }

    private static Student createStudent(String id, String first, String last, int age,
                                         String gender, String course, String year, String contact) {
        return new Student.Builder()
                .withStudentId(id)
                .withFirstName(first)
                .withLastName(last)
                .withAge(age)
                .withGender(gender)
                .withCourse(course)
                .withYearLevel(year)
                .withContactNumber(contact)
                .build();
    }
}