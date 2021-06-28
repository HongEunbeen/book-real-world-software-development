package com.iteratrlearning.shu_book.chapter_05;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessRuleEngineTest {
    @Test
    public void shouldHaveNoRulesInitially(){
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        assertEquals(0, businessRuleEngine.count());
    }

    @Test
    public void shouldAddTwoActions(){
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addAction(facts -> {
            final String jobTitle = facts.getFacts("jobTitle");
            if("CEO".equals(jobTitle)){
                final String name = facts.getFacts("name");
                System.out.println(name);
            }
        });
        businessRuleEngine.addAction(mock(Action.class));

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    public void shouldExecuteOneAction(){
        //given
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);
        final Action mockAction = mock(Action.class);

        //when
        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        //then
        verify(mockAction).perform(mockFacts);
    }

    @Test
    public void shouldPerformAnActionWithFacts(){
        //given
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        //when
        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        //then
        verify(mockAction).perform(mockFacts);

    }
}
