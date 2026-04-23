import java.util.Scanner;

class Bmi {

    // Constants
    private static final String OBESE         = "Obese";
    private static final String OVERWEIGHT    = "Overweight";
    private static final String NORMAL_WEIGHT = "Normal weight";
    private static final String UNDERWEIGHT   = "Underweight";

    // Fields (all private - encapsulation)
    private final String name;
    private final int    age;
    private final double weight;
    private final double height;
    private final String unit;
    private final String gender;

    // Constructor
    public Bmi(String name, int age, double weight, double height, String unit, String gender) {
        this.name   = name;
        this.age    = age;
        this.weight = weight;
        this.height = height;
        this.unit   = unit.toLowerCase();
        this.gender = gender.toUpperCase();
        validate(); // gate — reject bad data before object is fully created
    }

    // ── Getters ──────────────────────────────────────────────
    public String getName()   { return name; }
    public int    getAge()    { return age; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getUnit()   { return unit; }
    public String getGender() { return gender; }

    // ── Private validators (internal helpers — hidden) ────────
    private boolean isValidName()   { return name != null && !name.trim().isEmpty(); }
    private boolean isValidAge()    { return age >= 1 && age <= 120; }
    private boolean isValidGender() { return gender.equals("M") || gender.equals("F"); }
    private boolean isValidUnit()   { return unit.equals("metric") || unit.equals("imperial"); }

    // Gateway — all checks in one place, each independent
    private void validate() {
        if (!isValidName())
            throw new IllegalArgumentException("Name cannot be empty.");
        if (!isValidAge())
            throw new IllegalArgumentException("Age must be 1–120. Got: " + age);
        if (!isValidGender())
            throw new IllegalArgumentException("Gender must be M or F. Got: " + gender);
        if (!isValidUnit())
            throw new IllegalArgumentException("Unit must be metric or imperial. Got: " + unit);
        if (weight <= 0)
            throw new IllegalArgumentException("Weight must be positive. Got: " + weight);
        if (height <= 0)
            throw new IllegalArgumentException("Height must be positive. Got: " + height);
    }

    // ── Core calculation (uses object's own fields) ───────────
    public double calculateBmi() {
        if (unit.equals("metric")) {
            return weight / (height * height);          // kg / m²
        } else {
            return (weight / (height * height)) * 703;  // lbs / in²
        }
    }

    // Overloaded version — "what if" BMI with custom values
    public double calculateBmi(double customWeight, double customHeight) {
        if (unit.equals("metric")) {
            return customWeight / (customHeight * customHeight);
        } else {
            return (customWeight / (customHeight * customHeight)) * 703;
        }
    }

    // ── Category ──────────────────────────────────────────────
    public String bmiCategory() {
        double bmi = calculateBmi();
        if (bmi < 18.5) return UNDERWEIGHT;
        if (bmi < 25.0) return NORMAL_WEIGHT;
        if (bmi < 30.0) return OVERWEIGHT;
        return OBESE;
    }

    // ── Advice (based on category + gender) ───────────────────
    public String bmiCondition() {
        return switch (bmiCategory()) {
            case UNDERWEIGHT   -> gender.equals("F")
                ? "Eat well and consider medical tablets."
                : "Eat well and build strength.";
            case NORMAL_WEIGHT -> "Maintain your diet — you're doing great!";
            case OVERWEIGHT    -> gender.equals("F")
                ? "Hit the gym consistently."
                : "Hit the gym and walk daily.";
            case OBESE         -> gender.equals("F")
                ? "Consult a doctor and change your diet."
                : "Consult a doctor and increase exercise.";
            default -> throw new IllegalStateException("Unknown category: " + bmiCategory());
        };
    }

    // ── Motivation ────────────────────────────────────────────
    public String bmiStatus() {
        return switch (bmiCategory()) {
            case UNDERWEIGHT   -> "Eat, grow stronger!";
            case NORMAL_WEIGHT -> "Stay consistent!";
            case OVERWEIGHT    -> "Small steps matter!";
            case OBESE         -> "Start now — every day counts!";
            default -> throw new IllegalStateException("Unknown category: " + bmiCategory());
        };
    }

    // ── Display (single print method) ─────────────────────────
    public void displayResults() {
        System.out.println("\n════════════ BMI RESULT ════════════");
        System.out.println("Name:       " + name);
        System.out.println("Age:        " + age);
        System.out.println("Gender:     " + gender);
        System.out.println("Unit:       " + unit);
        System.out.println("Weight:     " + weight);
        System.out.println("Height:     " + height);
        System.out.printf ("BMI:        %.2f%n", calculateBmi());
        System.out.println("Category:   " + bmiCategory());
        System.out.println("Advice:     " + bmiCondition());
        System.out.println("Motivation: " + bmiStatus());
        System.out.println("════════════════════════════════════");
    }
}

// ── Separate class for program entry point ────────────────────
class BmiApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("═══════════════════════════════════");
        System.out.println("        BMI CALCULATOR");
        System.out.println("═══════════════════════════════════");

        // Name
        System.out.print("Enter your full name: ");
        String name = sc.nextLine();

        // Age — loop until valid
        int age;
        while (true) {
            try {
                System.out.print("Enter your age: ");
                age = Integer.parseInt(sc.nextLine());
                if (age >= 1 && age <= 120) break;
                System.out.println("Age must be between 1 and 120.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }

        // Weight
        double weight = 0;
        while (true) {
            try {
                System.out.print("Enter your weight: ");
                weight = Double.parseDouble(sc.nextLine());
                if (weight > 0) break;
                System.out.println("Weight must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Height
        double height = 0;
        while (true) {
            try {
                System.out.print("Enter your height: ");
                height = Double.parseDouble(sc.nextLine());
                if (height > 0) break;
                System.out.println("Height must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Unit
        System.out.print("Enter unit (metric/imperial): ");
        String unit = sc.nextLine();

        // Gender
        System.out.print("Enter gender (M/F): ");
        String gender = sc.nextLine();

        sc.close();

        // Create object — constructor validates everything
        try {
            Bmi user = new Bmi(name, age, weight, height, unit, gender);
            user.displayResults();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
