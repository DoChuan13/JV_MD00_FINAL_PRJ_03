package app.config;

public final class MenuConst {
    /*==========================================================================Alert==========================================================================*/
    public static final String SELECT_OPTION = ColorConfig.OPTION_COLOR + "Please choose a option: " + ColorConfig.RESET;
    public static final String REQUIRE_NAME = ColorConfig.ACTION_COLOR + "(Requires Max Length 40)" + ColorConfig.RESET;
    public static final String REQUIRE_ACCOUNT = ColorConfig.ACTION_COLOR + "(Requires Non Space and Max Length 30)" + ColorConfig.RESET;
    public static final String REQUIRE_PASSWORD = ColorConfig.ACTION_COLOR + "(Requires at least Uppercase, Lowercase, Digit, Special Character and Max Length 10)" + ColorConfig.RESET;
    public static final String REQUIRE_EMAIL = ColorConfig.ACTION_COLOR + "(Requires input Valid Email)" + ColorConfig.RESET;
    public static final String REQUIRE_POST_CONTENT = ColorConfig.ACTION_COLOR + "(Max Length 200)" + ColorConfig.RESET;
    public static final String REQUIRE_STATUS = ColorConfig.ACTION_COLOR + "(Private/Friend/Public)" + ColorConfig.RESET;
    public static final String REQUIRE_FRIEND_STATUS = ColorConfig.ACTION_COLOR + "(Accept/Reject)" + ColorConfig.RESET;

    /*==========================================================================Success==========================================================================*/
    public static final String ACCEPT_SUCCESS = ColorConfig.OPTION_COLOR + "Accept Friend Successful. Please any key to continue... " + ColorConfig.RESET;
    public static final String REJECT_SUCCESS = ColorConfig.OPTION_COLOR + "Reject Friend Successful. Please any key to continue... " + ColorConfig.RESET;
    public static final String UPDATE_SUCCESS = ColorConfig.OPTION_COLOR + "Update Successful. Please any key to continue... " + ColorConfig.RESET;
    public static final String CREATE_USER_SUCCESS = ColorConfig.OPTION_COLOR + "Create User Successful. Please any key to continue... " + ColorConfig.RESET;
    public static final String CREATE_POST_SUCCESS = ColorConfig.OPTION_COLOR + "Create Post Successful. Please any key to continue... " + ColorConfig.RESET;
    public static final String UPDATE_POST_SUCCESS = ColorConfig.OPTION_COLOR + "Update Post Successful. Please any key to continue... " + ColorConfig.RESET;

    /*==========================================================================Error==========================================================================*/
    public static final String EMPTY_ALERT = ColorConfig.ERROR_COLOR + "Please do not leave empty fields. Press and key to try again... " + ColorConfig.RESET;
    public static final String INVALID_OPTION = ColorConfig.ERROR_COLOR + "Invalid choice. Please any key to try again... " + ColorConfig.RESET;
    public static final String INVALID_NAME = ColorConfig.ERROR_COLOR + "Name To Long ...Press any key to try again... " + ColorConfig.RESET;
    public static final String INVALID_ACCOUNT = ColorConfig.ERROR_COLOR + "Invalid Account...Press any key to try again... " + ColorConfig.RESET;
    public static final String INVALID_PASSWORD = ColorConfig.ERROR_COLOR + "Invalid Password...Press any key to try again... " + ColorConfig.RESET;
    public static final String INVALID_EMAIL = ColorConfig.ERROR_COLOR + "Invalid Email...Press any key to try again... " + ColorConfig.RESET;
    public static final String INVALID_CONTENT = ColorConfig.ERROR_COLOR + "Content To Long...Press any key to try again... " + ColorConfig.RESET;
    public static final String INVALID_STATUS = ColorConfig.ERROR_COLOR + "Invalid Status...Press any key to try again... " + ColorConfig.RESET;
    public static final String UNMATCHED_PASSWORD = ColorConfig.ERROR_COLOR + "Un-Match Password...Press any key to try again... " + ColorConfig.RESET;
    public static final String CURRENT_PASS_INCORRECT = ColorConfig.ERROR_COLOR + "Current Password Incorrect...Press any key to try again... " + ColorConfig.RESET;
    public static final String EXIST_USER_ACCOUNT = ColorConfig.ERROR_COLOR + "User Name is Exist...Press any key to try again... " + ColorConfig.RESET;
    public static final String EXIST_EMAIL = ColorConfig.ERROR_COLOR + "Email is Exist...Press any key to try again... " + ColorConfig.RESET;
    /*==========================================================================Role==========================================================================*/
    public static final String FRIEND_ACCEPTED = "Accepted!!!";
    public static final String FRIEND_PENDING = "Pending!!!";
    public static final String FRIEND_REJECT = "Rejected!!!";
    /*==========================================================================Role==========================================================================*/
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_PM = "PM";
    public static final String ROLE_USER = "USER";
    public static final String CHOOSE_OPTION = "Please choose a option: ";
    public static final String INPUT_POST_ID = "Input a Post Id to See Detail: ";
    public static final String INPUT_USER_ID = "Input a User Id to See Detail: ";
    public static final String INPUT_FR_ID_TO_SEE = "Input a Friend Id to See Detail: ";
    public static final String INPUT_FR_ID_TO_CONFIRM = "Input a Friend Id to Confirm: ";
    public static final String INPUT_FR_ID_TO_REMOVE = "Input a Friend Id to Remove: ";
    public static final String INPUT_FR_ID_TO_ADD = "Input a Friend Id to Sent Request: ";
    public static final String INPUT_FR_ID_TO_CHAT = "Input a Friend Id to Start Chat: ";

    public static final String INPUT_CHAT_ID = "Input a Chat Id to Continue Chat: ";
    public static final String INPUT_SEARCH_NAME = "Input Name to Find Friend: ";
    public static final String INPUT_NAME = "Input Name: ";
    public static final String INPUT_USERNAME = "Input UserName: ";
    public static final String INPUT_EMAIL = "Input E-mail: ";
    public static final String INPUT_SEX = "Input Sex (True/False/Null): ";
    public static final String INPUT_PASSWORD = "Input Password: ";
    public static final String INPUT_NEW_PASSWORD = "Input New Password: ";
    public static final String INPUT_RE_PASSWORD = "Input Re-Password: ";
    public static final String INPUT_POST = "Input Post Content: ";
    public static final String INPUT_COMMENT = "Input Comment: ";
    public static final String INPUT_CHAT = "Input Chat: ";
    public static final String CHANGE_FRIEND_STATUS = "Input Friend Action: ";
    public static final String CANT_DELETE_EMPTY_CHAT = ColorConfig.OPTION_COLOR + "You can't delete Empty Chat Box... " + ColorConfig.RESET;
    public static final String DIRECT_TO_DETAIL = ColorConfig.OPTION_COLOR + "Please any key to continue... " + ColorConfig.RESET;
    public static final String COMMENT_SUCCESS = ColorConfig.OPTION_COLOR + "Create New Comment Successfully!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String LOGIN_SUCCESS = ColorConfig.OPTION_COLOR + "Login Successfully!!!" + ColorConfig.RESET;
    public static final String SENT_CHAT_SUCCESS = ColorConfig.OPTION_COLOR + "Sent Chat Successfully!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String DELETE_SUCCESS = ColorConfig.OPTION_COLOR + "Delete Post Successfully!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String DELETE_CHAT_SUCCESS = ColorConfig.OPTION_COLOR + "Delete Chat Successfully!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String UNFRIEND_SUCCESS = ColorConfig.OPTION_COLOR + "Un-Friend Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String DELETE_REQUEST_SUCCESS = ColorConfig.OPTION_COLOR + "Delete sent request Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String REMOVE_FRIEND_REQUEST_SUCCESS = ColorConfig.OPTION_COLOR + "Remove Friend Request Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String REMOVE_FRIEND_STATUS_SUCCESS = ColorConfig.OPTION_COLOR + "Remove Friend Status Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String SEND_FRIEND_REQUEST_SUCCESS = ColorConfig.OPTION_COLOR + "Sent Friend Request Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String BLOCK_USER_SUCCESS = ColorConfig.OPTION_COLOR + "Blocked Account Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String UNBLOCK_USER_SUCCESS = ColorConfig.OPTION_COLOR + "Un-Blocked Account Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String DELETE_USER_SUCCESS = ColorConfig.OPTION_COLOR + "Deleted Account Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String CHANGE_ROLE_SUCCESS = ColorConfig.OPTION_COLOR + "Changed Account's Role Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String LIKE_POST_SUCCESS = ColorConfig.OPTION_COLOR + "Like Post Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String UNLIKE_POST_SUCCESS = ColorConfig.OPTION_COLOR + "Un-Like Post Successful!!! Please any key to continue... " + ColorConfig.RESET;
    public static final String PASSWORD_INCORRECT = ColorConfig.ERROR_COLOR + "Password Incorrect!!! Press any key to try again... " + ColorConfig.RESET;
    public static final String ACCOUNT_DONT_EXIST = ColorConfig.ERROR_COLOR + "Account don't Exist!!! Press any key to try again... " + ColorConfig.RESET;
    public static final String BLOCKED_USER = ColorConfig.ERROR_COLOR + "User is Blocked!!! Please contact with Administration... " + ColorConfig.RESET;
    public static final String RESULT_NOT_FOUND = ColorConfig.ERROR_COLOR + "Result not Found!!! Press any key to try again... " + ColorConfig.RESET;
    public static final String FRIEND_EMPTY = ColorConfig.ERROR_COLOR + "Friend List Empty!!! Press any key to try again... " + ColorConfig.RESET;
    public static final String POST_EMPTY = ColorConfig.ERROR_COLOR + "Post List Empty!!! Press any key to try again... " + ColorConfig.RESET;
    public static final String REQUIRE_NEXT_TIME = ColorConfig.ERROR_COLOR + "Can't change info this time. Please wait until: " + ColorConfig.RESET;
    public static final String CONFIRM_DELETE_POST = ColorConfig.ERROR_COLOR + "Are you sure DELETE this post? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_ACCEPT = ColorConfig.ERROR_COLOR + "Are you sure ACCEPT Friend with this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_REJECT = ColorConfig.ERROR_COLOR + "Are you sure REJECT Friend with this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_DELETE_REQUEST = ColorConfig.ERROR_COLOR + "Are you sure DELETE sent request with this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_UN_FRIEND = ColorConfig.ERROR_COLOR + "Are you sure UN-FRIEND with this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_BLOCK_USER = ColorConfig.ERROR_COLOR + "Are you sure BLOCK this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_UNBLOCK_USER = ColorConfig.ERROR_COLOR + "Are you sure UN-BLOCK this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_DELETE_USER = ColorConfig.ERROR_COLOR + "Are you sure DELETE of this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_LIKE_POST = ColorConfig.ERROR_COLOR + "Are you sure LIKE of this post? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_UNLIKE_POST = ColorConfig.ERROR_COLOR + "Are you sure Un-LIKE of this post? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_CHANGE_ROLE = ColorConfig.ERROR_COLOR + "Are you sure ROLE of this user? (Y/N)....: " + ColorConfig.RESET;
    public static final String CONFIRM_DELETE_CHAT = ColorConfig.ERROR_COLOR + "Are you sure DELETE this chat? (Y/N)....: " + ColorConfig.RESET;
    public static final String FRIEND_PENDING_ALERT = ColorConfig.ERROR_COLOR + "Already sent request!!! Are you want to cancel this request? (Y/N)....: " + ColorConfig.RESET;
    public static final String FRIEND_ALREADY_ALERT = ColorConfig.ERROR_COLOR + "This user is already Friend to you!!! Are you want to Un-Friend? (Y/N)....: " + ColorConfig.RESET;
    public static final String FRIEND_REQUEST_ALERT = ColorConfig.ERROR_COLOR + "You have a Friend Request from this user!!! Are you want to Confirm? (Y/N)....: " + ColorConfig.RESET;
    public static final String FRIEND_REQUEST_CONFIRM = ColorConfig.ERROR_COLOR + "Are you sure to send add friend request? (Y/N)....: " + ColorConfig.RESET;
    public static final String DENY_PERMISSION_CHAT = ColorConfig.ERROR_COLOR + "You can't continue chat with Account that is not User Role!!! Press any key to continue.... " + ColorConfig.RESET;
    /*==========================================================================Menu==========================================================================*/
    public static final String BREAK_LINE = ColorConfig.BORDER_COLOR + "✧----------------------------------------------------------------------------------------✧" + ColorConfig.RESET;
    public static final String BLANK_LINE = ColorConfig.BORDER_COLOR + "|                                                                                        |" + ColorConfig.RESET;
    //Detail Menu
    public static final String WELCOME_TITLE = ColorConfig.BORDER_COLOR + "|                            WELCOME FACEBOOK FAKE Mark Zuckerberg                       |" + ColorConfig.RESET;
    public static final String SYS_CTR_FUL_123 = ColorConfig.BORDER_COLOR + "|" + ColorConfig.END_COLOR + "  0. Exit                          " + ColorConfig.BACK_COLOR + "9. Back                       " + ColorConfig.END_COLOR + "10. Log Out            " + ColorConfig.BORDER_COLOR + "|" + ColorConfig.RESET;
    public static final String SYS_CTR_MID_12 = ColorConfig.BORDER_COLOR + "|" + ColorConfig.END_COLOR + "  0. Exit                          " + ColorConfig.BACK_COLOR + "9. Back                       " + ColorConfig.END_COLOR + "                       " + ColorConfig.BORDER_COLOR + "|" + ColorConfig.RESET;
    public static final String SYS_CTR_LES_1 = ColorConfig.BORDER_COLOR + "|" + ColorConfig.END_COLOR + "  0. Exit                          " + ColorConfig.BACK_COLOR + "                              " + ColorConfig.END_COLOR + "                       " + ColorConfig.BORDER_COLOR + "|" + ColorConfig.RESET;
    public static final String SYS_CTR_MID_13 = ColorConfig.BORDER_COLOR + "|" + ColorConfig.END_COLOR + "  0. Exit                          " + ColorConfig.BACK_COLOR + "                              " + ColorConfig.END_COLOR + "10. Log Out            " + ColorConfig.BORDER_COLOR + "|" + ColorConfig.RESET;
    public static final String WIDTH_5_COL_ACC_LIST = "%-7s" + "%-30s" + "%-30s" + "%-10s" + "%-10s";
    public static final String POST_PUBLIC = "Public";
    public static final String POST_FRIEND = "Friend";
    public static final String POST_PRIVATE = "Private";
    private static final String MAIN_TITLE = ColorConfig.BORDER_COLOR + "✧----------------------------------------------------------------------------------------✧" + ColorConfig.RESET;
    public static final String HEADER_WELCOME = MAIN_TITLE + "\n" + WELCOME_TITLE + "\n" + BREAK_LINE;
    private static final String CHAT_TITLE = ColorConfig.BORDER_COLOR + "|                                        CHAT PAGE                                       |" + ColorConfig.RESET;
    public static final String HEADER_CHAT_PAGE = MAIN_TITLE + "\n" + CHAT_TITLE + "\n" + BREAK_LINE;
    private static final String CHAT_LIST_TITLE = ColorConfig.BORDER_COLOR + "|                                        CHAT LIST                                       |" + ColorConfig.RESET;
    public static final String HEADER_CHAT_LIST = MAIN_TITLE + "\n" + CHAT_LIST_TITLE + "\n" + BREAK_LINE;
    private static final String CHAT_DETAIL_TITLE = ColorConfig.BORDER_COLOR + "|                                       CHAT DETAIL                                      |" + ColorConfig.RESET;
    public static final String HEADER_CHAT_DETAIL = MAIN_TITLE + "\n" + CHAT_DETAIL_TITLE + "\n" + BREAK_LINE;
    private static final String NEW_POSS_TITLE = ColorConfig.BORDER_COLOR + "|                                         NEW POST                                       |" + ColorConfig.RESET;
    public static final String HEADER_NEW_POST = BREAK_LINE + "\n" + NEW_POSS_TITLE + "\n" + BREAK_LINE;
    private static final String FOOTER_TITLE = ColorConfig.BORDER_COLOR + "|                                 Facebook Fake by DoChuan                               |" + ColorConfig.RESET;
    public static final String FOOTER = BREAK_LINE + "\n" + FOOTER_TITLE + "\n" + BREAK_LINE;
    private static final String POST_TITLE = ColorConfig.BORDER_COLOR + "|                                        POST PAGE                                       |" + ColorConfig.RESET;
    public static final String HEADER_POST = BREAK_LINE + "\n" + POST_TITLE + "\n" + BREAK_LINE;
    private static final String ALL_POST_TITLE = ColorConfig.BORDER_COLOR + "|                                         ALL POST                                       |" + ColorConfig.RESET;
    public static final String HEADER_ALL_POST = BREAK_LINE + "\n" + ALL_POST_TITLE + "\n" + BREAK_LINE;
    private static final String FRIEND_REQUEST_TITLE = ColorConfig.BORDER_COLOR + "|                                    FRIEND REQUEST                                      |" + ColorConfig.RESET;
    public static final String HEADER_FRIEND_REQUEST = BREAK_LINE + "\n" + FRIEND_REQUEST_TITLE + "\n" + BREAK_LINE;
    private static final String SENT_REQUEST_TITLE = ColorConfig.BORDER_COLOR + "|                                      SENT REQUEST                                      |" + ColorConfig.RESET;
    public static final String HEADER_SENT_REQUEST = BREAK_LINE + "\n" + SENT_REQUEST_TITLE + "\n" + BREAK_LINE;
    private static final String ADD_FRIEND_TITLE = ColorConfig.BORDER_COLOR + "|                                        ADD FRIEND                                      |" + ColorConfig.RESET;
    public static final String HEADER_ADD_FRIEND = BREAK_LINE + "\n" + ADD_FRIEND_TITLE + "\n" + BREAK_LINE;
    private static final String UPDATE_POST_DETAIL_TITLE = ColorConfig.BORDER_COLOR + "|                                       UPDATE POST                                      |" + ColorConfig.RESET;
    public static final String HEADER_UPDATE_POST_DETAIL = BREAK_LINE + "\n" + UPDATE_POST_DETAIL_TITLE + "\n" + BREAK_LINE;
    private static final String POST_DETAIL_TITLE = ColorConfig.BORDER_COLOR + "|                                       POST DETAIL                                      |" + ColorConfig.RESET;
    public static final String HEADER_POST_DETAIL = BREAK_LINE + "\n" + POST_DETAIL_TITLE + "\n" + BREAK_LINE;
    private static final String TITLE_FRIEND_LIST = ColorConfig.BORDER_COLOR + "|                                      FRIEND LIST                                       |" + ColorConfig.RESET;
    public static final String HEADER_FRIEND_LIST = MAIN_TITLE + "\n" + TITLE_FRIEND_LIST + "\n" + BREAK_LINE;
    private static final String TITLE_PM = ColorConfig.BORDER_COLOR + "|                                         PM PAGE                                        |" + ColorConfig.RESET;
    public static final String HEADER_PM = MAIN_TITLE + "\n" + TITLE_PM + "\n" + BREAK_LINE;
    private static final String TITLE_ADMIN = ColorConfig.BORDER_COLOR + "|                                       ADMIN PAGE                                       |" + ColorConfig.RESET;
    public static final String HEADER_ADMIN = MAIN_TITLE + "\n" + TITLE_ADMIN + "\n" + BREAK_LINE;
    private static final String TITLE_ADMIN_PROFILE = ColorConfig.BORDER_COLOR + "|                                     ADMIN PROFILE                                      |" + ColorConfig.RESET;
    public static final String HEADER_ADMIN_PROFILE = MAIN_TITLE + "\n" + TITLE_ADMIN_PROFILE + "\n" + BREAK_LINE;
    private static final String TITLE_PM_PROFILE = ColorConfig.BORDER_COLOR + "|                                       PM PROFILE                                       |" + ColorConfig.RESET;
    public static final String HEADER_PM_PROFILE = MAIN_TITLE + "\n" + TITLE_PM_PROFILE + "\n" + BREAK_LINE;
    private static final String TITLE_ADMIN_ACC_LIST = ColorConfig.BORDER_COLOR + "|                                    ACCOUNT LIST                                        |" + ColorConfig.RESET;
    public static final String HEADER_ADMIN_ACC_LIST = MAIN_TITLE + "\n" + TITLE_ADMIN_ACC_LIST + "\n" + BREAK_LINE;
    private static final String TITLE_USER_PROFILE = ColorConfig.BORDER_COLOR + "|                                       MY PROFILE                                       |" + ColorConfig.RESET;
    public static final String HEADER_USER_PROFILE = MAIN_TITLE + "\n" + TITLE_USER_PROFILE + "\n" + BREAK_LINE;
    private static final String TITLE_USER_DETAIL = ColorConfig.BORDER_COLOR + "|                                   DETAIL PROFILE                                       |" + ColorConfig.RESET;
    public static final String HEADER_USR_DETAIL_PROFILE = MAIN_TITLE + "\n" + TITLE_USER_DETAIL + "\n" + BREAK_LINE;
    private static final String TITLE_PROFILE_INFO = ColorConfig.BORDER_COLOR + "|                                      PROFILE INFO                                      |" + ColorConfig.RESET;
    public static final String HEADER_PROFILE_INFO = MAIN_TITLE + "\n" + TITLE_PROFILE_INFO + "\n" + BREAK_LINE;
    private static final String TITLE_PROFILE_PASS = ColorConfig.BORDER_COLOR + "|                                   CHANGE PASSWORD                                      |" + ColorConfig.RESET;
    public static final String HEADER_PROFILE_PASS = MAIN_TITLE + "\n" + TITLE_PROFILE_PASS + "\n" + BREAK_LINE;
    private static final String TITLE_REGISTER = ColorConfig.BORDER_COLOR + "|                                     REGISTER PAGE                                      |" + ColorConfig.RESET;
    public static final String HEADER_REGISTER = MAIN_TITLE + "\n" + TITLE_REGISTER + "\n" + BREAK_LINE;
    private static final String TITLE_HOME = ColorConfig.BORDER_COLOR + "|                                        HOME PAGE                                       |" + ColorConfig.RESET;
    public static final String HEADER_HOME = MAIN_TITLE + "\n" + TITLE_HOME + "\n" + BREAK_LINE;
    private static final String TITLE_LOGIN = ColorConfig.BORDER_COLOR + "|                                       LOGIN PAGE                                       |" + ColorConfig.RESET;
    public static final String HEADER_LOGIN = MAIN_TITLE + "\n" + TITLE_LOGIN + "\n" + BREAK_LINE;
    private static final String TITLE_FRIEND = ColorConfig.BORDER_COLOR + "|                                      FRIEND PAGE                                       |" + ColorConfig.RESET;
    public static final String HEADER_FRIEND = MAIN_TITLE + "\n" + TITLE_FRIEND + "\n" + BREAK_LINE;
    //"✧------------------------------- Facebook Fake by DoChuan -------------------------------✧"
    /*==========================================================================Menu Config==========================================================================*/
    private static final int MAX_WIDTH = 83;
    public static final String WIDTH_1_COL = "%-" + MAX_WIDTH + "s";//%-83s"
    private static final int MENU_WIDTH = 30;
    private static final int ONE_CONTENT = MAX_WIDTH - MENU_WIDTH;
    public static final String WIDTH_2_COL = "%-" + MENU_WIDTH + "s" + "%-" + ONE_CONTENT + "s";//%-30s%-53s"
    private static final int COL_1 = 30;
    private static final int COL_2 = MAX_WIDTH - MENU_WIDTH - COL_1;
    public static final String WIDTH_3_COL = "%-" + MENU_WIDTH + "s" + "%-" + COL_1 + "s" + "%-" + COL_2 + "s";
    private static final int COL_4_1 = 60;
    private static final int COL_4_2 = MAX_WIDTH - COL_4_1;
    public static final String POST_2_COL = "%-" + COL_4_1 + "s" + "%-" + COL_4_2 + "s";
}
