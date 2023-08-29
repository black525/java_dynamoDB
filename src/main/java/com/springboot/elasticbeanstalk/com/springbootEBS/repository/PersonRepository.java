package com.springboot.elasticbeanstalk.com.springbootEBS.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDeleteExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.springboot.elasticbeanstalk.com.springbootEBS.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Person addPerson(Person person){
        dynamoDBMapper.save(person);
        return person;
    }

    public Person findPersonByPersonId(String personId){
       return dynamoDBMapper.load(Person.class,personId);
    }


    public String deletePerson(Person person){
        dynamoDBMapper.delete(person);
        return "requested Person removed";
    }

    public String deletePersonByExpression(Person person){
        dynamoDBMapper.delete(person,buildPersonDeleteExpression(person));
        return "requested personid is removed";
    }

    private DynamoDBDeleteExpression buildPersonDeleteExpression(Person person){
        DynamoDBDeleteExpression dynamoDBDeleteExpression = new DynamoDBDeleteExpression();
        Map<String, ExpectedAttributeValue> expectecMap = new HashMap<>();
        expectecMap.put("personId",new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        dynamoDBDeleteExpression.setExpected(expectecMap);
        return dynamoDBDeleteExpression;
    }

    public String editPerson(Person person){
        dynamoDBMapper.save(person,buildPersonUpdatExpression(person));
        return "record updated";
    }

    private DynamoDBSaveExpression buildPersonUpdatExpression(Person person){
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String,ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        expectedAttributeValueMap.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));
        dynamoDBSaveExpression.setExpected(expectedAttributeValueMap);
        return dynamoDBSaveExpression;
    }

}
