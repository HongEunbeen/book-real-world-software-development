package com.iteratrlearning.shu_book.chapter_05;


import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InspectorTest{

    @Test
    public void inspectOneConditionEvaluatesTrue(){
        final Facts facts = new Facts();
        facts.addFacts("jobTitle","CEO");
        final ConditionalAction conditionalAction = new JobTitleCondition();
        final Inspector inspector = new Inspector(conditionalAction);

        final List<Report> reportList = inspector.inspect(facts);

        assertEquals(1, reportList.size());
    }

}