package company;

public class Student {
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String gender;
    private final String course;
    private final String yearLevel;
    private final String contactNumber;

    private Student(Builder builder) {
        this.studentId = builder.studentId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.gender = builder.gender;
        this.course = builder.course;
        this.yearLevel = builder.yearLevel;
        this.contactNumber = builder.contactNumber;
    }

    // Getters (unchanged)
    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCourse() {
        return course;
    }

    public String getYearLevel() {
        return yearLevel;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", course='" + course + '\'' +
                ", yearLevel='" + yearLevel + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String studentId;
        private String firstName;
        private String lastName;
        private int age;
        private String gender;
        private String course;
        private String yearLevel;
        private String contactNumber;

        public Builder withStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withAge(int age) {
            this.age = age;
            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder withCourse(String course) {
            this.course = course;
            return this;
        }

        public Builder withYearLevel(String yearLevel) {
            this.yearLevel = yearLevel;
            return this;
        }

        public Builder withContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
            return this;
        }

        public Student build() {
            if (studentId == null || firstName == null || lastName == null || gender == null || course == null || yearLevel == null || contactNumber == null) {
                throw new IllegalStateException("All fields are required.");
            }
            if (age <= 0) {
                throw new IllegalStateException("Age must be positive.");
            }
            return new Student(this);
        }
    }
}
