#Author: bharat.sethi@espireinfo.com.au
#Keywords Summary : Generic Steps to validate the Test Framework
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@GenegicFeature
Feature: All generic steps available Katalon Framework
  This feature is used to test the genaric steps avaiable in the framnework
  
  
  @GenericScenario
  Scenario: Listing the steps to execute using the plain english
    Given I Navigate to https://www.frasersproperty.com.au/NSW/Ed-Square/Contact-Us/Enquire-Now
    When I Enter My First Name in the textbox First Name
    And I Enter My Last Name in the textbox last Name
    And I Enter test@mailinator.com in the textbox email
    And I Enter 0481819814 in the textbox Phone
    And I select Buying my first home from dropdown What best describes you?
    And I select $450k - $550k from dropdown What is your budget?
    And I select Apartments from dropdown Property Type you
    And I select 2 from dropdown Preferred number of bedrooms
    And I Enter My requirement as comment in the textarea comments
    And I select from a friend from dropdown How did you hear about us
    And I Click on the button Send
    Then Verify the SalesApp API  Scenario: Listing the steps to execute using the plain english
    Given I Navigate to https://www.frasersproperty.com.au/NSW/Ed-Square/Contact-Us/Enquire-Now
    When I Enter My First Name in the textbox First Name
    And I Enter My Last Name in the textbox last Name
    And I Enter test@mailinator.com in the textbox email
    And I Enter 0481819814 in the textbox Phone
    And I select Buying my first home from dropdown What best describes you?
    And I select $450k - $550k from dropdown What is your budget?
    And I select Apartments from dropdown Property Type you
    And I select 2 from dropdown Preferred number of bedrooms
    And I Enter My requirement as comment in the textarea comments
    And I select from a friend from dropdown How did you hear about us
    And I Click on the button Send
    Then Verify the SalesApp API
   
  @GenericScenario
  Scenario Outline: Listing the steps to execute using the plain english
    Given I Navigate to https://www.frasersproperty.com.au/NSW/Ed-Square/Contact-Us/Enquire-Now
    #Given I go to property Enquire us page for Discovery Point
    #When I Click on the button Submit
    When I Enter <fname> in the textbox First Name
    And I Enter <lname> in the textbox last Name
    And I Enter <email> in the textbox email
    And I Enter <phone> in the textbox Phone
    And I select <Q1> from dropdown What best describes you?
    And I select <Q2> from dropdown What is your budget?
    And I select <Q3> from dropdown Property Type you're interested in
    And I select <Q4> from dropdown Preferred number of bedrooms
    And I Enter <Comment> as comment in the textarea comments
    And I select <Q5> from dropdown How did you hear about us
    And I Click on the button Send
    Then Verify the SalesApp API

    Examples: 
      | fname      | lname     | email                         | phone    | Q1                        | Q2            | Q3         | Q4 | Q5                | Comment         |
      | First Name | Last name | testingKatalon@mailinator.com | 67876877 | Buying another investment | $551k - $650k | Townhouses |  3 | realestate.com.au | Testing Comment |
