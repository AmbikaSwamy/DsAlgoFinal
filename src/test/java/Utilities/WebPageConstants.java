package Utilities;

import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;

public class WebPageConstants 
{		
	public final String APP_URL;				//	URL = https://dsportalapp.herokuapp.com
	public final String Browser;				//	BROWSER = chrome
	
	public final String HeadLessOption;			//  Sets the browser to be Headless or not . Pass a TRUE or FALSE value to this variable in the config.properties file.
	
	public final String Username;				//	UserName = dynamic_boffins
	public final String Password;				//	Password = dsalgo@2023
	
	public final String ExcelFile_Path;			//	ExcelFilePath = ./src/test/resources/DataInput/RegisterandLogin.xlsx
	public final String SignIn_XL_DataSheet;	//	LoginDataSheet = LoginDataSheet 
	
	public final String HomePage_URL;			//	HomePage = https://dsportalapp.herokuapp.com/home	
	public final String User_Regn_Page_URL;		//	User_Regn_Page = https://dsportalapp.herokuapp.com/register	
	public final String SignIn_Page_URL;		//	SignIn_Page = https://dsportalapp.herokuapp.com/login
	
	// Data Structures Intro Page Constants
	public final String DSIntro_Page_URL;		//  Data Structures Intro Page = https://dsportalapp.herokuapp.com/data-structures-introduction/
	public final String DSTimeComplexity_URL;	//	DSTimeComplexity = https://dsportalapp.herokuapp.com/data-structures-introduction/time-complexity/
	public final String DSPracticeQuest_URL;	//	DSPracticeQuest = https://dsportalapp.herokuapp.com/data-structures-introduction/practice
	
	// Array Page Constants
	public final String Array_Page_URL ;
	public final String Arrays_in_Python_URL ;
	public final String Arrays_Using_List_URL;
	public final String Arrays_Operations_URL;
	public final String Arrays_Applications_URL;
	public final String Arrays_Practice_URL ;
	public final String Arrays_Search_URL;
	public final String Array_Consecutive_Ones_URL;
	public final String Array_Even_Digits_URL;
	public final String Array_Squares_URL;	
	public String Array_Search_Python_CodeSheet;
	
	//linked list page Constants	
	public final String LL_Page_URL;			//	LL_Page_URL = https://dsportalapp.herokuapp.com/linked-list/
	public final String LL_Page_Intro_URL;	//	LL_Page_Intro = https://dsportalapp.herokuapp.com/linked-list/introduction/
	public final String LL_Create_Page_URL;	//	LL_Create_Page = https://dsportalapp.herokuapp.com/linked-list/creating-linked-list/
	public final String LL_Types_Page_URL;;	//	LL_Types_Page = https://dsportalapp.herokuapp.com/linked-list/types-of-linked-list/
	public final String LL_Impl_Page_URL;		//	LL_Impl_Page = https://dsportalapp.herokuapp.com/linked-list/implement-linked-list-in-python/
	public final String LL_Traverse_URL;		//	LL_Traverse_Page = https://dsportalapp.herokuapp.com/linked-list/traversal/
	public final String LL_Insert_URL;		//	LL_Insert_Page = https://dsportalapp.herokuapp.com/linked-list/insertion-in-linked-list/
	public final String LL_Del_URL;			//	LL_Delete_Page = https://dsportalapp.herokuapp.com/linked-list/deletion-in-linked-list/
	public final String LL_Pract_Qstn_URL;	//	LL_PractQn_Page = https://dsportalapp.herokuapp.com/linked-list/practice
	
	// Stack Page Constants	
	public final String Stack_Page_URL;		//	Stack_Page_URL = https://dsportalapp.herokuapp.com/stack/
	public final String Stack_Op_URL;			//	Stack_Op_Page = https://dsportalapp.herokuapp.com/stack/operations-in-stack/
	public final String Stack_Impl_URL;		//	Stack_Impl_Page = https://dsportalapp.herokuapp.com/stack/implementation/
	public final String Stack_App_URL;		//	Stack_Appl_Page = https://dsportalapp.herokuapp.com/stack/stack-applications/
	public final String Stack_Pract_Qstn_URL;	//	Stack_PractQuest_Page = https://dsportalapp.herokuapp.com/stack/practice
	
	// Queue Page Constants	
	public final String QPage_URL;				//	QPage_URL = https://dsportalapp.herokuapp.com/queue/
	public final String QOp_URL;				//	QOp_Page = https://dsportalapp.herokuapp.com/queue/QueueOp/
	public final String QList_URL;			//	QList_Page = https://dsportalapp.herokuapp.com/queue/implementation-lists/
	public final String QColln_URL;			//	QColln_Page = https://dsportalapp.herokuapp.com/queue/implementation-collections/
	public final String QArray_URL;			//	QArray_Page = https://dsportalapp.herokuapp.com/queue/Implementation-array/
	public final String QPracticeQues_URL;
	
	// Tree Page Constants
	public final String Tree_Page_URL;
	public final String Tree_Overview_Page_URL;
	public final String Tree_Terminology_Page_URL; 		// Tree_Terminology_Page = https://dsportalapp.herokuapp.com/tree/terminologies/
	public final String Tree_Types_Page_URL; 			// Tree_Types_Page = https://dsportalapp.herokuapp.com/tree/types-of-trees/
	public final String Tree_Traverse_Page_URL;			// Tree_Traverse_Page = https://dsportalapp.herokuapp.com/tree/tree-traversals/
	public final String Tree_Traverse_Illust_Page_URL;	// Tree_Traverse_Illust_Page = https://dsportalapp.herokuapp.com/tree/traversals-illustration/
	public final String Tree_Binary_Page_URL;			// Tree_Binary_Page = https://dsportalapp.herokuapp.com/tree/binary-trees/
	public final String Tree_Binary_Types_Page_URL;		// Tree_Binary_Types_Page = https://dsportalapp.herokuapp.com/tree/types-of-binary-trees/
	public final String Tree_Impl_Page_URL;				// Tree_Impl_Page = https://dsportalapp.herokuapp.com/tree/implementation-in-python/
	public final String Tree_BinTree_Traverse_Page_URL;	// Tree_BinTree_Traverse_Page = https://dsportalapp.herokuapp.com/tree/binary-tree-traversals/
	public final String Tree_BinTree_Impl_Page_URL;		// Tree_BinTree_Impl_Page = https://dsportalapp.herokuapp.com/tree/implementation-of-binary-trees/
	public final String Tree_BinTree_Appn_Page_URL; 	// Tree_BinTree_Appn_Page = https://dsportalapp.herokuapp.com/tree/applications-of-binary-trees/
	public final String Tree_BinSearch_Page_URL;		// Tree_BinSearch_Page = https://dsportalapp.herokuapp.com/tree/binary-search-trees/
	public final String Tree_BST_Impl_Page_URL;			// Tree_BST_Impl_Page = https://dsportalapp.herokuapp.com/tree/implementation-of-bst/
	public final String Tree_PractQuest_Page_URL;		// Tree_PractQuest_Page = https://dsportalapp.herokuapp.com/tree/practice
		
	//Graph Page Constants	
	public final String Graph_Page_URL;					//	Graph_Page_URL = https://dsportalapp.herokuapp.com/graph/
	public final String Graph_Intro_Page_URL;            // 	GraphIntro Page = https://dsportalapp.herokuapp.com/graph/graph/
	public final String Graph_Repr_Link_URL;		    //	Graph_Repr_Page = https://dsportalapp.herokuapp.com/graph/graph-representations/
	public final String Graph_PracticeQuest_URL;		// 	Graph_PracticeQuest = https://dsportalapp.herokuapp.com/graph/practice
	
	public final String Try_Editor_URL;					// 	Try_Editor_Page = https://dsportalapp.herokuapp.com/tryEditor
	
	public ResourceBundle myRB;
	
	public WebPageConstants()
	{
		// BaseClassUtils objBaseClass = new BaseClassUtils();
		
		myRB = ResourceBundle.getBundle("config");
		
		APP_URL = myRB.getString("URL");
		Browser = myRB.getString("BROWSER");
		
		HeadLessOption = myRB.getString("Headless");
		
		Username = myRB.getString("UserName");
		Password = myRB.getString("Password");
		
		SignIn_XL_DataSheet = myRB.getString("LoginDataSheet");
		ExcelFile_Path = myRB.getString("ExcelFilePath");
		
		HomePage_URL = myRB.getString("Home_Page");
		User_Regn_Page_URL = myRB.getString("User_Regn_Page");
		SignIn_Page_URL = myRB.getString("SignIn_Page");
		
		DSIntro_Page_URL = myRB.getString("DSIntro_Page");
		DSTimeComplexity_URL = myRB.getString("DSTimeComplexity_Page");
		DSPracticeQuest_URL= myRB.getString("DSPracticeQuest_Page");

		Array_Page_URL = myRB.getString("Array_Page");		
		Arrays_in_Python_URL = myRB.getString("Arrays_in_Python_Page");
		Arrays_Using_List_URL = myRB.getString("Arrays_Using_List_Page");
		Arrays_Operations_URL = myRB.getString("Arrays_Basic_Operations_in_Lists_Page");
		Arrays_Applications_URL = myRB.getString("Arrays_Applications_of_Array_Page");
		Arrays_Practice_URL = myRB.getString("Arrays_Practice_Question_Page");
		Arrays_Search_URL = myRB.getString("Arrays_Search_the_Array_Page");
		Array_Consecutive_Ones_URL = myRB.getString("Array_Max_Consecutive_Ones_Page");
		Array_Even_Digits_URL = myRB.getString("Array_Find_Numbers_with_Even_Number_of_Digits_Page");
		Array_Squares_URL = myRB.getString("Array_Squares_of_a_Sorted_Array_Page");
		
		Array_Search_Python_CodeSheet = myRB.getString("ArraySearchCodeSheet");
		
		Stack_Page_URL = myRB.getString("Stack_Page");
		Stack_Op_URL = myRB.getString("Stack_Op_Page");		
		Stack_Impl_URL = myRB.getString("Stack_Impl_Page");
		Stack_App_URL = myRB.getString("Stack_Appl_Page");
		Stack_Pract_Qstn_URL = myRB.getString("Stack_PractQuest_Page");
		
		LL_Page_URL = myRB.getString("LL_Page");
		LL_Page_Intro_URL = myRB.getString("LL_Page_Intro");
		LL_Create_Page_URL = myRB.getString("LL_Create_Page");
		LL_Types_Page_URL = myRB.getString("LL_Types_Page");
		LL_Impl_Page_URL = myRB.getString("LL_Impl_Page");
		LL_Traverse_URL = myRB.getString("LL_Traverse_Page");
		LL_Insert_URL = myRB.getString("LL_Insert_Page");
		LL_Del_URL = myRB.getString("LL_Delete_Page");
		LL_Pract_Qstn_URL = myRB.getString("LL_PractQn_Page");
		
		QPage_URL = myRB.getString("QPage_URL");
		QOp_URL = myRB.getString("QOp_Page");		
		QList_URL = myRB.getString("QList_Page");
		QColln_URL = myRB.getString("QColln_Page");
		QArray_URL = myRB.getString("QArray_Page");
		QPracticeQues_URL = myRB.getString("QPracticeQuestion_Page");
		
		Tree_Page_URL = myRB.getString("Tree_Page");
		Tree_Overview_Page_URL = myRB.getString("Tree_Overview_Page");
		Tree_Terminology_Page_URL =  myRB.getString("Tree_Terminology_Page");
		Tree_Types_Page_URL =  myRB.getString("Tree_Types_Page");
		Tree_Traverse_Page_URL =  myRB.getString("Tree_Traverse_Page");
		Tree_Traverse_Illust_Page_URL =  myRB.getString("Tree_Traverse_Illust_Page");
		Tree_Binary_Page_URL =  myRB.getString("Tree_Binary_Page");
		Tree_Binary_Types_Page_URL =  myRB.getString("Tree_Binary_Types_Page");
		Tree_Impl_Page_URL = myRB.getString("Tree_Impl_Page");
		Tree_BinTree_Traverse_Page_URL = myRB.getString("Tree_BinTree_Traverse_Page");
		Tree_BinTree_Impl_Page_URL = myRB.getString("Tree_BinTree_Impl_Page");
		Tree_BinTree_Appn_Page_URL = myRB.getString("Tree_BinTree_Appn_Page");
		Tree_BinSearch_Page_URL = myRB.getString("Tree_BinSearch_Page");
		Tree_BST_Impl_Page_URL = myRB.getString("Tree_BST_Impl_Page");
		Tree_PractQuest_Page_URL = myRB.getString("Tree_PractQuest_Page");
		
		Graph_Page_URL = myRB.getString("Graph_Page");
		Graph_Intro_Page_URL = myRB.getString("Graph_Intro_Page");
		Graph_Repr_Link_URL = myRB.getString("Graph_Repr_Page");
		Graph_PracticeQuest_URL = myRB.getString("Graph_PracticeQuest_Page");
						
		Try_Editor_URL = myRB.getString("Try_Editor_Page");
	}
}
