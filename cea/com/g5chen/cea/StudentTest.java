package com.g5chen.cea;
import java.util.*;
import java.io.*;
import java.sql.*;

public class StudentTest {
	public static final String PROGRAM_NAME = "StudentTest";

	public static void main (String [] args) throws IOException, SQLException{
		// Check program arguments (properties file)
		if (args.length == 0) {
    		System.out.println("Usage: "+PROGRAM_NAME+" <name of properties file>");
    		System.exit(1);
    	}
		
		// Read properties file
		Properties props = new Properties();
    	FileInputStream in = new FileInputStream(args[0]);
    	props.load(in);
    	in.close();
    	
    	// Pre-set connection
    	java.sql.Connection conn = DBConnection.getConnection(props);		
		
		// Create a scanner so we can read the command-line input
	    Scanner scanner = new Scanner(System.in);
	    
	    // App begins
	    System.out.println("=================================================");
    	System.out.println("Welcome to the CEA Course Recommender Application");
    	System.out.println("=================================================\n");
    	
	    // Prompt for the user's username
    	System.out.print("Please enter your username: ");
	    
    	// Save username to variable
	    String username = scanner.next();
	    Student p = null;
	    
	    // Check if user already exists, if exists, pull his info from DB
    	if (Table.exists(conn, "select * FROM students WHERE username='" + username + "'") == true) {
    		System.out.println("\nWelcome back, " + username + "\n");
    		//Student.printTable(conn); // TEST
    		System.out.println("Your current information: ");
    		System.out.println();
    		System.out.println("name\tage\tgender\tnative_country");
    		System.out.println("--------------------------------------");
    		Table.printAll(conn, "SELECT username, age, gender, native_country "
    				+ "FROM students WHERE username='" + username + "'");
    		
    	} else { // User doesn't exist, ask for general info and add to DB
    		
    		p = new Student();	
    		System.out.println("New user, please enter your general information: ");	    	
	    	System.out.print("Name: " + username + "\n");
	    	p.setName(username);
	    	
	    	// Check if age is an integer
	    	System.out.print("Age: ");
	    	while (!scanner.hasNextInt()){
	    		System.out.print("Invalid input, please enter an integer"
	    				+ " between 15 and 100: ");
	    		scanner.next();
    		}
	    	p.setAge(scanner.nextInt());
	    	
	    	// Check if gender is "m" or "f"
	    	System.out.print("Gender: ");
	    	String g = scanner.next();
	    	while (!g.equals("m") && !g.equals("f")){
	    		System.out.print("Invalid input, please enter either 'm' or 'f': ");
	    		g = scanner.next();
    		}
	    	p.setGender(g);
	    	System.out.print("Native country: ");
	    	scanner.nextLine();
	    	p.setNativeCountry(scanner.nextLine());
	    	
	    	p.addToDatabase(conn);
	    	
	    	// TEST
	    	//System.out.println("------------Table with new person added---------");
	    	//Student.printTable(conn);
    			
    	}
    	
    	// Info stored in memory
    	// For courses taken
    	int course_id = 0;
    	String dept_code;
    	int course_number;
    	Course c;
    	Course[] coursesTaken = new Course[50];
    	int i;
    	int num_courses = 0;
    	
    	// For topics and skills
    	int topic_id;
    	int skill_id;
    	String topic;
    	String skill;
    	Topic t;
    	Skill s;
    	int topic_rating = 0;
    	int skill_rating = 0;
    	Topic[] topics_list = new Topic[100];
    	Skill[] skills_list = new Skill[100];
    	int num_topics = 0;
    	int num_skills = 0;
    	
    	// Store user's topics and skills and their corresponding rating
    	Map<Integer, Integer> topics = new HashMap<Integer, Integer>();
    	Map<Integer, Integer> skills = new HashMap<Integer, Integer>();
    	
    	// Basic user functions, run as long as the user wants
    	while (true) {
    		
    	// User can edit his info, delete his info, collect courses, exit
		System.out.print("\nWhat would you like to do next?\n"
				+ "1-Edit user information\n"
				+ "2-Delete current user\n"
				+ "3-Collect courses, topics, skills (Recommendation)\n"
				+ "4-Collect other information (Data collection)\n"
				+ "5-Exit\n"
				+ "Please enter the corresponding number: ");
		
		// Check if user enters an integer
		while (!scanner.hasNextInt()) {
    		System.out.println("Invalid input");
    		System.out.print("\nPlease enter the corresponding number: ");
    		scanner.next();
		}
		int choice = scanner.nextInt();
		
		switch(choice) {
		
    	case 1 : // Edit user's information
	    	System.out.println();
	    	System.out.println("Your current information: ");
    		System.out.println();
    		System.out.println("name\tage\tgender\tnative_country");
    		System.out.println("--------------------------------------");
    		Table.printAll(conn, "SELECT username, age, gender, native_country "
    				+ "FROM students WHERE username='" + username + "'");
	    	System.out.println();
	    	p = new Student();
	    	p.setName(username);
	    	System.out.println("Please enter you new information:");
	    	
	    	// Check if age is an integer
	    	System.out.print("Age: ");
	    	while (!scanner.hasNextInt()){
	    		System.out.print("Invalid input, please enter an integer "
	    				+ "between 15 and 100: ");
	    		scanner.next();
    		}
	    	p.setAge(scanner.nextInt());
	    	
	    	// Check if gender is "m" or "f"
	    	System.out.print("Gender: ");
	    	String g = scanner.next();
	    	while (!g.equals("m") && !g.equals("f")){
	    		System.out.print("Invalid input, please enter either 'm' or 'f': ");
	    		g = scanner.next();
    		}
	    	p.setGender(g);
	    	System.out.print("Native country: ");
	    	scanner.nextLine();
	    	p.setNativeCountry(scanner.nextLine());
	    	
	    	p.updateInDatabase(conn);
	    	System.out.println("------------Updated user information---------");
	    	Table.print(conn, "students WHERE username='" + username + "'");
	    	System.out.println();
	    	//Student.printTable(conn); // TEST
	    	break; 
	    	
	    case 2 : // Delete current user
	    	System.out.println();
	    	//System.out.println("------------All existing entries---------");
	    	//Student.printTable(conn);
	    	System.out.println("Deleting current user");
	    	p = new Student();
	    	p.setName(username);
	    	p.deleteFromDatabase(conn);
	    	//System.out.println("------------Table after delete---------");
	    	//Student.printTable(conn);
	    	System.out.println(username + " has been deleted");
	    	System.out.println("===============================");
	    	System.out.println("Good bye!");
	    	System.out.println("===============================");
	    	scanner.close();
	    	DBConnection.closeConnection (conn);
	    	System.exit(0);
	    	
	    case 3 : // Collect information
	    	System.out.println("Please enter the courses you have taken already"
	    			+ "(type 'done' when finished): ");
	    	
	    	i = 0;
	    	System.out.print("Department Code: ");
    		dept_code = scanner.next().toUpperCase();
	    	while (!dept_code.equals("DONE")) {
	    		c = new Course();
	    		
		    	c.setDeptCode(dept_code);
		    	System.out.print("Course number: ");
		    	while (!scanner.hasNextInt()){
		    		System.out.print("Invalid input, please enter an integer: ");
		    		scanner.next();
	    		}
		    	course_number = scanner.nextInt();
		    	c.setCourseNumber(course_number);
		    	
		    	// Find course_id with dept_code and course_number
		    	course_id = Course.getCourseID(conn, dept_code, course_number);
		    	
		    	// Check if course is in DB
		    	if (course_id == 0) { // Not in DB, need to add
		    		course_id = Course.getNewID(conn); // Get new course_id
		    		c.setCourseID(course_id); 
		    		c.addCourseToDatabase(conn);
		    		System.out.println("------------Table with new course---------");
		    		Course.printTable(conn);
		    	} else { // Course already in DB, set course_id normally
		    		c.setCourseID(course_id);
		    	}
		    	
		    	coursesTaken[i] = c;
		    	i++;
		    	System.out.println("\nYou just added course:");
		    	Table.printAll(conn, "SELECT course_id, dept_code, course_number "
		    			+ "FROM courses WHERE course_id=" + course_id);
		    	//Course.printTable(conn);
		    	
		    	// Get next course
		    	System.out.print("Department Code: ");
	    		dept_code = scanner.next().toUpperCase();
	    	}
	    	
	    	num_courses = i;
	    	System.out.println("\nAll taken courses:");
	    	for (i = 0; i < num_courses; i++) {
	    		coursesTaken[i].printSingleEntry(conn); 
	    	}
	    	
	    	//Table.printAll(conn, "SELECT * FROM courses");
	    	
	    	System.out.println("===============================");
	    	System.out.println("dept\tid\ttopic");
	    	Table.printAll(conn, "SELECT DISTINCT dept_code, topic_id, topic "
	    			+ "FROM topics NATURAL JOIN course_topics "
	    			+ "NATURAL JOIN courses ORDER BY dept_code");
	    	System.out.println("Please select the topics you are interested"
	    			+ " in from the above list (dept_code, topic_id, topic)");
	    	System.out.println("Enter one topic_id per line (type '0' when finished): ");
	    	
	    	i = 0;
	    	while (true) {
	    		System.out.print("topic_id: ");
	    		// Check if user entered an integer
	    		while (!scanner.hasNextInt()){
		    		System.out.println("Invalid input, please enter an integer from the topic_id list: ");
		    		scanner.next();
	    		} 
	    		topic_id = scanner.nextInt();
	    		
	    		// Check if user entered '0' (finished entering topics)
	    		if (topic_id == 0) {
	    			break; // Exit while loop
	    		}
	    		
		    	topic = Topic.getTopic(conn, topic_id);
		    	
	    		// Check if user entered a valid topic_id
	    		if (topic == null) {
	    			System.out.println("Invalid input, please enter a valid topic_id: ");
	    		} else {
	    			t = new Topic();
	    	    	t.setTopicID(topic_id);
	    			t.setTopic(topic);
	    			topics_list[i] = t;
	    			i++;
	    			System.out.println("You just added: " + topic);
	    			System.out.print("What is your interest in this topic? (before): ");
	    			
	    			boolean valid = false;
	    			
	    			while (valid == false) {
	    				while (!scanner.hasNextInt() ){
				    		System.out.print("Invalid input, please enter an integer from 1-5: ");
				    		scanner.next();
			    		} 
			    		topic_rating = scanner.nextInt();
			    		if (topic_rating >= 1 && topic_rating <= 5) {
			    			valid = true;
			    		} else {
			    			System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		}
	    			}
	    			
	    			topics.put(topic_id, topic_rating);
	    		}
	    	}
	    	
	    	System.out.println("\nAll added topics and their rating:");
	    	num_topics = i;
	    	for (int curr_topic : topics.keySet()) {
	    		System.out.println(Topic.getTopic(conn, curr_topic) + ": " + topics.get(curr_topic));
	    	}
	    	
	    	System.out.println("===============================");
	    	System.out.println("dept\tid\tskill");
	    	Table.printAll(conn, "SELECT DISTINCT dept_code, skill_id, skill "
	    			+ "FROM skills NATURAL JOIN course_skills "
	    			+ "NATURAL JOIN courses ORDER BY dept_code");
	    	System.out.println("Please select the skills you have from the "
	    			+ "above list (dept_code, skill_id, skill)");
	    	System.out.println("Enter one skill_id per line (type '0' when finished): ");
	    	
	    	i = 0;
	    	while (true) {
	    		System.out.print("skill_id: ");
	    		// Check if user entered an integer
	    		while (!scanner.hasNextInt()){
		    		System.out.println("Invalid input, please enter an integer from the skill_id list: ");
		    		scanner.next();
	    		} 
	    		skill_id = scanner.nextInt();
	    		
	    		// Check if user entered '0' (finished entering topics)
	    		if (skill_id == 0) {
	    			break; // Exit while loop
	    		}
	    		
		    	skill = Skill.getSkill(conn, skill_id);
		    	
	    		// Check if user entered a valid skill_id
	    		if (skill == null) {
	    			System.out.println("Invalid input, please enter a valid skill_id: ");
	    		} else {
	    			s = new Skill();
	    	    	s.setSkillID(skill_id);
	    			s.setSkill(skill);
	    			skills_list[i] = s;
	    			i++;
	    			System.out.println("You just added: " + skill);
	    			System.out.print("What is your interest in this skill? (before): ");
	    			
	    			boolean valid = false;
	    			
	    			while (valid == false) {
	    				while (!scanner.hasNextInt() ){
				    		System.out.print("Invalid input, please enter an integer from 1-5: ");
				    		scanner.next();
			    		} 
	    				skill_rating = scanner.nextInt();
			    		if (skill_rating >= 1 && skill_rating <= 5) {
			    			valid = true;
			    		} else {
			    			System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		}
	    			}
	    			
	    			skills.put(skill_id, skill_rating);
	    		}
	    	}
	    	
	    	System.out.println("\nAll added skills and their rating:");
	    	num_skills = i;
	    	for (int curr_skill : skills.keySet()) {
	    		System.out.println(Skill.getSkill(conn, curr_skill) + ": " + skills.get(curr_skill));
	    	}
	    	
	    	System.out.println();
	    	System.out.println("------------List of Neighbours---------");
	    	Table.dropView(conn, "part3");
	    	Table.buildPart3(conn, username, topics, skills);
	    	Table.dropView(conn, "part4");
	    	Table.buildPart4(conn, username, topics, skills);
	    	Table.print(conn, "part4");
	    	
	    	System.out.println("------------Course Recommendation---------");
	    	
	    	// Recommendation scheme
	    	System.out.print("\nWhat type of recommendation would you like?\n"
					+ "1-Recommend courses with the best predicted grade\n"
					+ "2-Recommend courses which promote my interests\n"
					+ "3-Recommend courses which improve my skills\n"
					+ "4-Recommend courses which make me happy\n\n"
					+ "Please enter the corresponding number: ");
	    	
	    	
	    	// Check if user enters an integer
			while (!scanner.hasNextInt()) {
	    		System.out.println("Invalid input");
	    		System.out.print("\nPlease enter the corresponding number: ");
	    		scanner.next();
			}
			int choice_rec = scanner.nextInt();
			
			switch(choice_rec) {
			
	    	case 1 : // Best predicted grade
	    		Table.buildRec1(conn, username);
	    		break;
	    	
	    	case 2 : // Promote my interests
	    		Table.buildRec2(conn, username);
	    		break;
	    		
	    	case 3 : // Improve my skills
	    		Table.buildRec3(conn, username);
	    		break;
	    		
	    	case 4 : // Make me happy
	    		Table.buildRec4(conn, username);
	    		break;
	    		
	    	default :
	    		System.out.println("Invalid option");
			}
			
	    	
	    	break;
	    
	    case 4 : // Collect information
	    	System.out.println("\nPlease enter information about the courses you have taken already:");
	    	System.out.println("(Note: Courses are taken from 3, please do that first or no courses will show up)\n");
	    	Course curr_course;
	    	
	    	for (i = 0; i < num_courses; i++) {
	    		curr_course = coursesTaken[i]; // Keep track of current course
	    		
	    		System.out.println("Course:");
	    		curr_course.printSingleEntry(conn);
	    		System.out.println();
	    		
	    		System.out.println("------------General course info---------");
	    		CourseEditions ce = new CourseEditions();
	    		course_id = curr_course.getCourseID();
	    		ce.setCourseID(course_id);
	    		
	    		System.out.print("Semester (s-summer f-fall w-winter): ");
	    		String semester = scanner.next();
		    	while (!semester.equals("s") && !semester.equals("f") && !semester.equals("w")) {
		    		System.out.print("Invalid input, please enter either 's' or 'f' or 'w': ");
		    		semester = scanner.next();
	    		}
		    	ce.setSemester(semester);
		    	
		    	System.out.print("Year: ");
		    	while (!scanner.hasNextInt()){
		    		System.out.print("Invalid input, please enter an integer: ");
		    		scanner.next();
	    		}
		    	int year = scanner.nextInt();
		    	ce.setYear(year);
		    	
		    	System.out.print("Time of day (m-morning a-afternoon e-evening): ");
	    		String time_day = scanner.next();
		    	while (!time_day.equals("m") && !time_day.equals("a") && !time_day.equals("e")) {
		    		System.out.print("Invalid input, please enter either 'm' or 'a' or 'e': ");
		    		time_day = scanner.next();
	    		}
		    	ce.setTimeDay(time_day);
		    	
		    	// Find edition_id with info
		    	int edition_id = CourseEditions.getEditionID(conn, course_id, semester, year, time_day);
		    	
		    	// Check if course_edition is in DB
		    	if (edition_id == 0) { // Not in DB, need to add
		    		edition_id = CourseEditions.getNewID(conn); // Get new course_id
		    		ce.setEditionID(edition_id); 
		    		ce.addCourseToDatabase(conn);
		    		System.out.println("------------Table with new course---------");
		    		CourseEditions.printTable(conn);
		    	} else { // Course already in DB, set course_id normally
		    		ce.setEditionID(edition_id); 
		    	}
		    	
		    	System.out.println();
		    	System.out.println("e_id\tc_id\tsem\tyear\ttotal\ttime_day");
		    	ce.printSingleEntry(conn);
		    	System.out.println();
		    	System.out.println("------------Course experience---------");
		    	
		    	Enrollments e = new Enrollments();
		    	e.setEditionID(edition_id);
		    	e.setUsername(username);
		    	
		    	System.out.print("Letter grade (A+ to F): ");
		    	String letter_grade = scanner.next().toUpperCase();
		    	
		    	// Check if valid grade
		    	while(!Table.exists(conn, "SELECT letter_grade FROM letter_grades "
		    			+ "WHERE letter_grade='" + letter_grade + "'")) {
		    		System.out.print("Invalid input, please enter a valid letter grade (A+ to F): ");
		    		letter_grade = scanner.next().toUpperCase();
		    	}
		    	
		    	e.setLetterGrade(letter_grade);
		    	
		    	boolean valid = false;
		    	System.out.print("Course ranking: ");
		    	int course_ranking = 0;
    			while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		scanner.next();
		    		} 
    				course_ranking = scanner.nextInt();
		    		if (course_ranking >= 1 && course_ranking <= 5) {
		    			valid = true;
		    		} else {
		    			System.out.print("Invalid input, please enter an integer from 1-5: ");
		    		}
    			}
		    	e.setCourseRanking(course_ranking);
		    	
		    	System.out.print("Instructor ranking: ");
		    	valid = false;
		    	int instr_ranking = 0;
    			while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		scanner.next();
		    		} 
    				instr_ranking = scanner.nextInt();
		    		if (instr_ranking >= 1 && instr_ranking <= 5) {
		    			valid = true;
		    		} else {
		    			System.out.print("Invalid input, please enter an integer from 1-5: ");
		    		}
    			}
		    	e.setInstrRanking(instr_ranking);
		    	
		    	e.addToDatabase(conn);
		    	
		    	System.out.println("------------New enrollment---------");
		    	System.out.println("e_id\tname\tgrade\tc_rank\ti_rank");
		    	e.printSingleEntry(conn);
		    	System.out.println();
		    	
	    	}
	    	
	    	System.out.println("------------Topics and Skills---------");
	    	System.out.println("\n------------All taken courses---------");
	    	System.out.println("c_id\tdept\tnum\tname");
	    	for (i = 0; i < num_courses; i++) {
	    		coursesTaken[i].printSingleEntry(conn); 
	    	}
	    	
	    	System.out.println("\n------------All added topics---------");
	    	System.out.println("t_id\ttopic");
	    	for (i = 0; i < num_topics; i++) {
	    		System.out.println(topics_list[i]); 
	    	}
	    	
	    	System.out.println("\n------------All added skills---------");
	    	System.out.println("s_id\tskill");
	    	for (i = 0; i < num_skills; i++) {
	    		System.out.println(skills_list[i]); 
	    	}
	    	
	    	boolean topic_skill_finished = false;
	    	while (topic_skill_finished == false) {
	    	// Add topics and skills
	    	System.out.print("\nWhat would you like to do?\n"
					+ "1-Add new topic\n"
					+ "2-Add new skill\n"
					+ "3-Record topic interest dynamics\n"
					+ "4-Record skill improvements\n"
					+ "5-No changes\n"
					+ "Please enter the corresponding number: ");
	    	// Check if user enters an integer
			while (!scanner.hasNextInt()) {
	    		System.out.println("Invalid input");
	    		System.out.print("\nPlease enter the corresponding number: ");
	    		scanner.next();
			}
			int choice_topic_skill = scanner.nextInt();
			
			int edition_id;
			
			switch(choice_topic_skill) {
			
	    	case 1 :
	    		Topic new_topic = new Topic();
	    		System.out.print("Topic name: ");
	    		scanner.nextLine();
	    		String topic_name = scanner.nextLine();
	    		while (Topic.getTopicID(conn, topic_name) != 0) {
	    			System.out.print("Topic already exists, please enter a new topic name: ");
	    			topic_name = scanner.nextLine();
	    		}
	    		new_topic.setTopic(topic_name);
	    		new_topic.setTopicID(Topic.getNewID(conn));
	    		new_topic.addToDatabase(conn);
	    		
	    		System.out.println("------------Table with new topic---------");
	    		Table.print(conn, "topics");
	    		break;
	    	
	    	case 2 :
	    		Skill new_skill = new Skill();
	    		System.out.print("Skill name: ");
	    		scanner.nextLine();
	    		String skill_name = scanner.nextLine();
	    		while (Skill.getSkillID(conn, skill_name) != 0) {
	    			System.out.print("Skill already exists, please enter a new skill name: ");
	    			skill_name = scanner.nextLine();
	    		}
	    		new_skill.setSkill(skill_name);
	    		new_skill.setSkillID(Topic.getNewID(conn));
	    		new_skill.addToDatabase(conn);
	    		
	    		System.out.println("------------Table with new skill---------");
	    		Table.print(conn, "skills");
	    		break;
	    		
	    	case 3 :
	    		TopicInterests new_topic_interests = new TopicInterests();
	    		
	    		System.out.print("Course id: ");
	    		boolean valid = false;
		    	course_id = 0;
    			while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer: ");
			    		scanner.next();
		    		} 
    				course_id = scanner.nextInt();
    				// Check if valid 
		    		if (!Table.exists(conn, "SELECT course_id FROM courses "
			    			+ "WHERE course_id='" + course_id + "'")) {
		    			System.out.print("Invalid input, please enter a valid course_id: ");
		    		} else {
		    			valid = true;
		    		}
    			}
		    	new_topic_interests.setCourseID(course_id);
		    	
	    		System.out.print("Edition id: ");
	    		valid = false;
		    	edition_id = 0;
	    		while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer: ");
			    		scanner.next();
		    		} 
    				edition_id = scanner.nextInt();
    				// Check if valid 
		    		if (!Table.exists(conn, "SELECT edition_id FROM course_editions "
			    			+ "WHERE edition_id='" + edition_id + "'")) {
		    			System.out.print("Invalid input, please enter a valid edition_id: ");
		    		} else {
		    			valid = true;
		    		}
    			}
	    		new_topic_interests.setEditionID(edition_id);
	    		
	    		new_topic_interests.setUsername(username);
	    		
	    		System.out.print("Topic id: ");
	    		valid = false;
		    	topic_id = 0;
	    		while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer: ");
			    		scanner.next();
		    		} 
    				topic_id = scanner.nextInt();
    				// Check if valid 
		    		if (!Table.exists(conn, "SELECT topic_id FROM topics "
			    			+ "WHERE topic_id='" + topic_id + "'")) {
		    			System.out.print("Invalid input, please enter a valid topic_id: ");
		    		} else {
		    			valid = true;
		    		}
    			}
	    		new_topic_interests.setTopicID(topic_id);
	    		
	    		System.out.print("Interest before: ");
	    		valid = false;
	    		int interest_before = 0;
	    		while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		scanner.next();
		    		} 
    				interest_before = scanner.nextInt();
		    		if (interest_before >= 1 && interest_before <= 5) {
		    			valid = true;
		    		} else {
		    			System.out.print("Invalid input, please enter an integer from 1-5: ");
		    		}
    			}
	    		new_topic_interests.setInterestBefore(interest_before);
	    		
	    		System.out.print("Interest after: ");
	    		valid = false;
	    		int interest_after = 0;
    			while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		scanner.next();
		    		} 
    				interest_after = scanner.nextInt();
		    		if (interest_after >= 1 && interest_after <= 5) {
		    			valid = true;
		    		} else {
		    			System.out.print("Invalid input, please enter an integer from 1-5: ");
		    		}
    			}
	    		new_topic_interests.setInterestAfter(interest_after);
	    		
	    		new_topic_interests.addToDatabase(conn);
	    		
	    		System.out.println("------------Table with new topic interest---------");
	    		System.out.println("c_id\te_id\tname\tst_id\tbefore\tafter");
	    		Table.print(conn, "topic_interests WHERE username='" + username + "'");
	    		break;
	    		
	    	case 4 :
	    		SkillRankings new_skill_rankings = new SkillRankings();
	    		
	    		System.out.print("Course id: ");
	    		valid = false;
		    	course_id = 0;
    			while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer: ");
			    		scanner.next();
		    		} 
    				course_id = scanner.nextInt();
    				// Check if valid 
		    		if (!Table.exists(conn, "SELECT course_id FROM courses "
			    			+ "WHERE course_id='" + course_id + "'")) {
		    			System.out.print("Invalid input, please enter a valid course_id: ");
		    		} else {
		    			valid = true;
		    		}
    			}
    			new_skill_rankings.setCourseID(course_id);
		    	
	    		System.out.print("Edition id: ");
	    		valid = false;
		    	edition_id = 0;
	    		while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer: ");
			    		scanner.next();
		    		} 
    				edition_id = scanner.nextInt();
    				// Check if valid 
		    		if (!Table.exists(conn, "SELECT edition_id FROM course_editions "
			    			+ "WHERE edition_id='" + edition_id + "'")) {
		    			System.out.print("Invalid input, please enter a valid edition_id: ");
		    		} else {
		    			valid = true;
		    		}
    			}
	    		new_skill_rankings.setEditionID(edition_id);
	    		
	    		new_skill_rankings.setUsername(username);
	    		
	    		System.out.print("Skill id: ");
	    		valid = false;
		    	skill_id = 0;
	    		while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer: ");
			    		scanner.next();
		    		} 
    				skill_id = scanner.nextInt();
    				// Check if valid 
		    		if (!Table.exists(conn, "SELECT skill_id FROM skills "
			    			+ "WHERE skill_id='" + skill_id + "'")) {
		    			System.out.print("Invalid input, please enter a valid skill_id: ");
		    		} else {
		    			valid = true;
		    		}
    			}
	    		new_skill_rankings.setSkillID(skill_id);
	    		
	    		System.out.print("Interest before: ");
	    		valid = false;
	    		int rank_before = 0;
	    		while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		scanner.next();
		    		} 
    				rank_before = scanner.nextInt();
		    		if (rank_before >= 1 && rank_before <= 5) {
		    			valid = true;
		    		} else {
		    			System.out.print("Invalid input, please enter an integer from 1-5: ");
		    		}
    			}
	    		new_skill_rankings.setRankBefore(rank_before);
	    		
	    		System.out.print("Interest after: ");
	    		valid = false;
	    		int rank_after = 0;
    			while (valid == false) {
    				while (!scanner.hasNextInt() ){
			    		System.out.print("Invalid input, please enter an integer from 1-5: ");
			    		scanner.next();
		    		} 
    				rank_after = scanner.nextInt();
		    		if (rank_after >= 1 && rank_after <= 5) {
		    			valid = true;
		    		} else {
		    			System.out.print("Invalid input, please enter an integer from 1-5: ");
		    		}
    			}
    			new_skill_rankings.setRankAfter(rank_after);
	    		
    			new_skill_rankings.addToDatabase(conn);
	    		
	    		System.out.println("------------Table with new skill ranking---------");
	    		System.out.println("c_id\te_id\tname\tss_id\tbefore\tafter");
	    		Table.print(conn, "skill_rankings WHERE username='" + username + "'");
	    		break;
	    		
	    	case 5 :
	    		topic_skill_finished = true;
	    		break;
	    		
	    	default :
	    		System.out.println("Invalid option");
			}
	    	}
		    
	    	
	    	// TEST
	    	//ArrayList<String> allStudents = Student.getStudentNames(conn);
	    	//System.out.println(Arrays.toString(allStudents.toArray()));
	    	
	    	
	    	break;
	    	
	    case 5 : // Exit
	    	System.out.println("===============================");
	    	System.out.println("Good bye!");
	    	System.out.println("===============================");
	    	scanner.close();
	    	DBConnection.closeConnection (conn);
	    	System.exit(0);
	    	
	    default : 
	    	System.out.println("Invalid option");	
	    	
		}
		
		}
	}
}