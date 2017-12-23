------------------------------------------------------------------------------------------------------------------------------------
											Assumptions and Algorithmic Analysis
------------------------------------------------------------------------------------------------------------------------------------
Assumptions:
1. Inputs are validated and always provided in two different files such as friends.txt and expenses.txt (Input Validation feature can be easily added in subsequent versions)	
2. The formats for files are as below
	
	a)friends.txt
		
		Alice
		Bob
		Claire
		David

		
	b)expenses.txt
	
		Claire paid $100.10 for phone bill.
		Bob paid $55.90 for petrol.
		David paid $170.80 for groceries.
		David paid $33.40 for breakfast.
		Bob paid $85.60 for lunch.
		Claire paid $103.45 for dinner.
		Claire paid $30.80 for snacks.
		Bob paid $70 for house-cleaning.
		David paid $63.50 for utilities.
		Alice paid $7 for house-cleaning.
		Alice paid $37 for house-cleaning.
		
3. JDK or JRE 1.8 and above is installed on target machine.
4. All necessary environment variables such as PATH,CLASS_PATH and JAVA_HOME have been setup for Java applications
	

Algorithm /Pseudocode

Step 1: Read friends.txt and store into MAP
Step 2: Read expense.txt and store and accumulate amount field for individual key into Map data structure from step1 .
Step 3:	calculate individual person expense
		sum of total expense/ no of people.
Step 4: To find the amount owe or outstanding amount, deduct individual person expense from accumulated amount in a map data structure for each individual key . (-ve amount denote outstanding)
Step 5:	load values from map into array and sort them.
Step 6: Begin with least amount in array allocate to max amount from array
Step 7:	Repeat stpe 6 until , the least amount element become zero.
Step 8: Proceed to next element in array. Repeat step 6 to 8 until there are all +ve numbers
		
------------------------------------------------------------------------------------------------------------------------------------
										Steps to execute command line application
------------------------------------------------------------------------------------------------------------------------------------

1. Extract App.zip file in your preferred directory
2. Copy 2 input files into inputs directory viz. friends.txt and expenses.txt
3. Type "java -jar Expense_Splitter.jar" in command prompt window. and hit the Enter.
4. It will display the following lines on same command window:

	Alice pays $xx.xx to David.
	Bob pays $xx.xx to Claire.

	
	And so on
	
	
	Please note that source code for the program is provided in source.zip file .

------------------------------------------------------------------------------------------------------------------------------------
										End
------------------------------------------------------------------------------------------------------------------------------------


