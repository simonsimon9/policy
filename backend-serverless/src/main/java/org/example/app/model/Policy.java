package org.example.app.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.UUID;

//https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/HowItWorks.NamingRulesDataTypes.html
@DynamoDBTable(tableName = "Policies")
public class Policy {

    private String name;
    private String date; //ISO time
    private Integer durationYears;
    private String companyName;
    private Integer initialDeposit;
    private PolicyType policyType;

    public Policy(){}

    public Policy(String name, String date, Integer durationYears, String companyName, Integer initialDeposit, PolicyType policyType) {
        this.name = name;
        this.date = date;
        this.durationYears = durationYears;
        this.companyName = companyName;
        this.initialDeposit = initialDeposit;
        this.policyType = policyType;
    }

    @DynamoDBHashKey(attributeName = "Name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "Date")
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @DynamoDBAttribute(attributeName = "DurationYears")
    public int getDurationYears() {
        return durationYears;
    }
    public void setDurationYears(int durationYears) {
        this.durationYears = durationYears;
    }

    @DynamoDBAttribute(attributeName = "CompanyName")
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @DynamoDBAttribute(attributeName = "InitialDeposit")
    public int getInitialDeposit() {
        return initialDeposit;
    }
    public void setInitialDeposit(int initialDeposit) {
        this.initialDeposit = initialDeposit;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "PolicyType")
    public PolicyType getPolicyType() {
        return policyType;
    }
    public void setPolicyType(PolicyType policyType) {
        this.policyType = policyType;
    }
}
