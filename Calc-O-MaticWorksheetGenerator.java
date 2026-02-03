import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.regex.*;

public class CalcWorksheetGenerator{
    static int choice1;
    static int choice2;
    public static void main(String []args) throws IOException {
        Scanner input=new Scanner(System.in);
        System.out.println("Topic!");
        System.out.println("1: polynomial\n2: radical\n3: rational\n4: exponetial\n5: Polynomial Integrals\nWould you like choice 1,2,3,or 4? ");
        choice1=input.nextInt();
        create();
        
    }
    public static void create() throws IOException {
        switch(choice1){
            case 1:
                poly();
            break;
            case 2:
                rad();
            break;
            case 3:
                rat();
            break;
            case 4:
                ex();
            break;
            case 5:
                polyIntegral();
                break;
        }
        
        
    }
    
    public static void poly(){
        Scanner scanner=new Scanner(System.in);
        
        
         Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PolynomialQuestions");

        // Header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Question");
        headerRow.createCell(1).setCellValue("Answer");

        // Input
        System.out.print("How many questions would you like to generate? ");
        int QNum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Choose difficulty (easy, medium, hard): ");
        String level=scanner.next();

        // Question Generation
        Random rand = new Random();
        for (int a = 0; a < QNum; a++) {
            int num = rand.nextInt(5) + 1;  // 1–5 terms
            String[] elements = new String[num];

            for (int i = 0; i < num; i++) {
                double coefficient;
                double exponent;
                if (level.equals("hard")) {
                    coefficient = Math.round((rand.nextDouble() * 99 + 1) * 10.0) / 10.0;
                    exponent = Math.round((rand.nextDouble() * 99 + 1) * 10.0) / 10.0;
                } else {
                    coefficient = rand.nextInt(9) + 1;
                    exponent = rand.nextInt(5) + 1;
                }
                elements[i] = coefficient + "x^" + exponent;
            }

            String function = String.join(" + ", elements);
            String derivative = PolyDerivative(elements);

            Row row = sheet.createRow(a + 1);
            row.createCell(0).setCellValue(function);
            row.createCell(1).setCellValue(derivative);
        }

        // Save the Excel file
        try {
            System.out.println("Saving to: " + System.getProperty("user.dir"));
            FileOutputStream fileOut = new FileOutputStream("PolynomialDerivativeQuestions.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void polyIntegral() {
        Scanner scanner = new Scanner(System.in);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PolynomialQuestions");

        // Header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Question");
        headerRow.createCell(1).setCellValue("Answer");

        // Input
        System.out.print("How many questions would you like to generate? ");
        int QNum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Choose difficulty (easy, medium, hard): ");
        String level = scanner.nextLine().toLowerCase();

        // Question Generation
        Random rand = new Random();
        for (int a = 0; a < QNum; a++) {
            int num = rand.nextInt(5) + 1;  // 1–5 terms
            String[] elements = new String[num];

            for (int i = 0; i < num; i++) {
                double coefficient;
                double exponent;
                if (level.equals("hard")) {
                    coefficient = Math.round((rand.nextDouble() * 99 + 1) * 10.0) / 10.0;
                    exponent = Math.round((rand.nextDouble() * 99 + 1) * 10.0) / 10.0;
                } else {
                    coefficient = rand.nextInt(9) + 1;
                    exponent = rand.nextInt(5) + 1;
                }
                elements[i] = coefficient + "x^" + exponent;
            }

            String function = String.join(" + ", elements);
            String derivative = PolyDerivative(elements);

            Row row = sheet.createRow(a + 1);
            row.createCell(1).setCellValue(function + " + c ");
            row.createCell(0).setCellValue("∫(" + derivative + " dx)");
        }

        // Save the Excel file
        try {
            System.out.println("Saving to: " + System.getProperty("user.dir"));
            FileOutputStream fileOut = new FileOutputStream("PolynomialDerivativeQuestions.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Excel file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static String PolyDerivative(String[] elements) {
        StringBuilder derivative = new StringBuilder();

        for (int a = 0; a < elements.length; a++) {
            String term = elements[a];
            int indexPower = term.indexOf("^");
            int indexX = term.indexOf("x");

            double coeff = Double.parseDouble(term.substring(0, indexX));
            double expo = Double.parseDouble(term.substring(indexPower + 1));

            double newCoeff = Math.round(coeff * expo * 10.0) / 10.0;
            double newExpo = Math.round((expo - 1) * 10.0) / 10.0;

            if (expo == 1) {
                derivative.append(String.format("%.1f", newCoeff));
            } else {
                derivative.append(String.format("%.1fx^%.1f", newCoeff, newExpo));
            }

            if (a < elements.length - 1) {
                derivative.append(" + ");
            }
        }

        return derivative.toString();

        
        
    }
    
    public static void rad() throws IOException {
        Scanner input=new Scanner(System.in);
        System.out.println("easy, medium, or hard");
        String level=input.next();
        
        Scanner scanner = new Scanner(System.in);

        // Ask user for number of questions
        System.out.print("How many questions would you like to generate? ");
        int numberOfQuestions = scanner.nextInt();

        // Create a new Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Math Questions");

        // Create the first row (headers for question and answer)
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Question");
        headerRow.createCell(1).setCellValue("Answer");

        // Generate questions and answers
        for (int i = 1; i <= numberOfQuestions; i++) {
            double coefficient = 0;
            double exponent = 0;
            double coefficient2 = 0;
            double otherNumber = 0;

            switch (level) {
                case "easy":
                    int coef = ((int) ((Math.random()) * 100) + 1);
                    coefficient = coef;
                    int exp = ((int) ((Math.random()) * 100) + 2);
                    exponent = exp;
                    coefficient2 = ((double) (Math.random()) * 100);
                    otherNumber = ((double) (Math.random()) * 100);
                    break;

                case "medium":
                    exponent = (((double) (Math.random()) * 100) + 2);
                    int num = (((int) (Math.random()) * 100) + 1);
                    double number = num;
                    coefficient = number / exponent;
                    coefficient2 = (((double) (Math.random()) * 100) + 1);
                    otherNumber = (((double) (Math.random()) * 100) + 1);
                    break;

                case "hard":
                    coefficient = ((double) (Math.random()) * 100);
                    exponent = ((double) (Math.random()) * 100 + 2);
                    coefficient2 = ((double) (Math.random()) * 100);
                    otherNumber = ((double) (Math.random()) * 100);
                    break;
            }

            // Format coefficients and numbers to 2 decimal places
            String Coefficient = String.valueOf((int) coefficient);
            String Exponent = String.valueOf((int) exponent);
            String Coefficient2 = String.valueOf(coefficient2);
            Coefficient2 = decimals(Coefficient2);
            String OtherNumber = String.valueOf(otherNumber);
            OtherNumber = decimals(OtherNumber);

            String eq = "";
            if (Coefficient.equals("0")) {
                eq = "√(" + Coefficient2 + "x - " + OtherNumber + ")";
            } else if (Exponent.equals("1")) {
                eq = "√(" + Coefficient + "x^" + Exponent + " + " + Coefficient2 + "x - " + OtherNumber + ")";
            } else if (Exponent.equals("0")) {
                eq = "√(" + Coefficient + " + " + Coefficient2 + "x - " + OtherNumber + ")";
            } else {
                eq = "√(" + Coefficient + "x^" + Exponent + " + " + Coefficient2 + "x - " + OtherNumber + ")";
            }

            // Get the derivative
            String answer = radical(eq);

            // Write question and answer to the next available row in the Excel sheet
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(eq);  // Question in column 1
            row.createCell(1).setCellValue(answer);  // Answer in column 2
        }

        // Write the output to an Excel file
        FileOutputStream fileOut = new FileOutputStream("MathQuestions.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        System.out.println("Excel file 'MathQuestions.xlsx' has been created with the questions and answers.");
    }

    static String decimals(String userinput) {
        String re = "\\b\\d+\\.\\d{2}\\b";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(userinput);

        if (matcher.matches()) {
            String formattedValue = String.format("%.15f", userinput).replaceAll("0*$", "").replaceAll("\\.$", "");
            return formattedValue;
        } else {
            try {
                double value = Double.parseDouble(userinput);
                String formattedValue = String.format("%.2f", value);
                formattedValue = formattedValue.replaceAll("0*$", "").replaceAll("\\.$", "");
                return formattedValue;
            } catch (NumberFormatException e) {
                return "Invalid input";
            }
        }
    }

    static String radical(String eq) {
        eq = eq.replace(" ", "");

        if (!eq.startsWith("√(") || !eq.endsWith(")")) {
            System.out.println("Invalid radical expression.");
            return "Invalid input";
        }

        String innerExpression = eq.substring(2, eq.length() - 1);  // Extract inside of √(...)
        //System.out.println("Inside radical: " + innerExpression);

        StringBuilder innerDerivative = sumRule(innerExpression);

        return ("Derivative: (" + innerDerivative + ") / (√(" + innerExpression + "))");
    }

    static StringBuilder sumRule(String eq) {
        StringBuilder derivative = new StringBuilder();
        String[] elements = eq.split("(?=[+-])");

        String[] derivatives = polynomial(elements);

        for (int i = 0; i < derivatives.length; i++) {
            String d = derivatives[i].trim();
            if (!d.equals("0")) {
                if (derivative.length() > 0 && !d.startsWith("-")) {
                    derivative.append("+");
                }
                derivative.append(d);
            }
        }

        return derivative;
    }

    static String[] polynomial(String[] elements) {
        String[] individualDerivatives = new String[elements.length];

        for (int a = 0; a < elements.length; a++) {
            String term = elements[a].trim();

            if (!term.contains("x")) {
                individualDerivatives[a] = "0";
                continue;
            }

            double coefficient = 1.0;
            int exponent = 1;

            int indexX = term.indexOf("x");

            try {
                // Handle coefficients
                String coeffStr = term.substring(0, indexX);
                if (!coeffStr.isEmpty() && !coeffStr.equals("+") && !coeffStr.equals("-")) {
                    coefficient = Double.parseDouble(coeffStr);
                } else if (coeffStr.equals("-")) {
                    coefficient = -1.0;
                } else if (coeffStr.equals("+")) {
                    coefficient = 1.0;
                }

                if (term.contains("^")) {
                    exponent = Integer.parseInt(term.substring(term.indexOf("^") + 1));
                }

                double newCoefficient = coefficient * exponent;
                int newExponent = exponent - 1;

                if (newExponent == 1) {
                    individualDerivatives[a] = String.format("%.2f", newCoefficient) + "x";
                } else if (newExponent == 0) {
                    individualDerivatives[a] = String.format("%.2f", newCoefficient);
                } else {
                    individualDerivatives[a] = String.format("%.2f", newCoefficient) + "x^" + newExponent;
                }
            } catch (Exception e) {
                individualDerivatives[a] = "0";
            }
        }

        return individualDerivatives;
    }


        
    
    
    public static void rat(){
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("RationalQuestions");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Question");
        headerRow.createCell(1).setCellValue("Answer");

        Scanner scanner = new Scanner(System.in);
        System.out.print("How many questions would you like to generate? ");
        int numQuestions = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Choose difficulty (easy, medium, hard): ");
        String difficulty = scanner.nextLine().toLowerCase();

        for (int i = 0; i < numQuestions; i++) {
            generateProblem(difficulty, sheet, i + 1); // i + 1 so it doesn't overwrite the header row
        }

        try (FileOutputStream fileOut = new FileOutputStream("RationalDerivativeQuestions.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing the Excel file: " + e.getMessage());
        }
    }


    static void generateProblem(String difficulty, Sheet sheet, int i) {
        Random rand = new Random();
        String numerator = generatePolynomial(difficulty, rand);
        String denominator = generatePolynomial(difficulty, rand);

        String numDeriv = derivative(numerator);
        String denomDeriv = derivative(denominator);

        String function = "(" + numerator + ")/(" + denominator + ")";
        String derivative = "((" + denominator + ")(" + numDeriv + ") - (" + numerator + ")(" + denomDeriv + ")) / ((" + denominator + ")^2)";

        //System.out.println("Function: " + function);
        //System.out.println("Derivative: " + derivative + "\n");
        
        // Write question and answer to the next available row in the Excel sheet
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(function);  // Question in column 1
            row.createCell(1).setCellValue(derivative);  // Answer in column 2
    }

    static String generatePolynomial(String difficulty, Random rand) {
        int maxPower = switch (difficulty) {
            case "easy" -> 2;
            case "medium" -> 3;
            case "hard" -> 4;
            default -> 2;
        };

        int numTerms = rand.nextInt(2) + 2; // 2 or 3 terms
        Map<Integer, Integer> terms = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < numTerms; i++) {
            int coeff = rand.nextInt(10) + 1;
            if (rand.nextBoolean()) coeff *= -1;

            int power = rand.nextInt(maxPower + 1);
            while (terms.containsKey(power)) {
                power = rand.nextInt(maxPower + 1); // avoid duplicate powers
            }
            terms.put(power, coeff);
        }

        return formatPolynomial(terms);
    }

    static String formatPolynomial(Map<Integer, Integer> terms) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : terms.entrySet()) {
            int power = entry.getKey();
            int coeff = entry.getValue();

            if (sb.length() > 0) {
                sb.append(coeff >= 0 ? "+" : "");
            }

            if (power == 0) {
                sb.append(coeff);
            } else if (power == 1) {
                sb.append(coeff == 1 ? "x" : (coeff == -1 ? "-x" : coeff + "x"));
            } else {
                sb.append(coeff == 1 ? "x^" + power : (coeff == -1 ? "-x^" + power : coeff + "x^" + power));
            }
        }
        return sb.toString();
    }

    static String derivative(String poly) {
        String[] terms = poly.split("(?=[+-])");
        List<String> results = new ArrayList<>();

        for (String term : terms) {
            term = term.trim();
            if (!term.contains("x")) continue;

            int coeff = 1;
            int power = 1;

            if (term.contains("x^")) {
                String[] parts = term.split("x\\^");
                coeff = parseCoeff(parts[0]);
                power = Integer.parseInt(parts[1]);
            } else if (term.contains("x")) {
                coeff = parseCoeff(term.replace("x", ""));
                power = 1;
            }

            int newCoeff = coeff * power;
            int newPower = power - 1;

            if (newPower == 0) {
                results.add(Integer.toString(newCoeff));
            } else if (newPower == 1) {
                results.add((newCoeff == 1 ? "" : (newCoeff == -1 ? "-" : newCoeff)) + "x");
            } else {
                results.add((newCoeff == 1 ? "" : (newCoeff == -1 ? "-" : newCoeff)) + "x^" + newPower);
            }
        }

        return results.isEmpty() ? "0" : String.join("+", results).replace("+-", "-");
    }

    static int parseCoeff(String part) {
        if (part == null || part.isEmpty()) return 1;
        if (part.equals("-")) return -1;
        return Integer.parseInt(part);
    }

    
    
    public static void ex(){
        Scanner input=new Scanner(System.in);
        System.out.println("Hi");
           Scanner scanner = new Scanner(System.in);
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("ExponentialQuestions"); //idk why, but on my computer, it just regenerates in the rational Derivative Excel. Check if it happens in yours too and troubleshoot this pls

        // Header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Question");
        headerRow.createCell(1).setCellValue("Answer");

        // Input
        System.out.print("How many questions would you like to generate? ");
        int QNum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Choose difficulty (easy, medium, hard): ");
        String level = scanner.nextLine().toLowerCase();

        // Question Generation
        Random rand = new Random();
        for (int a = 0; a < QNum; a++) {
            int num = rand.nextInt(5) + 1;  // 1–5 terms
            String[] elements = new String[num];

            for (int i = 0; i < num; i++) {
                double base;
                if (level.equals("hard")) {
                    base = rand.nextDouble() * 99 + 1;
                } else {
                    base = rand.nextInt(99) + 1;
                }
                elements[i] = String.format("%.1f^x", base);
            }

            // Function and Derivative
            String function = String.join(" + ", elements);
            String derivative = exponentialDerivative(elements).toString();

            // Write to Excel
            Row row = sheet.createRow(a + 1);
            row.createCell(0).setCellValue(function);
            row.createCell(1).setCellValue(derivative);
        }

        // Save File
        try (FileOutputStream fileOut = new FileOutputStream("ExponentialDerivativeQuestions.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            System.out.println("Error writing the Excel file: " + e.getMessage());
        }

        scanner.close();
    }

    static StringBuilder exponentialDerivative(String[] elements) {
        StringBuilder derivative = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            String expression = elements[i];
            int index = expression.indexOf("^");
            double base = Double.parseDouble(expression.substring(0, index));

            derivative.append(String.format("%.1f^x*ln(%.1f)", base, base));
            if (i < elements.length - 1) {
                derivative.append(" + ");
            }
        }
        return derivative;
    }

        
    }
    
    

